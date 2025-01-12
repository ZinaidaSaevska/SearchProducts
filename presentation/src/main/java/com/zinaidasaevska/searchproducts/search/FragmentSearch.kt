package com.zinaidasaevska.searchproducts.search

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.zinaidasaevska.domain.model.Product
import com.zinaidasaevska.searchproducts.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val SEARCH_TEXT = "search_text"

class FragmentSearch : Fragment(), AdapterSearch.IProductFavouriteListener {

    private var binding: FragmentSearchBinding? = null

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var searchAdapter: AdapterSearch

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_TEXT, binding?.searchInput?.editText?.text.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInputValue(savedInstanceState)
        setupRecyclerView()
        setInputTextListener()
        observeData()
    }

    private fun setupInputValue(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            binding?.searchInput?.editText?.setText(savedInstanceState.getString(SEARCH_TEXT))
        }
    }

    private fun setupRecyclerView() {
        val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            3
        } else {
            1
        }

        binding?.rvProducts?.apply {
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            searchAdapter = AdapterSearch(this@FragmentSearch)
            adapter = searchAdapter
        }
    }

    private fun setInputTextListener() {
        binding?.searchInput?.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.searchProducts(s.toString())
            }
        })
    }

    private fun observeData() {
        viewModel.products.observe(viewLifecycleOwner) { products ->
            searchAdapter.submitList(products)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun addProductToFavourites(product: Product) {
        viewModel.addProductToFavourites(product)
    }

    override fun removeProductFromFavourites(productId: Int) {
        viewModel.removeFromFavourites(productId)
    }
}