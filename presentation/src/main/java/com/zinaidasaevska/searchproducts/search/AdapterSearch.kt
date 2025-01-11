package com.zinaidasaevska.searchproducts.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.searchproducts.R
import com.zinaidasaevska.searchproducts.databinding.ProductItemBinding

class AdapterSearch(private val listener: IProductFavouriteListener) : ListAdapter<Product, AdapterSearch.ProductViewHolder>(ProductDiffUtils()) {

    interface IProductFavouriteListener {
        fun addProductToFavourites(product: Product)
        fun removeProductFromFavourites(productId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(layout, listener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductViewHolder(
        private val binding: ProductItemBinding,
        private val listener: IProductFavouriteListener) : ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.ivProductImage.load(product.thumbnail) {
                crossfade(true)
                placeholder(R.drawable.placeholder_image)
            }

            binding.tvItemTitle.text = product.title
            binding.tvItemDescription.text = product.description
            binding.ivFavourite.setOnClickListener {
                if (product.isFavourite) {
                    listener.removeProductFromFavourites(product.id)
                } else {
                    listener.addProductToFavourites(product)
                }
            }
        }
    }

    private class ProductDiffUtils : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
}