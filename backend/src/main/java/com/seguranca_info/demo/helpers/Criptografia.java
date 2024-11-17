package com.seguranca_info.demo.helpers;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class Criptografia {
    private static String algoritmo = "RSA";
    private static Integer keySize = 1024;

    public KeyPair gerarChave() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algoritmo);
            keyGen.initialize(keySize);

            KeyPair key = keyGen.genKeyPair();

            return key;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public Integer getKeySize() {
        return keySize;
    }

    public byte[] criptografar(String textSemCriptografia, PublicKey chave) {
        byte[] cipherText = null;

        try {
            Cipher cipher = Cipher.getInstance(algoritmo);

            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(textSemCriptografia.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cipherText;
    }

    public String descriptografar(byte[] textoEncriptado, PrivateKey chave, String algoritmo) {
        byte[] dectypedText = null;

        try {
            Cipher cipher = Cipher.getInstance(algoritmo);
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectypedText = cipher.doFinal(textoEncriptado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(dectypedText, StandardCharsets.UTF_8);
    }

    public static PrivateKey Base64ToPrivateKey(String privateKeyString) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algoritmo);
        return keyFactory.generatePrivate(privateKeySpec);
    }
}
