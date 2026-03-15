package com.takima.race.registration.services;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RunnerRepository runnerRepository;
    private final RaceRepository raceRepository;

    public RegistrationService(RegistrationRepository registrationRepository,
            RunnerRepository runnerRepository,
            RaceRepository raceRepository) {
        this.registrationRepository = registrationRepository;
        this.runnerRepository = runnerRepository;
        this.raceRepository = raceRepository;
    }

    public Registration register(Long runnerId, Long raceId) {

        Runner runner = runnerRepository.findById(runnerId)
                .orElseThrow(() -> new RuntimeException("Runner non trouvé"));
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new RuntimeException("Race non trouvée"));

        long currentParticipants = registrationRepository.countByRaceId(raceId);
        if (currentParticipants >= race.getMaxParticipants()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La course est complète (max: " + race.getMaxParticipants() + " participants)");
        }
        if (registrationRepository.existsByRunnerIdAndRaceId(runnerId, raceId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ce coureur est déjà inscrit à cette course");
        }

        Registration registration = new Registration();
        registration.setRunner(runner);
        registration.setRace(race);
        registration.setRegistrationDate(LocalDate.now());

        return registrationRepository.save(registration);
    }

}
