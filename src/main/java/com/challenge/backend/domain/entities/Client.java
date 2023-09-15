package com.challenge.backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;

    private String password;

    private Boolean status;

    //@OneToMany(cascade = CascadeType.ALL)
    @OneToMany
    private List<Account> accounts = new ArrayList<>();
}
