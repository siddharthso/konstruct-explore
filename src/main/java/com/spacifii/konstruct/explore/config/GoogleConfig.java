package com.spacifii.konstruct.explore.config;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class GoogleConfig {



    @Bean
    public CredentialsProvider credentialsProvider() throws IOException {

        //return  GoogleCredentialsProvider.newBuilder().build().getCredentials();
        /*
        return new GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(serviceAccount)
                .setServiceAccountScopes(SCOPES)
                .setServiceAccountUser(serviceAccountUser)
                // variable p12File is a String w/ path to the .p12 file name
                .setServiceAccountPrivateKeyFromP12File(new java.io.File(p12File))
                .build();*/

        CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(new FileInputStream("loginapis-189310-2c3ff929e93d.json")));
        return credentialsProvider;
    }


    @Bean
    public ImageAnnotatorClient imageAnnotatorClient(CredentialsProvider credentialsProvider) throws IOException {
        ImageAnnotatorSettings clientSettings = ImageAnnotatorSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .build();

        return ImageAnnotatorClient.create(clientSettings);
    }
}
