package com.task.oneday.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.task.oneday.R
import com.task.oneday.databinding.FragmentCustomiseBinding

class CustomiseFragment : Fragment(R.layout.fragment_customise) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomiseBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var _binding: FragmentCustomiseBinding? = null
    private val binding: FragmentCustomiseBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setPrefs(key: String, int: Int) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(key, int)
            apply()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->  // need setOnCheckedStateChangeListener


            when (group.checkedChipId) {
                R.id.theme_grey -> {
                    setPrefs(getString(R.string.THEME_KEY), 1)
                    requireActivity().let { requireActivity().recreate() }
                }

                R.id.theme_green -> {
                    setPrefs(getString(R.string.THEME_KEY), 2)
                    requireActivity().let { requireActivity().recreate() }
                }

                R.id.theme_blue -> {
                    setPrefs(getString(R.string.THEME_KEY), 3)
                    requireActivity().let { requireActivity().recreate() }
                }

                R.id.theme_red -> {
                    setPrefs(getString(R.string.THEME_KEY), 4)
                    requireActivity().let { requireActivity().recreate() }
                }

                R.id.theme_orange-> {
                    setPrefs(getString(R.string.THEME_KEY), 5)
                    requireActivity().let { requireActivity().recreate() }
                }

                else -> {
                    Toast.makeText(context, "что-то не то", Toast.LENGTH_SHORT).show()
                }
            }

        }
//
//        binding.chipForDelete.setOnCloseIconClickListener {
//
//            binding.chipForDelete.isChecked = false  //binding.chipForDelete.visibility = View.GONE
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CustomiseFragment()

    }
}