package com.oxentgames.xadrez;

import java.awt.*;
import java.util.Vector;

/**
 * Interface que representa as peças do tabuleiro
 */
public interface Peca {

    /**
     * Modifica a cor da peça
     *
     * @param cor Nova cor da peça
     */
    void setCor(Color cor);

    /**
     * Pega a cor da peça
     *
     * @return Cor da peça
     */
    Color getCor();

    /**
     * Move a peça no Eixo X (abcissa)
     *
     * @param posicao Posição para qual a peça irá
     */
    void setPosicaoX(int posicao);

    /**
     * Pega a posição que está no eixo X (abcissa)
     *
     * @return posição atual no eixo X
     */
    int getPosicaoX();

    /**
     * Move a peça no eixo Y (ordenada)
     *
     * @param posicao Posição para qual a peça irá
     */
    void setPosicaoY(int posicao);

    /**
     * Pega a posição que está no eixo Y (ordenada)
     *
     * @return posição atual no eixo Y
     */
    int getPosicaoY();

    /**
     * Informa ao tabuleiro quais casas a peça está atacando
     *
     * @param bordas
     */
    void setCasasQueEstaoEmAtaque(Borda bordas);

    /**
     * Pega do tabuleiro as casa que são visiveis a peça
     *
     * @param bordas
     * @return
     */
    Vector getCasasQueEstaoVisiveis(Borda bordas);

    /**
     * Pega as casas que a peça está atacando.
     *
     * @param bordas
     * @return as casas que a peça está atacando
     */
    Vector getCasasQueEstaoEmAtaque(Borda bordas);

    /**
     * Verifica se a casa é visível a peça no tabuleiro.
     *
     * @param bordas
     * @param casa
     * @return <code>true</code> se for visivel e <code>false</code> caso contrario
     */
    boolean casaVisivel(Borda bordas, CasaItf casa);

    /**
     * Move a peça para uma nova está.
     *
     * @param casaDeOrigem
     * @param casaDeDestino
     * @param bordas
     * @throws MovimentoEPosicaoInvalida
     */
    void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas) throws MovimentoEPosicaoInvalida;
}
