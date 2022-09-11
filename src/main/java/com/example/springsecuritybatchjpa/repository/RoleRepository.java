package com.example.springsecuritybatchjpa.repository;

import java.util.Optional;

import com.example.springsecuritybatchjpa.models.ERole;
import com.example.springsecuritybatchjpa.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
