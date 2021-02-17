package com.riyaldi.gamekuy.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.core.data.Resource
import com.riyaldi.core.ui.GameAdapter
import com.riyaldi.core.ui.MarginItemDecoration
import com.riyaldi.gamekuy.databinding.FragmentHomeBinding
import com.riyaldi.gamekuy.detail.DetailActivity
import com.riyaldi.gamekuy.detail.DetailActivity.Companion.EXTRA_GAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val gameAdapter = GameAdapter()

            gameAdapter.onItemClick = { selectedGame ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(EXTRA_GAME, selectedGame)
                startActivity(intent)
            }

            homeViewModel.game.observe(viewLifecycleOwner, { game ->
                if (game != null) {
                    when(game) {
                        is Resource.Loading -> Log.d("HomeFragment", "loading")
                        is Resource.Success -> {
                            gameAdapter.setData(game.data)
                        }
                        is Resource.Error -> Toast.makeText(context, "error ${game.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(binding.rvHome) {
                addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}