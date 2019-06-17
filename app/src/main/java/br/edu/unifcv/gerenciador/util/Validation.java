package br.edu.unifcv.gerenciador.util;

import br.edu.unifcv.gerenciador.model.Convidado;

public class Validation {
    private static Boolean validateString(String input){
        if(input == null) return false;
        return input.isEmpty();
    }
    private static void ValidateUser (Convidado convidado) throws Exception{
        if(!validateString(convidado.getNome())){
            throw new Exception("Nome deve ser informado");
        }
    }
}
