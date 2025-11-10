package com.example.kshitiz.server.entity;

import com.example.kshitiz.server.dto.AccountType;
import com.example.kshitiz.server.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Column(unique=true)
    @NotBlank(message = "Email can't be null or blank")
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Password is blank or null")
    @Size(min = 6)
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Account Type cannot be null or empty")
    private AccountType accountType;

    public User() {}

    public User(long id, String name, String email, String password, AccountType accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public UserDTO toDTO(){
        return new UserDTO(this.id,this.name,this.email,this.password,this.accountType,null);
    }
}
