package com.example.uas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas.NetworkConfig2.RetrofitClient.apiService
import com.example.uas.databinding.FragmentFFavoriteBinding
import com.example.uas.databinding.FragmentFHomeBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class fFavorite : Fragment() {
    private var _binding: FragmentFFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteAlbumAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_f_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Example of using the API service to get albums by email
        val email = arguments?.getString("userEmail")
        apiService.getAlbumsByEmail(email.toString()).enqueue(object : retrofit2.Callback<List<FavouriteAlbumModel>> {
            override fun onFailure(call: Call<List<FavouriteAlbumModel>>, t: Throwable) {
                // Handle failure
                Log.e("API Call Failure", "Error: ${t.message}", t)
            }

            override fun onResponse(call: Call<List<FavouriteAlbumModel>>, response: Response<List<FavouriteAlbumModel>>) {
                if (response.isSuccessful) {
                    val albums = response.body()
                    albums?.let {
                        // Handle the list of albums
                        Log.d("API Call Success", "Albums: $albums")
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("API Call Failed", "Code: ${response.code()}, Message: ${response.message()}")
                }
            }
        })

    }

    private fun setupRV() {
        val layoutManager = LinearLayoutManager(view?.context)
        adapter = FavoriteAlbumAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.addItemDecoration(
            DividerItemDecoration(view?.context, layoutManager.orientation)
        )
        adapter.setOnClickCallback(object : FavoriteAlbumAdapter.OnClickListener{
            override fun onClickCard(albumItem: FavouriteAlbumModelItem) {
                Log.d("Test", "Udah bisa diklik")

            }

        })
    }
}