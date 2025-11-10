package com.example.kshitiz.server.dto;

import com.example.kshitiz.server.entity.User;

public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String password;
    private AccountType accountType;
    private String jwtToken;

    public UserDTO() {}

    public UserDTO(long id, String name, String email, String password, AccountType accountType, String jwtToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.jwtToken = jwtToken;
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
    public String getJwtToken() { return jwtToken; }
    public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }

    public User toEntity() {
        return new User(this.id, this.name, this.email, this.password, this.accountType);
    }
}
