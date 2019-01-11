package com.oxentgames.xadrez;

import java.awt.*;

/**
 * Interface que representa as casas do tabuleiro.
 */
public interface CasaItf {

    /**
     * Pega a posição que está no eixo X (abcissa)
     *
     * @return posição atual no eixo X
     */
    int getPosicaoX();

    /**
     * Pega a posição que está no eixo Y (ordenada)
     *
     * @return posição atual no eixo Y
     */
    int getPosicaoY();


    /**
     * Modifica a cor da casa
     *
     * @param cor Nova cor da casa
     */
    void setCor(Color cor);

    /**
     * Pega a cor da casa
     *
     * @return Cor da casa
     */
    Color getCor();

    /**
     * Insere uma peça na casa
     *
     * @param peca Peça que irá ocupar a casa
     */
    void setPeca(Peca peca);

    /**
     * Remove a peça da casa
     */
    void removePeca();

    /**
     * Pega a peça que está ocupando a casa
     *
     * @return Peça que está na casa
     */
    Peca getPeca();

    /**
     * Verifica se a casa tem alguma peça
     *
     * @return <code>true</code> se tiver alguma peça e <code>false</code> caso contrario
     */
    boolean temPeca();

    /**
     * Verifica se a casa tem alguma peça de uma determinada cor
     *
     * @return <code>true</code> se tiver alguma peça da cor desejada e <code>false</code> caso contrario
     */
    boolean temPeca(Color cor);

    /**
     * Verifica se a casa é igual.
     *
     * @param casa Casa a ser comparada
     * @return <code>true</code> se as casas forem iguais e <code>false</code> caso contrario
     */
    boolean verificaCasaIgual(CasaItf casa);

    /**
     * Verifica se a casa faz parte do passante.
     *
     * @return <code>true</code> se fizer parte do movimento passante e <code>false</code> caso contrario
     */
    boolean getMovimentoPassante();

    /**
     * Seta essa casa como passante.
     *
     * @param passante <code>true</code> se for passante e <code>false</code> caso contrario
     */
    void setMovimentoPassante(boolean passante);

}