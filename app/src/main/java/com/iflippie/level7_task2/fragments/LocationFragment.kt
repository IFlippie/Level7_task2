package com.iflippie.level7_task2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.iflippie.level7_task2.QuestionViewModel
import com.iflippie.level7_task2.R
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(activity as AppCompatActivity).get(QuestionViewModel::class.java)
        initViewModel()
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    private fun initViewModel() {
        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer {
            ivLocation.setImageResource(it.clue)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNextQuestion.setOnClickListener {
            viewModel.setNextQuestion()
            if (viewModel.quizOver.value!!) {
                findNavController().navigate(R.id.action_locationFragment_to_completedFragment)
            } else {
                findNavController().navigate(R.id.action_locationFragment_to_questionFragment)
            }
        }
    }

}
