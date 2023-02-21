package com.example.remidi.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remidi.commons.Constanta.MENU_NETWORK_EXAMPLE
import com.example.remidi.commons.Utils
import com.example.remidi.databinding.ActivityMainBinding
import com.example.remidi.presenter.menuexample.adapter.MenuAdapter
import com.example.remidi.presenter.menuexample.model.MenuFeature


class MainActivity : AppCompatActivity(), MenuAdapter.OnMenuClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var listMenuData: ArrayList<MenuFeature>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpDataMenu()
        setUpAdapterMenu()
    }

    private fun setUpAdapterMenu() {
        listMenuData = arrayListOf(
           MenuFeature(MENU_NETWORK_EXAMPLE)
        )
    }

    private fun setUpDataMenu() {
        binding.apply {
            val adapterMenu = MenuAdapter(this@MainActivity)
            /*#15 set data dummy ke dalam adapter */
            adapterMenu.setData(listMenuData)
            val linearLayout =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            binding.rvMenu.apply {
                layoutManager = linearLayout
                adapter = adapterMenu
            }
        }
    }

    override fun onClickMenu(menuFeature: MenuFeature) = Utils.manuNavigateTo(this, menuFeature)

}