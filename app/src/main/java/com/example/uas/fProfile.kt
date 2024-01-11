package com.example.uas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uas.LoginActivity
import com.example.uas.MainActivity
import com.example.uas.databinding.FragmentFProfileBinding

class fProfile : Fragment() {
    private var _binding: FragmentFProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            val email = arguments?.getString("userEmail")
            binding.tvEmail.text = email.toString()

            (activity as? MainActivity)?.logout()
            val i = Intent(requireContext(), LoginActivity::class.java)
            startActivity(i)
        }

        // Other initialization code...
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
