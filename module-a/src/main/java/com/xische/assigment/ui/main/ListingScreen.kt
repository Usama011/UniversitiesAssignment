package com.xische.assigment.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xische.assigment.databinding.ActivityListingBinding
import com.xische.assigment.domain.university.entity.UniversityEntity
import com.xische.module_b.DetailScreenActivity
import com.xische.module_b.UniversityDetailsDataClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ListingScreen : AppCompatActivity() {
    private lateinit var binding: ActivityListingBinding

    private val viewModel: MainActivityViewModel by viewModels()

    private var resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // A little confused as per assignment
            // why to recreate activity again.
            // as we can refresh the api call alone here.
            finish()
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        observe()
        fetchUniversities()
    }

    private fun observe() {
        observeState()
        observeUniversitiesData()
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }
            .launchIn(lifecycleScope)
    }

    private fun observeUniversitiesData() {
        viewModel.universities.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleUniversities(it) }
            .launchIn(lifecycleScope)
    }

    private fun fetchUniversities() {
        viewModel.fetchUniversities()
    }

    private fun handleState(state: MainActivityState) {
        when (state) {
            is MainActivityState.ShowToast -> Toast.makeText(
                this@ListingScreen,
                state.message,
                Toast.LENGTH_LONG
            ).show()

            is MainActivityState.IsLoading -> handleLoading(state.isLoading)
            is MainActivityState.Init -> Unit
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingBar.visibility = View.VISIBLE
        } else {
            binding.loadingBar.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        val a = UniversityAdapter(mutableListOf())
        a.setOnTapListener(object : UniversityAdapter.Listener {
            override fun onTap(universityEntity: UniversityEntity) {
                resultLauncher.launch(
                    DetailScreenActivity.createOrderAgainActivity(
                        this@ListingScreen,
                        UniversityDetailsDataClass(
                            universityEntity.name,
                            universityEntity.stateProvince,
                            universityEntity.alpha_two_code,
                            universityEntity.country,
                            universityEntity.web_pages,
                            universityEntity.domains
                        )
                    )
                )
            }
        })

        binding.universityRecyclerView.apply {
            adapter = a
            layoutManager = LinearLayoutManager(this@ListingScreen)
        }
    }

    private fun handleUniversities(universities: List<UniversityEntity>) {
        binding.universityRecyclerView.adapter?.let { adapter ->
            if (adapter is UniversityAdapter) {
                adapter.updateList(universities)
            }
        }
    }
}