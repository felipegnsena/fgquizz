package com.example.fgquizz.service;

import com.example.fgquizz.model.Answer;
import com.example.fgquizz.model.Player;
import com.example.fgquizz.model.Question;

import java.util.List;

public interface QuizService {

    List<Question> getAllQuestions();

    Question getRandomQuestion();

    boolean submitAnswer(Long playerId, Long answerId);

    int getPlayerScore(Long playerId);

    void resetGame(Long playerId);

    List<Player> getAllPlayerScores();
}
