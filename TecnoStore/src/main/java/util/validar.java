package util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Janus
 */
import java.util.regex.Pattern;

public class validador {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean validarCorreo(String correo) {
        return Pattern.compile(EMAIL_REGEX).matcher(correo).matches();
    }

    public static boolean esPositivo(double valor) {
        return valor > 0;
    }

    public static boolean esPositivo(int valor) {
        return valor > 0;
    }
}
