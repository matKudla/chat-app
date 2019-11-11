package com.kudla.chatappbackend.user.repository;

import com.kudla.chatappbackend.user.model.Entity.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    public AppUser getUserByUsername(String username);
}
