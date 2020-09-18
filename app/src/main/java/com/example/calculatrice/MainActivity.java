package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtResultat;
    Button btn_cleanAll, btn_div, btn_multi, btn_delete, btn_mod, btn_add, btn_sustraction, btn_egal,
            btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9, btn_unnaire;

    int valOne =0, valTwo=0;
    boolean isOperateurSet = false;
    String operateur ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ma_vue);

        txtResultat = findViewById(R.id.result);
        btn_cleanAll = findViewById(R.id.cleanAll);
        btn_div = findViewById(R.id.division);
        btn_multi = findViewById(R.id.multiplication);
        btn_delete = findViewById(R.id.delete);
        btn_mod = findViewById(R.id.modulo);
        btn_sustraction = findViewById(R.id.sustration);
        btn_add = findViewById(R.id.addition);
        btn_egal = findViewById(R.id.egale);
        btn_0 = findViewById(R.id.zero);
        btn_1 = findViewById(R.id.un);
        btn_2 = findViewById(R.id.deux);
        btn_3 = findViewById(R.id.trois);
        btn_4 = findViewById(R.id.quatre);
        btn_5 = findViewById(R.id.cinq);
        btn_6 = findViewById(R.id.six);
        btn_7 = findViewById(R.id.sept);
        btn_8 = findViewById(R.id.huit);
        btn_9 = findViewById(R.id.neuf);
        btn_unnaire = findViewById(R.id.moinsUnaire);

        btn_cleanAll.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_cleanAll.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_multi.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_mod.setOnClickListener(this);
        btn_sustraction.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_egal.setOnClickListener(this);
        btn_unnaire.setOnClickListener(this);

        txtResultat.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(txtResultat.getText());
                Toast.makeText(MainActivity.this, cm.getText()+" copié dans le presse–papier", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        /*copie(txtResultat);*/
    }

    @Override
    public void onClick(View v) {
        TextView input,operator;
        String regexp ="((-[0-9])|([0-9])+[\\+\\-\\*\\÷\\%]{1}[0-9]+)+([\\+\\-\\*\\÷\\%]{1}[0-9]+)*";
        String regpexNeg = "(-[0-9]+)";
        String regpexPos = "([0-9]+)";
        switch (v.getId()){
            case R.id.cleanAll:
                txtResultat.setText("");
                isOperateurSet = false;
                break;
            case R.id.moinsUnaire:

                if (txtResultat.getText().toString().matches(regpexNeg)) {
                    int val = Math.abs(Integer.parseInt(txtResultat.getText().toString()));
                    txtResultat.setText(""+val);
                }
                else if (txtResultat.getText().toString().matches(regpexPos)){
                    txtResultat.setText("-"+txtResultat.getText());
                }
                //Toast.makeText(this, "OKKKK", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zero:
            case R.id.un:
            case R.id.deux:
            case R.id.trois:
            case R.id.quatre:
            case R.id.cinq:
            case R.id.six:
            case R.id.sept:
            case R.id.huit:
            case R.id.neuf:
                input = findViewById(v.getId());
                txtResultat.append(input.getText());
                break;
                //break;
            case R.id.division:
            case R.id.multiplication:
            case R.id.modulo:
            case R.id.addition:
               // break;
            case R.id.sustration:

                if (isOperateurSet){
                    operator = findViewById(v.getId());
                    if (txtResultat.getText().toString().matches(regexp)) {
                        TextView currentOp = findViewById(v.getId());
                        String[] vals = txtResultat.getText().toString().split("["+operateur+"//]");
                        valOne = Integer.parseInt(vals[0]);
                        valTwo = Integer.parseInt(vals[1]);

                        if ((operateur.equals("÷") || operateur.equals("%")) && valTwo == 0) {
                            txtResultat.setText("Error");
                            isOperateurSet = false;
                        }
                       else {
                            int resultat = Operation.calcul(operateur, valOne, valTwo);
                            // isOperateurSet = false;
                            txtResultat.setText(String.valueOf(resultat));
                            txtResultat.setText(txtResultat.getText().toString()+ currentOp.getText());
                            operateur = currentOp.getText().toString();
                        }
                    }
                    else if (!operateur.equals(operator.getText().toString())){
                        StringBuilder chaine = new StringBuilder();
                        chaine.append(txtResultat.getText().toString());
                        if (txtResultat.length() > 0) {
                            chaine.deleteCharAt(chaine.length() - 1);
                            chaine.append(operator.getText().toString());
                            txtResultat.setText(chaine);
                            operateur = operator.getText().toString();
                        }
                    }
                }
                else {
                    operator = findViewById(v.getId());
                    operateur = operator.getText().toString();
                    txtResultat.setText(txtResultat.getText() + operateur);
                    isOperateurSet = true;
                }

               break;
            case R.id.egale:
                if (txtResultat.getText().toString().matches(regexp)) {
                    String[] vals = txtResultat.getText().toString().split("["+operateur+"//]");
                    valOne = Integer.parseInt(vals[0]);
                    valTwo = Integer.parseInt(vals[1]);
                    if ((operateur.equals("÷") || operateur.equals("%")) && valTwo == 0){
                        txtResultat.setText("Error"); isOperateurSet = false;}
                    else {
                        int resultat = Operation.calcul(operateur, valOne, valTwo);
                        isOperateurSet = false;
                        txtResultat.setText(String.valueOf(resultat));
                    }
                }
                break;
            case R.id.delete:

                if (txtResultat.getText().toString().matches(regpexNeg)){
                    int val = Math.abs(Integer.parseInt(txtResultat.getText().toString()));
                    txtResultat.setText(""+val);
                }
                else {
                    StringBuilder chaine = new StringBuilder();
                    chaine.append(txtResultat.getText().toString());
                    if (txtResultat.length() > 0)chaine.deleteCharAt(chaine.length() - 1);
                    txtResultat.setText(chaine);
                    if (txtResultat.length() == 0 || operateur.length() != 0)
                        isOperateurSet = false;

                }
                break;
            /*case R.id.division:
            case R.id.modulo:
                input = findViewById(v.getId());
                if (!isOperateurSet){
                    txtResultat.append(input.getText());
                    operateur = input.getText().toString();
                    isOperateurSet = true;
                }
                else {
                    String ecran = txtResultat.getText().toString();
                    String valInput = input.getText().toString();

                    String regexp = "["+operateur+"]";
                    String[] valeurs = ecran.split(regexp);
                    int resulat = Operation.calcul(operateur, Integer.parseInt(valeurs[0]), Integer.parseInt(valeurs[1]));
                   // isOperateurSet = false;
                    txtResultat.setText(String.valueOf(resulat));
                    txtResultat.append(valInput);
                    operateur = valInput;
                    Toast.makeText(this, operateur +" "+ input.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                break;*/
        }
    }

    private void copie(TextView tv){
        String stringYouExtracted = tv.getText().toString();
        int startIndex = tv.getSelectionStart();
        int endIndex = tv.getSelectionEnd();
        stringYouExtracted = stringYouExtracted.substring(startIndex, endIndex);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(stringYouExtracted);
    }
}