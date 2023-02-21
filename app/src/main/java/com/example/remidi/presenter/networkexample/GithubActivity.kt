package com.example.remidi.presenter.networkexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remidi.data.DataSearchByRepo
import com.example.remidi.data.DataUserSearchByName
import com.example.remidi.databinding.ActivityGithubBinding
import com.example.remidi.presenter.networkexample.adapter.GithubUserAdapter

class GithubActivity : AppCompatActivity() {
    lateinit var binding: ActivityGithubBinding
    lateinit var adapterGithub: GithubUserAdapter

    private val viewModel by viewModels<GithubViewModel> { GithubViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservabel()
        intAdapter()
        initAction()
    }

    private fun initObservabel() {
        viewModel.getUserByNameData().observe(this) {
            onSuccessSearchUser(it)
        }
        viewModel.getSearchByRepo().observe(this) {
            onSuccessSearchRepo(it)
        }
        viewModel.isEmptyCase.observe(this) { isEmptyVisible ->
            runOnUiThread {
                viewModel.isLoadingCase.postValue(false)
                if (isEmptyVisible) {
                    binding.tvEmptyCase.visibility = View.VISIBLE
                } else {
                    binding.tvEmptyCase.visibility = View.GONE
                }
            }
        }
        viewModel.isFailureCaseMessage.observe(this) {
            onFailedSearch(it.toString())
        }
    }

    private fun initAction() {
        binding.etSearch.doOnTextChanged { text, start, before, count ->
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.onLoadData(text.toString())
            }, 1500)
        }
    }

    private fun intAdapter() {
        adapterGithub = GithubUserAdapter()
        adapterGithub.setDataUser(arrayListOf())
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUserGithub.apply {
            layoutManager = linearLayout
            adapter = adapterGithub
        }
    }

    private fun onSuccessSearchUser(listData: DataUserSearchByName) {
        listData.items?.let { listItems -> adapterGithub.setDataUser(listItems) }
        adapterGithub.notifyDataSetChanged()
        viewModel.isLoadingCase.postValue(false)
    }

    private fun onSuccessSearchRepo(listData: DataSearchByRepo) {
        listData.license?.let { listLicence -> adapterGithub.setDataRepo(listLicence) }
        adapterGithub.notifyDataSetChanged()
        viewModel.isLoadingCase.postValue(false)
    }

    private fun onFailedSearch(messageError: String) {
        Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show()
        viewModel.isLoadingCase.postValue(false)
    }
}