package com.synrgy.chapter3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.chapter3.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object {
        const val COUNTRY = "country"
        const val SEARCH = "https://www.google.com/search?q="
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countryId = arguments?.getString(COUNTRY)

        countryId?.let { id ->
            val recyclerView = binding.recycleview
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = DetailAdapter(id, requireContext())

            recyclerView.addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
            activity?.title = "Negara yang berawalan $id"
            setHasOptionsMenu(true)
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
