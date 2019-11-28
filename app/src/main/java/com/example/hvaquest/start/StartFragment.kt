package com.example.hvaquest.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.hvaquest.R
import com.example.hvaquest.main.MainActivity
import kotlinx.android.synthetic.main.start_fragment.*

class StartFragment : Fragment() {

    private lateinit var viewModel: StartViewModel
    private var pageIndex = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        btnStartQuest.setOnClickListener { onNextButtonClicked() }

        (activity as MainActivity?)?.unsetBackButton()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)
    }

    private fun onNextButtonClicked() {
        findNavController().navigate(
            StartFragmentDirections.actionStartFragmentToQuestionFragment(
                pageIndex
            ))
    }
}
