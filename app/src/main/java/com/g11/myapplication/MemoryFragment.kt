package com.g11.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_memory.*


class MemoryFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    var intScore = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memory, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.back_button).setOnClickListener(this)
        val images: MutableList<Int> = mutableListOf(R.drawable.luigi, R.drawable.zelda,R.drawable.digiball,R.drawable.link,R.drawable.marioverde,R.drawable.notagirl,R.drawable.luigi, R.drawable.zelda,R.drawable.digiball,R.drawable.link,R.drawable.marioverde,R.drawable.notagirl)
        val buttons:Array<Button> = arrayOf(button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12)
        val reverso = R.drawable.nintendoq
        var click = 0
        var finTurno = false
        var lastClick = -1
        var score = 100
        val duration = Toast.LENGTH_SHORT
        var record = 0
        var puntActual = 0
        images.shuffle()

        fun reset(){
            score = 100
            textView2.setText(score.toString())
            click = 0
            finTurno = false
            lastClick = -1
            intScore = 0
            images.shuffle()
            for(i:Int in 0..11){
                buttons[i].setBackgroundResource(reverso)
                buttons[i].text = "reverso"
                buttons[i].isClickable = true
            }
        }

        resetButton.setOnClickListener {
            reset()
            puntActual=0
        }

        for(i:Int in 0..11){
            buttons[i].text = "reverso"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "reverso" && !finTurno) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (click == 0) {
                        lastClick = i
                    }
                    click++
                } else if (buttons[i].text !in "reverso") {
                    buttons[i].setBackgroundResource(reverso)
                    buttons[i].text = "reverso"
                    click--
                }

                if (click == 2) {
                    finTurno = true
                    if (buttons[i].text == buttons[lastClick].text) {
                        buttons[i].isClickable = false
                        buttons[lastClick].isClickable = false
                        finTurno = false
                        click = 0
                        intScore = intScore+1
                        if (intScore==6){
                            puntActual = puntActual+score
                            if(record <= puntActual){
                                record = puntActual
                                textView4.setText(record.toString())
                            }
                            val text1 = "Now you have "+puntActual.toString()+" points"
                            val toast1 = Toast.makeText(activity,text1,duration)
                            toast1.show()
                            reset()
                            if(puntActual>=100 && puntActual<200){
                                score=90
                                textView2.setText(score.toString())
                            }else if(puntActual>=200 && puntActual<300){
                                score=80
                                textView2.setText(score.toString())
                            }else if(puntActual>=300) {
                                score = 70
                                textView2.setText(score.toString())
                            }
                        }
                    }
                } else if (click == 0) {
                    finTurno = false
                    score=score-10
                    textView2.setText(score.toString())
                    if(score == 0){
                        if(record <= puntActual){
                            record = puntActual
                            textView4.setText(record.toString())
                        }
                        puntActual = 0
                        val text2 = "Now you have 0 points"
                        val toast2 = Toast.makeText(activity,text2,duration)
                        toast2.show()
                        reset()
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.back_button -> {
                navController!!.navigate(R.id.action_memoryFragment_to_mainFragment)
            }
        }
    }

}
