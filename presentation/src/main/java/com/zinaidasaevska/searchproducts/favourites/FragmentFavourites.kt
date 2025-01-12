package com.zinaidasaevska.searchproducts.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zinaidasaevska.domain.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentFavourites : Fragment() {

    private val viewModel: FavouritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    FavouritesList(
                        viewModel = viewModel,
                        onFavouriteIconClick = viewModel::removeFromFavourites,
                        onItemClick = ::navigateToProductDetails
                    )
                }
            }
        }
    }

    private fun navigateToProductDetails(product: Product) {
        findNavController().navigate(
            FragmentFavouritesDirections.actionFragmentFavouritesToFragmentProductDetails(
                product
            )
        )
    }
}