package com.example.fgquizz.service;

import com.example.fgquizz.model.Answer;
import com.example.fgquizz.model.dto.AnswerDTO;

import java.util.List;

public interface AnswerService {

    List<Answer> getAllAnswers();

    Answer getAnswerById(Long answerId);

    Answer createAnswer(Answer answer);

    Answer updateAnswer(Long answerId, Answer updatedAnswer);

    void deleteAnswer(Long answerId);
}
