package com.example.bootcamp.ui.main

import android.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bootcamp.RecyclerFragment
import com.example.bootcamp.adapters.CryptoItem
import com.example.bootcamp.adapters.VpAdapter
import com.example.bootcamp.databinding.FragmentMainBinding
import com.google.android.material.chip.Chip
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var vpAdapter : VpAdapter
    private val listRF = listOf(
        RecyclerFragment.newInstance()
    )
    private val listEF = listOf(
        ErrorFragment.newInstance()
    )
    private val model:MainViewModel by activityViewModels()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initializeRecycler() = with(binding){
        vpAdapter = VpAdapter(activity as FragmentActivity,listRF)
        vp2Recycler.adapter = vpAdapter
    }

    private fun initializeError() = with(binding){
        vpAdapter = VpAdapter(activity as FragmentActivity,listEF)
        vp2Recycler.adapter = vpAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        choiceChips()

        //CryptoInfo.newInstance()
        //initializeError()

        //Toast.makeText(this.context,"toast",LENGTH_LONG).show()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun choiceChips () {
        binding.choiceGroupChips
            .setOnCheckedStateChangeListener {
                    group, checkedIds ->

                val chip: Chip? = group.findViewById(checkedIds.last())

                chip?.let {
                    /*Toast.makeText(
                        this.context,
                        it.text.toString().lowercase(),
                        Toast.LENGTH_LONG).show()*/
                    cryptoInfo(it.text.toString().lowercase())
                }


            }
    }

    private fun cryptoInfo (currentPrice: String){
        val url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=" +
                currentPrice +
                "&order=market_cap_desc&per_page=30&page=1&sparkline=false"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result ->
                parseCryptoData(result, currentPrice)
                initializeRecycler()
            },
            { error ->
                Log.d("RESULT", "Error: $error")
                initializeError()
            }
        )
        queue.add(request)
    }

    private fun parseCryptoData (result: String,cur: String){

        val jsonArray = JSONArray(result)
        val list = ArrayList <CryptoItem>()
        val currency: String

        if(cur.equals("eur")) currency = "\u20ac"
        else currency = "\u0024"

        for (i in 0 until jsonArray.length()){
            val item = CryptoItem(
                JSONObject(jsonArray.getString(i)).getString("symbol"),
                JSONObject(jsonArray.getString(i)).getString("name"),
                currency+JSONObject(jsonArray.getString(i)).getString("current_price"),
                JSONObject(jsonArray.getString(i)).getString("market_cap_change_percentage_24h"),
                JSONObject(jsonArray.getString(i)).getString("image"),
            )
            list.add(item)
        }
        model.crypto.value = list
        Log.d("RESULT","mainFR Symbol: ${list[0].imageUrl}")
    }


}