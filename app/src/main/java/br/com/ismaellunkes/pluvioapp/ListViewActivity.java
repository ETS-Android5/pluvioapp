package br.com.ismaellunkes.pluvioapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {


    public static final int NOVO_REGISTRO = 1;
    public static final String REGISTRO = "REGISTRO";
    public static final String MODO = "MODO";
    List<Registro> registros = new ArrayList<>();
    private int  posicaoSelecionada = -1;
    ArrayAdapter<Registro> adapter;
    ListView listViewEntities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listViewEntities = findViewById(R.id.listViewRegistros);
        final Button btnAdicionar = findViewById(R.id.btnAdicionar);
        final Button btnCreditos = findViewById(R.id.btnCreditos);

        listViewEntities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posicaoSelecionada = i;
                alterarRegistro();
                //Registro registro = (Registro) listViewEntities.getItemAtPosition(i);
               // Toast.makeText(getApplicationContext(), registro.getRegistroResumido(), Toast.LENGTH_LONG).show();
            }
        });

        listViewEntities.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                                   View view,
                                                   int position,
                                                   long id) {

                        posicaoSelecionada = position;
                        alterarRegistro();
                        return true;
                    }
                });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarRegistro(view);
            }
        });

        btnCreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirSobre(view);
            }
        });

        popularLista();

    }

    private void popularLista(){

        registros = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                registros);

        if (listViewEntities != null) {
            listViewEntities.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void alterarRegistro(){

        Registro registro = registros.get(posicaoSelecionada);

        CadastroActivity.alterarRegistro(this, registro);
    }

    public void adicionarRegistro(View view){
        CadastroActivity.novoRegistro(this);
    }

    public void abrirSobre(View view){
        SobreActivity.sobre(this);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            Registro registroNew = (Registro) data.getSerializableExtra(REGISTRO);

            if (requestCode == CadastroActivity.ALTERAR) {
                registros.remove(posicaoSelecionada);
                registros.add(posicaoSelecionada, registroNew);
                posicaoSelecionada = -1;
            } else {
                registros.add(registroNew);
            }

            adapter.notifyDataSetChanged();
        }
    }

}
