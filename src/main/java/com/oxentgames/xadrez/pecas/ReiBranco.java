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

//Classe que representa a rei branco do xadrez.
public class ReiBranco extends PecaAbstrata
{
	private boolean movimentou = false;
	private Component popupMenu;
	
	public ReiBranco( int x, int y)
	{
		super(GeralImagens.IMG_REI_BRANCO);
		super.x = x;
		super.y = y;
		super.cor = Color.WHITE;
	}

	public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas)throws MovimentoEPosicaoInvalida
	{	
		if ((x!=4 ) && (y!=7))
		{
			movimentou = true;
		}
		Vector casasPossiveis = new Vector();
		Vector casasTemporarias = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.
		casasTemporarias.add(bordas.getCasa(x+1,y+1));
		casasTemporarias.add(bordas.getCasa(x+1,y-1));
		casasTemporarias.add(bordas.getCasa(x+1,y));
		casasTemporarias.add(bordas.getCasa(x-1,y+1));
		casasTemporarias.add(bordas.getCasa(x-1,y-1));
		casasTemporarias.add(bordas.getCasa(x-1,y));
		casasTemporarias.add(bordas.getCasa(x,y+1));
		casasTemporarias.add(bordas.getCasa(x,y-1));
		
		// possíveis posições.
		CasaItf casaTemporaria=null;
		Iterator it = casasTemporarias.iterator();
		while(it.hasNext())
		{
			casaTemporaria = (CasaItf)it.next();
			if (casaTemporaria!=null)
			{	
				if (!((Casa)casaTemporaria).ehAtaquePreto() && !casaTemporaria.temPeca(Color.WHITE))
				{
					casasPossiveis.add(casaTemporaria);
				}
			}
		}
		
		// verifica se pode fazer roque do lado esquerdo.	
		if ( (!reiMovimentou()) && (casaDeDestino.getPosicaoY()==7) &&
		     (casaDeDestino.getPosicaoX()==6) && !((Tabuleiro)bordas).ehChequeBranco())
		{
		    Peca pecaTemporaria = bordas.getCasa(7,7).getPeca();
		    CasaItf casa6_7 = bordas.getCasa(6,7);
		    CasaItf casa5_7 = bordas.getCasa(5,7);
		    if ((pecaTemporaria!=null) && (pecaTemporaria instanceof TorreBranca) &&
		        (!((TorreBranca)pecaTemporaria).torreMovimentou()) && (casa6_7.getPeca()==null) &&
		        !((Casa)casa6_7).ehAtaquePreto() && (casa5_7.getPeca()==null) &&
				!((Casa)casa5_7).ehAtaquePreto())
		    {
		        	casasPossiveis.add(bordas.getCasa(6,7));
		    }
		}
		
		// verifica se pode fazer roque do lado direito.
		if ( (!reiMovimentou()) && (casaDeDestino.getPosicaoY()==7) &&
			 (casaDeDestino.getPosicaoX()==2) && !((Tabuleiro)bordas).ehChequeBranco())
		{
				Peca pecaTemporaria = bordas.getCasa(0,7).getPeca();
				CasaItf casa2_7 = bordas.getCasa(2,7);
				CasaItf casa3_7 = bordas.getCasa(3,7);
				CasaItf casa1_7 = bordas.getCasa(1,7);
				if ((pecaTemporaria!=null) &&	(pecaTemporaria instanceof TorreBranca) &&
					(!((TorreBranca)pecaTemporaria).torreMovimentou()) && (casa2_7.getPeca()==null) &&
					!((Casa)casa2_7).ehAtaquePreto() && (casa3_7.getPeca()==null) &&
					!((Casa)casa3_7).ehAtaquePreto() && (casa1_7.getPeca()==null))
				{
						casasPossiveis.add(bordas.getCasa(2,7));
				}
			}
		
		if (casasPossiveis.contains(casaDeDestino))
		{			
			if (!reiMovimentou() && (casaDeDestino.getPosicaoX()== 6) && (casaDeDestino.getPosicaoY()==7))
			{
				casaDeOrigem.removePeca();
				casaDeDestino.setPeca(this);
				Peca torre = bordas.getCasa(7,7).getPeca();
				bordas.getCasa(5,7).setPeca(torre);
				bordas.getCasa(7,7).removePeca();
				Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
				tabuleiroTemporario.removeTodosAtaques();
				tabuleiroTemporario.atualizaAtaqueBranco();
				tabuleiroTemporario.atualizaAtaquePreto();		
			}
			else if (!reiMovimentou() && (casaDeDestino.getPosicaoX()== 2) && (casaDeDestino.getPosicaoY()==7))
			{
				casaDeOrigem.removePeca();
				casaDeDestino.setPeca(this);
				Peca torre = bordas.getCasa(0,7).getPeca();
				bordas.getCasa(3,7).setPeca(torre);
				bordas.getCasa(0,7).removePeca();
				Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
				tabuleiroTemporario.removeTodosAtaques();
				tabuleiroTemporario.atualizaAtaqueBranco();
				tabuleiroTemporario.atualizaAtaquePreto();			
			}
			else
			{
				Peca pecaTemporaria = casaDeDestino.getPeca();
				casaDeOrigem.removePeca();
				casaDeDestino.removePeca();
				casaDeDestino.setPeca(this);
				Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
				tabuleiroTemporario.removeTodosAtaques();
				tabuleiroTemporario.atualizaAtaqueBranco();
				tabuleiroTemporario.atualizaAtaquePreto();
				
				if (tabuleiroTemporario.ehChequeBranco())
				{
					casaDeOrigem.setPeca(this);
					casaDeDestino.removePeca();
					casaDeDestino.setPeca(pecaTemporaria);
					tabuleiroTemporario.removeTodosAtaques();
					tabuleiroTemporario.atualizaAtaqueBranco();
					tabuleiroTemporario.atualizaAtaquePreto();
					
					JOptionPane.showMessageDialog(popupMenu, "Lampião está em xeque, está inválida","ERRO",JOptionPane.ERROR_MESSAGE);
					throw new MovimentoEPosicaoInvalida("Lampião está em xeque");
				}
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(popupMenu, "Lampião: Oxente bixin, está inválida","ERRO",JOptionPane.ERROR_MESSAGE);
			throw new MovimentoEPosicaoInvalida("Lampião: Oxente bixin, está inválida");
		}	
	}

	public void setCasasQueEstaoEmAtaque(Borda bordas)
	{
		Vector casasTemporarias = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.
		casasTemporarias.add(bordas.getCasa(x+1,y+1));
		casasTemporarias.add(bordas.getCasa(x+1,y-1));
		casasTemporarias.add(bordas.getCasa(x+1,y));
		casasTemporarias.add(bordas.getCasa(x-1,y+1));
		casasTemporarias.add(bordas.getCasa(x-1,y-1));
		casasTemporarias.add(bordas.getCasa(x-1,y));
		casasTemporarias.add(bordas.getCasa(x,y+1));
		casasTemporarias.add(bordas.getCasa(x,y-1));
		// possíveis posições.
		CasaItf casaTemporaria=null;
		Iterator it = casasTemporarias.iterator();
		while(it.hasNext())
		{
			casaTemporaria = (CasaItf)it.next();
			if (casaTemporaria!=null)
			{		
				if (!((Casa)casaTemporaria).ehAtaquePreto() && !casaTemporaria.temPeca(Color.WHITE))
				{
					//casasTemp.add(casaTemp);
					((Casa)casaTemporaria).setAtaqueBranco(true);
				}
			}				
		}		
	}
	
	//Verifica se o rei já se movimento.
	public boolean reiMovimentou()
	{
		return movimentou;
	}

	public Vector getCasasQueEstaoVisiveis(Borda bordas)
	{
		casasQueEstaoVisiveis = new Vector();
		Vector casasTemporarias = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.
		casasTemporarias.add(bordas.getCasa(x+1,y+1));
		casasTemporarias.add(bordas.getCasa(x+1,y-1));
		casasTemporarias.add(bordas.getCasa(x+1,y));
		casasTemporarias.add(bordas.getCasa(x-1,y+1));
		casasTemporarias.add(bordas.getCasa(x-1,y-1));
		casasTemporarias.add(bordas.getCasa(x-1,y));
		casasTemporarias.add(bordas.getCasa(x,y+1));
		casasTemporarias.add(bordas.getCasa(x,y-1));
		
		// possíveis posições.
		CasaItf casaTemporaria=null;
		Iterator it = casasTemporarias.iterator();
		while(it.hasNext())
		{
			casaTemporaria = (CasaItf)it.next();
			if (casaTemporaria!=null)
			{
				casasQueEstaoVisiveis.add((Casa)casaTemporaria);	
			}
		}
		return casasQueEstaoVisiveis;
	}

	public Vector getCasasQueEstaoEmAtaque(Borda bordas) {
		super.casasQueEstaoEmAtaque = new Vector();
		Vector casasTemporaria = new Vector();

		// Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.
		casasTemporaria.add(bordas.getCasa(x+1,y+1));
		casasTemporaria.add(bordas.getCasa(x+1,y-1));
		casasTemporaria.add(bordas.getCasa(x+1,y));
		casasTemporaria.add(bordas.getCasa(x-1,y+1));
		casasTemporaria.add(bordas.getCasa(x-1,y-1));
		casasTemporaria.add(bordas.getCasa(x-1,y));
		casasTemporaria.add(bordas.getCasa(x,y+1));
		casasTemporaria.add(bordas.getCasa(x,y-1));
		
		// possíveis posições.
		CasaItf casaTemporaria=null;
		Iterator it = casasTemporaria.iterator();
		while(it.hasNext()){
			casaTemporaria = (CasaItf)it.next();
			if (casaTemporaria!=null)
			{	if (!((Casa)casaTemporaria).ehAtaquePreto() && !casaTemporaria.temPeca(Color.WHITE))
				{
					casasQueEstaoEmAtaque.add(casaTemporaria);
				}
			}
		}
		return casasQueEstaoEmAtaque;
	}

	public boolean casaVisivel(Borda bordas, CasaItf casa)
	{
		casasQueEstaoVisiveis = this.getCasasQueEstaoVisiveis(bordas);
		if (casasQueEstaoVisiveis.contains(casa))
		{
			return true;
		}
		return false;
	}
}