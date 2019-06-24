package io.lker.mailchimp.converters;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MCResponseToSubscriberTest {
    String json = "\n" +
            "\n" +
            "{\n" +
            "  \"id\": \"e22feeab4a81052f54a09b6bb871b73c\",\n" +
            "  \"email_address\": \"urist.mcvankab+21@freddiesjokes.com\",\n" +
            "  \"unique_email_id\": \"56aa75eddb\",\n" +
            "  \"web_id\": 341532001,\n" +
            "  \"email_type\": \"html\",\n" +
            "  \"status\": \"subscribed\",\n" +
            "  \"merge_fields\": {\n" +
            "    \"FNAME\": \"\",\n" +
            "    \"LNAME\": \"\",\n" +
            "    \"ADDRESS\": \"\",\n" +
            "    \"PHONE\": \"\",\n" +
            "    \"BIRTHDAY\": \"\"\n" +
            "  },\n" +
            "  \"stats\": {\n" +
            "    \"avg_open_rate\": 0,\n" +
            "    \"avg_click_rate\": 0\n" +
            "  },\n" +
            "  \"ip_signup\": \"\",\n" +
            "  \"timestamp_signup\": \"\",\n" +
            "  \"ip_opt\": \"68.47.208.48\",\n" +
            "  \"timestamp_opt\": \"2019-06-19T00:56:52+00:00\",\n" +
            "  \"member_rating\": 2,\n" +
            "  \"last_changed\": \"2019-06-19T00:56:52+00:00\",\n" +
            "  \"language\": \"\",\n" +
            "  \"vip\": false,\n" +
            "  \"email_client\": \"\",\n" +
            "  \"location\": {\n" +
            "    \"latitude\": 0,\n" +
            "    \"longitude\": 0,\n" +
            "    \"gmtoff\": 0,\n" +
            "    \"dstoff\": 0,\n" +
            "    \"country_code\": \"\",\n" +
            "    \"timezone\": \"\"\n" +
            "  },\n" +
            "  \"source\": \"API - Generic\",\n" +
            "  \"tags_count\": 0,\n" +
            "  \"tags\": [\n" +
            "    \n" +
            "  ],\n" +
            "  \"list_id\": \"9ac5e96108\",\n" +
            "  \"_links\": [\n" +
            "    {\n" +
            "      \"rel\": \"self\",\n" +
            "      \"href\": \"https:\\/\\/us3.api.mailchimp.com\\/3.0\\/lists\\/9ac5e96108\\/members\\/e22feeab4a81052f54a09b6bb871b73c\",\n" +
            "      \"method\": \"GET\",\n" +
            "      \"targetSchema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/Definitions\\/Lists\\/Members\\/Response.json\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"parent\",\n" +
            "      \"href\": \"https:\\/\\/us3.api.mailchimp.com\\/3.0\\/lists\\/9ac5e96108\\/members\",\n" +
            "      \"method\": \"GET\",\n" +
            "      \"targetSchema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/Definitions\\/Lists\\/Members\\/CollectionResponse.json\",\n" +
            "      \"schema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/CollectionLinks\\/Lists\\/Members.json\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"update\",\n" +
            "      \"href\": \"https:\\/\\/us3.api.mailchimp.com\\/3.0\\/lists\\/9ac5e96108\\/members\\/e22feeab4a81052f54a09b6bb871b73c\",\n" +
            "      \"method\": \"PATCH\",\n" +
            "      \"targetSchema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/Definitions\\/Lists\\/Members\\/Response.json\",\n" +
            "      \"schema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/Definitions\\/Lists\\/Members\\/PATCH.json\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"upsert\",\n" +
            "      \"href\": \"https:\\/\\/us3.api.mailchimp.com\\/3.0\\/lists\\/9ac5e96108\\/members\\/e22feeab4a81052f54a09b6bb871b73c\",\n" +
            "      \"method\": \"PUT\",\n" +
            "      \"targetSchema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/Definitions\\/Lists\\/Members\\/Response.json\",\n" +
            "      \"schema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/Definitions\\/Lists\\/Members\\/PUT.json\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"delete\",\n" +
            "      \"href\": \"https:\\/\\/us3.api.mailchimp.com\\/3.0\\/lists\\/9ac5e96108\\/members\\/e22feeab4a81052f54a09b6bb871b73c\",\n" +
            "      \"method\": \"DELETE\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"activity\",\n" +
            "      \"href\": \"https:\\/\\/us3.api.mailchimp.com\\/3.0\\/lists\\/9ac5e96108\\/members\\/e22feeab4a81052f54a09b6bb871b73c\\/activity\",\n" +
            "      \"method\": \"GET\",\n" +
            "      \"targetSchema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/Definitions\\/Lists\\/Members\\/Activity\\/Response.json\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"goals\",\n" +
            "      \"href\": \"https:\\/\\/us3.api.mailchimp.com\\/3.0\\/lists\\/9ac5e96108\\/members\\/e22feeab4a81052f54a09b6bb871b73c\\/goals\",\n" +
            "      \"method\": \"GET\",\n" +
            "      \"targetSchema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/Definitions\\/Lists\\/Members\\/Goals\\/Response.json\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"notes\",\n" +
            "      \"href\": \"https:\\/\\/us3.api.mailchimp.com\\/3.0\\/lists\\/9ac5e96108\\/members\\/e22feeab4a81052f54a09b6bb871b73c\\/notes\",\n" +
            "      \"method\": \"GET\",\n" +
            "      \"targetSchema\": \"https:\\/\\/us3.api.mailchimp.com\\/schema\\/3.0\\/Definitions\\/Lists\\/Members\\/Notes\\/CollectionResponse.json\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"delete_permanent\",\n" +
            "      \"href\": \"https:\\/\\/us3.api.mailchimp.com\\/3.0\\/lists\\/9ac5e96108\\/members\\/e22feeab4a81052f54a09b6bb871b73c\\/actions\\/delete-permanent\",\n" +
            "      \"method\": \"POST\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "\n";

    @BeforeEach
    void setUp() {
    }

    @Test
    void mcResponseToSubscriber() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        LinkedTreeMap<String, Object> response = gsonBuilder.create().fromJson(json, LinkedTreeMap.class);
        response.entrySet().stream().forEach(e -> {
            if(e.getKey().equals("id") && !e.getKey().isEmpty()) {
                Object value = e.getValue();
                assertEquals(value, "e22feeab4a81052f54a09b6bb871b73c");
            }
        });

    }
}