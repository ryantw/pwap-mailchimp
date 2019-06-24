package io.lker.mailchimp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@Slf4j
@Table(name = "users")
public class MCSubscriber implements Serializable {

    public MCSubscriber(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mail_chimp_id")
    private String mcId;

    @Column(name = "unique_email_id")
    private String uniqueEmailId;

    @Column(name = "web_id")
    private String webId;

    @Column(name = "status")
    private String mcStatus;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mailchimp_list")
    private String mcList;

    @Column(name = "mailchimp_success")
    private boolean mcSuccess;

    @JsonProperty("email_address")
    @Column(name = "emailaddress", unique = true)
    private String emailAddress;

}
