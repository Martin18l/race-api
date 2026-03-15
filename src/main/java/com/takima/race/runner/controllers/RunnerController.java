package com.takima.race.runner.controllers;

import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.registration.entities.Registration;
import com.takima.race.runner.services.RunnerService;

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

import java.util.List;

@RestController
@RequestMapping("/runners")
public class RunnerController {
    private final RunnerService runnerService;
    private final RegistrationRepository registrationRepository;
    

    public RunnerController(RunnerService runnerService, RegistrationRepository registrationRepository) {
        this.runnerService = runnerService;
        this.registrationRepository = registrationRepository;
    }

    @GetMapping
    public List<Runner> getAll() {
        return runnerService.getAll();
    }

    @GetMapping("/{id}")
    public Runner getById(@PathVariable Long id) {
        return runnerService.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Runner create(@RequestBody Runner runner){
        return runnerService.create(runner);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        runnerService.delete(id);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Runner update(@PathVariable Long id, @RequestBody Runner runner){
        return runnerService.update(id, runner);
    }

    @GetMapping("/{runnerId}/races")
    public List<Registration> getRacesByRunner(@PathVariable Long runnerId) {
        
        return registrationRepository.findAllByRunnerId(runnerId);
    }
}
