package com.example.bootcamp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bootcamp.adapters.CryptoAdapter
import com.example.bootcamp.adapters.CryptoItem
import com.example.bootcamp.databinding.FragmentRecyclerBinding
import com.example.bootcamp.ui.main.MainViewModel
import org.json.JSONArray
import org.json.JSONObject


class RecyclerFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerBinding
    private lateinit var adapter: CryptoAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = RecyclerFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRcView()
        model.crypto.observe(viewLifecycleOwner){
            adapter.submitList(it)
            //Log.d("IMAGE","URI IMAGE:")
        }
    }


    private fun initRcView() = with(binding){
        rvCryptoList.layoutManager = LinearLayoutManager(activity)
        adapter = CryptoAdapter()
        rvCryptoList.adapter = adapter
    }
}