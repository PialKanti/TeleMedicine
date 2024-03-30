package com.codecraftershub.telemedicine.services.user;

import com.codecraftershub.telemedicine.dtos.auth.ChangePasswordRequest;
import com.codecraftershub.telemedicine.dtos.auth.RegistrationRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.UserUpdateRequest;
import com.codecraftershub.telemedicine.dtos.responses.users.UserResponse;
import com.codecraftershub.telemedicine.entities.user.Role;
import com.codecraftershub.telemedicine.entities.user.User;
import com.codecraftershub.telemedicine.enums.UserRole;
import com.codecraftershub.telemedicine.exceptions.PasswordMismatchException;
import com.codecraftershub.telemedicine.repositories.user.RoleRepository;
import com.codecraftershub.telemedicine.repositories.user.UserRepository;
import com.codecraftershub.telemedicine.services.BaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "users")
public class UserService extends BaseService<User, Long, RegistrationRequest, UserUpdateRequest, UserResponse> implements UserDetailsService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Cacheable(key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username, UserDetails.class).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public <T> T findByUsername(String username, Class<T> type) {
        return repository.findByUsername(username, type).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @CacheEvict(key = "#username")
    public UserResponse update(String username, UserUpdateRequest request) throws EntityNotFoundException {
        User entity = repository.findByUsername(username, User.class).orElseThrow(EntityNotFoundException::new);
        User updatedEntity = convertToUpdateEntity(entity, request);

        return convertToEntityResponse(repository.save(updatedEntity));
    }

    @Override
    protected User convertToCreateEntity(RegistrationRequest request) {
        return User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNo(request.getPhoneNo())
                .roles(extractRoleEntities(request.getRoles()))
                .build();
    }

    private List<Role> extractRoleEntities(List<UserRole> enumRoles) {
        List<Role> roles = new ArrayList<>();

        for (UserRole enumRole : enumRoles) {
            Optional<Role> role = roleRepository.findByKey(enumRole.toString());
            role.ifPresent(roles::add);
        }

        return roles;
    }

    @Override
    protected User convertToUpdateEntity(User entity, UserUpdateRequest request) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setRoles(extractRoleEntities(request.getRoles()));

        return entity;
    }

    @Override
    protected UserResponse convertToEntityResponse(User entity) {
        return UserResponse
                .builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .build();
    }

    public void updatePassword(String username, ChangePasswordRequest request) throws PasswordMismatchException {
        User user = repository.findByUsername(username, User.class)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);
    }

    @Transactional
    public void deleteByUsername(String username) {
        if (!repository.existsByUsername(username)) {
            throw new EntityNotFoundException();
        }

        repository.deleteByUsername(username);
    }
}
