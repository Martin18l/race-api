package com.takima.race.registration.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.registration.services.RegistrationService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/races/{raceId}/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationService registrationService,
            RegistrationRepository registrationRepository) {
        this.registrationService = registrationService;
        this.registrationRepository = registrationRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Registration create(@PathVariable Long raceId, @RequestBody Map<String, Long> ids) {
        Long runnerId = ids.get("runnerId");
        
        if (runnerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le champ runnerId est obligatoire dans le JSON");
        }
        return registrationService.register(runnerId, raceId);
    }
    @GetMapping
    public List<Registration> getAllByRace(@PathVariable Long raceId) {
        return registrationRepository.findAllByRaceId(raceId);
    }

    @GetMapping("/count")
    public Map<String, Long> countParticipants(@PathVariable Long raceId) {
        long total = registrationRepository.countByRaceId(raceId);
        return Collections.singletonMap("count", total);
    }


}
