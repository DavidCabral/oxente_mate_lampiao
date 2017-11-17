package com.oxentgames.xadrez;

//Interface que representa o tabuleiro do jogo.

public interface Borda
{
	//Dada as coordenadas o tabuleiro retorna a casa.
	//parametro x Abcissa.
	//parametro y Ordenada.
	//retorna a casa possicionada nas coordenadas.
	
	public CasaItf getCasa(int x, int y);
	
}