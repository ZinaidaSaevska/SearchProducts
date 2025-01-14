package com.zinaidasaevska.searchproducts.productdetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.searchproducts.R
import com.zinaidasaevska.searchproducts.databinding.FragmentProductDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentProductDetails : Fragment() {

    private var binding: FragmentProductDetailsBinding? = null
    private val args: FragmentProductDetailsArgs by navArgs()
    private val viewModel: ProductDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductDetailsData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initProductDetailsData() {
        val product = args.product

        binding?.apply {
            ivProductImage.load(product.thumbnail) {
                crossfade(true)
                placeholder(R.drawable.placeholder_image)
            }

            tvProductTitle.text = product.title
            tvProductDescription.text = product.description

            if (product.isFavourite) {
                ivFavourite.setColorFilter(Color.RED)
            }

            setFavouritesOnClickListener(product, ivFavourite)
        }
    }

    private fun setFavouritesOnClickListener(product: Product, favouritesIcon: ImageView) {
        favouritesIcon.setOnClickListener {
            product.isFavourite = !product.isFavourite

            if (product.isFavourite) {
                viewModel.addToFavourites(product)
                favouritesIcon.setColorFilter(Color.RED)
            } else {
                viewModel.removeFromFavourites(product.id)
                favouritesIcon.clearColorFilter()
            }
        }
    }
}