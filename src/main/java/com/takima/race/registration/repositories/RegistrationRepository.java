package com.takima.race.registration.repositories;

import com.takima.race.registration.entities.Registration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    
    //possibilite creer methode dans repo
    // Runner findByFirstName(String firstName);
    long countByRaceId(Long raceId);

    List<Registration> findAllByRaceId(Long raceId);
    List<Registration> findAllByRunnerId(Long runnerId);

    boolean existsByRunnerIdAndRaceId(Long runnerId, Long raceId);
}