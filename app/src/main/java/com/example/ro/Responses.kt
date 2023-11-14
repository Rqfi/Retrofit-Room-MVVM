package com.example.ro

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Responses(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>? = null
) : Parcelable

@Parcelize
data class ResultsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("height")
    val height: String? = null,

    @field:SerializedName("birth_year")
    val birthYear: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null
) : Parcelable
