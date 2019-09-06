package com.example.parcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.parcial.MainActivity.ListC;
import static com.example.parcial.MainActivity.Ln;

public class Formulario extends AppCompatActivity implements View.OnClickListener {

    Button btnAlimentos, btnProcesarEncuesta;
    EditText edtNombre, edtApellido;
    TextView txvAlimento;
    public static String nombre = "";
    public static String apellido = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        txvAlimento = findViewById(R.id.txvAlimento);
        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        btnAlimentos = findViewById(R.id.btnAlimentos);
        btnProcesarEncuesta = findViewById(R.id.btnProcesarEncuesta);
        btnAlimentos.setOnClickListener(this);
        btnProcesarEncuesta.setOnClickListener(this);

        if (ListC == null || ListC.size() == 0) {
            ListC.add("Carne Asada");
            ListC.add("Pollo Frito");
            ListC.add("Pizza");
            ListC.add("Sopa de res");
            ListC.add("Ensalada");
            ListC.add("Lazaña");
            ListC.add("Pupusas");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAlimentos: {
                if(edtNombre.getText().toString().isEmpty() && edtApellido.getText().toString().isEmpty()) {
                    new AlertDialog.Builder(this).setTitle("Alerta").setMessage("¡Llene los campos anteriores!").show();
                }else{
                    nombre=edtNombre.getText().toString();
                    apellido=edtApellido.getText().toString();
                    Intent nueva = new Intent(Formulario.this, Alimentos.class);
                    startActivityForResult(nueva, 1);
                }
            }
            break;
            case R.id.btnProcesarEncuesta: {
                //Alerta si la lista se encuentra vacia
                if (edtNombre.getText().toString().isEmpty()) {
                    new AlertDialog.Builder(this).setTitle("Alerta").setMessage("¡Campo Nombre Vacio!").show();
                } else if (edtApellido.getText().toString().isEmpty()) {
                    new AlertDialog.Builder(this).setTitle("Alerta").setMessage("¡Campo Apellido Vacio!").show();
                } else if (txvAlimento.getText().toString().isEmpty()) {
                    new AlertDialog.Builder(this).setTitle("Alerta").setMessage("¡Campo Alimento Vacio!").show();
                }else{
                    Ln.add("Nombre: "+edtNombre.getText().toString()+" "+edtApellido.getText().toString()+", Comida: "+txvAlimento.getText().toString());
                    Toast.makeText(Formulario.this,"Encuestado Ingresado con exito",Toast.LENGTH_LONG).show();
                    //new AlertDialog.Builder(Formulario.this).setTitle("¡Aviso!").setMessage("Encuestado Ingresado con exito").show();
                    finish();


                }
            }
        }

    }

    public void onActivityResult(int RequestCode, int ResultCode, Intent datos){
        super.onActivityResult(RequestCode,ResultCode,datos);
        switch (RequestCode){
            case 1:
                if(ResultCode==RESULT_OK){
                    txvAlimento.setText(datos.getStringExtra("alimento"));
                    //Toast.makeText(Formulario.this," "+datos.getStringExtra("alimento"),Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
