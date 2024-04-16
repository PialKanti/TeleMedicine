package com.codecraftershub.telemedicine.specifications;

import com.codecraftershub.telemedicine.entities.user.doctor.Doctor;
import org.springframework.data.jpa.domain.Specification;

public class DoctorSpecification {
    public static Specification<Doctor> isActive(boolean isActive) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user").get("isActive"), isActive);
    }

    public static Specification<Doctor> isApproved(boolean isApproved) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isApproved"), isApproved);
    }
}
