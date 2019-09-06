package com.example.parcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.parcial.Formulario.apellido;
import static com.example.parcial.Formulario.nombre;
import static com.example.parcial.MainActivity.ListC;

public class Alimentos extends AppCompatActivity {
    ListView ListAlimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos);
        ListAlimentos = findViewById(R.id.ListAlimentos);

        ListAlimentos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListC));

        this.ListAlimentos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(position>=0){
                    new AlertDialog.Builder(Alimentos.this).setTitle("¡Aviso!").setMessage("¿"+nombre+" "+apellido+" seleciono el plato: " + ListC.get(position) + "?")
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {// Agregamos y cerramos
                                    Intent form=new Intent();
                                    form.putExtra("alimento",ListC.get(position));
                                    setResult(RESULT_OK,form);
                                    finish();
                                }
                            }).show();

                }
            }
        });
    }
}