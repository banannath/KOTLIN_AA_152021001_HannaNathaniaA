package com.example.uas

import com.google.gson.annotations.SerializedName

data class FavouriteAlbumModel(

	@field:SerializedName("FavouriteAlbumModel")
	val favouriteAlbumModel: List<FavouriteAlbumModelItem?>? = null
)

data class FavouriteAlbumModelItem(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("artists")
	val artists: String? = null,

	@field:SerializedName("album_name")
	val albumName: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)
