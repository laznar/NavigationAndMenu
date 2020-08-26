package com.g11.myapplication


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use the Kotlin extension in the fragment-ktx artifact
        setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getString("bundleKey")
            val result2 = bundle.getString("bundleKey2")
            // Do something with the result...
            textViewscore.setText(result)
            textViewscore2.setText(result2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        /*val datosRecuperados  = arguments
        val puntaje : Int
        if(datosRecuperados!=null){
            puntaje=datosRecuperados.getInt("score")
        }else{
            puntaje=0
        }
        val vistascore = view.findViewById<TextView>(R.id.textViewscore)
        vistascore.setText(puntaje.toString())*/
        view.findViewById<Button>(R.id.memory_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.sequence_button).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.memory_button -> {
                navController.navigate(R.id.action_mainFragment_to_memoryFragment)
            }
            R.id.sequence_button -> {
                navController.navigate(R.id.action_mainFragment_to_sequenceFragment)
            }
        }
    }
}