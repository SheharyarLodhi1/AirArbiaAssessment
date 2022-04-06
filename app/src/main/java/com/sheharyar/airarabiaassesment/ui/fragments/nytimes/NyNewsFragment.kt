package com.sheharyar.airarabiaassesment.ui.fragments.nytimes

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.employer.jobbie.bases.BaseFragment
import com.sheharyar.airarabiaassesment.R
import com.sheharyar.airarabiaassesment.data.entities.NewsResultsModel
import com.sheharyar.airarabiaassesment.databinding.FragmentNyNewsBinding
import com.sheharyar.airarabiaassesment.ui.adapters.NewsAdapter
import com.sheharyar.airarabiaassesment.viewmodel.NewsViewModel
import com.sheharyar.pixabay.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ny_news.*


@AndroidEntryPoint
class NyNewsFragment : BaseFragment<FragmentNyNewsBinding>(FragmentNyNewsBinding::inflate),
    NewsAdapter.NewsItemListener {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var mNewsAdapter: NewsAdapter
    var mSelectRadioList: ArrayList<NewsResultsModel> = ArrayList()
    override fun getLayoutId(): Int {
        return R.layout.fragment_ny_news
    }

    override fun isBindedToActivityLifeCycle(): Boolean {
        return true
    }

    override fun onVisible(view: View, savedInstanceState: Bundle?) {
        val actionBar: ActionBar = (activity as AppCompatActivity?)!!.supportActionBar!!
        actionBar.hide()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText.toString())
                return false
            }

        })
        setupRecyclerView()
        setupObservers()
    }

    private fun setUpUI() {

    }

    override fun onInVisible() {

    }

    private fun setupObservers() {
        viewModel.newYorkTimeNews.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) mNewsAdapter.setItems(ArrayList(it.data))
                    mSelectRadioList.addAll(it.data!!)
                    //AppConstant.MediaMetaList.mediaMetaArrayList.add(it.data?.get(0)?.media?.get(0)?.media_metadata?.get(0)!!)
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progress_bar.visibility = View.VISIBLE

            }
        })
    }

    private fun setupRecyclerView() {
        mNewsAdapter = NewsAdapter(this)
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.adapter = mNewsAdapter
    }

    override fun onClickedNews(hitsList: NewsResultsModel, position: Int) {
        if (!hitsList.media.isNullOrEmpty()) {
            findNavController().navigate(R.id.action_nyNewsFragment_to_nyNewsDetailsFragment,
                bundleOf("position" to position))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater: MenuInflater = requireActivity().getMenuInflater()
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<NewsResultsModel> = ArrayList()

        for (item in mSelectRadioList) {
            if (item.section.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }

        mNewsAdapter.filterList(filteredList)
    }
}