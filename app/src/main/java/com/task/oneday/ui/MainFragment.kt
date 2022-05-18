package com.task.oneday.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.task.oneday.R
import com.task.oneday.R.layout
import com.task.oneday.databinding.FragmentMainBinding
import com.task.oneday.domain.NasaRepositoryImpl
import com.task.oneday.viewmodel.MainViewModel
import com.task.oneday.viewmodel.MainViewModelFactory

class MainFragment : Fragment(layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(NasaRepositoryImpl())
    }

    private var _binding: FragmentMainBinding? = null
    val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.requestPictureOfTheDay()

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
        val binding = FragmentMainBinding.bind(view)

        setBottomAppBar(view)


        binding.textInput.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse(
                        "https://en.wikipedia.org/w/index.php?search=${binding.wikiSearch.text.toString()}")
            })
        }


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
                expl.let {
                    binding.explanation.setText(it)             //load(it)
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav -> Toast.makeText(context, "Favourite selected", Toast.LENGTH_LONG)
                .show()

            R.id.action_settings -> requireActivity().supportFragmentManager.beginTransaction()
                .replace(androidx.navigation.ui.ktx.R.id.container, CustomiseFragment.newInstance())
                .addToBackStack("Customise")
                .commit()

            android.R.id.home -> NavigationDrawerFragment().show(
                requireActivity().supportFragmentManager,
                "goHome"
            )
        }
        return super.onOptionsItemSelected(item)
    }

    private var isMain = true

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(binding.toggleBottomAppbar)
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.toggleBottomAppbar.navigationIcon = null
                binding.toggleBottomAppbar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_back_fab
                    )
                )
                binding.toggleBottomAppbar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                binding.toggleBottomAppbar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                binding.toggleBottomAppbar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_plus_fab
                    )
                )
                binding.toggleBottomAppbar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }
}