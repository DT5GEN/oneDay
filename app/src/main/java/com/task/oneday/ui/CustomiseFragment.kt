package com.task.oneday.ui


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.task.oneday.R
import com.task.oneday.databinding.FragmentCustomiseBinding
import com.google.android.material.chip.ChipGroup

class CustomiseFragment : Fragment(R.layout.fragment_customise) {

    private lateinit var parentActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        parentActivity = (context as MainActivity) // 1 способ получить родительскую активити
//
//        parentActivity = activity as MainActivity // воторой способ
        parentActivity = requireActivity() as MainActivity
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context: Context = ContextThemeWrapper(activity, getStyleName(getCurrentTheme()))
        val localInflater = inflater.cloneInContext(context)
        _binding = FragmentCustomiseBinding.inflate(localInflater)
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


    private fun setPrefs(key: String, int: Int) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(key, int)
            apply()
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chipGroup.setOnCheckedChangeListener { group, _ ->

            when (group.checkedChipId) {
                R.id.chip_grey -> {
                    setPrefs(getString(R.string.THEME_KEY), 1)
                    recreateFragment()
                }

                R.id.chip_green -> {
                    setPrefs(getString(R.string.THEME_KEY), 2)
                    recreateFragment()
                }

                R.id.chip_blue -> {
                    setPrefs(getString(R.string.THEME_KEY), 3)
                    recreateFragment()
                }

                R.id.chip_red -> {
                    setPrefs(getString(R.string.THEME_KEY), 4)
                    parentActivity.recreate()
                 //   requireActivity().let { requireActivity().recreate() }
                }

                R.id.chip_orange -> {
                    setPrefs(getString(R.string.THEME_KEY), 5)
                    requireActivity().let { requireActivity().recreate() }
                }

                else -> {
                    Toast.makeText(context, " else ", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }



    private fun recreateFragment() {
        requireActivity().let {
            parentActivity.supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container_first, newInstance())
            }
        }
    }

    private fun getCurrentTheme() : Int{
        return requireActivity().getPreferences(Context.MODE_PRIVATE).getInt(getString(R.string.THEME_KEY), -1)
    }

    private fun getStyleName(currentTheme: Int): Int {
        return when (currentTheme) {
            1 -> R.style.Theme_OneDay_NoActionBarGreenApiL
            2 -> R.style.Theme_OneDay_NoActionBar
            3 -> R.style.Theme_Blue
            4 -> R.style.Theme_Red
            5 -> R.style.Theme_OneDay
            else -> 0
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CustomiseFragment()

    }
}