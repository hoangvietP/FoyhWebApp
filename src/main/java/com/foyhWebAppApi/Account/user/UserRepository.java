package com.foyhWebAppApi.Account.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {
    UserDAO findByUsername(String username);
}
