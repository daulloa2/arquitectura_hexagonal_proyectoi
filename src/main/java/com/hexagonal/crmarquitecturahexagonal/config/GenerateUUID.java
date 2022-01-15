package com.hexagonal.crmarquitecturahexagonal.config;

import java.util.UUID;

public class GenerateUUID {
    
    public static String generateUUID(String uuid){
        return UUID.nameUUIDFromBytes(uuid.getBytes()).toString();
    }
}
