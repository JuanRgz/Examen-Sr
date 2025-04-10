package com.development.cursoandroid.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.development.cursoandroid.R
import com.development.cursoandroid.databinding.FragmentSegundoBinding


class SegundoFragment : Fragment() {

    private var nombre:String?=null
    private var edad:Int?=null

    private lateinit var bind: FragmentSegundoBinding
    val args: SegundoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentSegundoBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nombre = args.nombre
        edad = args.edad
        bind.txt.text = "$nombre $edad"

        bind.btnGo.setOnClickListener {
            val result = "Resultado"
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            findNavController().navigate(Uri.parse("cursoandroid://card"))
        }
    }

    companion object{
        private const val MI_NOMBRE = "nombre"
        private const val MI_EDAD = "edad"

        fun newInstance(nombre: String, edad: Int) = SegundoFragment().apply {
            arguments = bundleOf(MI_NOMBRE to nombre, MI_EDAD to edad)
        }
    }
}