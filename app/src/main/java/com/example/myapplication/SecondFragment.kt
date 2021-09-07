package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.epayco.android.Epayco
import co.epayco.android.models.Authentication
import com.example.myapplication.databinding.FragmentSecondBinding
import org.json.JSONException

import org.json.JSONObject

import co.epayco.android.util.EpaycoCallback
import java.lang.Exception



/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        System.out.println("Hola mundo")

        val auth = Authentication()


        auth.setApiKey("32c8ef12cc65878db1ccff30cdaf8e49")
        auth.setPrivateKey("d84e9885d4f7de545e09736e9c5beb61")
        auth.setLang("ES")
        auth.setTest(true)

        val epayco = Epayco(auth)

        epayco.getBanks(object : EpaycoCallback {
            @Throws(JSONException::class)
            override fun onSuccess(data: JSONObject) {

                System.out.println(data)
            }

            override fun onError(error: Exception) {
                System.out.println("Error")
                System.out.println("Exception")
            }
        })
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}