package com.riyaldi.gamekuy.explore

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.core.data.Resource
import com.riyaldi.core.ui.GameAdapter
import com.riyaldi.core.ui.MarginItemDecoration
import com.riyaldi.gamekuy.R
import com.riyaldi.gamekuy.databinding.FragmentExploreBinding
import com.riyaldi.gamekuy.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private val exploreViewModel: ExploreViewModel by viewModels()

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding as FragmentExploreBinding

    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setHasOptionsMenu(true)
            showLoading(false)
            showSearchGame(true)

            gameAdapter = GameAdapter()

            gameAdapter.onItemClick = { selectedGame ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_GAME, selectedGame)
                lifecycleScope.launch {
                    exploreViewModel.insertGame(selectedGame)
                }
                startActivity(intent)
            }

            exploreViewModel.games.observe(viewLifecycleOwner, { games ->
                if (games != null) {
                    when(games) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            gameAdapter.setData(games.data)
                            showLoading(false)
                        }
                        is Resource.Error -> {
                            showNoGame(true)
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                            showLoading(false)
                        }
                    }
                }
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(binding.rvExplore){
                addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = "Cari game"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showSearchGame(false)
                showNoGame(false)
                showLoading(true)

                if (query != null) {
                    lifecycleScope.launch {
                        exploreViewModel.searchGames(query)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun showLoading(state: Boolean) {
        binding.pbExplore.isVisible = state
        binding.rvExplore.isInvisible = state
    }

    private fun showSearchGame(state: Boolean) {
        binding.ivSearchGame.isVisible = state
        binding.tvSearchGame.isVisible = state
        binding.rvExplore.isVisible = !state
    }

    private fun showNoGame(state: Boolean) {
        binding.ivNoGame.isVisible = state
        binding.tvNoGame.isVisible = state
        binding.rvExplore.isVisible = !state
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}