package com.g11.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_sequence.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SequenceFragment : Fragment(), View.OnClickListener  {
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sequence, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.back_button2).setOnClickListener(this)
        val images: MutableList<Int> = mutableListOf(
            R.drawable.numero1,
            R.drawable.numero2,
            R.drawable.numero3,
            R.drawable.numero4,
            R.drawable.numero5,
            R.drawable.numero6,
            R.drawable.numero7,
            R.drawable.numero8,
            R.drawable.numero9,
            R.drawable.numero10,
            R.drawable.numero11,
            R.drawable.numero12,
            R.drawable.numero13,
            R.drawable.numero14,
            R.drawable.numero15,
            R.drawable.numero16,
            R.drawable.numero17,
            R.drawable.numero18,
            R.drawable.numero19,
            R.drawable.numero20,
            R.drawable.numero21,
            R.drawable.numero22,
            R.drawable.numero23,
            R.drawable.numero24,
            R.drawable.numero25
        )
        val buttons: Array<Button> = arrayOf(
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button10,
            button11,
            button12,
            button13,
            button14,
            button16,
            button17,
            button18,
            button19,
            button20,
            button21,
            button22,
            button24,
            button25,
            button26,
            button27
        )
        val reverso = R.drawable.nintendoq
        val duration = Toast.LENGTH_SHORT
        var puntActual = 0
        var intScore = 0
        images.shuffle()
        var n = 3
        var cont = 0
        val randoms: MutableList<Button> = mutableListOf()
        val randomImg: MutableList<Int> = mutableListOf()
        fun randomizer () {
            for (i: Int in 0..n-1) {
                val rand = buttons.random()
                randoms.add(rand)
                randomImg.add(images[i])
            }
            for (i: Int in 0..n-1) {
                for (j: Int in 0..24) {
                    if (buttons[j] == randoms[i]) {
                        buttons[j].setBackgroundResource(images[i])
                    }
                }
            }
        }
        fun retraso () {
            for (i: Int in 0..24) {
                buttons[i].isClickable = false
            }
            resetButton.isClickable = false
            GlobalScope.launch(context = Dispatchers.Main) {
                delay(4200)
                for (j: Int in 0..24) {
                    if (randoms.contains(buttons[j])) {
                        buttons[j].setBackgroundResource(reverso)
                    }
                    buttons[j].isClickable = true
                    resetButton.isClickable = true
                }
            }
        }
        fun reset() {
            cont = 0
            intScore = 0
            images.shuffle()
            for (i: Int in 0..24) {
                buttons[i].setBackgroundResource(reverso)
            }
            randoms.clear()
            randomImg.clear()
            randomizer()
            retraso()
        }
        resetButton.setOnClickListener {
            reset()
        }
        for (i: Int in 0..24) {
            buttons[i].isClickable = false
        }
        resetButton.isClickable = false
        if (n == 25) {
            reset()
            n = 3
        }
        startButton.setOnClickListener {
            reset()
            puntActual = 0
            for (i: Int in 0..24) {
                buttons[i].text = "reverso"
                buttons[i].textSize = 0.0F
                buttons[i].setOnClickListener {
                    if (buttons[i].text == "reverso") {
                        if (randoms.contains(buttons[i])) {
                            for (j: Int in 0..n-1) {
                                if (buttons[i] == randoms[j]) {
                                    buttons[i].setBackgroundResource(randomImg[j])
                                    buttons[i].isClickable = false
                                    cont += 1
                                }
                            }
                            if  (cont == n){
                                n += 1
                                intScore = intScore + 100
                                puntActual = puntActual + intScore
                                for (k: Int in 0..24) {
                                    buttons[k].isClickable = false
                                }
                                resetButton.isClickable = false
                                GlobalScope.launch(context = Dispatchers.Main) {
                                    delay(1000)
                                    reset()
                                }
                                val text1 = "Now you have " + puntActual.toString() + " points"
                                val toast1 = Toast.makeText(activity, text1, duration)
                                toast1.show()
                            }
                        } else {
                            for (g: Int in 0..24) {
                                buttons[g].isClickable = false
                            }
                            val text2 = "Try Again"
                            val toast2 = Toast.makeText(activity, text2, duration)
                            toast2.show()
                            puntActual = 0
                            n = 3
                            GlobalScope.launch(context = Dispatchers.Main) {
                                delay(1000)
                                reset()
                            }

                        }
                    }
                }
            }
            startButton.isClickable = false
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.back_button2 -> {
                navController!!.navigate(R.id.action_sequenceFragment_to_mainFragment)
            }
        }
    }

}
