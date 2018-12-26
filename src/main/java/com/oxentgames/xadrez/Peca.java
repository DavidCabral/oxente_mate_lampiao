package com.oxentgames.xadrez;

import java.awt.*;
import java.util.Vector;

public interface Peca {
    /**
     * Modifica a Cor da peça
     *
     * @param novaCor
     */
    public void setCor(Color novaCor);

    /**
     * Pega a cor da peça
     *
     * @return
     */
    public Color getCor();

    /**
     * Modifica a abcissa da peça
     *
     * @param novaAbscissa
     */
    public void setPosicaoX(int novaAbscissa);

    /**
     * Pega a está abcissa da peça no tabuleiro
     *
     * @return
     */
    public int getPosicaoX();

    /**
     * Modifica a ordenada da peça
     *
     * @param novaOrdenada
     */
    public void setPosicaoY(int novaOrdenada);

    /**
     * Pega a está ordenada da peça no tabuleiro.
     *
     * @return
     */
    public int getPosicaoY();

    /**
     * Informa ao tabuleiro quais casas a peça está atacando
     *
     * @param bordas
     */
    public void setCasasQueEstaoEmAtaque(Borda bordas);

    /**
     * Pega do tabuleiro as casa que são visiveis a peça
     *
     * @param bordas
     * @return
     */
    public Vector getCasasQueEstaoVisiveis(Borda bordas);

    /**
     * Pega as casas que a peça está atacando.
     *
     * @param bordas
     * @return as casas que a peça está atacando
     */
    public Vector getCasasQueEstaoEmAtaque(Borda bordas);

    /**
     * Verifica se a casa é visível a peça no tabuleiro.
     *
     * @param bordas
     * @param casa
     * @return <code>true</code> se for visivel e <code>false</code> caso contrario
     */
    public boolean casaVisivel(Borda bordas, CasaItf casa);

    /**
     * Move a peça para uma nova está.
     *
     * @param casaDeOrigem
     * @param casaDeDestino
     * @param bordas
     * @throws MovimentoEPosicaoInvalida
     */
    public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas) throws MovimentoEPosicaoInvalida;
}
