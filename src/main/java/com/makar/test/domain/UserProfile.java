package com.makar.test.domain;

import javax.persistence.*;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @OneToOne(mappedBy = "userProfile")
    private UserAuth userAuth;

}
