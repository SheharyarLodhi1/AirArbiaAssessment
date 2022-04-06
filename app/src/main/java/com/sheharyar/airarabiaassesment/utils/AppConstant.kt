package com.sheharyar.airarabiaassesment.utils

import com.sheharyar.airarabiaassesment.data.entities.MediaMetaList

object AppConstant {
    const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/viewed/"
    const val API_KEY = "1.json?api-key=eddyhP0Xfg6MPr8LDFEoNU5T0GcjAeG6"

    object MediaMetaList {
        var mediaMetaArrayList : ArrayList<com.sheharyar.airarabiaassesment.data.entities.MediaMetaList> = ArrayList()
    }
}