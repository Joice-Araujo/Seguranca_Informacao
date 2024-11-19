package com.seguranca_info.demo.helpers;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class Criptografia {
    private static String algoritmo = "AES";

    public SecretKey gerarChave() {
        try {
            final KeyGenerator keyGen = KeyGenerator.getInstance(algoritmo);
            SecretKey key = keyGen.generateKey();
            return key;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public byte[] criptografar(String textSemCriptografia, SecretKey chave) {
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

    public String descriptografar(byte[] textoEncriptado, SecretKey chave, String algoritmo) {
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

    public static SecretKey BytesToPrivateKey(byte[] privateKey) throws Exception {
        SecretKey secretKey = new SecretKeySpec(privateKey, algoritmo);
        return secretKey;
    }
}
