package br.edu.unifcv.gerenciador.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.unifcv.gerenciador.R;
import br.edu.unifcv.gerenciador.adapter.ConvidadoAdapter;
import br.edu.unifcv.gerenciador.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.listener.OnConvidadoListener;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.service.ConvidadoService;

public class NaoConfirmadosFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private ConvidadoService mConvidadoService;
    private OnConvidadoListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_naoconfirmados, container, false);
        final Context context = view.getContext();

        this.mViewHolder.mRecyclerViewNaoConfirmados = view.findViewById(R.id.recycler_naoconfirmados);

        this.mConvidadoService = new ConvidadoService(context);

        listener = new OnConvidadoListener() {
            @Override
            public void onClickList(int id) {
                Convidado convidado = mConvidadoService.findById(id);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(context, ConvidadoFormActivity.class);
                intent.putExtra(ConvidadoConstants.BundleConstants.BUNDLECONVIDADO, convidado);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int id) {
                mConvidadoService.delete(id);
                loadConvidados();
            }
        };

        this.mViewHolder.mRecyclerViewNaoConfirmados.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadConvidados();
    }


    public void loadConvidados() {
        List<Convidado> convidados = this.mConvidadoService.findAllNaoConfirmados();
        ConvidadoAdapter adapter = new ConvidadoAdapter(convidados, listener);
        this.mViewHolder.mRecyclerViewNaoConfirmados.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private static class ViewHolder {
        RecyclerView mRecyclerViewNaoConfirmados;
        TextView mTextPresente;
        TextView mTextAusente;
        TextView mTextTodos;
    }
}

