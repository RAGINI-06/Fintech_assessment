package com.ragini.financebackend.repository;

import com.ragini.financebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}