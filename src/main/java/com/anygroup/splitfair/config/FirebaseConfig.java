package com.anygroup.splitfair.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            InputStream serviceAccount;

            String firebasePath = System.getenv("FIREBASE_CREDENTIAL_PATH");

            if (firebasePath != null && !firebasePath.isEmpty()) {
                // Cloud Run / Prod
                serviceAccount = new FileInputStream(firebasePath);
            } else {
                // Local
                serviceAccount = getClass()
                        .getClassLoader()
                        .getResourceAsStream("firebase/firebase-service-account.json");
            }

            if (serviceAccount == null) {
                throw new RuntimeException("Firebase credential not found");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

        } catch (Exception e) {
            throw new RuntimeException("Firebase init failed", e);
        }
    }


}
