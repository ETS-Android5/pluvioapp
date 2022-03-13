package br.com.ismaellunkes.pluvioapp;

import android.app.Activity;
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
        lerPreferenciaOrdenacaoLista();
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

        registros = new ArrayList<>();

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

    private void alterarRegistro(Integer posicaoSelecionada) {

        Registro registro = registros.get(posicaoSelecionada);

        CadastroActivity.alterarRegistro(this, registro);
    }

    private void excluirRegistro(Integer posicao) {
        registros.remove(registros.get(posicao));
        adapter.notifyDataSetChanged();
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

        if (resultCode == Activity.RESULT_OK) {

            Registro registroNew = (Registro) data.getSerializableExtra(REGISTRO);

            if (requestCode == CadastroActivity.ALTERAR) {
                registros.remove(posicaoSelecionada);
                registros.add(posicaoSelecionada, registroNew);
                posicaoSelecionada = -1;
            } else {
                registros.add(registroNew);
            }
            ordenarRegistros(registros);
        }
    }

    private void ordenarRegistros(List<Registro> registros){
        ordenarRegistros(preferenciaOrdenacao, registros);
        adapter.notifyDataSetChanged();
    }

    private void ordenarRegistros(String preferencia, List<Registro> registros) {
        if (preferencia.equalsIgnoreCase(getString(R.string.sp_ordenar_por_responsavel))) {
            Collections.sort(registros, new Comparator<Registro>() {
                @Override
                public int compare(Registro o1, Registro o2) {
                    return (o1.getResponsavel()).compareTo(o2.getResponsavel());
                }
            });
        }

        if (preferencia.equalsIgnoreCase(getString(R.string.sp_ordenar_por_precipitacao))) {
            Collections.sort(registros, new Comparator<Registro>() {
                @Override
                public int compare(Registro o1, Registro o2) {
                    return (o1.getPrecipitacao()).compareTo(o2.getPrecipitacao());
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
