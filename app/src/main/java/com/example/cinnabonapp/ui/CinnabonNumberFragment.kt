package com.example.cinnabonapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cinnabonapp.R
import com.example.cinnabonapp.databinding.FragmentCinnabonNumberBinding
import com.example.cinnabonapp.viewmodel.CinnabonViewModel

class CinnabonNumberFragment : Fragment() {
    private var _binding: FragmentCinnabonNumberBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: CinnabonViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cinnabon_number,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            cinnabonNumber = this@CinnabonNumberFragment
            viewModel = sharedViewModel
        }
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_cinnabonNumberFragment_to_startFragment)
    }
    fun nextScreen(){
        sharedViewModel.setQuantity(binding.increaseDecrease.getCurrentNumber())
        findNavController().navigate(R.id.action_cinnabonNumberFragment_to_pickupDateFragment)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}