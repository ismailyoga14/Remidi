package com.example.remidi.commons

import android.app.Activity
import android.content.Intent
import com.example.remidi.presenter.menuexample.model.MenuFeature
import com.example.remidi.presenter.networkexample.GithubActivity

object Utils {
    fun manuNavigateTo(activity: Activity, menuFeature: MenuFeature) {
    var intent = Intent()
    when (menuFeature.nameFeature) {
        Constanta.MENU_NETWORK_EXAMPLE -> {
            intent = Intent(activity, GithubActivity::class.java)
        }
    }
    activity.startActivity(intent)

}
}
