package com.example.hvaquest.question

import androidx.lifecycle.ViewModel
import com.example.hvaquest.data.QuestRepository
import com.example.hvaquest.model.Question

class QuestionViewModel : ViewModel() {

    fun getQuestion(index: Int): Question? {
        return QuestRepository().getHvaQuestion(index)
    }

    fun getQuestSize(): Int {
        return QuestRepository().getQuestSize()
    }
}
