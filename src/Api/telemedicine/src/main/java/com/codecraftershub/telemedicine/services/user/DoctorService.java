package com.codecraftershub.telemedicine.services.user;

import com.codecraftershub.telemedicine.dtos.auth.DoctorRegistrationRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.DoctorUpdateRequest;
import com.codecraftershub.telemedicine.dtos.responses.users.UserResponse;
import com.codecraftershub.telemedicine.entities.user.Doctor;
import com.codecraftershub.telemedicine.entities.user.User;
import com.codecraftershub.telemedicine.repositories.user.DoctorRepository;
import com.codecraftershub.telemedicine.repositories.user.UserRepository;
import com.codecraftershub.telemedicine.services.BaseService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class DoctorService extends BaseService<Doctor, Long, DoctorRegistrationRequest, DoctorUpdateRequest, UserResponse> {
    private final UserRepository userRepository;
    public DoctorService(DoctorRepository repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    @Override
    protected Doctor convertToCreateEntity(DoctorRegistrationRequest request) {
        User user = userRepository.findByUsername(request.getUsername(), User.class).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return Doctor
                .builder()
                .title(request.getTitle().toString())
                .speciality(request.getSpeciality().toString())
                .registrationNumber(request.getRegistrationNumber())
                .nidNumber(request.getNidNumber())
                .fee(request.getFee())
                .user(user)
                .build();
    }

    @Override
    protected Doctor convertToUpdateEntity(Doctor entity, DoctorUpdateRequest doctorUpdateRequest) {
        return Doctor.builder().build();
    }

    @Override
    protected UserResponse convertToEntityResponse(Doctor entity) {
        return UserResponse.builder().build();
    }
}
