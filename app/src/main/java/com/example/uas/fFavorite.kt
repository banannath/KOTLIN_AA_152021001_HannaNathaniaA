package com.example.uas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uas.NetworkConfig2.RetrofitClient.apiService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class fFavorite : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_f_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Example of using the API service to get albums by email
        val email = arguments?.getString("userEmail")
        apiService.getAlbumsByEmail(email.toString())
    }
}