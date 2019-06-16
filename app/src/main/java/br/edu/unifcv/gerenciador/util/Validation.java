package br.edu.unifcv.gerenciador.util;

public class Validation {
    public static Boolean validateString(String input){
        if(input == null) return false;
        return input.isEmpty();
    }
}
