package com.takima.race.race.services;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.runner.entities.Runner;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getAll() {
        return raceRepository.findAll();
    }
    public Race getById(Long id) {
        return raceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race %s not found", id)
                )
        );
    }
    public Race create (Race race){
        String.format("201 Created");
        return raceRepository.save(race);
    }
    
    public void delete (Long id){
        raceRepository.deleteById(id);
    }

    public Race update (Long id, Race race){
        Race existingRace = getById(id);
        existingRace.setName(race.getName());
        existingRace.setLocation(race.getLocation());
        existingRace.setDate(race.getDate());
        existingRace.setMaxParticipants(race.getMaxParticipants());
        return raceRepository.save(existingRace);
    }
    

}
