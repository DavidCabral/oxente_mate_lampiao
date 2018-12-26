package com.oxentgames.xadrez.gui;

import com.oxentgames.utilitarios.CarregaImagemUtil;
import com.oxentgames.utilitarios.GeralImagens;
import com.oxentgames.xadrez.CasaItf;
import com.oxentgames.xadrez.Peca;

import javax.swing.*;
import java.awt.*;

//Classe que representa a casa do xadrez.
public class Casa extends JLabel implements CasaItf
{	
	// atributos.
	private boolean ataquePreto = false, ataqueBranco = false;
	private boolean passante = false;
	private Color cor;
	private int x;
	private int y;
	private Peca peca = null;
	private JLabel bordaJLabel;

	public Casa(int x, int y, Color cor)
	{
		this.x = x;
		this.y = y;
		this.cor = cor;
		bordaJLabel  = new JLabel(CarregaImagemUtil.createImageIcon(GeralImagens.IMG_CASA_BORDA));
		if (cor == Color.WHITE)
		{
			this.setIcon(CarregaImagemUtil.createImageIcon(GeralImagens.IMG_CASA_BRANCA));
		}
		else
		{
			this.setIcon(CarregaImagemUtil.createImageIcon(GeralImagens.IMG_CASA_PRETA));
		}
		super.setBounds(80*(x),80*(y),80,80);
	}

	//Adiciona a borda na casa.
	public void pintaBorda()
	{
		super.add(bordaJLabel).setBounds(0,0,80,80);
		super.repaint();
	}

	//Remove a borda da casa.
	public void removeBorda()
	{
		for (int i = 0; i < super.getComponentCount(); i++)
			if (bordaJLabel==super.getComponent(i))
			{
				super.remove(getComponent(i));
			}
		super.repaint();
	}

	public Color getCor()
	{
		return cor;
	}

	public void setCor(Color novaCor)
	{
		this.cor = novaCor;
	}

	public int getPosicaoX()
	{
		return x;
	}

	public void setPosicaoX(int novaAbcissa)
	{
		this.x = novaAbcissa;
	}

	public int getPosicaoY()
	{
		return y;
	}

	public void setPosicaoY(int novaOrdenada)
	{
		this.y = novaOrdenada;
	}

	public Peca getPeca()
	{
		return peca;
	}

	public void setPeca(Peca novaPeca)
	{
		if (novaPeca !=null)
		{
			novaPeca.setPosicaoX(x);
			novaPeca.setPosicaoY(y);
			this.peca = novaPeca;
			super.add((JComponent)peca).setBounds(10,-10,95,95);
			super.repaint();
		}
		else
		{
			peca = novaPeca;
		}
	}

	public void removePeca()
	{
		peca = null;
		super.removeAll();
		super.repaint();
	}

	public boolean temPeca()
	{
		return (peca!=null)?true:false;
	}

	public boolean temPeca(Color cor)
	{
		if (peca == null)
			return false;
		return (peca.getCor()==cor)?true:false;
	}

	//Pergunta se a casa está sendo atacada por uma peça preta.
	public boolean ehAtaquePreto()
	{
		return ataquePreto;
	}
	
	//Pergunta se a casa está sendo atacada por uma peça branca.
	public boolean ehAtaqueBranco()
	{
		return ataqueBranco;
	}

	//Remove o status de ataque branco e preto. 
	public void removeTodosAtaques()
	{
		ataquePreto = false;
		ataqueBranco = false;
	}

	//Informa a casa que ela está sendo atacada por uma peça preta.
	public void setAtaquePreto(boolean ataque)
	{
		ataquePreto = ataque;
	}

	//Informa a casa que ela está sendo atacada por uma peça branca
	public void setAtaqueBranco(boolean ataque)
	{
		ataqueBranco = ataque;
	}

	public boolean getMovimentoPassante()
	{
		return passante;
	}

	public void setMovimentoPassante(boolean passante)
	{
		this.passante = passante;
	}

	//Verifica se casa é igual.
	public boolean verificaCasaIgual(CasaItf casa)
	{
		return (this.x==casa.getPosicaoX() 
				&& this.y == casa.getPosicaoY())?true:false;
	}

	//Pega o String da casa. Que tem como modelo -> Casa = (x,y)
	public String toString()
	{
		return "Casa = ("+x+","+y+")";
	}	
}
