package com.takima.race.runner.services;

import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public List<Runner> getAll() {
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {
        return runnerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", id)
                )
        );
    }
    public Runner create (Runner runner){
        if (runner.getEmail() == null || !runner.getEmail().contains("@")) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "L'adresse email doit contenir un @"
            );
        }
        String.format("201 Created");
        return runnerRepository.save(runner);
    }
    
    public void delete (Long id){
        runnerRepository.deleteById(id);
    }

    public Runner update (Long id, Runner runner){
        Runner existingRunner = getById(id);
        existingRunner.setFirstName(runner.getFirstName());
        existingRunner.setLastName(runner.getLastName());
        existingRunner.setEmail(runner.getEmail());
        existingRunner.setAge(runner.getAge());
        return runnerRepository.save(existingRunner);
    }

}
