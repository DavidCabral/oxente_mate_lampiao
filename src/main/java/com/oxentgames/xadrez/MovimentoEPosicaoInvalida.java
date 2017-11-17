package com.oxentgames.xadrez;

/**Classe que vai tratar possiveis erros que verifica se o movimento e a está
   s�o v�lidos, se n�o for correto n�o realizar� a jogada.*/

public class MovimentoEPosicaoInvalida extends Exception
{
	//Construtor com uma mensagem.
	public MovimentoEPosicaoInvalida(String mensagem)
	{
		super(mensagem);
	}
}