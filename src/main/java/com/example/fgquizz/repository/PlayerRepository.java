package com.example.fgquizz.repository;

import com.example.fgquizz.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
