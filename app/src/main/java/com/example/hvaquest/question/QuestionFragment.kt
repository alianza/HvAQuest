package com.example.hvaquest.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.R
import com.example.hvaquest.main.MainActivity
import com.example.hvaquest.model.Question
import kotlinx.android.synthetic.main.question_fragment.*

class QuestionFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel
    private lateinit var question: Question
    private val args: QuestionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.question_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        question = viewModel.getQuestion(args.questionIndex)!!

        setQuestion()
        setCounter()
    }

    private fun setQuestion() {
        tvQuestion.text = question.question
        rb1.text = question.choices[0]
        rb2.text = question.choices[1]
        rb3.text = question.choices[2]
    }

    private fun setCounter() {
        tvCounter.text = getString(R.string.counter, args.questionIndex, viewModel.getQuestSize())
    }

    private fun initViews() {
        btnConfirm.setOnClickListener { onConfirmButtonClicked() }

        (activity as MainActivity?)?.setToolbarTitle(R.string.question_page)
        (activity as MainActivity?)?.setBackButton()
    }

    private fun onConfirmButtonClicked() {
        if (rgQuestion.checkedRadioButtonId > 0) {
            if (view?.findViewById<RadioButton>(rgQuestion.checkedRadioButtonId)?.text  == question.correctAnswer) {
                findNavController().navigate(
                    QuestionFragmentDirections.actionQuestionFragmentToLocationFragment(
                        args.questionIndex
                    ))
            } else {
                Toast.makeText(context, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
                rgQuestion.clearCheck()
            }
        } else {
            Toast.makeText(context, getString(R.string.no_choice), Toast.LENGTH_SHORT).show()
        }
    }
}
