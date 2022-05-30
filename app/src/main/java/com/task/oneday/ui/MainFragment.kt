package com.task.oneday.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.task.oneday.R
import com.task.oneday.R.layout
import com.task.oneday.databinding.FragmentMainBinding

class MainFragment : Fragment(layout.fragment_main) {


    private var _binding: FragmentMainBinding? = null
    val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        if (savedInstanceState == null) {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, FragmentPOD())
                ?.commit()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bottomNavigationView: BottomNavigationView? = view?.findViewById(R.id.bottom_nav)

        bottomNavigationView?.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.action_cosmos -> FragmentPOD()
                R.id.action_wiki -> FragmentWiki()
                else -> null

            }?.also { fragment ->
                fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, fragment)
                    ?.commit()
            }

            true
        }


    }


}