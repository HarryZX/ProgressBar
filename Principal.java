package com.example.a21_pc25.progressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//Declaramos las variables
private static int contProgreso = 0;
private static int estadoProceso = 0;
private ProgressBar pgbarHorizontal;
private Button btnDescargar;
private Handler manejarProcesos;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Inicializamos los controles
        this.pgbarHorizontal = findViewById(R.id.pgbarHorizontal);
        this.btnDescargar = findViewById(R.id.btnDescargar);
        this.manejarProcesos = new Handler();

        //Modificamos las propiedades de los controles
        this.pgbarHorizontal.setMax(100);
    }

    //El evento click del boton
    public void OnClick (View v){
        //creamos el hilo secundario
        new Thread(new HiloSecundario()).start();
    }
    //Hilo secundario
    //==========================
    final class HiloSecundario implements Runnable{
        @Override
        public void run(){
            while (contProceso < 100){

                MetodoEspera();
                //Establecemos un manejador para la parte visual
                //===========================================
                pgbarHorizontal.setProgress(contProgreso);
                //Preguntamos si ya se termino de completar el progressbar
                if (contProgreso == 100){
                    //si el proceso ya termno mostramos el mensaje
                    Toast.makeText(Principal.this,
                            "Proceso Concluido",
                            Toast.LENGTH_LONG).show();
                }
            }
        }

        //Metodo que simula un tiempo de espera
        private void MetodoEspera(){
            try{
                Thread.sleep(100);
                contProgreso++;
            }catch (Exception e){

            }
        }
    }
}
