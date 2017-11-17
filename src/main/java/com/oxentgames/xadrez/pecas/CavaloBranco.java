package com.oxentgames.xadrez.pecas;

import com.oxentgames.utilitarios.GeralImagens;
import com.oxentgames.xadrez.Borda;
import com.oxentgames.xadrez.CasaItf;
import com.oxentgames.xadrez.MovimentoEPosicaoInvalida;
import com.oxentgames.xadrez.Peca;
import com.oxentgames.xadrez.gui.Casa;
import com.oxentgames.xadrez.gui.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

//Classe que representa o cavalo branco do xadrez.
public class CavaloBranco extends PecaAbstrata
{
	private Component popupMenu;
	
	public CavaloBranco(int x, int y)
	{
		super(GeralImagens.IMG_CAVALO_BRANCO);
		super.x = x;
		super.y = y;
		super.cor = Color.WHITE;
	}

	public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas)throws MovimentoEPosicaoInvalida
	{
		Vector casasTemporarias = this.getCasasQueEstaoEmAtaque(bordas);
		
		if (casasTemporarias.contains(casaDeDestino))
		{
			Peca pecaCasaDestino = casaDeDestino.getPeca();
			casaDeOrigem.removePeca();
			casaDeDestino.removePeca();
			casaDeDestino.setPeca(this);
			
			Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
			tabuleiroTemporario.removeTodosAtaques();
			tabuleiroTemporario.atualizaAtaquePreto();
			tabuleiroTemporario.atualizaAtaqueBranco();
			
			if (tabuleiroTemporario.ehChequeMatePreto())
			{
				((Tabuleiro)bordas).mensagemNoFimDoJogo("Xeque-mate! Cangaceiros Vencem!");
			}
			else if (tabuleiroTemporario.ehChequeBranco())
			{
				casaDeOrigem.removePeca();
				casaDeOrigem.setPeca(this);
				casaDeDestino.removePeca();
				casaDeDestino.setPeca(pecaCasaDestino);
			
				JOptionPane.showMessageDialog(popupMenu, "Lampião está em xeque, está inválida","ERRO",JOptionPane.ERROR_MESSAGE);
				throw new MovimentoEPosicaoInvalida("Lampião está em xeque, está inválida");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(popupMenu, "Jegue: Oxente bixin, está inválida!","ERRO",JOptionPane.ERROR_MESSAGE);
			throw new MovimentoEPosicaoInvalida("Jegue: Oxente bixin, está inválida!");
		}
		
	}

	public void setCasasQueEstaoEmAtaque(Borda bordas)
	{
		Vector casasTemporarias = new Vector();
		// 8 jogadas do cavalo
		casasTemporarias.add(bordas.getCasa(x-1,y-2));
		casasTemporarias.add(bordas.getCasa(x+1,y-2));
		casasTemporarias.add(bordas.getCasa(x-2,y+1));
		casasTemporarias.add(bordas.getCasa(x-2,y-1));
		casasTemporarias.add(bordas.getCasa(x+2,y+1));
		casasTemporarias.add(bordas.getCasa(x+2,y-1));
		casasTemporarias.add(bordas.getCasa(x-1,y+2));
		casasTemporarias.add(bordas.getCasa(x+1,y+2));
		
		Iterator it = casasTemporarias.iterator();
		CasaItf casaTemporaria;
		while (it.hasNext())
		{
			casaTemporaria = (CasaItf)it.next();
			if (casaTemporaria!=null)
			{
				((Casa)casaTemporaria).setAtaqueBranco(true);
			}		
		}
	}

	public Vector getCasasQueEstaoVisiveis(Borda bordas)
	{
		super.casasQueEstaoVisiveis = new Vector();
		Vector casasTemporarias = new Vector();
		// 8 jogadas do cavalo
		casasTemporarias.add(bordas.getCasa(x-1,y-2));
		casasTemporarias.add(bordas.getCasa(x+1,y-2));
		casasTemporarias.add(bordas.getCasa(x-2,y+1));
		casasTemporarias.add(bordas.getCasa(x-2,y-1));
		casasTemporarias.add(bordas.getCasa(x+2,y+1));
		casasTemporarias.add(bordas.getCasa(x+2,y-1));
		casasTemporarias.add(bordas.getCasa(x-1,y+2));
		casasTemporarias.add(bordas.getCasa(x+1,y+2));
		
		Iterator it = casasTemporarias.iterator();
		CasaItf casaTemporaria;
		while (it.hasNext())
		{
			casaTemporaria = (CasaItf)it.next();
			if (casaTemporaria!=null)
			{
				casasQueEstaoVisiveis.add(casaTemporaria);
			}		
		}
		return casasQueEstaoVisiveis;
	}

	public Vector getCasasQueEstaoEmAtaque(Borda bordas)
	{
		super.casasQueEstaoEmAtaque = new Vector();
		Vector casasTemporarias = new Vector();
		// 8 jogadas do cavalo
		casasTemporarias.add(bordas.getCasa(x-1,y-2));
		casasTemporarias.add(bordas.getCasa(x+1,y-2));
		casasTemporarias.add(bordas.getCasa(x-2,y+1));
		casasTemporarias.add(bordas.getCasa(x-2,y-1));
		casasTemporarias.add(bordas.getCasa(x+2,y+1));
		casasTemporarias.add(bordas.getCasa(x+2,y-1));
		casasTemporarias.add(bordas.getCasa(x-1,y+2));
		casasTemporarias.add(bordas.getCasa(x+1,y+2));
		
		Iterator it = casasTemporarias.iterator();
		CasaItf casaTemporaria;
		while (it.hasNext())
		{
			casaTemporaria = (CasaItf)it.next();
			if (casaTemporaria!=null)
			{
				if (casaTemporaria.temPeca() && (casaTemporaria.getPeca().getCor()==Color.BLACK))
				{
					casasQueEstaoEmAtaque.add(casaTemporaria);
				}
				else if(!casaTemporaria.temPeca())
				{
					casasQueEstaoEmAtaque.add(casaTemporaria);
				}
			}		
		}
		return casasQueEstaoEmAtaque;
	}

	public boolean casaVisivel(Borda bordas, CasaItf casa)
	{
		Vector casasTemporarias = this.getCasasQueEstaoVisiveis(bordas);
		if (casasTemporarias.contains(casa))
			return true;
		return false;
	}
}
