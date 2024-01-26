import 'dart:async';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Quiz App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: QuizScreen(),
    );
  }
}

class QuizScreen extends StatefulWidget {
  @override
  _QuizScreenState createState() => _QuizScreenState();
}

class _QuizScreenState extends State<QuizScreen> {
  final String apiUrl = 'http://your-api-base-url'; // Substitua pelo seu URL de API
  List<dynamic> questions = [];
  int score = 0;
  dynamic currentQuestion;

  @override
  void initState() {
    super.initState();
    loadQuestions();
  }

  Future<void> loadQuestions() async {
    final response = await http.get(Uri.parse('$apiUrl/api/quiz/questions'));

    if (response.statusCode == 200) {
      setState(() {
        questions = json.decode(response.body);
        loadRandomQuestion();
      });
    } else {
      print('Failed to load questions: ${response.statusCode}');
    }
  }

  Future<void> loadRandomQuestion() async {
    final response = await http.get(Uri.parse('$apiUrl/api/quiz/questions/random'));

    if (response.statusCode == 200) {
      setState(() {
        currentQuestion = json.decode(response.body);
      });
    } else {
      print('Failed to load random question: ${response.statusCode}');
    }
  }

  Future<void> submitAnswer(int answerId) async {
    final response = await http.post(
      Uri.parse('$apiUrl/api/quiz/players/1/answers/$answerId'), // Substitua pelo seu ID de jogador
      headers: {'Content-Type': 'application/json'},
    );

    if (response.statusCode == 200) {
      final Map<String, dynamic> result = json.decode(response.body);
      final bool correct = result['correct'];

      setState(() {
        if (correct) {
          score++;
        }
        loadRandomQuestion();
      });
    } else {
      print('Failed to submit answer: ${response.statusCode}');
    }
  }

  Future<void> resetGame() async {
    final response = await http.post(Uri.parse('$apiUrl/api/quiz/players/1/reset')); // Substitua pelo seu ID de jogador

    if (response.statusCode == 200) {
      setState(() {
        score = 0;
        loadRandomQuestion();
      });
    } else {
      print('Failed to reset game: ${response.statusCode}');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Quiz App'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            if (currentQuestion != null)
              Text(
                'Question: ${currentQuestion['text']}',
                style: TextStyle(fontSize: 18),
              ),
            if (currentQuestion != null)
              Column(
                children: List.generate(
                  (currentQuestion['answers'] as List<dynamic>).length,
                      (index) {
                    final answer = currentQuestion['answers'][index];
                    return ElevatedButton(
                      onPressed: () => submitAnswer(answer['id']),
                      child: Text(answer['text']),
                    );
                  },
                ),
              ),
            Text('Score: $score'),
            ElevatedButton(
              onPressed: resetGame,
              child: Text('Reset Game'),
            ),
          ],
        ),
      ),
    );
  }
}
