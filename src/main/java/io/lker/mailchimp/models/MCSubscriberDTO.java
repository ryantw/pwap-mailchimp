package io.lker.mailchimp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MCSubscriberDTO {

    private String firstName;
    private String lastName;
    private String mcStatus;
    private boolean mcSuccess;
    private String emailAddress;

}
