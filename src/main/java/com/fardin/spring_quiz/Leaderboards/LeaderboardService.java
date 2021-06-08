package com.fardin.spring_quiz.Leaderboards;

import com.fardin.spring_quiz.Players.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    @Autowired
    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    public void addNewScore(Leaderboard leaderboard){
        leaderboardRepository.save(leaderboard);
    }

    public void deleteScore(Long leaderboardId){
        if(!leaderboardRepository.existsById(leaderboardId)){
            throw new IllegalStateException("Score with id " + leaderboardId + " not found");
        }

        leaderboardRepository.deleteById(leaderboardId);
    }


    public List<Leaderboard> getAllScores(){
        return leaderboardRepository.findAll();
    }

    public Leaderboard getSelectedScore(Long playerId){
        Leaderboard l = leaderboardRepository.findLeaderboardByPlayerId(playerId).orElseThrow(() -> new IllegalStateException("Score with id " + playerId + " doesnt exist"));
        return l;
    }



    @Transactional
    public void updateScore(Long playerID, Integer newScore){
//        if(!leaderboardRepository.findScoreByPlayerId(playerID)){
//            throw new IllegalStateException("Player with id " + playerID + " does not exist");
//        }
        Leaderboard l = leaderboardRepository.findLeaderboardByPlayerId(playerID).orElseThrow(() -> new IllegalStateException("Player with id " + playerID + " doesnt exist"));
        if(newScore == null){
            throw new IllegalStateException("Null is unacceptable value for newScore");
        }

        l.setScore(newScore);
    }

    @Transactional
    public void updateName(Long playerID, String newName){
//        if(!leaderboardRepository.existsById(leaderboardId)){
//            throw new IllegalStateException("Score with id " + leaderboardId + " does not exist");
//        }

        Leaderboard l = leaderboardRepository.findLeaderboardByPlayerId(playerID).orElseThrow(() -> new IllegalStateException("Player with id " + playerID + " doesnt exist"));
        if(newName == null){
            throw new IllegalStateException("Null is unacceptable value for newScore");
        }

        l.setName(newName);

    }








}