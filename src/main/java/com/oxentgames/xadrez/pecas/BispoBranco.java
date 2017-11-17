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

//Classe que representa o bispo branco do xadrez.
public class BispoBranco extends PecaAbstrata
{
	private Component popupMenu;
	
	public BispoBranco( int x, int y)
	{
		super(GeralImagens.IMG_BISPO_BRANCO);
		super.x = x;
		super.y = y;
		super.cor = Color.WHITE;
	}

	public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas)throws MovimentoEPosicaoInvalida
	{		
		Vector casasTemporarias = this.getCasasQueEstaoEmAtaque(bordas);
		
		// Verifica se a casa destino está contida numas das possíveis casas de movimento.
		if (casasTemporarias.contains(casaDeDestino))
		{	
			Peca pecaCasaDeDestino = casaDeDestino.getPeca();
			casaDeOrigem.removePeca();
			casaDeDestino.removePeca();
			casaDeDestino.setPeca(this);
			
			Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
			tabuleiroTemporario.removeTodosAtaques();
			tabuleiroTemporario.atualizaAtaqueBranco();
			tabuleiroTemporario.atualizaAtaquePreto();
			
			if (tabuleiroTemporario.ehChequeMatePreto())
			{
				
				((Tabuleiro)bordas).mensagemNoFimDoJogo("Xeque-mate! Cangaceiros Vencem!");
			}
			else if (tabuleiroTemporario.ehChequeBranco())
			{
				casaDeOrigem.removePeca();
				casaDeOrigem.setPeca(this);
				casaDeDestino.removePeca();
				casaDeDestino.setPeca(pecaCasaDeDestino);
				
				JOptionPane.showMessageDialog(popupMenu, "Lampião está em xeque, Posição inválida","ERRO",JOptionPane.ERROR_MESSAGE);
				throw new MovimentoEPosicaoInvalida("Lampião está em xeque, Proteja-o");
			}	
		}
		else
		{
			JOptionPane.showMessageDialog(popupMenu, "Padin ciço: Oxente bixin, Posição inválida!","ERRO",JOptionPane.ERROR_MESSAGE);
			throw new MovimentoEPosicaoInvalida("Padin ciço: Oxente bixin, Posição inválida!");
		}	
	}

	public void setCasasQueEstaoEmAtaque(Borda bordas)
	{
		// seta ataque para diagonal inferior direita
		Vector casasTemporarias = this.getCasasQueEstaoVisiveis(bordas);
		Iterator it = casasTemporarias.iterator();
		while(it.hasNext())
		{
			((Casa)it.next()).setAtaqueBranco(true);
		}		
	}

	public Vector getCasasQueEstaoVisiveis(Borda bordas)
	{		
		super.casasQueEstaoVisiveis = new Vector();
		// seta ataque para diagonal inferior direita
		int cont = 1;
		
		while(bordas.getCasa(x+cont, y+cont)!=null)
		{
			if (bordas.getCasa(x+cont, y+cont).temPeca())
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x+cont,y+cont));
				break;
			}
			else
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x+cont,y+cont));
			}
			cont++;
		}
		
		// set ataque para diagonal inferior esquerda
		cont = 1;
		while(bordas.getCasa(x-cont, y+cont)!=null) 
		{
			if (bordas.getCasa(x-cont,y+cont).temPeca())
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x-cont,y+cont));
				break;
				
			}
			else 
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x-cont,y+cont));
			}
			cont++;
		}
		
		// set ataque para diagonal superior esquerda
		cont = 1;
		while(bordas.getCasa(x-cont, y-cont)!=null)
		{
			if (bordas.getCasa(x-cont,y-cont).temPeca())
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x-cont,y-cont));
					break;
			}
			else
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x-cont,y-cont));
			}
			cont++;
		}
		
		// set ataque para diagonal superior direita
		cont = 1;
		while(bordas.getCasa(x+cont, y-cont)!=null)
		{
			if (bordas.getCasa(x+cont,y-cont).temPeca())
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x+cont,y-cont));
				break;
			}
			else
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x+cont,y-cont));
			}
			cont++;
		}		
		return casasQueEstaoVisiveis;
	}
	
	public Vector getCasasQueEstaoEmAtaque(Borda bordas)
	{
		// possíveis casas que o bispo pode ocupar.
		super.casasQueEstaoEmAtaque = new Vector();
		
		// verifica se pode mover para diagonal inferior direita
		int cont = 1;
		boolean encontrou = false;
		while(bordas.getCasa(x+cont, y+cont)!=null)
		{
			if (bordas.getCasa(x+cont,y+cont).getPeca()!=null)
			{
				if (bordas.getCasa(x+cont, y+cont).getPeca().getCor()==Color.BLACK)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x+cont,y+cont));
					break;
				}
				if (bordas.getCasa(x+cont,y+cont).getPeca().getCor()==Color.WHITE)
				{
					break;
				}
			}
			casasQueEstaoEmAtaque.add(bordas.getCasa(x+cont,y+cont));
			cont++;
		}
		
		// verifica se pode mover para diagonal inferior esquerda
		cont = 1;
		while(bordas.getCasa(x-cont, y+cont)!=null) 
		{
			if (bordas.getCasa(x-cont,y+cont).getPeca()!=null)
			{
				if (bordas.getCasa(x-cont, y+cont).getPeca().getCor()==Color.BLACK)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x-cont,y+cont));
					break;
				}
				if (bordas.getCasa(x-cont,y+cont).getPeca().getCor()==Color.WHITE)
				{
					break;
				}
			}
			casasQueEstaoEmAtaque.add(bordas.getCasa(x-cont,y+cont));
			cont++;
		}
		
		// verifica se pode mover para diagonal superior esquerda
		cont = 1;
		while(bordas.getCasa(x-cont, y-cont)!=null)
		{
			if (bordas.getCasa(x-cont,y-cont).getPeca()!=null)
			{
				if (bordas.getCasa(x-cont, y-cont).getPeca().getCor()==Color.BLACK)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x-cont,y-cont));
					break;
				}
				if (bordas.getCasa(x-cont,y-cont).getPeca().getCor()==Color.WHITE)
				{
					break;
				}
			}
			casasQueEstaoEmAtaque.add(bordas.getCasa(x-cont,y-cont));
			cont++;
		}
		
		// verifica se pode mover para diagonal superior direita
		cont = 1;
		while(bordas.getCasa(x+cont, y-cont)!=null)
		{
			if (bordas.getCasa(x+cont,y-cont).getPeca()!=null)
			{
				if (bordas.getCasa(x+cont, y-cont).getPeca().getCor()==Color.BLACK)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x+cont,y-cont));
					break;
				}
				if (bordas.getCasa(x+cont,y-cont).getPeca().getCor()==Color.WHITE)
				{
					break;
				}
			}
			casasQueEstaoEmAtaque.add(bordas.getCasa(x+cont,y-cont));
			cont++;
		}
		return casasQueEstaoEmAtaque;		
	}

	public boolean casaVisivel(Borda bordas, CasaItf casas)
	{
		Vector casaTemporaria = getCasasQueEstaoVisiveis(bordas);
		if (casaTemporaria.contains(casas))
		{
			return true;
		}
		return false;
	}	
}
