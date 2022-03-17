package br.com.ismaellunkes.pluvioapp.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.ismaellunkes.pluvioapp.R;
import br.com.ismaellunkes.pluvioapp.model.Registro;

public class RegistroAdapterPersonalizado extends BaseAdapter {

    private final List<Registro> registros;
    private final Activity activity;

    public RegistroAdapterPersonalizado(List<Registro> registros, Activity act) {
        this.registros = registros;
        this.activity = act;
    }

    @Override
    public int getCount() {
        return registros.size();
    }

    @Override
    public Object getItem(int i) {
        return registros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (activity != null){

        }
        View view = LayoutInflater.from(activity).inflate(R.layout.item_list, viewGroup, false);
        Registro registro = registros.get(i);

        //pegando as referências das Views
        TextView dataRegistro = (TextView) view.findViewById(R.id.txtViewDataReg);
        String detalhesRegistro = activity.getString(R.string.registro_detalhes,
                                                        registro.dataHoraRegistro,
                                                        String.valueOf(registro.precipitacao),
                                                        registro.locais,
                                                        registro.isLigouIrrigacao ? activity.getString(R.string.sim) : activity.getString(R.string.nao),
                                                        registro.responsavel);

        //populando as Views
        dataRegistro.setText(detalhesRegistro);
        return view;
    }
}