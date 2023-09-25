package com.example.StudentRecordManagementSystem.repository;

import com.example.StudentRecordManagementSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName (String name);
}
