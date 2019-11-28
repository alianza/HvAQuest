package com.example.hvaquest.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.R
import com.example.hvaquest.main.MainActivity
import com.example.hvaquest.model.Question
import kotlinx.android.synthetic.main.location_fragment.*

class LocationFragment : Fragment() {

    private lateinit var viewModel: LocationViewModel
    private lateinit var question: Question
    private val args: LocationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @Suppress("DEPRECATION")
    private fun setLocation() {
        ivLocation.setImageDrawable(resources.getDrawable(question.clue))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        question = viewModel.getQuestion(args.questionIndex)!!

        setLocation()
    }

    private fun initViews() {
        btnNextQuestion.setOnClickListener { onNextButtonClicked() }

        (activity as MainActivity?)?.setToolbarTitle(R.string.location_page)
        (activity as MainActivity?)?.setBackButton()
    }

    private fun onNextButtonClicked() {
        if (args.questionIndex + 1 < viewModel.getQuestSize()) {
            findNavController().navigate(LocationFragmentDirections.actionLocationFragmentToQuestionFragment(
                args.questionIndex + 1
            ))
        } else {
            findNavController().navigate(LocationFragmentDirections.actionLocationFragmentToCompletedFragment())
        }
    }
}
