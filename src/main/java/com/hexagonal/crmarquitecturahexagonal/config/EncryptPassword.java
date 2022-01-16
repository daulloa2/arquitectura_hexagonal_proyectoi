package com.hexagonal.crmarquitecturahexagonal.config;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptPassword {

    // public  String encriptar(String datos, String claveSecreta) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
    //     SecretKeySpec secretKey = crearClave(claveSecreta);
         
    //     Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");        
    //     cipher.init(Cipher.ENCRYPT_MODE, secretKey);
 
    //     byte[] datosEncriptar = datos.getBytes("UTF-8");
    //     byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
    //     String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);
 
    //     return encriptado;
    // }

    public static SecretKeySpec crearClave(String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] claveEncriptacion = clave.getBytes("UTF-8");
         
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
         
        claveEncriptacion = sha.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);
         
        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");
 
        return secretKey;
    }
}
