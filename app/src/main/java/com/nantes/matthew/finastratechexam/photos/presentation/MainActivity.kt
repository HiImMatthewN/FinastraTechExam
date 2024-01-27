package com.nantes.matthew.finastratechexam.photos.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.factor.bouncy.util.OnOverPullListener
import com.nantes.matthew.finastratechexam.core.util.isPortrait
import com.nantes.matthew.finastratechexam.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binder: ActivityMainBinding

    private val viewModelMain by viewModels<MainViewModel>()
    private val adapterPhotoFeed by lazy {
        PhotoFeedAdapter(this)
    }
    private val spanCount by lazy {
        if (isPortrait()) 2 else 3
    }
    private var distance = 0f

    companion object {
        const val PULL_TO_REFRESH_THRESHOLD = 0.2f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        initViews()
        initOnClicks()
        subscribeObservers()


    }
    private fun initViews(){
        binder.rvPhotoFeed.apply {
            adapter = adapterPhotoFeed

            layoutManager =
                StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
            onOverPullListener = object : OnOverPullListener {
                override fun onOverPulledBottom(deltaDistance: Float) {

                }

                override fun onOverPulledTop(deltaDistance: Float) {
                    distance += deltaDistance
                    binder.tvReleaseToRefresh.isVisible = distance >= PULL_TO_REFRESH_THRESHOLD
                }

                override fun onRelease() {
                    if (distance >= PULL_TO_REFRESH_THRESHOLD) {
                        viewModelMain.setIntent(PhotoFeedIntent.FetchPhotos)
                        binder.tvReleaseToRefresh.isVisible = false
                    }

                    distance = 0f
                }
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    viewModelMain.setIntent(
                        PhotoFeedIntent.UserScrolled(
                            isScrolling = newState == RecyclerView.SCROLL_STATE_DRAGGING
                        )
                    )

                }
            }

            )

        }

    }
    private fun initOnClicks(){
        binder.btnPreviousPage.setOnClickListener {
            viewModelMain.setIntent(PhotoFeedIntent.PreviousPage)
        }
        binder.btnNextPage.setOnClickListener {
            viewModelMain.setIntent(PhotoFeedIntent.NextPage)
        }
    }
    private fun subscribeObservers() {

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelMain.uiState.collect { state ->
                    adapterPhotoFeed.submitList(state.photos)
                    binder.btnNextPage.isVisible = state.showNextButton
                    binder.btnPreviousPage.isVisible = state.showPreviousButton
                    binder.progressBar.isVisible = state.showProgressBar


                    if (!state.error.isNullOrEmpty()){
                        binder.tvError.isVisible = true
                        binder.tvError.text = state.error
                    }else{
                        binder.tvError.isVisible = false
                        binder.tvError.text = ""
                    }
                }


            }

        }
    }




}