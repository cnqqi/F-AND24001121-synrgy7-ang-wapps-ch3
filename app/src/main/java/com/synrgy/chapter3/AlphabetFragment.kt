package com.synrgy.chapter3

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.synrgy.chapter3.databinding.FragmentAlphabetBinding

class AlphabetFragment : Fragment() {

    private var _binding: FragmentAlphabetBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlphabetBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recycleview
        layout()
    }

    private fun layout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
        recyclerView.adapter = AlphabetAdapter { countryId ->
            val bundle = bundleOf(DetailFragment.COUNTRY to countryId)
            findNavController().navigate(R.id.action_alphabetFragment_to_detailFragment, bundle)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val layoutButton = menu.findItem(R.id.fab_grid)
        switchIcon(layoutButton)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fab_grid -> {
                isLinearLayoutManager = !isLinearLayoutManager
                layout()
                switchIcon(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun switchIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(requireContext(), R.drawable.baseline_grid_on_24)
            else ContextCompat.getDrawable(requireContext(), R.drawable.baseline_grid_off_24)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
