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
import java.util.Vector;

//Classe que representa a rainha preta do xadrez.
public class RainhaPreta extends PecaAbstrata
{
	private Component popupMenu;
	
	public RainhaPreta(int x, int y)
	{
		super(GeralImagens.IMG_RAINHA_PRETA);
		super.x = x;
		super.y = y;
		super.cor = Color.BLACK;
	}

	public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas) 
			throws MovimentoEPosicaoInvalida
	{
		Vector casasPossiveis = this.getCasasQueEstaoEmAtaque(bordas);		
		// Verifica se a casa destino está contida numas das possíveis casas de movimento.
		if (casasPossiveis.contains(casaDeDestino))
		{
			Peca pecaTemporaria = casaDeDestino.getPeca();
			casaDeOrigem.removePeca();
			casaDeDestino.removePeca();
			casaDeDestino.setPeca(this);
			
			Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
			tabuleiroTemporario.removeTodosAtaques();
			tabuleiroTemporario.atualizaAtaquePreto();
			tabuleiroTemporario.atualizaAtaqueBranco();
			
			if (tabuleiroTemporario.ehChequeMateBranco())
			{
				((Tabuleiro)bordas).mensagemNoFimDoJogo("Xeque-mate! Políciais Vencem!");
			}
			else if (tabuleiroTemporario.ehChequePreto())
			{
				casaDeOrigem.setPeca(this);
				casaDeDestino.removePeca();
				casaDeDestino.setPeca(pecaTemporaria);
				// atualiza tabuleiro
				tabuleiroTemporario.removeTodosAtaques();
				tabuleiroTemporario.atualizaAtaquePreto();
				tabuleiroTemporario.atualizaAtaqueBranco();			
				
				JOptionPane.showMessageDialog(popupMenu, "O Delegado está em xeque, está inválida","ERRO",JOptionPane.ERROR_MESSAGE);
				throw new MovimentoEPosicaoInvalida(	"O Delegado está em xeque, está inválida");
			}			
		} 
		else
		{
			JOptionPane.showMessageDialog(popupMenu, "Capitú: Oxente bixin, está inválida!","ERRO",JOptionPane.ERROR_MESSAGE);
			throw new MovimentoEPosicaoInvalida("Capitú: Oxente bixin, está inválida!");
		}	
	}

	public void setCasasQueEstaoEmAtaque(Borda bordas)
	{
		// seta ataque para diagonal inferior direita
		int cont = 1;
		
		while(bordas.getCasa(x+cont, y+cont)!=null)
		{
			if (bordas.getCasa(x+cont, y+cont).temPeca())
			{
				((Casa)bordas.getCasa(x+cont,y+cont)).setAtaquePreto(true);
				break;
			}
			else
			{
				((Casa)bordas.getCasa(x+cont,y+cont)).setAtaquePreto(true);
			}
			cont++;
		}
		
		// set ataque para diagonal inferior esquerda
		cont = 1;
		while(bordas.getCasa(x-cont, y+cont)!=null) 
		{
			if (bordas.getCasa(x-cont,y+cont).temPeca())
			{
				((Casa)bordas.getCasa(x-cont,y+cont)).setAtaquePreto(true);
				break;	
			}
			else 
			{
				((Casa)bordas.getCasa(x-cont,y+cont)).setAtaquePreto(true);
			}
			cont++;
		}
		
		// set ataque para diagonal superior esquerda
		cont = 1;
		while(bordas.getCasa(x-cont, y-cont)!=null)
		{
			if (bordas.getCasa(x-cont,y-cont).temPeca())
			{			
				((Casa)bordas.getCasa(x-cont,y-cont)).setAtaquePreto(true);
				break;
			}
			else
			{
				((Casa)bordas.getCasa(x-cont,y-cont)).setAtaquePreto(true);
			}
			cont++;
		}
		
		// set ataque para diagonal superior direita
		cont = 1;
		while(bordas.getCasa(x+cont, y-cont)!=null)
		{
			if (bordas.getCasa(x+cont,y-cont).temPeca())
			{
				((Casa)bordas.getCasa(x+cont,y-cont)).setAtaquePreto(true);
				break;
			}
			else
			{
				((Casa)bordas.getCasa(x+cont,y-cont)).setAtaquePreto(true);
			}
			cont++;
		}
		
		//verifica se pode mover para baixo
		cont = 1;
		while(bordas.getCasa(x, y+cont)!=null)
		{
			if (bordas.getCasa(x,y+cont).temPeca())
			{
				((Casa)bordas.getCasa(x,y+cont)).setAtaquePreto(true);
				break;
			}
			else 
			{
				((Casa)bordas.getCasa(x,y+cont)).setAtaquePreto(true);
			}
			cont++;
		}
		
		// verifica se pode mover para esquerda
		cont = 1;
		while(bordas.getCasa(x-cont, y)!=null) 
		{
			if (bordas.getCasa(x-cont,y).temPeca())
			{
				((Casa)bordas.getCasa(x-cont,y)).setAtaquePreto(true);
				break;
			}
			else
			{
				((Casa)bordas.getCasa(x-cont,y)).setAtaquePreto(true);
			}
			cont++;
		}
		
		// verifica se pode mover para direita
		cont = 1;

		while(bordas.getCasa(x+cont, y)!=null)
		{
			if (bordas.getCasa(x+cont,y).temPeca())
			{
				((Casa)bordas.getCasa(x+cont,y)).setAtaquePreto(true);
				break;
			}
			else
			{
				((Casa)bordas.getCasa(x+cont,y)).setAtaquePreto(true);
			}
			cont++;
		}
		
		// verifica se pode mover para cima
		cont = 1;
		while(bordas.getCasa(x, y-cont)!=null)
		{
			if (bordas.getCasa(x,y-cont).temPeca())
			{
				((Casa)bordas.getCasa(x,y-cont)).setAtaquePreto(true);
				break;
			}
			else
			{
				((Casa)bordas.getCasa(x,y-cont)).setAtaquePreto(true);
			}
			cont++;
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
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x-cont,y-cont));;
					break;
			} 
			else
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x-cont,y-cont));;
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
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x+cont,y-cont));;
			}
			cont++;
		}
		//verifica se pode mover para baixo
		cont = 1;
		while(bordas.getCasa(x, y+cont)!=null)
		{
			if (bordas.getCasa(x,y+cont).temPeca())
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x,y+cont));
				break;
			}
			else 
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x,y+cont));
			}
			cont++;
		}
		
		// verifica se pode mover para esquerda
		cont = 1;
		while(bordas.getCasa(x-cont, y)!=null) 
		{
			if (bordas.getCasa(x-cont,y).temPeca())
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x-cont,y));
				break;
			}
			else
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x-cont,y));
			}
			cont++;
		}
		
		// verifica se pode mover para direita
		cont = 1;
		while(bordas.getCasa(x+cont, y)!=null)
		{
			if (bordas.getCasa(x+cont,y).temPeca())
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x+cont,y));
				break;
			}
			else
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x+cont,y));
			}
			cont++;
		}
		
		// verifica se pode mover para cima
		cont = 1;
		while(bordas.getCasa(x, y-cont)!=null)
		{
			if (bordas.getCasa(x,y-cont).temPeca())
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x,y-cont));
				break;
			}
			else
			{
				casasQueEstaoVisiveis.add((Casa)bordas.getCasa(x,y-cont));
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
				if (bordas.getCasa(x+cont, y+cont).getPeca().getCor()==Color.WHITE)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x+cont,y+cont));
					break;
				}
				if (bordas.getCasa(x+cont,y+cont).getPeca().getCor()==Color.BLACK)
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
				if (bordas.getCasa(x-cont, y+cont).getPeca().getCor()==Color.WHITE)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x-cont,y+cont));
					break;
				}
				if (bordas.getCasa(x-cont,y+cont).getPeca().getCor()==Color.BLACK)
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
				if (bordas.getCasa(x-cont, y-cont).getPeca().getCor()==Color.WHITE)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x-cont,y-cont));
					break;
				}
				if (bordas.getCasa(x-cont,y-cont).getPeca().getCor()==Color.BLACK)
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
			if (bordas.getCasa(x+cont,y-cont).getPeca()!=null){
			
				if (bordas.getCasa(x+cont, y-cont).getPeca().getCor()==Color.WHITE)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x+cont,y-cont));
					break;
				}
				if (bordas.getCasa(x+cont,y-cont).getPeca().getCor()==Color.BLACK)
				{
					break;
				}
			}
			casasQueEstaoEmAtaque.add(bordas.getCasa(x+cont,y-cont));
			cont++;
		}
		// verifica se pode mover para baixo
		cont = 1;
		while(bordas.getCasa(x, y+cont)!=null)
		{
			if (bordas.getCasa(x,y+cont).getPeca()!=null)
			{
				if (bordas.getCasa(x, y+cont).getPeca().getCor()==Color.WHITE)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x,y+cont));
					break;
				}
				if (bordas.getCasa(x,y+cont).getPeca().getCor()==Color.BLACK)
				{
					break;
				}
			}
			casasQueEstaoEmAtaque.add(bordas.getCasa(x,y+cont));
			cont++;
		}
		
		// verifica se pode mover para esquerda
		cont = 1;
		while(bordas.getCasa(x-cont, y)!=null) 
		{
			if (bordas.getCasa(x-cont,y).getPeca()!=null)
			{
				if (bordas.getCasa(x-cont, y).getPeca().getCor()==Color.WHITE)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x-cont,y));
					break;
				}
				if (bordas.getCasa(x-cont,y).getPeca().getCor()==Color.BLACK)
				{
					break;
				}
			}
			casasQueEstaoEmAtaque.add(bordas.getCasa(x-cont,y));
			cont++;
		}
		
		// verifica se pode mover para direita
		cont = 1;
		while(bordas.getCasa(x+cont, y)!=null)
		{
			if (bordas.getCasa(x+cont,y).getPeca()!=null)
			{
				if (bordas.getCasa(x+cont, y).getPeca().getCor()==Color.WHITE)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x+cont,y));
					break;
				}
				if (bordas.getCasa(x+cont,y).getPeca().getCor()==Color.BLACK)
				{
					break;
				}
			}
			casasQueEstaoEmAtaque.add(bordas.getCasa(x+cont,y));
			cont++;
		}
		
		// verifica se pode mover para cima
		cont = 1;
		while(bordas.getCasa(x, y-cont)!=null)
		{
			if (bordas.getCasa(x,y-cont).getPeca()!=null)
			{
				if (bordas.getCasa(x, y-cont).getPeca().getCor()==Color.WHITE)
				{
					casasQueEstaoEmAtaque.add(bordas.getCasa(x,y-cont));
					break;
				}
				if (bordas.getCasa(x,y-cont).getPeca().getCor()==Color.BLACK)
				{
					break;
				}
			}
			casasQueEstaoEmAtaque.add(bordas.getCasa(x,y-cont));
			cont++;
		}
		return casasQueEstaoEmAtaque;		
	}

	public boolean casaVisivel(Borda bordas, CasaItf casa) {
		Vector casaTemporaria = getCasasQueEstaoVisiveis(bordas);
		if (casaTemporaria.contains(casa)){
			return true;
		}
		return false;
	}
	
}