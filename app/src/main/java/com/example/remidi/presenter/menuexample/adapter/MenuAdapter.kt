package com.example.remidi.presenter.menuexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.remidi.databinding.ItemMenuBinding
import com.example.remidi.presenter.MainActivity
import com.example.remidi.presenter.menuexample.model.MenuFeature

class MenuAdapter(private val onClickMenu: MainActivity) :
    RecyclerView.Adapter<MenuAdapter.MenuHolder>(
    ) {

    lateinit var listMenu: ArrayList<MenuFeature>

    fun setData(listMenu: ArrayList<MenuFeature>) {
        this.listMenu = listMenu
    }

    interface OnMenuClickListener {
        fun onClickMenu(menuFeature: MenuFeature)
    }

    class MenuHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            menu: MenuFeature,
            listener: OnMenuClickListener
        ) {
            binding.apply {
                tvName.text = menu.nameFeature
                root.setOnClickListener {
                    listener.onClickMenu(menu)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        return MenuHolder(ItemMenuBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.bindData(listMenu[position], onClickMenu)
    }

    override fun getItemCount(): Int = listMenu.size
}