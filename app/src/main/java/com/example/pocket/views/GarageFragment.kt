package com.example.pocket.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pocket.R
import com.example.pocket.databinding.FragmentGarageBinding


class GarageFragment : Fragment() {
    private lateinit var binding: FragmentGarageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGarageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatBtn.setOnClickListener {
            val intent = Intent(context,SearchAndAddCarActivity::class.java)
            startActivity(intent)
        }
    }


}