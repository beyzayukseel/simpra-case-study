package com.beyzanur.simpracasestudy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    @OneToOne(fetch = FetchType.EAGER)
    private Role role;
    private String created;
    private String updated;
}
