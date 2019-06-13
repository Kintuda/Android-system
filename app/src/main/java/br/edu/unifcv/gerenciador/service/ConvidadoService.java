package br.edu.unifcv.gerenciador.service;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.List;

import br.edu.unifcv.gerenciador.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.constants.DataBaseConstants;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.model.ConvidadosCount;
import br.edu.unifcv.gerenciador.repository.ConvidadoRepository;

public class ConvidadoService {

    private ConvidadoRepository convidadoRepository;

    public ConvidadoService(Context context) {
        this.convidadoRepository = ConvidadoRepository.getInstance(context);
    }

    public boolean insert(Convidado convidado) {
        return this.convidadoRepository.save(convidado);
    }

    public List<Convidado> findAll() {
        return this.convidadoRepository.getConvidadoByQuery("select * from " +
                DataBaseConstants.CONVIDADO.TABLE_NAME);

    }

    public List<Convidado> findAllAusentes() {
        return this.convidadoRepository.getConvidadoByQuery("select * from " +
                DataBaseConstants.CONVIDADO.TABLE_NAME + " where presenca = " + ConvidadoConstants.CONFIRMACAO.AUSENTE);
    }

    public List<Convidado> findAllPresentes() {
        return this.convidadoRepository.getConvidadoByQuery("select * from " +
                DataBaseConstants.CONVIDADO.TABLE_NAME + " where presenca = " + ConvidadoConstants.CONFIRMACAO.PRESENTE);
    }

    public List<Convidado> findAllNaoConfirmados() {
        return this.convidadoRepository.getConvidadoByQuery("select * from " +
                DataBaseConstants.CONVIDADO.TABLE_NAME + " where presenca = " + ConvidadoConstants.CONFIRMACAO.NAO_CONFIRMADO);
    }

    public void delete(Integer id){
        this.convidadoRepository.delete(id);
    }

    public Boolean update(Convidado convidado){
        return this.convidadoRepository.update(convidado);
    }

    public Convidado findById(Integer id){
        return this.convidadoRepository.findById(id);
    }

    public ConvidadosCount count() {
        return this.convidadoRepository.getCount();
    }
}
