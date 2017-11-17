package com.oxentgames.xadrez;

import java.awt.*;
import java.util.Vector;

public interface Peca
{
	//Modifica a Cor da pe�a
	public void setCor(Color novaCor);

	//Pega a cor da pe�a
	public Color getCor();

	//Modifica a abcissa da pe�a
	public void setPosicaoX(int novaAbscissa);
	
	//Pega a está abcissa da pe�a no tabuleiro
	public int getPosicaoX();

	//Modifica a ordenada da pe�a
	public void setPosicaoY(int novaOrdenada);
	
	//Pega a está ordenada da pe�a no tabuleiro.
	public int getPosicaoY();

	//Informa ao tabuleiro quais casas a pe�a está atacando
	public void setCasasQueEstaoEmAtaque(Borda bordas);

	//Pega do tabuleiro as casa que s�o visiveis a pe�a
	public Vector getCasasQueEstaoVisiveis(Borda bordas);

	//Pega as casa que a pe�a está atacando.
	public Vector getCasasQueEstaoEmAtaque(Borda bordas);

	//Verifica se a casa � vis�vel a pe�a no tabuleiro.
	public boolean casaVisivel(Borda bordas, CasaItf casa);

	//Move a pe�a para uma nova está.
	public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas) throws MovimentoEPosicaoInvalida;
}
