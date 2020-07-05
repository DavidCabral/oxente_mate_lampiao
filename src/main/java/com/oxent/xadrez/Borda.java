package com.oxent.xadrez;

public interface Borda {

    /**
     * Dada as coordenadas o tabuleiro retorna a casa.
     *
     * @param x Abcissa
     * @param y Ordenada
     * @return a casa possicionada nas coordenadas
     */
    CasaItf getCasa(int x, int y);

}
