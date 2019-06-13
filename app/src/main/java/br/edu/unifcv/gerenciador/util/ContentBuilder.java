package br.edu.unifcv.gerenciador.util;

import android.content.ContentValues;

import br.edu.unifcv.gerenciador.constants.DataBaseConstants;
import br.edu.unifcv.gerenciador.model.Convidado;

public class ContentBuilder {
    public static ContentValues getValues(Convidado convidado){
        ContentValues content= new ContentValues();
        content.put(DataBaseConstants.CONVIDADO.COLUMNS.ID, convidado.getId());
        content.put(DataBaseConstants.CONVIDADO.COLUMNS.NOME, convidado.getNome());
        content.put(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA, convidado.getPresenca());
        return  content;
    }
}
