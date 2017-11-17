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

//Classe que representa o peão preto do xadrez.
public class PeaoPreto extends PecaAbstrata
{
	private Component popupMenu;
	
	public PeaoPreto(int x, int y)
	{
		super(GeralImagens.IMG_PEAO_PRETO);		
		this.x = x;
		this.y = y;
		this.cor = Color.BLACK;

	}

	public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas)throws MovimentoEPosicaoInvalida
	{
		Vector casasEmAtaqueTemporario = this.getCasasQueEstaoEmAtaque(bordas);
		Vector casasVisiveisTemporarias = this.getCasasQueEstaoVisiveis(bordas);		
		// Verifica se a casa destino está contida numas das possíveis casas de movimento.
		if (casasEmAtaqueTemporario.contains(casaDeDestino)||casasVisiveisTemporarias.contains(casaDeDestino)){
			// verifica se o peão pulou uma casa na sua sa�da. Set a casa pulada como passante.
			if ((casaDeDestino.getPosicaoY()-casaDeOrigem.getPosicaoY())==2)
			{
				((Casa)bordas.getCasa(x,y+1)).setMovimentoPassante(true);
			}
			
			// verifica se pode fazer passante.
			if ((casaDeOrigem.getPosicaoY()==4) && (casaDeDestino.getMovimentoPassante()))
			{
				// pega a casa em que está o peão que sofrer� passante.
				CasaItf casaTemporaria = bordas.getCasa(casaDeDestino.getPosicaoX(),casaDeDestino.getPosicaoY()-1);
				// pega o peão que sofre passante.
				Peca pecaTemporaria = casaTemporaria.getPeca();
				casaDeOrigem.removePeca();
				// remove o peão que sofreu passante.
				casaTemporaria.removePeca();
				casaDeDestino.setPeca(this);
				// atualiza o tabuleiro.
				Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
				tabuleiroTemporario.removeTodosAtaques();
				tabuleiroTemporario.atualizaAtaqueBranco();
				tabuleiroTemporario.atualizaAtaquePreto();
				// verifica se o movimento que fiz causou xeque mate no advers�rio.
				if (tabuleiroTemporario.ehChequeMateBranco())
				{
					((Tabuleiro)bordas).mensagemNoFimDoJogo("Xeque - mate! Policiais Vencem!");
				// verifica se o movimento que fiz deixou o meu rei em xeque.
				}
				else if (tabuleiroTemporario.ehChequePreto())
				{
					// volta ao estado anterior.
					casaTemporaria.setPeca(pecaTemporaria);
					casaDeDestino.removePeca();
					casaDeOrigem.setPeca(this);
					// atualiza tabuleiro
					tabuleiroTemporario.removeTodosAtaques();
					tabuleiroTemporario.atualizaAtaqueBranco();
					tabuleiroTemporario.atualizaAtaquePreto();
					
					JOptionPane.showMessageDialog(popupMenu, "O Delegado está em xeque, está inválida!","ERRO",JOptionPane.ERROR_MESSAGE);
					throw new MovimentoEPosicaoInvalida("O Delegado está em xeque, está inválida");
				}
								
			// VERIFICA SE VAI SER PROMOVIDO.
			}
			else if(casaDeDestino.getPosicaoY()==7)
			{
				Peca pecaTemporaria = casaDeDestino.getPeca();
				casaDeOrigem.removePeca();
				casaDeDestino.removePeca();
				casaDeDestino.setPeca(this);
				casaDeDestino.removePeca();
				casaDeDestino.setPeca(getPromocaoPeao());
				
				Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
				tabuleiroTemporario.removeTodosAtaques();
				tabuleiroTemporario.atualizaAtaqueBranco();
				tabuleiroTemporario.atualizaAtaquePreto();
				
				// verifica se as pe�as brancas venceram o jogo
				if (tabuleiroTemporario.ehChequeMateBranco())
				{
					((Tabuleiro)bordas).mensagemNoFimDoJogo("Xeque - mate! Policiais Vencem!");
				// se o movimento que fiz deixou meu rei em xeque.
				}
				else if (tabuleiroTemporario.ehChequePreto())
				{
					// volta ao estado anterior
					casaDeOrigem.setPeca(this);
					casaDeDestino.removePeca();
					casaDeDestino.setPeca(pecaTemporaria);
					tabuleiroTemporario.removeTodosAtaques();
					tabuleiroTemporario.atualizaAtaqueBranco();
					tabuleiroTemporario.atualizaAtaquePreto();
					
					JOptionPane.showMessageDialog(popupMenu, "O Delegado está em xeque, está inválida!","ERRO",JOptionPane.ERROR_MESSAGE);
					throw new MovimentoEPosicaoInvalida("O Delegado está em xeque, está inválida");
				}
			// MOVE PE�A
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
				if (tabuleiroTemporario.ehChequeMateBranco())
				{
					((Tabuleiro)bordas).mensagemNoFimDoJogo("Xeque - mate! Policiais Vencem!");
				// se o movimento que fiz deixou o meu rei em xeque.
				}
				else if (tabuleiroTemporario.ehChequePreto())
				{
					// volta ao estado anterior
					casaDeOrigem.setPeca(this);
					casaDeDestino.removePeca();
					casaDeDestino.setPeca(pecaTemporaria);
					tabuleiroTemporario.removeTodosAtaques();
					tabuleiroTemporario.atualizaAtaqueBranco();
					tabuleiroTemporario.atualizaAtaquePreto();
					
					JOptionPane.showMessageDialog(popupMenu, "O Delegado está em xeque, está inválida!","ERRO",JOptionPane.ERROR_MESSAGE);
					throw new MovimentoEPosicaoInvalida("O Delegado está em xeque, está inválida");
				}
			}
			movePassante(bordas);		
		}
		else
		{
			JOptionPane.showMessageDialog(popupMenu, "Oxente bixin, está inválida!","ERRO",JOptionPane.ERROR_MESSAGE);
			throw new MovimentoEPosicaoInvalida("Oxente bixin, Posicao invalida");
		}			 
	}
	
	//Pega a pe�a promovida.
	private Peca getPromocaoPeao()
	{
		Object[] possiveis = {"Vigia", "Cavalo", "Padre","Capit�"};
		String s;
		do{
			s = (String)JOptionPane.showInputDialog(
							this,
							"Escolha a pe�a:",
							"",
							JOptionPane.PLAIN_MESSAGE,
							null,
							possiveis,
							"Vigia");
		} while (s==null);
		
		if (s.equals("Vigia"))
		{
			return new TorrePreta(x,y); 
		}
		if (s.equals("Cavalo"))
		{
			return new CavaloPreto(x,y);
		}
		if (s.equals("Padre"))
		{
			return new BispoPreto(x,y);
		}
		return new RainhaPreta(x,y);
	}

	public void setCasasQueEstaoEmAtaque(Borda bordas)
	{
		CasaItf casaTemporaria = bordas.getCasa(x-1,y+1);
		if (casaTemporaria!=null && casaTemporaria.temPeca())
		{
			((Casa)casaTemporaria).setAtaquePreto(true);
		}
		casaTemporaria = bordas.getCasa(x+1,y+1);
		if (casaTemporaria!=null && casaTemporaria.temPeca())
		{
			((Casa)casaTemporaria).setAtaquePreto(true);
		}			
	}

	//Indica que a casa n�o pode mais receber passante.
	private void movePassante(Borda board)
	{
		for (int x = 0; x < 8 ; x++)
		{
			board.getCasa(x,5).setMovimentoPassante(false);
		}
	}

	public Vector getCasasQueEstaoVisiveis(Borda bordas)
	{
		super.casasQueEstaoVisiveis = new Vector();
		// Verifica se pode mover para baixo	
		CasaItf casaTemporaria = bordas.getCasa(x,y+1);
		if (casaTemporaria!=null)
		{
			if (!casaTemporaria.temPeca() )
			{
				casasQueEstaoVisiveis.add(casaTemporaria);
				if ((y==1) && (bordas.getCasa(x,y+2)!=null) && !(bordas.getCasa(x,y+2).temPeca()))
				{
					casasQueEstaoVisiveis.add(bordas.getCasa(x,y+2));
				}		
			}
		}
		return casasQueEstaoVisiveis;
	}

	public Vector getCasasQueEstaoEmAtaque(Borda bordasa)
	{
		super.casasQueEstaoEmAtaque = new Vector();
		// Verifica se pode mover para baixo e para a esquerda
		CasaItf casaTemporaria = bordasa.getCasa(x-1,y+1);
		if (casaTemporaria!=null)
		{
			if (casaTemporaria.temPeca(Color.WHITE))
			{
				casasQueEstaoEmAtaque.add(casaTemporaria);
			}
			// verifica se � passante.
			else if (((Casa)casaTemporaria).getMovimentoPassante()	&& (casaTemporaria.getPeca()==null))
			{
				casasQueEstaoEmAtaque.add(casaTemporaria);
			}
		}
		
		// Verifica se pode mover para baixo e para direita
		casaTemporaria = bordasa.getCasa(x+1,y+1);
		if (casaTemporaria!=null)
		{
			if (casaTemporaria.temPeca(Color.WHITE))
			{
				casasQueEstaoEmAtaque.add(casaTemporaria);
			}
			// verifica se � passante
			else if (((Casa)casaTemporaria).getMovimentoPassante() && (casaTemporaria.getPeca()==null))
			{
				casasQueEstaoEmAtaque.add(casaTemporaria);
			}		
		}
		return casasQueEstaoEmAtaque;
	}

	public boolean casaVisivel(Borda bordas, CasaItf casa)
	{
		Vector casasTemporariasVisiveis = this.getCasasQueEstaoVisiveis(bordas);
		Vector casasTemporariasEmAtaque = this.getCasasQueEstaoEmAtaque(bordas);
		if (casasTemporariasVisiveis.contains(casa) || casasTemporariasEmAtaque.contains(casa))
		{
			return true;
		}
		return false;
	}

}