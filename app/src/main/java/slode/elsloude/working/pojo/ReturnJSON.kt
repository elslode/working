package slode.elsloude.working.pojo

import com.google.gson.annotations.SerializedName

data class ReturnJSON(
    @SerializedName("mode")
    val mode: String,
    @SerializedName("link")
    val link: Links
)