package com.codecraftershub.telemedicine.repositories.user;

import com.codecraftershub.telemedicine.entities.user.User;
import com.codecraftershub.telemedicine.repositories.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNo(String phoneNo);

    @EntityGraph(value = "User.roles", type = EntityGraph.EntityGraphType.FETCH)
    <T> Optional<T> findByUsername(String username, Class<T> type);

    @Modifying
    @Query("delete from User u where u.username = :username")
    void deleteByUsername(String username);
}
