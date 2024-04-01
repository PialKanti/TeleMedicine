package com.codecraftershub.telemedicine.configs;

import com.codecraftershub.telemedicine.entities.user.Permission;
import com.codecraftershub.telemedicine.entities.user.Role;
import com.codecraftershub.telemedicine.entities.user.User;
import com.codecraftershub.telemedicine.enums.UserPermission;
import com.codecraftershub.telemedicine.enums.UserRole;
import com.codecraftershub.telemedicine.repositories.user.PermissionRepository;
import com.codecraftershub.telemedicine.repositories.user.RoleRepository;
import com.codecraftershub.telemedicine.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DbValueInitializer implements ApplicationRunner {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private Map<String, List<Permission>> permissionMap;
    private Map<String, Role> roleMap;

    @Override
    public void run(ApplicationArguments args) {
        permissionMap = new HashMap<>();
        roleMap = new HashMap<>();
        insertPermissionToDB();
        insertUserRolesToDB();
        createAdminUser();
    }

    private void insertPermissionToDB() {
        for (UserPermission permission : UserPermission.values()) {
            var createdEntity = permissionRepository.save(Permission
                    .builder()
                    .key(permission.toString())
                    .name(permission.getDisplayValue())
                    .build());

            String key = createdEntity.getKey().split("_")[0];

            List<Permission> permissions;
            if (!permissionMap.containsKey(key)) {
                permissions = new ArrayList<>();
            } else {
                permissions = permissionMap.get(key);
            }
            permissions.add(createdEntity);
            permissionMap.put(key, permissions);
        }
    }

    private void insertUserRolesToDB() {
        for (UserRole userRole : UserRole.values()) {
            var createdEntity = roleRepository.save(Role
                    .builder()
                    .key(userRole.toString())
                    .name(userRole.getDisplayValue())
                    .permissions(permissionMap.get(userRole.toString()))
                    .build());

            roleMap.put(userRole.toString(), createdEntity);
        }
    }

    private void createAdminUser() {
        User user = User
                .builder()
                .firstName("Admin")
                .username("admin")
                .password("admin")
                .email("admin@gmail.com")
                .phoneNo("123456789")
                .isActive(true)
                .roles(Collections.singletonList(roleMap.get(UserRole.ADMIN.toString())))
                .build();

        userRepository.save(user);
    }
}
