package io.lker.mailchimp.converters;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import io.lker.mailchimp.models.MCSubscriber;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MCResponseToSubscriber implements Converter<String, MCSubscriber> {

    @Synchronized
    @Override
    public MCSubscriber convert(String mcResponse){
        if(mcResponse == null)
            return null;

        GsonBuilder builder = new GsonBuilder();
        MCSubscriber converted = new MCSubscriber();
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
