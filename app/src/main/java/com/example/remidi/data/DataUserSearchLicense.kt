package com.example.remidi.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DataUserSearchLicense(
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("spdx_id")
    val spdxId: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("node_id")
    val nodeId: String? = null
) : Parcelable