package br.edu.unifcv.gerenciador.constants;

public class DataBaseConstants {

    private DataBaseConstants() {}

    public static class CONVIDADO {
        public static final String TABLE_NAME = "Convidado";

        public static class COLUMNS {
            public static final String ID = "id";
            public static final String NOME = "nome";
            public static final String PRESENCA = "presenca";
        }
    }
}