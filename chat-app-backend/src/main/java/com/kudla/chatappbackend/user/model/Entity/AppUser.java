package com.kudla.chatappbackend.user.model.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@EqualsAndHashCode
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private boolean accountNonExpired;
    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private String passwordEncrypted;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passwordEncrypted;
    }


}
