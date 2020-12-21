package com.foyhWebAppApi.Account.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {
    UserDAO findByUsername(String username);

    ArrayList<UserDAO> findAll();

    @Override
    void delete(UserDAO userDAO);
}
