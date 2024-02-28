package com.eymr.rickandmorty.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.eymr.rickandmorty.R
import com.eymr.rickandmorty.adapter.RickMortyAdapter
import com.eymr.rickandmorty.databinding.FragmentRickMortyBinding
import com.eymr.rickandmorty.domain.models.RickMortyCharacters
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RickMortyFragment : Fragment() {

    private lateinit var binding: FragmentRickMortyBinding
    private val viewModel : RickMortyViewModel by activityViewModels()
    //Lazy use delegator pattern
    private val rickMortyAdapter by lazy {
        RickMortyAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRickMortyBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        //binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvRickMorty.adapter = rickMortyAdapter
        }
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.uiState.collect{
                rickMortyAdapter.submitList(it)
                rickMortyAdapter.updateData()
            }
        }
    }

}
