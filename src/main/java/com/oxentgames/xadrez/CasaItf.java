package com.oxentgames.xadrez;

import java.awt.*;

//Interface que representa a casa do tabuleiro.
public interface CasaItf
{
	//Modifica a abscissa da casa
	public void setPosicaoX(int novaAbscissa);
	
	//Pega a abscissa da casa.
	public int getPosicaoX();
	
	//Modifica a ordenada da casa
	public void setPosicaoY(int novaOrdenada);
	
	//Pega a ordenada da casa
	public int getPosicaoY();
	
	//Modifica a cor da casa
	public void setCor(Color novaCor);
	
	//Pega a cor da casa
	public Color getCor();

	//Modifica a pe�a que vai ocupar a casa
	public void setPeca(Peca novaPeca);
    
	//Remove a pe�a da casa
	public void removePeca();
	
    //Pega a pe�a que está ocupando a casa
	public Peca getPeca();

	//Verifica se a casa tem pe�a 
	public boolean temPeca();

	//Verifica se a casa tem uma pe�a da seguinte cor.
	public boolean temPeca(Color cor);
	
	//Verifica se a casa � igual. 
	public boolean verificaCasaIgual(CasaItf casa);

	//Verifica se essa casa faz parte do passante.
	public boolean getMovimentoPassante();

	//Seta essa casa como passante.
	public void setMovimentoPassante(boolean passante);
}