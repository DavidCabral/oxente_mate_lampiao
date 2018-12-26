package com.oxentgames.xadrez;

public interface Borda
{

	/**
	 * Dada as coordenadas o tabuleiro retorna a casa.
	 *
	 * @param x Abcissa
	 * @param y Ordenada
	 * @return a casa possicionada nas coordenadas
	 */
	public CasaItf getCasa(int x, int y);
	
}