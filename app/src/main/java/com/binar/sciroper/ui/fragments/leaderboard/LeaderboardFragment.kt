package com.binar.sciroper.ui.fragments.leaderboard

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.binar.sciroper.data.firebase.FirebaseRtdb
import com.binar.sciroper.databinding.FragmentLeaderboardBinding
import com.binar.sciroper.util.App


class LeaderboardFragment : Fragment() {
    private var imageUri: Uri? = null

    private var _binding: FragmentLeaderboardBinding? = null
    private val binding get() = _binding!!
    private val leaderBoardVm: LeaderBoardVm by viewModels {
        LeaderBoardVmFactory(App.appDb.getUserDao())
    }
    private lateinit var recyclerView: RecyclerView
    private val database = FirebaseRtdb()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rcPlayer
        val adapter = UserListAdapter(leaderBoardVm)
        recyclerView.adapter = adapter


        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = leaderBoardVm
        }

        binding.btnBack.setOnClickListener {
            val action = LeaderboardFragmentDirections.actionLeaderboardFragmentToMenuFragment()
            findNavController().navigate(action)
        }

        binding.btnShare.setOnClickListener {

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}