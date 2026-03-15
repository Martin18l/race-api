package com.takima.race.race.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RunnerService;
import com.takima.race.race.repositories.RaceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceService raceService;
    private final RaceRepository raceRepository;

    public RaceController(RaceService raceService, RaceRepository raceRepository) {
        this.raceService = raceService;
        this.raceRepository = raceRepository;
    }

    @GetMapping
public List<Race> getAll(@RequestParam(required = false) String location) {
    if (location != null && !location.isEmpty()) {
        // Si une localisation est fournie, on filtre
        return raceRepository.findAllByLocation(location);
    }
    // Sinon, on renvoie tout comme avant
    return raceService.getAll();
}

    
    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Race create(@RequestBody Race race){
        return raceService.create(race);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        raceService.delete(id);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Race update(@PathVariable Long id, @RequestBody Race race){
        return raceService.update(id, race);
    }

    
    

    

}
