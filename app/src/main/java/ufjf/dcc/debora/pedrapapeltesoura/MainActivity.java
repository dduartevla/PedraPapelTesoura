package ufjf.dcc.debora.pedrapapeltesoura;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Integer pontosComputador = 0;
    private Integer pontosHumano = 0;

    private Button buttonPedra;
    private Button buttonPapel;
    private Button buttonTesoura;
    private Button buttonLagarto;
    private Button buttonSpock;

    private ProgressBar progressBarComputador;
    private ProgressBar progressBarHumano;

    private TextView textViewStatus;

    private Random dado = new Random();

    public enum Jogada{
        PEDRA(0), PAPEL(1), TESOURA(2), LAGARTO(3), SPOCK(4);

        public final int valor;

        Jogada(int valor){
            this.valor = valor;
        }
    }

    public enum Resultado{
        DERROTA(-1), EMPATE(0), VITORIA(1);

        private final int valor;

        Resultado(int valor){
            this.valor = valor;
        }
    }

    public static final Resultado TABELA [] [] = {
            {Resultado.EMPATE, Resultado.DERROTA, Resultado.VITORIA, Resultado.VITORIA, Resultado.DERROTA},
            {Resultado.VITORIA, Resultado.EMPATE, Resultado.DERROTA, Resultado.DERROTA, Resultado.VITORIA},
            {Resultado.DERROTA, Resultado.VITORIA, Resultado.EMPATE, Resultado.VITORIA, Resultado.DERROTA},
            {Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA, Resultado.EMPATE, Resultado.VITORIA},
            {Resultado.VITORIA, Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA, Resultado.EMPATE},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Componentes de entrada
        buttonPapel = findViewById(R.id.buttonPapel);
        buttonPedra = findViewById(R.id.buttonPedra);
        buttonTesoura = findViewById(R.id.buttonTesoura);
        buttonLagarto = findViewById(R.id.buttonLagarto);
        buttonSpock = findViewById(R.id.buttonSpock);

        //Componentes de Saída
        progressBarComputador = findViewById(R.id.progressBarComputador);
        progressBarHumano = findViewById(R.id.progressBarHumano);

        textViewStatus = findViewById(R.id.textViewStatus);

    }

    public void buttonPedraClick(View view){
        rodada(Jogada.PEDRA);
    }

    public void buttonPapelClick(View view){
        rodada(Jogada.PAPEL);
    }

    public void buttonTesouraClick(View view){
        rodada(Jogada.TESOURA);
    }

    public void buttonLagartoClick(View view) {rodada(Jogada.LAGARTO);}

    public void buttonSpockClick(View view) {rodada(Jogada.SPOCK);}



    public void rodada(Jogada jogada){
        Jogada jogadaComputador = Jogada.values()[dado.nextInt(5)];
        Toast.makeText(this,"O computador jogou: " + jogadaComputador.name(),Toast.LENGTH_SHORT).show();

        switch (TABELA[jogada.valor][jogadaComputador.valor]){
            case VITORIA:
                pontosHumano += 3;
                Toast.makeText(this,"O humano ganhou!",Toast.LENGTH_SHORT).show();
                break;

            case EMPATE:
                pontosComputador ++;
                Toast.makeText(this,"Empate!",Toast.LENGTH_SHORT).show();
                pontosHumano ++;
                break;

            case DERROTA:
                pontosComputador +=3;
                Toast.makeText(this,"O computador ganhou!",Toast.LENGTH_SHORT).show();
                break;
        }
        atualizaStatus();
    }

    private void atualizaStatus(){
        progressBarComputador.setProgress(pontosComputador);
        progressBarHumano.setProgress(pontosHumano);

        if (pontosHumano <15 && pontosComputador <15){
            textViewStatus.setText("Escolha uma opção...");
        } else if (pontosHumano >=15 && pontosComputador<15){
            textViewStatus.setText("Humano Venceu!");
            iniciaTorneio();
        } else if (pontosHumano <15 && pontosComputador>=15){
            textViewStatus.setText("Computador Venceu");
            iniciaTorneio();
        } else {
            textViewStatus.setText("Empate");
            iniciaTorneio();
        }
    }

    public void iniciaTorneio(){
        pontosHumano = 0;
        pontosComputador = 0;
        progressBarComputador.setProgress(pontosComputador);
        progressBarHumano.setProgress(pontosHumano);

    }

    public void TextViewStatusClick(View view){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Reiniciar o torneio");
        dialogBuilder.setMessage("Deseja reiniciar o torneio?");
        dialogBuilder.setPositiveButton("Reiniciar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        iniciaTorneio();
                        atualizaStatus();
                    }
                }
        );
        dialogBuilder.create();
        dialogBuilder.show();
    }
}
