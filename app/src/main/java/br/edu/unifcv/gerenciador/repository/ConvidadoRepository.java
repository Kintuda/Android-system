package br.edu.unifcv.gerenciador.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.internal.Experimental;

import java.util.ArrayList;
import java.util.List;

import br.edu.unifcv.gerenciador.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.constants.DataBaseConstants;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.model.ConvidadosCount;
import br.edu.unifcv.gerenciador.repository.base.DataBaseHelper;
import br.edu.unifcv.gerenciador.util.ContentBuilder;

public class ConvidadoRepository {

    private static ConvidadoRepository INSTANCE;
    private DataBaseHelper dataBaseHelper;

    private ConvidadoRepository(Context context) {
        this.dataBaseHelper = new DataBaseHelper(context);
    }

    public static synchronized ConvidadoRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ConvidadoRepository(context);
        }
        return INSTANCE;
    }

    public Boolean update(Convidado convidado){
        try {
            SQLiteDatabase db = this.dataBaseHelper.getWritableDatabase();
            ContentBuilder contentBuilder = new ContentBuilder();
            ContentValues values = ContentBuilder.getValues(convidado);
//            String select = "id=?";
//            String[] args  (String) = { String(convidado.getId()) };
            db.update(DataBaseConstants.CONVIDADO.TABLE_NAME, values, "id="+ convidado.getId(), null);
            return  true;
        }catch (Exception e){
            return false;
        }
    }

    public void delete(Integer id){
        try{
            SQLiteDatabase db = this.dataBaseHelper.getWritableDatabase();
            db.delete(DataBaseConstants.CONVIDADO.TABLE_NAME, " id=" + id, null);
        }catch (Exception e){

        }
    }

    public Convidado findById(Integer id){
        try{
            SQLiteDatabase db = this.dataBaseHelper.getReadableDatabase();
            String sql = "SELECT * FROM " + DataBaseConstants.CONVIDADO.TABLE_NAME + " WHERE id = " +  id;
            Cursor cursor = db.rawQuery(sql, null);

            if(cursor.moveToFirst()){
                Convidado convidado = new Convidado();
                convidado.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseConstants.CONVIDADO.COLUMNS.ID)));
                convidado.setNome(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseConstants.CONVIDADO.COLUMNS.NOME)));
                convidado.setPresenca(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA)));
                return convidado;
            }
            return  null;
        }catch (Exception e){
            return  null;
        }
    }

    public boolean save(Convidado convidado) {
        try {
            SQLiteDatabase db = this.dataBaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DataBaseConstants.CONVIDADO.COLUMNS.NOME, convidado.getNome());
            values.put(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA, convidado.getPresenca());
            if(convidado.getId()){
                values.put(DataBaseConstants.CONVIDADO.COLUMNS.ID, convidado.getId());
                db.update(DataBaseConstants.CONVIDADO.TABLE_NAME, values, "id="+ convidado.getId(), null);
            }else {
                db.insert(DataBaseConstants.CONVIDADO.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public List<Convidado> getConvidadoByQuery(String sql) {
        List<Convidado> convidados = new ArrayList<>();
        try {
            SQLiteDatabase db = this.dataBaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Convidado convidado = new Convidado();
                    convidado.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                            DataBaseConstants.CONVIDADO.COLUMNS.ID)));
                    convidado.setNome(cursor.getString(cursor.getColumnIndexOrThrow(
                            DataBaseConstants.CONVIDADO.COLUMNS.NOME)));
                    convidado.setPresenca(cursor.getInt(cursor.getColumnIndexOrThrow(
                            DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA)));
                    convidados.add(convidado);
                }
                cursor.close();
            }

        } catch (Exception e) {
            return convidados;
        }

        return convidados;
    }

    public ConvidadosCount getCount() {
        ConvidadosCount convidadosCount = new ConvidadosCount(0, 0, 0);
        Cursor cursor;
        try {
            SQLiteDatabase db = this.dataBaseHelper.getReadableDatabase();
                cursor = db.rawQuery("select count(*) from "
                            + DataBaseConstants.CONVIDADO.TABLE_NAME + " where "
                            + DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA + " = "
                            + ConvidadoConstants.CONFIRMACAO.PRESENTE,
                    null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setPresente(cursor.getInt(0));
                cursor.close();
            }

            cursor = db.rawQuery("select count(*) from "
                            + DataBaseConstants.CONVIDADO.TABLE_NAME + " where "
                            + DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA + " = "
                            + ConvidadoConstants.CONFIRMACAO.AUSENTE,
                    null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setAusente(cursor.getInt(0));
                cursor.close();
            }

            cursor = db.rawQuery("select count(*) from "
                            + DataBaseConstants.CONVIDADO.TABLE_NAME,
                    null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setTodos(cursor.getInt(0));
                cursor.close();
            }


            return convidadosCount;
        } catch (Exception e) {
            return convidadosCount;
        }
    }
}
