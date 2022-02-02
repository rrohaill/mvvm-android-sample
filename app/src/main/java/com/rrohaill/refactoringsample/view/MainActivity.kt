package com.rrohaill.refactoringsample.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rrohaill.refactoringsample.data.Place
import com.rrohaill.refactoringsample.data.PlacesResult
import com.rrohaill.refactoringsample.databinding.ActivityMainBinding
import com.rrohaill.refactoringsample.domain.usecase.FetchPlacesUseCaseImpl
import com.rrohaill.refactoringsample.domain.usecase.GetPlacesUseCaseImpl
import com.rrohaill.refactoringsample.utils.RepositoryFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by lazy { getViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            button.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View?) {
        mainViewModel.fetchPlaces()
        observeResult()

    }

    private fun observeResult() {
        lifecycleScope.launch {
            mainViewModel.getResult().collectLatest {
                when (it) {
                    is PlacesResult.Success -> updateUi(it.data.place)
                    is PlacesResult.Error -> updateError(it.error.message)
                }
            }
        }
    }

    private fun updateUi(result: List<Place>) {
        binding.apply {
            if (result.isNotEmpty()) {
                placesRecyclerView.adapter = PlacesAdapter(result)
                message.visibility = View.GONE
            } else {
                message.text = "Try again!"
                message.visibility = View.VISIBLE
            }
        }
    }

    private fun updateError(msg: String) {
        binding.apply {
            message.apply {
                text = msg
                visibility = View.VISIBLE
            }
        }
    }

    private fun getViewModel(): MainViewModel {
        val placesRepository = RepositoryFactory.getPlacesRepository(this)
        val vmFactory = MainViewModelFactory(
            fetchPlacesUseCase = FetchPlacesUseCaseImpl(placesRepository = placesRepository),
            getPlacesUseCase = GetPlacesUseCaseImpl(placesRepository = placesRepository)
        )

        return ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)
    }

}