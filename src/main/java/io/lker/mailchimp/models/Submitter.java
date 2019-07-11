package io.lker.mailchimp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@Slf4j
@Table(name = "submissions")
public class Submitter implements Serializable {

    public Submitter(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mailchimp_list")
    private String mcList;

    @JsonProperty("email_address")
    @Column(name = "emailaddress", unique = true)
    private String emailAddress;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_attempt")
    private LocalDateTime lastAttempt;

    @Column(name = "attempted_tries")
    private int attemptedTries;

    @Column(name = "mc_success")
    private boolean mcSuccess = false;
}
