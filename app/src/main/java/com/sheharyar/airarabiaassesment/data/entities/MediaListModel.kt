package com.sheharyar.airarabiaassesment.data.entities

import com.google.gson.annotations.SerializedName

data class MediaListModel(
    var type: String,
    var subtype: String,
    var caption: String,
    var copyright: String,
    var approved_for_syndication: Int,
    @SerializedName("media-metadata")
    val media_metadata: ArrayList<MediaMetaList>
)