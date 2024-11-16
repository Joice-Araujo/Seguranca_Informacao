package com.seguranca_info.demo.helpers;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criptografia {
    private static String algoritmo = "RSA";
    private static Integer keySize = 1024;

    public static KeyPair gerarChave() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algoritmo);
            keyGen.initialize(keySize);

            KeyPair key = keyGen.genKeyPair();

            return key;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static byte[] criptografar(String textSemCriptografia, PublicKey chave) {
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

    public static String descriptografar(byte[] textoEncriptado, PrivateKey chave) {
        byte[] dectypedText = null;

        try {
            Cipher cipher = Cipher.getInstance(algoritmo);
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectypedText = cipher.doFinal(textoEncriptado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(dectypedText);
    }
}
