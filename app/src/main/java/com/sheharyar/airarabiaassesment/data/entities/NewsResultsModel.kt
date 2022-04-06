package com.sheharyar.airarabiaassesment.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "news")
data class NewsResultsModel(
    @PrimaryKey
    var id: Long,
    var uri: String,
    var url: String,

    var asset_id: Long,
    var source: String,
    var published_date: String,
    var updated: String,
    var section: String,
    var subsection: String,
    var nytdsection: String,
    var adx_keywords: String,
    var column: Int,
    var byline: String,
    var type: String,
    var title: String,
    var abstract: String,
    var des_facet: ArrayList<String>,
    var org_facet: ArrayList<String>,
    var per_facet: ArrayList<String>,
    var geo_facet: ArrayList<String>,

    var media: List<MediaListModel>,
    var eta_id: Int
) {
    constructor() : this(0, "", "", 0, "", "", "", "", "", "",
    "",0,"","","","", ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), 0)
}