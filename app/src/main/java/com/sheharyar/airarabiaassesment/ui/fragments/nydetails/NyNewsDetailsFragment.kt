package com.sheharyar.airarabiaassesment.ui.fragments.nydetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.employer.jobbie.bases.BaseFragment
import com.sheharyar.airarabiaassesment.R
import com.sheharyar.airarabiaassesment.databinding.FragmentNyDetailsBinding
import com.sheharyar.airarabiaassesment.viewmodel.NyNewsDetailsViewModel
import com.sheharyar.pixabay.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ny_details.*

@AndroidEntryPoint
class NyNewsDetailsFragment : BaseFragment<FragmentNyDetailsBinding>(FragmentNyDetailsBinding::inflate) {
    private val viewModel: NyNewsDetailsViewModel by viewModels()
    private var position = 0
    override fun getLayoutId(): Int {
        return R.layout.fragment_ny_details
    }

    override fun isBindedToActivityLifeCycle(): Boolean {
        return true
    }

    override fun onVisible(view: View, savedInstanceState: Bundle?) {
        position = arguments?.getInt("position")!!
        setupObservers()
        setUpUI()
    }

    private fun setUpUI() {

    }

    override fun onInVisible() {

    }
    private fun setupObservers() {
        viewModel.newsDetails.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty() && !it.data.get(position).media.isNullOrEmpty()) {
                        Glide.with(requireContext())
                            .load(it.data.get(position).media?.get(0)?.media_metadata?.get(2)?.url)
                            /*.transform(CircleCrop())*/
                            .into(ivContentImage)
                        tvTitle.text = it.data.get(position).title!!
                        tvDescription.text = it.data.get(position).abstract
                        tvPublished.text = it.data.get(position).published_date
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progress_bar.visibility = View.VISIBLE

            }
        })
    }
}