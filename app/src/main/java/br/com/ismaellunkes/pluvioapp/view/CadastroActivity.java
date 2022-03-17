package br.com.ismaellunkes.pluvioapp.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.ismaellunkes.pluvioapp.R;
import br.com.ismaellunkes.pluvioapp.model.Registro;
import br.com.ismaellunkes.pluvioapp.persistencia.PluvioDatabase;

public class CadastroActivity extends AppCompatActivity {

    public static final String MODO = "MODO";
    public static final String REGISTRO = "REGISTRO";
    public static final int NOVO = 1;
    public static final int ALTERAR = 2;
    private Registro registro;
    private Registro registroOriginal;
    private List<Registro> registros;
    private PluvioDatabase database;

    private int modo;

    EditText edtTxtDataHoraReg;
    EditText edtTxtPrecipitacao;
    CheckBox chkPomar01;
    CheckBox chkPomar02;
    CheckBox chkPomar03;
    RadioGroup rdGroupIrrigacao;
    Spinner spiResponsavel;
    Button btnSalvar;
    Button btnLimpar;
    RadioButton rdBtnSim;
    RadioButton rdBtnNao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        database = PluvioDatabase.getDatabase(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        registros = new ArrayList<>();

        edtTxtDataHoraReg = findViewById(R.id.edtTxtDataHoraReg);
        edtTxtPrecipitacao = findViewById(R.id.edtTxtPrecipitacao);
        chkPomar01 = findViewById(R.id.chkPomar01);
        chkPomar02 = findViewById(R.id.chkPomar02);
        chkPomar03 = findViewById(R.id.chkPomar03);
        rdGroupIrrigacao = findViewById(R.id.rdGroupIrrigacao);
        spiResponsavel = findViewById(R.id.spiResponsavel);
        btnSalvar = findViewById(R.id.item_salvar);
        btnLimpar = findViewById(R.id.item_limpar);
        rdBtnSim = findViewById(R.id.rdBtnSim);
        rdBtnNao = findViewById(R.id.rdBtnNao);
        rdBtnNao.setChecked(true);

        Intent intent = getIntent();
        Registro registro = (Registro) intent.getSerializableExtra(REGISTRO);

        modo = intent.getIntExtra(MODO, NOVO);

        if (registro != null) {

            modo = intent.getIntExtra(MODO, ALTERAR);

         /*   modo = intent.getIntExtra(MODO, NOVO);

            if (modo == NOVO){
               // setTitle(getString(R.string.nova_pessoa));
            } else {*/

            registroOriginal = (Registro) intent.getSerializableExtra(REGISTRO);
            edtTxtDataHoraReg.setText(registroOriginal.dataHoraRegistro);
            edtTxtPrecipitacao.setText(String.valueOf(registroOriginal.precipitacao));
            chkPomar01.setChecked(registro.locais.contains(chkPomar01.getText().toString()));
            chkPomar02.setChecked(registro.locais.contains(chkPomar02.getText().toString()));
            chkPomar03.setChecked(registro.locais.contains(chkPomar03.getText().toString()));

                /*for (String local :  registroOriginal.getLocais()) {
                    if (local.equalsIgnoreCase(chkPomar01.getText().toString())){
                        chkPomar01.setChecked(true);
                    }

                    if (local.equalsIgnoreCase(chkPomar02.getText().toString())){
                        chkPomar02.setChecked(true);
                    }

                    if (local.equalsIgnoreCase(chkPomar03.getText().toString())){
                        chkPomar03.setChecked(true);
                    }
                }*/

            rdBtnNao.setChecked(true);
            if (registroOriginal.isLigouIrrigacao) {
                rdBtnSim.setChecked(true);
            }

            for (int pos = 0; 0 < spiResponsavel.getAdapter().getCount(); pos++) {

                String valor = (String) spiResponsavel.getItemAtPosition(pos);

                if (valor.equals(registroOriginal.responsavel)) {
                    spiResponsavel.setSelection(pos);
                    break;
                }
            }
        //}
        }

    }

    public void avisoCamposLimpos() {
        Toast.makeText(this, getString(R.string.campos_limpos), Toast.LENGTH_SHORT).show();
    }

    private boolean isDadosValidos(Registro registro) {

        if (registro.dataHoraRegistro.isEmpty()) {
            Toast.makeText(this, getString(R.string.validacao_datahora), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (registro.precipitacao == null) {
            Toast.makeText(this, getString(R.string.validacao_precipitacao), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (registro.locais.isEmpty()) {
            Toast.makeText(this, getString(R.string.validacao_locais), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (registro.responsavel.isEmpty()) {
            Toast.makeText(this, getString(R.string.validacao_responsavel), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void salvarDados(Registro registro) {

        Intent intent = new Intent();
        intent.putExtra(REGISTRO, registro);

        setResult(Activity.RESULT_OK, intent);

        if (modo == NOVO) {
            database.registroDao().insert(registro);
        }

        if (modo == ALTERAR) {
            database.registroDao().update(registro);
        }

        finish();

        String registroSalvo = getString(R.string.datahora_mensagem)+" "+ registro.dataHoraRegistro +
                            " | "+getString(R.string.precip_mensagem)+" "+ registro.precipitacao +
                            "\n"+getString(R.string.locais_mensagem)+" "+ registro.locais +
                            " | "+getString(R.string.ligou_irrig_mensagem)+" "+ (registro.isLigouIrrigacao ? getString(R.string.sim) : getString(R.string.nao)) +
                            "\n "+getString(R.string.responsavel_mensagem)+" "+ registro.responsavel;


        Toast.makeText(this, getString(R.string.mensagem_sucesso) + "\n"+registroSalvo, Toast.LENGTH_LONG).show();

    }


    public static void novoRegistro(AppCompatActivity appCompatActivity){
        Intent intent = new Intent(appCompatActivity, CadastroActivity.class);
        intent.putExtra(MODO, NOVO);
        appCompatActivity.startActivityForResult(intent, NOVO);
    }

    public static void alterarRegistro(AppCompatActivity appCompatActivity, Registro registro){
        Intent intent = new Intent(appCompatActivity, CadastroActivity.class);
        intent.putExtra(MODO, ALTERAR);
        intent.putExtra(REGISTRO, registro);
        appCompatActivity.startActivityForResult(intent, ALTERAR);
    }

    private void cancelar() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
    @Override
    public void onBackPressed() {
        cancelar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void salvarRegistro() {

        if(registroOriginal == null){
            registroOriginal = new Registro();
        }
        registroOriginal.dataHoraRegistro = edtTxtDataHoraReg.getText().toString();
        registroOriginal.precipitacao = !edtTxtPrecipitacao.getText().toString().isEmpty() ? Integer.parseInt(edtTxtPrecipitacao.getText().toString()) : null;
        registroOriginal.locais = getLocais();
        registroOriginal.isLigouIrrigacao = rdBtnSim.isChecked();
        registroOriginal.responsavel = (String) spiResponsavel.getSelectedItem();

        if (isDadosValidos(registroOriginal)) {
            salvarDados(registroOriginal);
        }
    }

    private String getLocais() {
        /*List<String> locais = new ArrayList<>();*/
        StringBuilder locais = new StringBuilder();
        locais.append("[");
        if (chkPomar01.isChecked()) {
            //locais.add(chkPomar01.getText().toString());
            locais.append(chkPomar01.getText().toString().concat(", "));
        }

        if (chkPomar02.isChecked()) {
            //locais.add(chkPomar02.getText().toString());
            locais.append(chkPomar02.getText().toString().concat(", "));
        }

        if (chkPomar03.isChecked()) {
            ///locais.add(chkPomar03.getText().toString());
            locais.append(chkPomar03.getText().toString());
        }
        locais.append("]");
        String locaisFormatado = locais.toString().replace(", ]", "]");

        if (locaisFormatado.trim().contentEquals("[]")) {
            return "";
        }
        return locaisFormatado;
    }

    private void limparCampos() {

        edtTxtDataHoraReg.setText("");
        edtTxtPrecipitacao.setText("");
        chkPomar01.setChecked(false);
        chkPomar02.setChecked(false);
        chkPomar03.setChecked(false);
        rdGroupIrrigacao.clearCheck();
        avisoCamposLimpos();
        edtTxtDataHoraReg.requestFocus();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.item_salvar:
                salvarRegistro();
                return true;

            case R.id.item_limpar:
                limparCampos();
                return true;

            default:
                cancelar();
                return true;
        }
    }

}