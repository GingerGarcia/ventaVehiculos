/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.actores;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 * @author User
 */
public class HashCode {
    // Se llama al método getInstance estático con hashing SHA, método digest () llamado para calcular el resumen del mensaje de una entrada y un array de bytes de retorno

    /**
     *
     * @param input
     * @return
     */
    public static byte[] getSHA(String input) {
        MessageDigest md =null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashCode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     *
     * @param hash
     * @return
     */
    public static String toHexString(byte[] hash) {
        // Convierte un array de bytes en representación de signum
        BigInteger number = new BigInteger(1, hash);
        // Convierte el mensaje en un valor hexadecimal
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');// coloca ceros a la izquierda
        }
        return hexString.toString(); //retorna el string en el formato toString() antes programado
    }
}
