package com.task.oneday.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import coil.load
import com.task.oneday.R
import com.task.oneday.databinding.FragmentMainBinding
import com.task.oneday.domain.NasaRepositoryImpl

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(NasaRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.requestPictureOfTheDay()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)

        binding.bottomAppbar.setOnMenuItemClickListener {
            when(it.itemId){
              else -> true
            }
        }

//binding.explanation.text = "${PictureOfTheDayResponse.explanation}"

        binding.textInput.setEndIconOnClickListener {
            EditSomethingImportantBottomSheetDialogFragment().show(parentFragmentManager, "tag")
        }

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->  }
        

//        val behavior: BottomSheetBehavior<LinearLayout> = BottomSheetBehavior.from(binding.bottomSheet)
//        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
//
//        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                Toast.makeText(requireContext(), "Change", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                Toast.makeText(requireContext(), "Slide", Toast.LENGTH_SHORT)   .show()         }
//
//        })


        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.loading.collect {
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.error.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.image.collect { url ->
                url?.let {
                    binding.fragmentMainImage.load(it)
                }
            }
        }
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.explanation.collect { expl ->
                expl?.let {
                    binding.explanation.setText(it)             //load(it)
                }
            }
        }

     //   binding.explanation.setText("${viewModel.explanation}" )


    }
}