package com.graphql.GraphQL_Spring_Boot.controller;

import com.graphql.GraphQL_Spring_Boot.model.Player;
import com.graphql.GraphQL_Spring_Boot.model.Team;
import com.graphql.GraphQL_Spring_Boot.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @QueryMapping
    public Optional<Player> findPlayerById(@Argument Integer id) {
        return playerService.findPlayerById(id);
    }

    @MutationMapping
    public Player createPlayer(@Argument String name, @Argument Team team) {
        return playerService.createPlayer(name, team);
    }

    @MutationMapping
    public Player updatePlayer(@Argument Integer id, @Argument String name, @Argument Team team) {
        return playerService.updatePlayer(id, name, team);
    }

    @MutationMapping
    public Player deletePlayer(@Argument Integer id) {
        return playerService.deletePlayer(id);
    }
}

