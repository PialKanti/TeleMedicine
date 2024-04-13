package com.codecraftershub.telemedicine.repositories.user;

import com.codecraftershub.telemedicine.entities.user.doctor.Speciality;
import com.codecraftershub.telemedicine.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends BaseRepository<Speciality, Long> {
    @Query("select s from Speciality s where s.isActive = true")
    <T> Page<T> findAllActive(Pageable pageable, Class<T> type);
}
