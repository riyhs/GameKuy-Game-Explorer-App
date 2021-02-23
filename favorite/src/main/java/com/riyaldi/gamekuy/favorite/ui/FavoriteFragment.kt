package com.riyaldi.gamekuy.favorite.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.core.ui.GameAdapter
import com.riyaldi.core.ui.MarginItemDecoration
import com.riyaldi.gamekuy.detail.DetailActivity
import com.riyaldi.gamekuy.di.FavoriteModuleDependencies
import com.riyaldi.gamekuy.favorite.DaggerFavoriteComponent
import com.riyaldi.gamekuy.favorite.ViewModelFactory
import com.riyaldi.gamekuy.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
                .context(context)
                .appDependencies(
                        EntryPointAccessors.fromApplication(
                                context.applicationContext,
                                FavoriteModuleDependencies::class.java
                        )
                )
                .build()
                .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val favoriteGameAdapter = GameAdapter()

            favoriteGameAdapter.onItemClick = { selectedGame ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_GAME, selectedGame)
                startActivity(intent)
            }

            favoriteViewModel.favoriteGames.observe(viewLifecycleOwner, { favoriteGames ->
                if (favoriteGames.isEmpty()) {
                    binding.ivNoFavGames.isVisible = true
                    binding.tvNoFavGames.isVisible = true
                    favoriteGameAdapter.setData(favoriteGames)
                } else {
                    binding.ivNoFavGames.isVisible = false
                    binding.tvNoFavGames.isVisible = false
                    favoriteGameAdapter.setData(favoriteGames)
                }
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(binding.rvFavorite) {
                addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteGameAdapter
            }
        }

    }
}