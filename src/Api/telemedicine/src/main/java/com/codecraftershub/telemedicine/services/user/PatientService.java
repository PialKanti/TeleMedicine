package com.codecraftershub.telemedicine.services.user;

import com.codecraftershub.telemedicine.dtos.auth.PatientRegistrationRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.PatientUpdateRequest;
import com.codecraftershub.telemedicine.dtos.responses.users.UserResponse;
import com.codecraftershub.telemedicine.entities.user.Patient;
import com.codecraftershub.telemedicine.entities.user.User;
import com.codecraftershub.telemedicine.repositories.user.PatientRepository;
import com.codecraftershub.telemedicine.repositories.user.UserRepository;
import com.codecraftershub.telemedicine.services.BaseService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends BaseService<Patient, Long, PatientRegistrationRequest, PatientUpdateRequest, UserResponse> {
    private final UserRepository userRepository;

    public PatientService(PatientRepository repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    @Override
    protected Patient convertToCreateEntity(PatientRegistrationRequest request) {
        User user = userRepository.findByUsername(request.getUsername(), User.class).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return Patient
                .builder()
                .dateOfBirth(request.getDateOfBirth())
                .bloodGroup(request.getBloodGroup().toString())
                .gender(request.getGender().toString())
                .address(request.getAddress())
                .user(user)
                .build();
    }

    @Override
    protected Patient convertToUpdateEntity(Patient entity, PatientUpdateRequest patientUpdateRequest) {
        return Patient.builder().build();
    }

    @Override
    protected UserResponse convertToEntityResponse(Patient entity) {
        return UserResponse.builder().build();
    }
}
