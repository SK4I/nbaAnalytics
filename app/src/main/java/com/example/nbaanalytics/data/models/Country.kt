package com.example.network_services.network.models

import com.google.gson.annotations.SerializedName

data class Country (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("code" ) var code : String? = null,
    @SerializedName("flag" ) var flag : String? = null

)
