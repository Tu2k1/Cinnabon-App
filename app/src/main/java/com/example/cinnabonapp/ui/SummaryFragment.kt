package com.example.cinnabonapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cinnabonapp.R
import com.example.cinnabonapp.databinding.FragmentSummaryBinding
import com.example.cinnabonapp.viewmodel.CinnabonViewModel
import java.util.*

class SummaryFragment : Fragment() {
    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: CinnabonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    fun openGoogleMap(){
        val latitude = 24.715240
        val longitude = 46.685853
        val uri = "geo:0,0?q=$latitude,$longitude"
        val intent = Intent(Intent.ACTION_VIEW,Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        requireContext().startActivity(intent)
    }
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}