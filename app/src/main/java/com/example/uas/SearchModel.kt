package com.example.uas

import com.google.gson.annotations.SerializedName

data class SearchModel(

	@field:SerializedName("albums")
	val albums: Albums? = null,

	@field:SerializedName("query")
	val query: String? = null
)

data class Albums(

	@field:SerializedName("pagingInfo")
	val pagingInfo: PagingInfo? = null,

	@field:SerializedName("totalCount")
	val totalCount: Int? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class PagingInfo(

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("nextOffset")
	val nextOffset: Int? = null
)

data class CoverArt(

	@field:SerializedName("sources")
	val sources: List<SourcesItem?>? = null
)

data class ItemsItem(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("profile")
	val profile: Profile? = null,

	@field:SerializedName("uri")
	val uri: String? = null
)

data class Data(

	@field:SerializedName("date")
	val date: Date? = null,

	@field:SerializedName("artists")
	val artists: Artists? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("coverArt")
	val coverArt: CoverArt? = null,

	@field:SerializedName("uri")
	val uri: String? = null
)

data class Date(

	@field:SerializedName("year")
	val year: Int? = null
)

data class SourcesItem(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class Artists(

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class Profile(

	@field:SerializedName("name")
	val name: String? = null
)
