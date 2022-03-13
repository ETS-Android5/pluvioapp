package br.com.ismaellunkes.pluvioapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    public static final String REGISTRO = "REGISTRO";
    List<Registro> registros = new ArrayList<>();
    private int  posicaoSelecionada = -1;
    RegistroAdapterPersonalizado adapter;
    ListView listViewEntities;
    private ActionMode actionMode;
    private View       viewSelecionada;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.menu_contexto, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch(item.getItemId()){
                case R.id.alterar:
                    alterarRegistro(posicaoSelecionada);
                    mode.finish();
                    return true;

                case R.id.excluir:
                    excluirRegistro(posicaoSelecionada);
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (viewSelecionada != null){
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode         = null;
            viewSelecionada    = null;

            listViewEntities.setEnabled(true);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listViewEntities = findViewById(R.id.listViewRegistros);
        listViewEntities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posicaoSelecionada = i;
                alterarRegistro(posicaoSelecionada);
                Registro registro = (Registro) listViewEntities.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), registro.getRegistroResumido(), Toast.LENGTH_LONG).show();
            }
        });

        listViewEntities.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                                   View view,
                                                   int position,
                                                   long id) {

                        if (actionMode != null) {
                            return false;
                        }

                        posicaoSelecionada = position;

                        view.setBackgroundColor(Color.LTGRAY);

                        viewSelecionada = view;

                        listViewEntities.setEnabled(false);

                        actionMode = startSupportActionMode(mActionModeCallback);

                        return true;

                    }
                });
        listViewEntities.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        popularLista();
        registerForContextMenu(listViewEntities);

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.novo:
                adicionarRegistro();
                return true;
            case R.id.sobre:
                abrirSobre();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void popularLista(){

        registros = new ArrayList<>();

        /*adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                registros);*/

        //adapter = new ArrayAdapter<Registro>(this,
        //        android.R.layout.activity_list_item, R.id.listViewRegistros, registros);

        if (listViewEntities != null) {
            adapter = new RegistroAdapterPersonalizado(registros, this);
            listViewEntities.setAdapter(adapter);
        }
    }

    private void cancelar() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
    @Override
    public void onBackPressed() {
        cancelar();
    }

    private void alterarRegistro(Integer posicaoSelecionada){

        Registro registro = registros.get(posicaoSelecionada);

        CadastroActivity.alterarRegistro(this, registro);
    }

    private void excluirRegistro(Integer posicao){
        registros.remove(registros.get(posicao));
        adapter.notifyDataSetChanged();
    }

    public void adicionarRegistro(){
        CadastroActivity.novoRegistro(this);
    }

    public void abrirSobre(){
        SobreActivity.sobre(this);
    }

    private String getRegistroEmString(Registro registro){
        return  getString(R.string.datahora_mensagem)+" "+ registro.getDataHoraRegistro() +
                " | "+getString(R.string.precip_mensagem)+" "+ registro.getPrecipitacao() +
                "\n"+getString(R.string.locais_mensagem)+" "+ registro.getLocais() +
                "\n"+getString(R.string.ligou_irrig_mensagem)+" "+ (registro.isLigouIrrigacao() ? R.string.sim : R.string.nao) +
                " | "+getString(R.string.responsavel_mensagem)+" "+ registro.getResponsavel();
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
