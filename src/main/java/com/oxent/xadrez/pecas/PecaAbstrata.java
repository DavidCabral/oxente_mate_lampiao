package com.oxent.xadrez.pecas;

import com.oxent.utilitarios.CarregaImagemUtil;
import com.oxent.xadrez.Borda;
import com.oxent.xadrez.CasaItf;
import com.oxent.xadrez.MovimentoEPosicaoInvalida;
import com.oxent.xadrez.Peca;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public abstract class PecaAbstrata extends JLabel implements Peca {
    // atributos
    protected Vector casasQueEstaoVisiveis;
    protected Vector casasQueEstaoEmAtaque;
    protected Color cor;
    protected int x;
    protected int y;

    protected PecaAbstrata(String path) {
        super.setIcon(CarregaImagemUtil.createImageIcon(path));
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color novaCor) {
        cor = novaCor;
    }

    public int getPosicaoX() {
        return x;
    }

    public void setPosicaoX(int novaAbcissa) {
        this.x = novaAbcissa;
    }

    public int getPosicaoY() {
        return y;
    }

    public void setPosicaoY(int novaOrdenada) {
        this.y = novaOrdenada;
    }

    public abstract void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas) throws MovimentoEPosicaoInvalida;

    public abstract Vector getCasasQueEstaoVisiveis(Borda bordas);

    public abstract Vector getCasasQueEstaoEmAtaque(Borda bordas);

    public abstract void setCasasQueEstaoEmAtaque(Borda bordas);

    public abstract boolean casaVisivel(Borda bordas, CasaItf casas);
}
