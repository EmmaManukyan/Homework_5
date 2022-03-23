package com.example.homework_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.homework_5.databinding.ActivityMain2Binding
import com.example.homework_5.databinding.ActivityMainBinding
import com.example.homework_5.databinding.FragmentFirst2Binding
import com.example.homework_5.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment2 : Fragment(), View.OnClickListener {
    enum class Turn {
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private lateinit var binding: FragmentFirst2Binding
    private var boardList = mutableListOf<Button>()
    private lateinit var a1: Button
    private lateinit var a2: Button
    private lateinit var a3: Button
    private lateinit var b1: Button
    private lateinit var b2: Button
    private lateinit var b3: Button
    private lateinit var c1: Button
    private lateinit var c2: Button
    private lateinit var c3: Button
    private lateinit var btnAgain:Button
    private lateinit var result:String
    private lateinit var turnTextView:TextView
    private var crossesScore = 0
    private var noughtsScore = 0

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first2, container, false)
        a1 = view?.findViewById<Button>(R.id.a1)!!
        a2 = view.findViewById<Button>(R.id.a2)!!
        a3 = view.findViewById<Button>(R.id.a3)!!
        b1 = view.findViewById<Button>(R.id.b1)!!
        b2 = view.findViewById<Button>(R.id.b2)!!
        b3 = view.findViewById<Button>(R.id.b3)!!
        c1 = view.findViewById<Button>(R.id.c1)!!
        c2 = view.findViewById<Button>(R.id.c2)!!
        c3 = view.findViewById<Button>(R.id.c3)!!
        turnTextView = view.findViewById<TextView>(R.id.turnTV)!!
        btnAgain = view.findViewById(R.id.btn_again)!!
        btnAgain.setOnClickListener(this)
        binding = FragmentFirst2Binding.inflate(layoutInflater)
        initBoard()
        boardList.forEach {
            it?.setOnClickListener(this)
        }
        return view
    }

    private fun initBoard() {
        boardList.add(a1)
        boardList.add(a2)
        boardList.add(a3)
        boardList.add(b1)
        boardList.add(b2)
        boardList.add(b3)
        boardList.add(c1)
        boardList.add(c2)
        boardList.add(c3)
    }


    private fun addToBoard(button: Button) {
        if (button.text != "") {
            return
        }
        if (currentTurn == Turn.NOUGHT) {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        } else if (currentTurn == Turn.CROSS) {
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if (currentTurn == Turn.CROSS) {
            turnText = "Turn $CROSS"
        } else if (currentTurn == Turn.NOUGHT) {
            turnText = "Turn $NOUGHT"
        }
        turnTextView.text = turnText
    }

    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.turnTV -> {
            }
            R.id.btn_again->{
                resetBoard()
            }
            else -> {
                if (p0 !is Button) {
                    return
                }
                addToBoard(p0)
                if (checkForVictory(NOUGHT)){
                    noughtsScore++
                    result = "Noughts win!"
                    turnTextView.text = result
                    turnTextView.textSize = 35F
                    btnAgain.visibility = View.VISIBLE

                }
                if (checkForVictory(CROSS)){
                    crossesScore++
                    result = "Crosses win!"
                    Toast.makeText(activity, "hey", Toast.LENGTH_SHORT).show()
                    turnTextView.text = result
                    turnTextView.textSize = 35F
                    btnAgain.visibility = View.VISIBLE
                }
                if(fullBoard()){
                    btnAgain.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun checkForVictory(s: String): Boolean {

        if (a1.text==s && a2.text==s && a3.text==s){
            return true
        }
        if (b1.text==s && b2.text==s && b3.text==s){
            return true
        }
        if (c1.text==s && c2.text==s && c3.text==s){
            return true
        }

        if (a1.text==s && b1.text==s && c1.text==s){
            return true
        }
        if (a2.text==s && b2.text==s && c2.text==s){
            return true
        }
        if (a3.text==s && b3.text==s && c3.text==s){
            return true
        }

        if (a1.text==s && b2.text==s && c3.text==s){
            return true
        }
        if (a3.text==s && b2.text==s && c1.text==s){
            return true
        }
        return false
    }




    private fun resetBoard() {
        for(button in boardList){
            button.text = ""
        }
        if (firstTurn==Turn.NOUGHT){
            firstTurn = Turn.CROSS
            turnTextView.text =  "Turn $CROSS"
        }
        if (firstTurn==Turn.CROSS){
            firstTurn = Turn.NOUGHT
            turnTextView.text = "Turn $NOUGHT"
        }
        currentTurn = firstTurn
        btnAgain.visibility = View.GONE

    }

    private fun fullBoard(): Boolean {
        for (button in boardList){
            if (button.text==""){
                return false
            }
        }
        return true
    }
}