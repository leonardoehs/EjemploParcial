package com.example.parcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNuevo, btnLista, btnProcesar;
    ProgressBar Pbar;
    TextView Porcentaje;
    private int mProgressStatus = 0;
    Handler mHandler = new Handler();
    public static List<String> Ln;
    public static List<String> ListC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ln = new ArrayList<>();
        ListC = new ArrayList<>();
        setContentView(R.layout.activity_main);
        btnLista = findViewById(R.id.btnLista);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnProcesar = findViewById(R.id.btnProcesar);
        Pbar = findViewById(R.id.Pbar);
        Porcentaje = findViewById(R.id.Porcentaje);
        btnLista.setOnClickListener(this);
        btnNuevo.setOnClickListener(this);
        btnProcesar.setOnClickListener(this);
        if (Pbar.getVisibility() == View.VISIBLE && Porcentaje.getVisibility() == View.VISIBLE) { //si es Visible lo pones Gone
            Pbar.setVisibility(View.GONE);
            Porcentaje.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNuevo: {
                Intent i = new Intent(this, Formulario.class);
                startActivity(i);
            }
            break;
            case R.id.btnLista: {
                //Alerta si la lista se encuentra vacia
                if (Ln == null || Ln.size() == 0) {
                    new AlertDialog.Builder(this).setTitle("Alerta").setMessage("Lista Vacia").show();
                } else {
                    Intent i = new Intent(this, Lista.class);
                    startActivity(i);
                }
            }
            break;
            case R.id.btnProcesar: {
                Porcentaje.setVisibility(View.VISIBLE);
                Pbar.setVisibility(View.VISIBLE);
                Thread hr = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mProgressStatus <= 100) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Porcentaje.setText(mProgressStatus + " %");
                                    Pbar.setProgress(mProgressStatus);
                                }
                            });
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (mProgressStatus == 100) {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        new AlertDialog.Builder(MainActivity.this).setTitle("¡Aviso!").setMessage("Encuesta procesada con exito").show();
                                    }
                                });
                            }
                            mProgressStatus++;
                        }
                    }
                });
                hr.start();
            }
            break;
        }
    }
}
