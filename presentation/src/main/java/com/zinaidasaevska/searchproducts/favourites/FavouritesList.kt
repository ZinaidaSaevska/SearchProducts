package com.zinaidasaevska.searchproducts.favourites

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.searchproducts.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouritesList(
    viewModel: FavouritesViewModel = koinViewModel(),
    onFavouriteIconClick: (Int) -> Unit,
    onItemClick: (Product) -> Unit
) {

    val favouritesUiState = viewModel.favouritesUiState.collectAsState()

    val spanCount =
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            3
        } else {
            1
        }

    LazyVerticalGrid(
        columns = GridCells.Fixed(spanCount),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(dimensionResource(R.dimen.margin_small_16))
    ) {
        items(favouritesUiState.value.favouritesList) { item: Product ->
            FavouriteItem(item, onFavouriteIconClick, onItemClick)
        }
    }
}

@Composable
fun FavouriteItem(
    product: Product,
    onFavouriteIconClick: (Int) -> Unit,
    onItemClick: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.product_item_card_size))
            .padding(dimensionResource(R.dimen.margin_small_16))
            .clickable {
                onItemClick(product)
            }
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .weight(0.7f)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = product.thumbnail,
                contentDescription = null
            )

            IconButton(
                onClick = { onFavouriteIconClick(product.id) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(dimensionResource(R.dimen.margin_small_16)),
                content = {
                    Icon(
                        painterResource(R.drawable.ic_favourite),
                        tint = Color.Red,
                        contentDescription = null
                    )
                }
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f),
            text = product.title
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            text = product.description,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )
    }
}

@Composable
@Preview
fun FavouriteItemPreview() {
    FavouriteItem(Product(
        id = 1,
        title = "Pixel 6",
        description = stringResource(R.string.lorem_ipsum),
        thumbnail = "https://cdn.dummyjson.com/products/images/mobile-accessories/Apple%20iPhone%20Charger/thumbnail.png",
        isFavourite = true
    ), {}, {})
}