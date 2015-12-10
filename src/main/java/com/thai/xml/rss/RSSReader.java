package com.thai.xml.rss;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class RSSReader {

    public static final String TEST_URI = "http://www.feedforall.com/sample.xml";
    public static final String CATEGORY_URI = "www.dmoz.com";

    public static InputStream read() throws ClientProtocolException, IOException {
        // Ouverture d'un client
        HttpClient httpClient = HttpClientBuilder.create().build();

        // Création d'une méthode HTTP
        HttpGet httpGet = new HttpGet(TEST_URI);
        HttpResponse response;
        response = httpClient.execute(httpGet);
        StatusLine statusLine = response.getStatusLine();

        // Invocation de la méthode
        int statusCode = statusLine.getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("Method failed : " + response.getStatusLine());
            return null;
        }

        // Lecture de la réponse dans un flux
        InputStream is = response.getEntity().getContent();
        return is;
    }

}
