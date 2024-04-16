package com.codecraftershub.telemedicine.repositories.user;

import com.codecraftershub.telemedicine.entities.user.doctor.Doctor;
import com.codecraftershub.telemedicine.entities.user.doctor.Speciality;
import com.codecraftershub.telemedicine.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends BaseRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE d.isApproved = true AND d.user.isActive = true")
    <T> Page<T> findAllActiveAndApprovedDoctors(Pageable pageable, Class<T> type);

    @EntityGraph(value = "DoctorEntityGraph")
    Page<Doctor> findAll(Specification<Doctor> specification, Pageable pageable);

    @EntityGraph(value = "DoctorEntityGraph")
    Page<Doctor> findAllBySpecialitiesAndIsApprovedTrue(List<Speciality> specialities, Pageable pageable);
}
