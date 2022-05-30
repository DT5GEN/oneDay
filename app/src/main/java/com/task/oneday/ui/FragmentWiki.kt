package com.task.oneday.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.task.oneday.R.layout
import com.task.oneday.databinding.FragmentWikiBinding

class FragmentWiki : Fragment(layout.fragment_wiki) {


    private var _binding: FragmentWikiBinding? = null
    val binding: FragmentWikiBinding
        get() {
            return _binding!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWikiBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentWikiBinding.bind(view)




        binding.textInput.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse(
                        "https://en.wikipedia.org/w/index.php?search=${binding.wikiSearch.text.toString()}")
            })
        }



    }

}