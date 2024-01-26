package com.example.fgquizz.service.impl;

import com.example.fgquizz.model.Answer;
import com.example.fgquizz.model.Player;
import com.example.fgquizz.model.Question;
import com.example.fgquizz.repository.AnswerRepository;
import com.example.fgquizz.repository.PlayerRepository;
import com.example.fgquizz.repository.QuestionRepository;
import com.example.fgquizz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> allQuestions = questionRepository.findAll();
        if (allQuestions.isEmpty()) {
            return null; // No questions available
        }

        Random random = new Random();
        int randomIndex = random.nextInt(allQuestions.size());
        return allQuestions.get(randomIndex);
    }

    @Override
    public boolean submitAnswer(Long playerId, Long answerId) {
        Answer correctAnswer = answerRepository.findById(answerId).orElse(null);
        Player player = playerRepository.findById(playerId).orElse(null);

        if (correctAnswer != null && player != null && correctAnswer.isCorrect()) {
            player.setScore(player.getScore() + 1);
            playerRepository.save(player);
            return true; // Resposta correta
        }

        return false; // Resposta incorreta
    }

    @Override
    public int getPlayerScore(Long playerId) {
        Player player = playerRepository.findById(playerId).orElse(null);
        return (player != null) ? player.getScore() : 0;
    }

    @Override
    public void resetGame(Long playerId) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player != null) {
            player.setScore(0);
            playerRepository.save(player);
        }
    }

    @Override
    public List<Player> getAllPlayerScores() {
        return playerRepository.findAll();
    }
}
