package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Solo extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView, image2;
    private ImageButton Pedra, Papel, Tesoura;
    private TextView resultadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);

        imageView = findViewById(R.id.imagem);
        image2 = findViewById(R.id.image2);
        Pedra = findViewById(R.id.Pedra1);
        Papel = findViewById(R.id.Papel1);
        Tesoura = findViewById(R.id.Tesoura1);
        resultadoTextView = findViewById(R.id.textView);

        Pedra.setOnClickListener(this);
        Papel.setOnClickListener(this);
        Tesoura.setOnClickListener(this);

        exibirImagem(imageView, android.R.color.transparent);
        exibirImagem(image2, android.R.color.transparent);
    }

    @Override
    public void onClick(View view) {
        animarImagem();
        animarImagem2();

        int escolhaUsuario = obterEscolhaUsuario(view.getId());
        int escolhaComputador = obterEscolhaComputador();

        agendarProcessamentoResultado(escolhaUsuario, escolhaComputador);
    }

    private int obterEscolhaUsuario(int idBotao) {
        if (idBotao == R.id.Pedra1) {
            return R.drawable.pedra_laranja;
        } else if (idBotao == R.id.Papel1) {
            return R.drawable.papel_azul;
        } else if (idBotao == R.id.Tesoura1) {
            return R.drawable.tesoura_vermelha;
        } else {
            return android.R.color.transparent;
        }
    }

    private int obterEscolhaComputador() {
        Random random = new Random();
        int escolha = random.nextInt(3);

        if (escolha == 0) {
            return R.drawable.pedra_laranja;
        } else if (escolha == 1) {
            return R.drawable.papel_azul;
        } else if (escolha == 2) {
            return R.drawable.tesoura_vermelha;
        } else {
            return android.R.color.transparent;
        }
    }

    private void agendarProcessamentoResultado(final int escolhaUsuario, final int escolhaComputador) {
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                exibirImagem(imageView, escolhaUsuario);
                processarResultado(escolhaUsuario, escolhaComputador);
            }
        }, 500);

        image2.postDelayed(new Runnable() {
            @Override
            public void run() {
                exibirImagem(image2, escolhaComputador);
            }
        }, 500);

        image2.postDelayed(new Runnable() {
            @Override
            public void run() {
                processarResultado(escolhaUsuario, escolhaComputador);
            }
        }, 1000);
    }

    private void processarResultado(int escolhaUsuario, int escolhaComputador) {
        if (escolhaUsuario == escolhaComputador) {
            exibirResultado("Empate!");
        } else if ((escolhaUsuario == R.drawable.pedra_laranja && escolhaComputador == R.drawable.tesoura_vermelha) ||
                (escolhaUsuario == R.drawable.papel_azul && escolhaComputador == R.drawable.pedra_laranja) ||
                (escolhaUsuario == R.drawable.tesoura_vermelha && escolhaComputador == R.drawable.papel_azul)) {
            exibirResultado("Ganhou!");
        } else {
            exibirResultado("Perdeu!");
        }
    }

    private void exibirResultado(String resultado) {
        resultadoTextView.setText(resultado);
        resultadoTextView.setVisibility(View.VISIBLE);
    }

    private void exibirImagem(ImageView imageView, int imagemId) {
        imageView.setImageResource(imagemId);
    }

    private void animarImagem() {
        Animation animacao = AnimationUtils.loadAnimation(this, R.anim.animacao_subir);
        final Animation animacaoResultado = AnimationUtils.loadAnimation(this, R.anim.fade_in_resultado);

        imageView.startAnimation(animacao);

        animacao.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                resultadoTextView.startAnimation(animacaoResultado);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void animarImagem2() {
        Animation animacao = AnimationUtils.loadAnimation(this, R.anim.animacao_descer);
        final Animation animacaoResultado = AnimationUtils.loadAnimation(this, R.anim.fade_in_resultado);

        image2.startAnimation(animacao);

        animacao.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                resultadoTextView.startAnimation(animacaoResultado);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}

