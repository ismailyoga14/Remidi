package com.example.remidi.presenter.networkexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.remidi.data.DataUserSearchItem
import com.example.remidi.data.DataUserSearchLicense
import com.example.remidi.databinding.ItemUserGithubBinding

class GithubUserAdapter :
    RecyclerView.Adapter<GithubUserAdapter.GithubUserHolder>() {
    lateinit var listGithubUser: List<DataUserSearchItem>
    lateinit var listGithubRepo: List<DataUserSearchLicense>

    fun setDataUser(listGithubUser: List<DataUserSearchItem>) {
        this.listGithubUser = listGithubUser
    }
    fun setDataRepo(listGithubRepo: DataUserSearchLicense) {
        this.listGithubRepo = listOf(listGithubRepo)
    }

    class GithubUserHolder(private val binding: ItemUserGithubBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listGithubUser: DataUserSearchItem) {
            binding.apply {
                tvUsername.text = listGithubUser.owner?.login ?: "-"
                tvNama.text = listGithubUser.name
                tvLinkGithub.text = listGithubUser.owner?.htmlUrl ?: "-"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserHolder {
        return GithubUserHolder(ItemUserGithubBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GithubUserHolder, position: Int) =
        holder.bindData(listGithubUser[position])


    override fun getItemCount(): Int = listGithubUser.size
}