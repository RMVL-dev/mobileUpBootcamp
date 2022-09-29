package com.example.bootcamp.ui.main

import android.app.DownloadManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bootcamp.R
import org.json.JSONObject
import kotlin.math.log

class CryptoInfo : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        return inflater.inflate(R.layout.fragment_crypto_info, container, false)
    }

    companion object {

        fun newInstance() = CryptoInfo()
    }

    private fun getInfo (name: String){
        val url = "https://api.coingecko.com/api/v3/coins/"+name.lowercase()
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {result->
                Log.d("RESULT","${result.toString()}")
            },
            {error->
                Log.d("ERROR","error")
            }
        )
        queue.add(request)
    }

    fun parseData(result:String){
        val jsonObject = JSONObject(result)

    }
}