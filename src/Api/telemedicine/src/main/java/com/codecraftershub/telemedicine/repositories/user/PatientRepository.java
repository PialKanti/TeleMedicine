package com.codecraftershub.telemedicine.repositories.user;

import com.codecraftershub.telemedicine.entities.user.Patient;
import com.codecraftershub.telemedicine.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends BaseRepository<Patient, Long> {
}
