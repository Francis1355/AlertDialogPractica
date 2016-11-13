package com.example.francisco.alertdialogpractica;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView txtMsg;
    Button btnCustomDialog;
    Button btnAlertDialog;
    Button btnDialogFragment;
    Context activityContext;
    String msg = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityContext = this;

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        btnAlertDialog = (Button) findViewById(R.id.btn_alert_dialog1);
        btnCustomDialog = (Button) findViewById(R.id.btn_custom_dialog);
        btnDialogFragment = (Button) findViewById(R.id.btn_alert_dialog2);

        btnCustomDialog.setOnClickListener(this);
        btnAlertDialog.setOnClickListener(this);
        btnDialogFragment.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btnAlertDialog.getId()) {
            showMyAlertDialog(this);
        }
        if (v.getId() == btnCustomDialog.getId()) {
            showCustomDialogBox();
        }
        if (v.getId() == btnDialogFragment.getId()) {
            showMyAlertDialogFragment(this);
        }
    }

    private void showMyAlertDialog(MainActivity mainActivity) {
        new AlertDialog.Builder(mainActivity)
                .setTitle("Terminar")
                .setMessage("¿Estas seguro que deseas salir?")
                .setIcon(R.drawable.ic_email_black_24dp)
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                msg = "YES " + Integer.toString(whichButton);
                                txtMsg.setText(msg);
                            }
                        })
                .setNeutralButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                msg = "CANCEL " + Integer.toString(whichButton);
                                txtMsg.setText(msg);
                            }
                        })
                .setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                msg = "NO " + Integer.toString(whichButton);
                                txtMsg.setText(msg);
                            }
                        })
                .create()
                .show();
    }


    private void showCustomDialogBox() {
        final Dialog customDialog = new Dialog(activityContext);
        customDialog.setTitle("Custom Dialog Title");
        customDialog.setContentView(R.layout.custom_dialog_layout);

        ((TextView) customDialog.findViewById(R.id.sd_textView1)).setText("\nMessage line1\nMessage line2\n"
        +"Dismiss: Back btn, Close, or touch outside");

        final EditText sd_txtInputData = (EditText) customDialog.findViewById(R.id.sd_editText1);
        ((Button) customDialog.findViewById(R.id.sd_btnClose))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtMsg.setText(sd_txtInputData.getText().toString());
                        customDialog.dismiss();
                    }
                });
        customDialog.show();
    }

    private void showMyAlertDialogFragment(MainActivity mainActivity) {
        MyAlertDialogFragment dialogFragment = MyAlertDialogFragment.newInstance(R.string.title);
        dialogFragment.show(getFragmentManager(), "TAG_MYDIALOGFRAGMENT1");

    }

    public void doPositiveClick(Date time) {
        txtMsg.setText("POSITIVO - DialogFragment picked @ " + time);
    }

    public void doNegativeClick(Date time) {
        txtMsg.setText("NEGATIVO - DialogFragment picked @ " + time);
    }

    public void doNeutralClick(Date time) {
        txtMsg.setText("NEUTRAL - DialogFragment picked @ " + time);
    }

}

