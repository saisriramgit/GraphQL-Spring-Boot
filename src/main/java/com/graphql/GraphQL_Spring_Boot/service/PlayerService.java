package com.graphql.GraphQL_Spring_Boot.service;




import com.graphql.GraphQL_Spring_Boot.model.Player;
import com.graphql.GraphQL_Spring_Boot.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findPlayerById(Integer id) {
        return players.stream().filter(player -> player.Id() == id).findFirst();
    }

    public Player createPlayer(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player deletePlayer(Integer id) {
        Player player = players.stream().filter(c -> c.Id() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException());
        players.remove(player);
        return player;
    }

    public Player updatePlayer(Integer id, String name, Team team) {
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> optional = players.stream()
                .filter(c -> c.Id() == id).findFirst();
        if(optional.isPresent()) {
            Player player = optional.get();
            int index = players.indexOf(player);
            players.set(index, player);
        } else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayer;
    }

    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.India));
        players.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.Australia));
        players.add(new Player(id.incrementAndGet(), "Jasprit Bumrah", Team.England));
        players.add(new Player(id.incrementAndGet(), "Rishabh pant", Team.Bangladesh));
        players.add(new Player(id.incrementAndGet(), "Suresh Raina", Team.India));
    }
}
