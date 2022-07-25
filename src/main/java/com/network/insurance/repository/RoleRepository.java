package com.network.insurance.repository;

import com.network.insurance.model.Role;
import com.network.insurance.model.UserRoles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(UserRoles name);
}
