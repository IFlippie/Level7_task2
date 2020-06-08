package com.iflippie.level7_task2.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.iflippie.level7_task2.QuestionViewModel
import com.iflippie.level7_task2.R
import com.iflippie.level7_task2.model.Question
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(activity as AppCompatActivity).get(QuestionViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }


    private fun initViewModel() {
        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer {
            updateQuestion(it)
        })
        viewModel.currentQuestionNum.observe(viewLifecycleOwner, Observer {
            updateQuestionNumber(it)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestionNumber(it: Int) {
        tvQuestionNumber.text = "$it/${viewModel.totalQuestions}"
    }

    private fun updateQuestion(question: Question) {
        tvQuestion.text = question.question
        rBtn1.text = question.choices[0]
        rBtn2.text = question.choices[1]
        rBtn3.text = question.choices[2]
        btnConfirm.setOnClickListener {
            val id = radioGroup.checkedRadioButtonId
            if (id == -1) {
                Toast.makeText(context, "Enter something", Toast.LENGTH_SHORT).show()
            } else {
                val radioButton = radioGroup.findViewById<RadioButton>(id)
                if (radioButton.text == question.correctAnswer) {
                    findNavController().navigate(R.id.action_questionFragment_to_locationFragment)
                } else {
                    Toast.makeText(context, "Wrong answer broski", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
