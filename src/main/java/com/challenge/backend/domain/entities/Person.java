package com.challenge.backend.domain.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {
    private String name;

    private String gender;

    private Integer age;

    private String dni;

    private String address;

    private String phoneNumber;
}
