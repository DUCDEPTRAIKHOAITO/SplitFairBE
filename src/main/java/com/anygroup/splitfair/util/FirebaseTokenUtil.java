package com.anygroup.splitfair.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

public class FirebaseTokenUtil {

    public static FirebaseToken verify(String token) {
        try {
            return FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (Exception e) {
            throw new RuntimeException("Invalid Firebase ID Token", e);
        }
    }
}
