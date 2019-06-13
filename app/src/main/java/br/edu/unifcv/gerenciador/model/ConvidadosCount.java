package br.edu.unifcv.gerenciador.model;

public class ConvidadosCount {

    private int presente;
    private int ausente;
    private int todos;

    public ConvidadosCount(int presente, int ausente, int todos) {
        this.presente = presente;
        this.ausente = ausente;
        this.todos = todos;
    }

    public int getPresente() {
        return presente;
    }

    public void setPresente(int presente) {
        this.presente = presente;
    }

    public int getAusente() {
        return ausente;
    }

    public void setAusente(int ausente) {
        this.ausente = ausente;
    }

    public int getTodos() {
        return todos;
    }

    public void setTodos(int todos) {
        this.todos = todos;
    }
}
