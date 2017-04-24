package com.akurey.jruiz.sleeptime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText horaLlegada;
    EditText minutoLlegada;
    EditText tiempoDormir;
    EditText tiempoAlistarse;
    EditText tiempoBus;
    EditText tiempoDestino;
    TextView horaDormir;

    int hora = 0;
    int minutos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        horaLlegada = (EditText) findViewById(R.id.horaLlegada);
        minutoLlegada = (EditText) findViewById(R.id.minutoLlegada);
        tiempoDormir = (EditText) findViewById( R.id.tiempoDormir );
        tiempoAlistarse = (EditText) findViewById( R.id.tiempoAlistarse );
        tiempoBus = (EditText) findViewById( R.id.tiempoBus );
        tiempoDestino = (EditText) findViewById( R.id.tiempoDestino );
        horaDormir = (TextView) findViewById( R.id.horaDormir );


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void restarHoras(int pCantidadHoras){
        int resultado = hora - pCantidadHoras;

        if(resultado<1){
            resultado+=12;
        }
        hora = resultado;
    }

    void restarMinutos(int pCantidadMinutos){
        int resultado = minutos - pCantidadMinutos;
        if(resultado < 0){
            resultado+= 60;
            restarHoras(1);
        }
        minutos = resultado;
    }

    void mostrarHora(){
        if(minutos<10){
            horaDormir.setText(hora+":0"+minutos);
        }else{
            horaDormir.setText(hora+":"+minutos);
        }
    }

    public void calcularHora(View view){
        hora = Integer.parseInt(horaLlegada.getText().toString());
        minutos = Integer.parseInt(minutoLlegada.getText().toString());
        restarHoras(Integer.parseInt(tiempoDormir.getText().toString()));
        restarMinutos(Integer.parseInt(tiempoAlistarse.getText().toString()));
        restarMinutos(Integer.parseInt(tiempoBus.getText().toString()));
        restarMinutos(Integer.parseInt(tiempoDestino.getText().toString()));
        mostrarHora();
    }
}
