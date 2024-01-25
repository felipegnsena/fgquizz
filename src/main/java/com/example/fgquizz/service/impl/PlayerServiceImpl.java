package com.example.fgquizz.service.impl;

import com.example.fgquizz.model.Player;
import com.example.fgquizz.repository.PlayerRepository;
import com.example.fgquizz.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    @Override
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(Long playerId, Player updatedPlayer) {
        Optional<Player> existingPlayer = playerRepository.findById(playerId);
        if (existingPlayer.isPresent()) {
            updatedPlayer.setPlayerId(playerId);
            return playerRepository.save(updatedPlayer);
        }
        return null; // Player not found
    }

    @Override
    public void deletePlayer(Long playerId) {
        playerRepository.deleteById(playerId);
    }
}
