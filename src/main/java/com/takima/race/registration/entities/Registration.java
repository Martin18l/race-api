package com.takima.race.registration.entities;

import com.takima.race.runner.entities.Runner;
import com.takima.race.race.entities.Race;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "runner_id", nullable = false)
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    // Constructeurs
    public Registration() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Runner getRunner() { return runner; }
    public void setRunner(Runner runner) { this.runner = runner; }

    public Race getRace() { return race; }
    public void setRace(Race race) { this.race = race; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}