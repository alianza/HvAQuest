package com.example.hvaquest.completed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.hvaquest.R
import com.example.hvaquest.main.MainActivity
import kotlinx.android.synthetic.main.completed_fragment.*

class CompletedFragment : Fragment() {

    private lateinit var viewModel: CompletedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.completed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        btnFinish.setOnClickListener { onFinishButtonClick() }

        (activity as MainActivity?)?.setToolbarTitle(R.string.completed_page)
        (activity as MainActivity?)?.setBackButton()
    }

    private fun onFinishButtonClick() {
        findNavController().navigate(CompletedFragmentDirections.actionCompletedFragmentToStartFragment())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CompletedViewModel::class.java)
    }

}
