package com.example.fgquizz.service;

import com.example.fgquizz.model.Player;
import com.example.fgquizz.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {


    List<Player> getAllPlayers();

    Player getPlayerById(Long playerId);

    Player createPlayer(Player player);

    Player updatePlayer(Long playerId, Player updatedPlayer);

    void deletePlayer(Long playerId);
}
