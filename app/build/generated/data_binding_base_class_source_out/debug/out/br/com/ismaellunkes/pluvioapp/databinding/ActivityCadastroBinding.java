// Generated by view binder compiler. Do not edit!
package br.com.ismaellunkes.pluvioapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import br.com.ismaellunkes.pluvioapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCadastroBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnLimpar;

  @NonNull
  public final Button btnSalvar;

  @NonNull
  public final CheckBox chkPomar01;

  @NonNull
  public final CheckBox chkPomar02;

  @NonNull
  public final CheckBox chkPomar03;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final EditText edtTxtDataHoraReg;

  @NonNull
  public final EditText edtTxtPrecipitacao;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final LinearLayout linearLayout5;

  @NonNull
  public final RadioButton rdBtnNao;

  @NonNull
  public final RadioButton rdBtnSim;

  @NonNull
  public final RadioGroup rdGroupIrrigacao;

  @NonNull
  public final Spinner spiResponsavel;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView4;

  private ActivityCadastroBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnLimpar,
      @NonNull Button btnSalvar, @NonNull CheckBox chkPomar01, @NonNull CheckBox chkPomar02,
      @NonNull CheckBox chkPomar03, @NonNull ConstraintLayout constraintLayout,
      @NonNull EditText edtTxtDataHoraReg, @NonNull EditText edtTxtPrecipitacao,
      @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2,
      @NonNull LinearLayout linearLayout3, @NonNull LinearLayout linearLayout4,
      @NonNull LinearLayout linearLayout5, @NonNull RadioButton rdBtnNao,
      @NonNull RadioButton rdBtnSim, @NonNull RadioGroup rdGroupIrrigacao,
      @NonNull Spinner spiResponsavel, @NonNull TextView textView, @NonNull TextView textView2,
      @NonNull TextView textView3, @NonNull TextView textView4) {
    this.rootView = rootView;
    this.btnLimpar = btnLimpar;
    this.btnSalvar = btnSalvar;
    this.chkPomar01 = chkPomar01;
    this.chkPomar02 = chkPomar02;
    this.chkPomar03 = chkPomar03;
    this.constraintLayout = constraintLayout;
    this.edtTxtDataHoraReg = edtTxtDataHoraReg;
    this.edtTxtPrecipitacao = edtTxtPrecipitacao;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.linearLayout3 = linearLayout3;
    this.linearLayout4 = linearLayout4;
    this.linearLayout5 = linearLayout5;
    this.rdBtnNao = rdBtnNao;
    this.rdBtnSim = rdBtnSim;
    this.rdGroupIrrigacao = rdGroupIrrigacao;
    this.spiResponsavel = spiResponsavel;
    this.textView = textView;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.textView4 = textView4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCadastroBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCadastroBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_cadastro, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCadastroBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnLimpar;
      Button btnLimpar = ViewBindings.findChildViewById(rootView, id);
      if (btnLimpar == null) {
        break missingId;
      }

      id = R.id.btnSalvar;
      Button btnSalvar = ViewBindings.findChildViewById(rootView, id);
      if (btnSalvar == null) {
        break missingId;
      }

      id = R.id.chkPomar01;
      CheckBox chkPomar01 = ViewBindings.findChildViewById(rootView, id);
      if (chkPomar01 == null) {
        break missingId;
      }

      id = R.id.chkPomar02;
      CheckBox chkPomar02 = ViewBindings.findChildViewById(rootView, id);
      if (chkPomar02 == null) {
        break missingId;
      }

      id = R.id.chkPomar03;
      CheckBox chkPomar03 = ViewBindings.findChildViewById(rootView, id);
      if (chkPomar03 == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.edtTxtDataHoraReg;
      EditText edtTxtDataHoraReg = ViewBindings.findChildViewById(rootView, id);
      if (edtTxtDataHoraReg == null) {
        break missingId;
      }

      id = R.id.edtTxtPrecipitacao;
      EditText edtTxtPrecipitacao = ViewBindings.findChildViewById(rootView, id);
      if (edtTxtPrecipitacao == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      id = R.id.linearLayout4;
      LinearLayout linearLayout4 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout4 == null) {
        break missingId;
      }

      id = R.id.linearLayout5;
      LinearLayout linearLayout5 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout5 == null) {
        break missingId;
      }

      id = R.id.rdBtnNao;
      RadioButton rdBtnNao = ViewBindings.findChildViewById(rootView, id);
      if (rdBtnNao == null) {
        break missingId;
      }

      id = R.id.rdBtnSim;
      RadioButton rdBtnSim = ViewBindings.findChildViewById(rootView, id);
      if (rdBtnSim == null) {
        break missingId;
      }

      id = R.id.rdGroupIrrigacao;
      RadioGroup rdGroupIrrigacao = ViewBindings.findChildViewById(rootView, id);
      if (rdGroupIrrigacao == null) {
        break missingId;
      }

      id = R.id.spiResponsavel;
      Spinner spiResponsavel = ViewBindings.findChildViewById(rootView, id);
      if (spiResponsavel == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      return new ActivityCadastroBinding((ConstraintLayout) rootView, btnLimpar, btnSalvar,
          chkPomar01, chkPomar02, chkPomar03, constraintLayout, edtTxtDataHoraReg,
          edtTxtPrecipitacao, linearLayout, linearLayout2, linearLayout3, linearLayout4,
          linearLayout5, rdBtnNao, rdBtnSim, rdGroupIrrigacao, spiResponsavel, textView, textView2,
          textView3, textView4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
