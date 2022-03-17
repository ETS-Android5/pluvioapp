package br.com.ismaellunkes.pluvioapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.ismaellunkes.pluvioapp.R;
import br.com.ismaellunkes.pluvioapp.model.Registro;
import br.com.ismaellunkes.pluvioapp.persistencia.PluvioDatabase;
import br.com.ismaellunkes.pluvioapp.utils.UtilsGUI;

public class ListViewActivity extends AppCompatActivity {

    public static final String REGISTRO = "REGISTRO";
    private static final String ARQUIVO =
            "br.com.ismaellunkes.pluvioapp.PREFERENCIA_ORDENACAO_LISTA1";
    List<Registro> registros = new ArrayList<>();
    private int posicaoSelecionada = -1;
    RegistroAdapterPersonalizado adapter;
    ListView listViewEntities;
    private ActionMode actionMode;
    private View viewSelecionada;
    private String preferenciaOrdenacao;
    private PluvioDatabase database;

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

            switch (item.getItemId()) {
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

            if (viewSelecionada != null) {
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode = null;
            viewSelecionada = null;

            listViewEntities.setEnabled(true);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        database = PluvioDatabase.getDatabase(this);
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
        lerPreferenciaOrdenacaoLista();
        popularLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo:
                adicionarRegistro();
                return true;
            case R.id.sobre:
                abrirSobre();
                return true;
            case R.id.ordenar_reponsavel:
                salvarPrefereenciasOrdenacaoLista(getString(R.string.sp_ordenar_por_responsavel));
                return true;
            case R.id.ordenar_precipitacao:
                salvarPrefereenciasOrdenacaoLista(getString(R.string.sp_ordenar_por_precipitacao));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void popularLista() {
        carregarRegistros();
        carregarListView();
    }

    private void cancelar() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
    @Override
    public void onBackPressed() {
        cancelar();
    }

    private void alterarRegistro(Integer posicaoSelecionada) {

        Registro registro = registros.get(posicaoSelecionada);

        CadastroActivity.alterarRegistro(this, registro);
    }

    private void excluirRegistro(Integer posicao) {

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch(which){

                            case DialogInterface.BUTTON_POSITIVE:
                                database.registroDao().delete(registros.get(posicao));
                                popularLista();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

        UtilsGUI.confirmaAcao(this, getString(R.string.mensagem_exlusao), listener);

    }

    public void adicionarRegistro() {
        CadastroActivity.novoRegistro(this);
    }

    public void abrirSobre() {
        SobreActivity.sobre(this);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        posicaoSelecionada = -1;
        popularLista();
    }

    private void carregarRegistros() {
        registros = new ArrayList<>();
        registros = database.registroDao().findAll();
        ordenarRegistros(registros);
    }

    private void ordenarRegistros(List<Registro> registros){
        ordenarRegistros(preferenciaOrdenacao, registros);
    }

    private void carregarListView() {
        if (listViewEntities != null) {
            adapter = new RegistroAdapterPersonalizado(registros, this);
            adapter.notifyDataSetChanged();
            listViewEntities.setAdapter(adapter);
        }
    }

    private void ordenarRegistros(String preferencia, List<Registro> registros) {
        if (preferencia.equalsIgnoreCase(getString(R.string.sp_ordenar_por_responsavel))) {
            Collections.sort(registros, new Comparator<Registro>() {
                @Override
                public int compare(Registro o1, Registro o2) {
                    return (o1.responsavel).compareTo(o2.responsavel);
                }
            });
        }

        if (preferencia.equalsIgnoreCase(getString(R.string.sp_ordenar_por_precipitacao))) {
            Collections.sort(registros, new Comparator<Registro>() {
                @Override
                public int compare(Registro o1, Registro o2) {
                    return (o1.precipitacao).compareTo(o2.precipitacao);
                }
            });
        }
    }

    private void lerPreferenciaOrdenacaoLista() {
        SharedPreferences shared = getSharedPreferences(ARQUIVO, MODE_PRIVATE);
        String defaultPreferencesOrdemList = getString(R.string.sp_ordenar_por_precipitacao);
        preferenciaOrdenacao = shared.getString(getString(R.string.ordem_lista), defaultPreferencesOrdemList);
        ordenarRegistros(registros);
    }

    private void salvarPrefereenciasOrdenacaoLista(String spOrdemLista) {
        SharedPreferences sharedPref = getSharedPreferences(ARQUIVO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.ordem_lista), spOrdemLista);
        editor.apply();
        ordenarRegistros(spOrdemLista, registros);
        adapter.notifyDataSetChanged();
    }
}
