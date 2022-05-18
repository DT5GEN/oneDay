package com.task.oneday.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.task.oneday.R
import com.task.oneday.databinding.NavigationDrawerLayoutBinding

class NavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: NavigationDrawerLayoutBinding? = null
    private val binding: NavigationDrawerLayoutBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_fav -> {
                    Toast.makeText(context, "One", Toast.LENGTH_SHORT).show()
                }
                R.id.action_settings -> {
                    Toast.makeText(context, "Two", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NavigationDrawerLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

}