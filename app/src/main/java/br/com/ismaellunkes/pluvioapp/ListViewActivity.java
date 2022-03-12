package br.com.ismaellunkes.pluvioapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ListViewActivity extends AppCompatActivity {


    public static final String REGISTRO = "REGISTRO";
    List<Registro> registros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        final ListView listViewEntities = findViewById(R.id.listViewRegistros);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        Registro registro = (Registro) getIntent().getSerializableExtra(REGISTRO);

        registros.add(registro);

        ArrayAdapter<Registro> adapter = new ArrayAdapter<Registro>(this,
                android.R.layout.simple_list_item_1, getRegistrosList());
        listViewEntities.setAdapter(adapter);

        listViewEntities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Registro registro = (Registro) listViewEntities.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), registro.getRegistroResumido(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private List<Registro> getRegistrosList() {
        List<String> dataHoras = Arrays.asList(getResources().getStringArray(R.array.dataHora));
        String[] precipitacaos = getResources().getStringArray(R.array.precipitacao);
        String[] locais = getResources().getStringArray(R.array.locais);
        String[] responsaveis = getResources().getStringArray(R.array.responsavel);
        registros = new ArrayList<>();

        for (String dataHora : dataHoras) {
            Registro registro = new Registro();
            registro.setDataHoraRegistro(dataHora);
            registro.setPrecipitacao(precipitacaos[getValorRandomico()]);
            registro.setLocais(new ArrayList(Arrays.asList(locais[getValorRandomico()])));
            registro.setLigouIrrigacao(Integer.parseInt(registro.getPrecipitacao()) < 30);
            registro.setResponsavel(responsaveis[getValorRandomico()]);
            registros.add(registro);
        }
        return registros;
    }

    private int getValorRandomico() {
        Random random = new Random();
        return random.nextInt(6);
    }
}
