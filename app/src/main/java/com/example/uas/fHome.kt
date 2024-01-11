package com.example.uas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas.databinding.FragmentFHomeBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val networkConfig = NetworkConfig()

/**
 * A simple [Fragment] subclass.
 * Use the [fHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class fHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentFHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AlbumsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize NetworkConfig


        // Example function to make an API call
        val btn_search = binding.btnSearch

        btn_search.setOnClickListener {
            // Example search query
            val query = binding.edtQuery.text.toString()

            // Make the API call
            NetworkConfig().getApiService()
                .searchArtists("$query","albums")
                .enqueue(object : retrofit2.Callback<SearchModel> {
                    override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                        // Handle failure
                        Log.e("API Call Failure", "Error: ${t.message}", t)
                    }

                    override fun onResponse(
                        call: Call<SearchModel>,
                        response: Response<SearchModel>
                    ) {
                        if (response.isSuccessful) {
                            val searchItems = response.body()
                            searchItems?.let {
                                // Log the response details
                                Log.d("API Call Success", "Response: $searchItems")
                                searchItems?.let {searchModel ->
                                    searchModel.albums?.let {
                                        adapter.submitList(searchModel.albums?.items)
                                    }
                                }
                            }
                        } else {
                            // Handle unsuccessful response
                            Log.e("API Call Failed", "Code: ${response.code()}, Message: ${response.message()}")
                        }
                    }
                })

            setupRV()

        }

    }

    private fun setupRV() {
        val layoutManager = LinearLayoutManager(view?.context)
        adapter = AlbumsAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.addItemDecoration(
            DividerItemDecoration(view?.context, layoutManager.orientation)
        )
        adapter.setOnClickCallback(object : AlbumsAdapter.OnClickListener{
            override fun onClickCard(albumItem: ItemsItem) {
                Log.d("Test", "Udah bisa diklik")


            }

        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}