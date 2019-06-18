package io.lker.mailchimp.api.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;

@Slf4j
public class Connection {

    public static String doPost(String postUrl, String body, String authorization) throws IOException {
        StringBuilder response = new StringBuilder();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(postUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Authorization",
                    "Basic " + Base64.getEncoder().encodeToString(authorization.getBytes()));
            urlConnection.setDoOutput(true);

            DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
            writer.writeBytes(body);
            writer.flush();
            writer.close();

            int responseCode = urlConnection.getResponseCode();
            if(responseCode < 200 || responseCode > 299){
                throw new IOException("Response code not right: " + responseCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()
            ));

            String inputLine;
            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            in.close();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }

        return response.toString();
    }

}
