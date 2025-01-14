package com.kamel.springjwt.repository;

import java.util.Optional;

import com.kamel.springjwt.models.ERole;
import com.kamel.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
