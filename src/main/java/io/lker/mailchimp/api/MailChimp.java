package io.lker.mailchimp.api;

import com.google.gson.Gson;
import io.lker.mailchimp.api.models.SubscriberResponse;
import io.lker.mailchimp.api.util.Connection;
import io.lker.mailchimp.exceptions.MCHttpBadResponse;
import io.lker.mailchimp.models.Submitter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailChimp {
    private final String API_KEY;
    private final String API_ENDPOINT;

    public MailChimp(String API_KEY) {
        this.API_KEY = API_KEY;
        this.API_ENDPOINT = setEndPoint(this.API_KEY);

    }

    private String setEndPoint(String apiKey){
        if(apiKey.contains("-")){
            String server = apiKey.split("-")[1];
            return "https://" + server + ".api.mailchimp.com/3.0";
        } else {
            log.error("Mailchimp API_KEY Not formatted right, must include '-'.");
        }

        return null;
    }

    public String subscribeUserToList(String listId, Submitter subscriber) throws MCHttpBadResponse {
        final String LIST_ENDPOINT = API_ENDPOINT + "/lists/" + listId + "/members";
        Gson gson = new Gson();
        SubscriberResponse s = SubscriberResponse.builder()
                .email_address(subscriber.getEmailAddress())
                .status("subscribed")
                .build();
        StringBuilder response = new StringBuilder(
                Connection.doPost(LIST_ENDPOINT, gson.toJson(s), this.API_KEY));

        // Response
        log.info("Response: " + response.toString());

        return response.toString();
    }
}
