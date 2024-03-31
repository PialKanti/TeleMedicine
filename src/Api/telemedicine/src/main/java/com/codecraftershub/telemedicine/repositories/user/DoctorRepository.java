package com.codecraftershub.telemedicine.repositories.user;

import com.codecraftershub.telemedicine.entities.user.Doctor;
import com.codecraftershub.telemedicine.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends BaseRepository<Doctor, Long> {
}
