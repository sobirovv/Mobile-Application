package com.example.loginsignupapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentA : Fragment() {

    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        textView = view.findViewById(R.id.fragmentTextView)
        textView.text = "FragmentA"

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentA()
    }
}
