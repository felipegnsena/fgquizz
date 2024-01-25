package com.example.fgquizz.service.impl;

import com.example.fgquizz.model.Answer;
import com.example.fgquizz.repository.AnswerRepository;
import com.example.fgquizz.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Answer getAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }

    @Override
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer updateAnswer(Long answerId, Answer updatedAnswer) {
        Optional<Answer> existingAnswer = answerRepository.findById(answerId);
        if (existingAnswer.isPresent()) {
            updatedAnswer.setAnswerId(answerId);
            return answerRepository.save(updatedAnswer);
        }
        return null; // Answer not found
    }

    @Override
    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }
}
