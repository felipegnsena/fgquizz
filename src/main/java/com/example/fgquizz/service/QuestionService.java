package com.example.fgquizz.service;

import com.example.fgquizz.model.Question;
import com.example.fgquizz.model.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    Question getQuestionById(Long questionId);

    Question createQuestion(Question question);

    Question updateQuestion(Long questionId, Question updatedQuestion);

    void deleteQuestion(Long questionId);
}
