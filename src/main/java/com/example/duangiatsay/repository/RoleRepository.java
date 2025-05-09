package com.example.duangiatsay.repository;

import com.example.duangiatsay.model.Role;
import com.example.duangiatsay.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
