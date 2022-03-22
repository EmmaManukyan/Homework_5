package com.example.homework_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val button = view.findViewById<TextView>(R.id.btn_send)
        val edt = view.findViewById<EditText>(R.id.edt_1)
        val fragment = SecondFragment()


        button.setOnClickListener {
            val bundle = Bundle()
            fragment.arguments = bundle
            bundle.putString("message",edt.text.toString())
           fragmentManager
               ?.beginTransaction()
               ?.replace(R.id.fr_cont, fragment, "Second")
               ?.addToBackStack(null)
               ?.commit()
        }
        return view
    }

}