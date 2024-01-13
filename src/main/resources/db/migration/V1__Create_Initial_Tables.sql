CREATE TABLE question (
                          question_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          question_text VARCHAR(255) NOT NULL
);

CREATE TABLE answer (
                        answer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        answer_text VARCHAR(255) NOT NULL,
                        correct BOOLEAN,
                        question_id BIGINT,
                        FOREIGN KEY (question_id) REFERENCES question(question_id)
);

CREATE TABLE player (
                        player_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        player_name VARCHAR(255) NOT NULL,
                        score INT
);