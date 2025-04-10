package com.development.cursoandroid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.development.cursoandroid.R
import com.development.cursoandroid.databinding.FragmentPrimerBinding


class PrimerFragment : Fragment() {

    lateinit var bind: FragmentPrimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentPrimerBinding.inflate(inflater,container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.btnGo.setOnClickListener {
            val action = PrimerFragmentDirections.actionPrimerFragmentToSegundoFragment("Juan", 28)
            findNavController().navigate(action)
        }

        setFragmentResultListener("requestKey"){ key, bundle ->
            val result = bundle.getString("bundleKey")
            bind.txt.text = result
        }
    }
}