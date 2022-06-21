package ufjf.dcc.debora.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonPedra;
    private Button buttonPapel;
    private Button buttonTesoura;

    private ProgressBar progressBarComputador;
    private ProgressBar progressBarHumano;

    private TextView textViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Componentes de entrada
        buttonPapel = findViewById(R.id.buttonPapel);
        buttonPedra = findViewById(R.id.buttonPedra);
        buttonTesoura = findViewById(R.id.buttonTesoura);

        //Componentes de Saída
        progressBarComputador = findViewById(R.id.progressBarComputador);
        progressBarHumano = findViewById(R.id.progressBarHumano);

        textViewStatus = findViewById(R.id.textViewStatus);

    }

    public void buttonPedraClick(View view){

    }

    public void buttonPapelClick(View view){

    }

    public void buttonTesouraClick(View view){

    }
}