package com.example.cinnabonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cinnabonapp.R
import com.example.cinnabonapp.databinding.FragmentStartBinding
import com.example.cinnabonapp.viewmodel.CinnabonViewModel


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: CinnabonViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            startFragment = this@StartFragment
        }
    }

    fun nextScreen(){
        findNavController().navigate(R.id.action_startFragment_to_cinnabonListFragment)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}