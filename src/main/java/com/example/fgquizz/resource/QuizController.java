package com.example.fgquizz.resource;

import com.example.fgquizz.model.Player;
import com.example.fgquizz.model.Question;
import com.example.fgquizz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return quizService.getAllQuestions();
    }

    @GetMapping("/questions/random")
    public Question getRandomQuestion() {
        return quizService.getRandomQuestion();
    }

    @PostMapping("/players/{playerId}/answers/{answerId}")
    public Map<String, Object> submitAnswer(@PathVariable Long playerId, @PathVariable Long answerId) {
        boolean correct = quizService.submitAnswer(playerId, answerId);
        return Map.of("correct", correct);
    }

    @GetMapping("/players/{playerId}/score")
    public Map<String, Integer> getPlayerScore(@PathVariable Long playerId) {
        int score = quizService.getPlayerScore(playerId);
        return Map.of("score", score);
    }

    @PostMapping("/players/{playerId}/reset")
    public void resetGame(@PathVariable Long playerId) {
        quizService.resetGame(playerId);
    }

    @GetMapping("/players")
    public List<Player> getAllPlayerScores() {
        return quizService.getAllPlayerScores();
    }
}

