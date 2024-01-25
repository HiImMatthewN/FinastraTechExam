package com.nantes.matthew.finastratechexam.photos.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.factor.bouncy.util.OnOverPullListener
import com.nantes.matthew.finastratechexam.R
import com.nantes.matthew.finastratechexam.core.util.isPortrait
import com.nantes.matthew.finastratechexam.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binder: ActivityMainBinding

    private val viewModelMain by viewModels<MainViewModel>()

    private val spanCount by lazy {
        if (isPortrait()) 2 else 3
    }
    private var distance = 0f
    companion object{
        const val PULL_TO_REFRESH_THRESHOLD = 0.2f
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        val adapterPhotoFeed = PhotoFeedAdapter(this)
        adapterPhotoFeed.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binder.rvPhotoFeed.apply {
            adapter = adapterPhotoFeed

            layoutManager =
                StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
            onOverPullListener = object :OnOverPullListener{
                override fun onOverPulledBottom(deltaDistance: Float) {

                }

                override fun onOverPulledTop(deltaDistance: Float) {
                    distance += deltaDistance
                    binder.tvReleaseToRefresh.isVisible = distance >= PULL_TO_REFRESH_THRESHOLD
                }

                override fun onRelease() {
                    if (distance >= PULL_TO_REFRESH_THRESHOLD){
                        viewModelMain.getData()
                        binder.tvReleaseToRefresh.isVisible = false
                    }

                    distance = 0f
                }
            }
            addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE)
                        recyclerView.invalidateItemDecorations()
                }
            })

        }


        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelMain.uiState.collect { state ->
                    adapterPhotoFeed.submitList(state.photos)
                }


            }

        }


    }


}