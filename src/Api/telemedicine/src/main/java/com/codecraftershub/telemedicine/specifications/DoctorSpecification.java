package com.codecraftershub.telemedicine.specifications;

import org.springframework.data.jpa.domain.Specification;

public class DoctorSpecification {
    public static <T> Specification<T> isActive(boolean isActive, Class<T> type) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user").get("isActive"), isActive);
    }

    public static <T> Specification<T> isApproved(boolean isApproved, Class<T> type) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isApproved"), isApproved);
    }
}
