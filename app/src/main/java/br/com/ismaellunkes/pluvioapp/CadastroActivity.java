package br.com.ismaellunkes.pluvioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    public static final String MODO = "MODO";
    public static final String REGISTRO = "REGISTRO";
    public static final int NOVO = 1;
    public static final int ALTERAR = 2;
    private Registro registro;
    private Registro registroOriginal;
    private List<Registro> registros;

    private int modo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        registros = new ArrayList<>();


        final EditText edtTxtDataHoraReg = findViewById(R.id.edtTxtDataHoraReg);
        final EditText edtTxtPrecipitacao = findViewById(R.id.edtTxtPrecipitacao);
        final CheckBox chkPomar01 = findViewById(R.id.chkPomar01);
        final CheckBox chkPomar02 = findViewById(R.id.chkPomar02);
        final CheckBox chkPomar03 = findViewById(R.id.chkPomar03);
        final RadioGroup rdGroupIrrigacao = findViewById(R.id.rdGroupIrrigacao);
        final Spinner spiResponsavel = findViewById(R.id.spiResponsavel);
        final Button btnSalvar = findViewById(R.id.btnSalvar);
        final Button btnLimpar = findViewById(R.id.btnLimpar);
        final RadioButton rdBtnSim = findViewById(R.id.rdBtnSim);
        final RadioButton rdBtnNao = findViewById(R.id.rdBtnNao);
        rdBtnNao.setChecked(true);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registro = new Registro();
                registro.setDataHoraRegistro(edtTxtDataHoraReg.getText().toString());
                registro.setPrecipitacao(edtTxtPrecipitacao.getText().toString());
                List<String> locais = new ArrayList<>();
                if (chkPomar01.isChecked()) {
                    locais.add(chkPomar01.getText().toString());
                }

                if (chkPomar02.isChecked()) {
                    locais.add(chkPomar02.getText().toString());
                }

                if (chkPomar03.isChecked()) {
                    locais.add(chkPomar03.getText().toString());
                }
                registro.setLocais(locais);
                registro.setLigouIrrigacao(rdBtnSim.isChecked());
                registro.setResponsavel((String) spiResponsavel.getSelectedItem());

                if (isDadosValidos(registro)) {
                    salvarDados(registro);
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTxtDataHoraReg.setText("");
                edtTxtPrecipitacao.setText("");
                chkPomar01.setChecked(false);
                chkPomar02.setChecked(false);
                chkPomar03.setChecked(false);
                rdGroupIrrigacao.clearCheck();
                avisoCamposLimpos();
                edtTxtDataHoraReg.requestFocus();
            }
        });

        Intent intent = getIntent();
        Registro registro = (Registro) intent.getSerializableExtra(REGISTRO);

        if (registro != null){

            modo = intent.getIntExtra(MODO, NOVO);

            if (modo == NOVO){
               // setTitle(getString(R.string.nova_pessoa));
            }else{

                registroOriginal = (Registro) intent.getSerializableExtra(REGISTRO);
                edtTxtDataHoraReg.setText(registroOriginal.getDataHoraRegistro());
                edtTxtPrecipitacao.setText(registroOriginal.getPrecipitacao());
                for (String local :  registroOriginal.getLocais()) {
                    if (local.equalsIgnoreCase("Pomar 01")){
                        chkPomar01.setChecked(true);
                    }

                    if (local.equalsIgnoreCase("Pomar 02")){
                        chkPomar02.setChecked(true);
                    }

                    if (local.equalsIgnoreCase("Pomar 03")){
                        chkPomar03.setChecked(true);
                    }
                }

                rdBtnNao.setChecked(true);
                if (registroOriginal.isLigouIrrigacao()) {
                    rdBtnSim.setChecked(true);
                }
            }
        }
    }

    public void avisoCamposLimpos() {
        Toast.makeText(this, "Campos foram limpos.", Toast.LENGTH_SHORT).show();
    }

    private boolean isDadosValidos(Registro registro) {

        if (registro.getDataHoraRegistro().isEmpty()) {
            Toast.makeText(this, "Informe o data/hora do registro.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (registro.getPrecipitacao().isEmpty()) {
            Toast.makeText(this, "Informe o registro de precipitação.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (registro.getLocais().isEmpty()) {
            Toast.makeText(this, "Informe os locais.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (registro.getResponsavel().isEmpty()) {
            Toast.makeText(this, "Informe o responsável.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void salvarDados(Registro registro) {

        Intent intent = new Intent();
        intent.putExtra(REGISTRO, registro);

        setResult(Activity.RESULT_OK, intent);

        finish();

        Toast.makeText(this, "Dados foram salvos: " + registro.toString(), Toast.LENGTH_LONG).show();

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

    public void cancelar(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}