package br.edu.unifcv.gerenciador.views;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import br.edu.unifcv.gerenciador.R;
import br.edu.unifcv.gerenciador.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.service.ConvidadoService;

public class ConvidadoFormActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private ConvidadoService mConvidadoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convidado_form);

        this.loadComponents();
        
        this.setEvents();
        String data = getIntent().getStringExtra(ConvidadoConstants.BundleConstants.NOME);
        Integer presenca = getIntent().getIntExtra(ConvidadoConstants.CONFIRMACAO.CONFIRMACAO_TITULO,0);
        this.mConvidadoService = new ConvidadoService(this);
        this.mViewHolder.mEditName.setText(data);
        this.mViewHolder.mRadioAbsent.setChecked(presenca == ConvidadoConstants.CONFIRMACAO.AUSENTE);
        this.mViewHolder.mRadioPresent.setChecked(presenca == ConvidadoConstants.CONFIRMACAO.PRESENTE);
        this.mViewHolder.mRadioNotConfirmed.setChecked(presenca == ConvidadoConstants.CONFIRMACAO.NAO_CONFIRMADO);
    }

    private void setEvents() {
        this.mViewHolder.mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSave();
            }
        });
    }

    private void handleSave() {
        Convidado guestEntity = new Convidado();
        guestEntity.setNome(this.mViewHolder.mEditName.getText().toString());

        if (this.mViewHolder.mRadioNotConfirmed.isChecked()) {
            guestEntity.setPresenca(ConvidadoConstants.CONFIRMACAO.NAO_CONFIRMADO);
        } else if (this.mViewHolder.mRadioPresent.isChecked()) {
            guestEntity.setPresenca(ConvidadoConstants.CONFIRMACAO.PRESENTE);
        } else {
            guestEntity.setPresenca(ConvidadoConstants.CONFIRMACAO.AUSENTE);
        }
        // Salva entidade no banco
        this.mConvidadoService.insert(guestEntity);
        this.finish();
    }

    private void loadComponents() {
        this.mViewHolder.mEditName = findViewById(R.id.edit_name);
        this.mViewHolder.mRadioNotConfirmed = findViewById(R.id.radio_not_confirmed);
        this.mViewHolder.mRadioPresent = findViewById(R.id.radio_present);
        this.mViewHolder.mRadioAbsent = findViewById(R.id.radio_absent);
        this.mViewHolder.mButtonSave = findViewById(R.id.button_save);
    }

    private static class ViewHolder {
        EditText mEditName;
        RadioButton mRadioNotConfirmed;
        RadioButton mRadioPresent;
        RadioButton mRadioAbsent;
        Button mButtonSave;
    }
}
