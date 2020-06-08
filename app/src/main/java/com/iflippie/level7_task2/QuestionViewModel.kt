package com.iflippie.level7_task2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iflippie.level7_task2.database.QuestionRepository
import com.iflippie.level7_task2.model.Question

class QuestionViewModel : ViewModel() {
    private val questionRepository = QuestionRepository()
    private var questionList = questionRepository.getHvaQuestions()
    private var questionId: Int = 0
    var currentQuestion = MutableLiveData<Question>()
    var quizOver = MutableLiveData<Boolean>()
    var currentQuestionNum = MutableLiveData<Int>()
    var totalQuestions = questionList.size
    init {
        reset()
    }

    fun setNextQuestion() {
        questionId++
        currentQuestionNum.value = questionId
        if (questionId >= totalQuestions) {
            quizOver.value = true
        } else {
            currentQuestion.value = questionList.get(questionId)
        }
    }

    fun reset() {
        questionId = 0
        quizOver.value = false
        currentQuestion.value = questionList.get(questionId)
    }
}