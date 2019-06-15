package com.pinwheelsandpearlsboutique.mailchimp.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Builder
@Slf4j
@Table(name = "users")
public class PWAPUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @NotEmpty
    private String emailAddress;

}
