package io.lker.mailchimp.converters;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import io.lker.mailchimp.models.Subscriber;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubmitterToSubscriber implements Converter<String, Subscriber> {

    @Synchronized
    @Override
    public Subscriber convert(String mcResponse){
        if(mcResponse == null)
            return null;

        GsonBuilder builder = new GsonBuilder();
        Subscriber converted = new Subscriber();
        LinkedTreeMap<String, Object> response = builder.create().fromJson(mcResponse, LinkedTreeMap.class);
        response.entrySet().stream().forEach(e ->{
            if(e.getValue() != null) {
                if (e.getKey().equals("id"))
                    converted.setMcId(e.getValue().toString());
                else if(e.getKey().equals("unique_email_id"))
                    converted.setUniqueEmailId(e.getValue().toString());
                else if(e.getKey().equals("web_id"))
                    converted.setWebId(e.getValue().toString());
                else if(e.getKey().equals("status"))
                    converted.setMcStatus(e.getValue().toString());
                else if(e.getKey().equals("email_address"))
                    converted.setEmailAddress(e.getValue().toString());
                else if(e.getKey().equals("list_id"))
                    converted.setMcList(e.getValue().toString());
            }
        });

        return converted;
    }

}
