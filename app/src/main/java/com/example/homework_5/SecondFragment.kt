package com.example.homework_5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.zip.Inflater

class SecondFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second,container, false)
        val args = this.arguments
        val text = args?.get("message")
        val myText = view.findViewById<TextView>(R.id.myText)
        myText.text = text.toString()
        return view
    }
}