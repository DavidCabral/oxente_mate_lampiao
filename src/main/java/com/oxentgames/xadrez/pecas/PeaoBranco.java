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

//Classe que representa o peão branco do xadrez.
public class PeaoBranco extends PecaAbstrata
{
	private Component popupMenu;
	
	public PeaoBranco(int x, int y)
	{
		super(GeralImagens.IMG_PEAO_BRANCO);
		this.x = x;
		this.y = y;
		this.cor = Color.WHITE;

	}

	public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas)throws MovimentoEPosicaoInvalida
	{

		Vector casasComPossiveisAtaque = this.getCasasQueEstaoEmAtaque(bordas);
		Vector casasComPossiveisVisiveis = this.getCasasQueEstaoVisiveis(bordas);
		
        // Verifica se a casa destino está contida numas das possíveis casas de movimento.
		if (casasComPossiveisAtaque.contains(casaDeDestino)
				||(casasComPossiveisVisiveis.contains(casaDeDestino)))
		{
			// Se o a peça inicio pulando uma casa, set a casa pulada como passante.
			if ((casaDeOrigem.getPosicaoY()-casaDeDestino.getPosicaoY())==2)
			{
				((Casa)bordas.getCasa(x,y-1)).setMovimentoPassante(true);
			}
			
			// SE O peão PODE FAZER PASSANTE.
			if ((casaDeOrigem.getPosicaoY()==3)&& (casaDeDestino.getMovimentoPassante()))
			{
				// pega a casa em que está o peão que sofrerá passante.
				CasaItf casaTemporaria = bordas.getCasa(casaDeDestino.getPosicaoX(),
						casaDeDestino.getPosicaoY()+1);
				// pega o peão que sofre passante.
				Peca pecaTemporaria = casaTemporaria.getPeca();
				casaDeOrigem.removePeca();
				// remove o peão que sofreu passante.
				casaTemporaria.removePeca();
				casaDeDestino.setPeca(this);
				// atualiza o tabuleiro.
				
				Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
				tabuleiroTemporario.removeTodosAtaques();
				tabuleiroTemporario.atualizaAtaquePreto();
				tabuleiroTemporario.atualizaAtaqueBranco();
				
				// verifica se o movimento que fiz causou xeque mate no adversário.
				if (tabuleiroTemporario.ehChequeMatePreto())
				{
					((Tabuleiro)bordas).mensagemNoFimDoJogo("Xeque-mate! Cangaceiros Vencem!");					
				// verifica se o movimento que fiz deixou o meu rei em xeque.
				}
				else if (tabuleiroTemporario.ehChequeBranco())
				{
					// volta ao estado anterior.
					casaTemporaria.setPeca(pecaTemporaria);
					casaDeDestino.removePeca();
					casaDeOrigem.setPeca(this);
					// atualiza tabuleiro
					tabuleiroTemporario.removeTodosAtaques();
					tabuleiroTemporario.atualizaAtaquePreto();
					tabuleiroTemporario.atualizaAtaqueBranco();
					
					JOptionPane.showMessageDialog(popupMenu, "Lampião está em xeque, está inválida","ERRO",JOptionPane.ERROR_MESSAGE);
					throw new MovimentoEPosicaoInvalida("Lampião está em xeque, está inválida");
				}
								
			// VERIFICA SE A PEÇA SERÁ PROMOVIDA
			}
			else if (casaDeDestino.getPosicaoY()==0)
			{
				Peca pecaTemporaria = casaDeDestino.getPeca();
				casaDeOrigem.removePeca();
				casaDeDestino.removePeca();
				casaDeDestino.setPeca(this);
				casaDeDestino.removePeca();
				casaDeDestino.setPeca(getPromocaoPeao());
				Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
				tabuleiroTemporario.removeTodosAtaques();
				tabuleiroTemporario.atualizaAtaquePreto();
				tabuleiroTemporario.atualizaAtaqueBranco();
				// verifica se as peças brancas venceram o jogo
				if (tabuleiroTemporario.ehChequeMatePreto())
				{
					((Tabuleiro)bordas).mensagemNoFimDoJogo("Xeque-mate! Cangaceiros Vencem!");					
				// se o movimento que fiz deixou meu rei em xeque.
				}
				else if (tabuleiroTemporario.ehChequeBranco())
				{
					// volta ao estado anterior
					casaDeOrigem.setPeca(this);
					casaDeDestino.removePeca();
					casaDeDestino.setPeca(pecaTemporaria);
					tabuleiroTemporario.removeTodosAtaques();
					tabuleiroTemporario.atualizaAtaquePreto();
					tabuleiroTemporario.atualizaAtaqueBranco();
					
					JOptionPane.showMessageDialog(popupMenu, "Lampião está em xeque, está inválida","ERRO",JOptionPane.ERROR_MESSAGE);
					throw new MovimentoEPosicaoInvalida("Lampião está em xeque, está inválida");
				}
			// MOVE O peão
			}
			else
			{
				Peca pecaTemporaria = casaDeDestino.getPeca();
				casaDeOrigem.removePeca();
				casaDeDestino.removePeca();
				casaDeDestino.setPeca(this);
				
				Tabuleiro tabuleiroTemporario = (Tabuleiro)bordas;
				tabuleiroTemporario.removeTodosAtaques();
				tabuleiroTemporario.atualizaAtaquePreto();
				tabuleiroTemporario.atualizaAtaqueBranco();
				
				if (tabuleiroTemporario.ehChequeMatePreto()){
					((Tabuleiro)bordas).mensagemNoFimDoJogo("Xeque-mate! Cangaceiros Vencem!");					
				// se o movimento que fiz deixou o meu rei em xeque.
				}
				else if (tabuleiroTemporario.ehChequeBranco())
				{
					// volta ao estado anterior
					casaDeOrigem.setPeca(this);
					casaDeDestino.removePeca();
					casaDeDestino.setPeca(pecaTemporaria);
					
					tabuleiroTemporario.removeTodosAtaques();
					tabuleiroTemporario.atualizaAtaquePreto();
					tabuleiroTemporario.atualizaAtaqueBranco();
					
					JOptionPane.showMessageDialog(popupMenu, "Lampião está em xeque, está inválida","ERRO",JOptionPane.ERROR_MESSAGE);
					throw new MovimentoEPosicaoInvalida("Lampião está em xeque, está inválida");
				}
			}
			movePassante(bordas);	
		}
		else{
			JOptionPane.showMessageDialog(popupMenu, "Oxente bixin, está inválida!","ERRO",JOptionPane.ERROR_MESSAGE);
			throw new MovimentoEPosicaoInvalida("Oxente bixin, Posicao invalida");
		}
	}
	
	//Pega as opções para o passante.
	private Peca getPromocaoPeao()
	{
		Object[] possiveis = {"Mandacaru", "Jegue", "Padin ciço","Maria Bonita"};
				String s;
				do{
					s = (String)JOptionPane.showInputDialog(
									this,
									"Escolha a peça:",
									"",
									JOptionPane.PLAIN_MESSAGE,
									null,
									possiveis,
									"Mandacaru");
				} while (s==null);
		
				if (s.equals("Mandacaru"))
				{
					return new TorreBranca(x,y); 
				}
				if (s.equals("Jegue"))
				{
					return new CavaloBranco(x,y);
				}
				if (s.equals("Padin ciço"))
				{
					return new BispoBranco(x,y);
				}
				return new RainhaBranca(x,y);
	}
	
	//Informa ao tabuleiro quais casas o peão está atacando
	public void setCasasQueEstaoEmAtaque(Borda bordas)
	{
		CasaItf casaTemporaria = bordas.getCasa(x-1,y-1);
		if (casaTemporaria!=null)
		{
			((Casa)casaTemporaria).setAtaqueBranco(true);
		}
		casaTemporaria = bordas.getCasa(x+1,y-1);
		if (casaTemporaria!=null)
		{
			((Casa)casaTemporaria).setAtaqueBranco(true);
		}		
	}
	
	//Indica que a casa não pode mais receber passante.
	private void movePassante(Borda bordas)
	{
		for (int x = 0; x < 8 ; x++)
		{
			bordas.getCasa(x,2).setMovimentoPassante(false);
		}
	}

	public Vector getCasasQueEstaoVisiveis(Borda bordas) {
		super.casasQueEstaoVisiveis = new Vector();
		// Verifica se pode mover para cima
		CasaItf casaTemporaria = bordas.getCasa(x, y -1);
		if (casaTemporaria!=null)
		{
			if (!casaTemporaria.temPeca())
			{
				casasQueEstaoVisiveis.add(casaTemporaria);
				// Verifica se pode mover duas casa para cima.
				if ((y==6) && (bordas.getCasa(x,y-2)!=null) && !(bordas.getCasa(x,y-2).temPeca()))
				{
					casasQueEstaoVisiveis.add(bordas.getCasa(x,y-2));
				}		
			}
		}
		return casasQueEstaoVisiveis;
	}

	public Vector getCasasQueEstaoEmAtaque(Borda bordas) 
	{
		super.casasQueEstaoEmAtaque = new Vector();
		
		//Verifica se pode mover para cima e para esquerda.
		CasaItf casaTemporaria = bordas.getCasa(x-1,y-1);	
		if (casaTemporaria!=null)
		{
			if (casaTemporaria.temPeca(Color.BLACK))
			{
				casasQueEstaoEmAtaque.add(casaTemporaria);
			//	verifica se é passante.
			}
			else if (((Casa)casaTemporaria).getMovimentoPassante() && (casaTemporaria.getPeca()==null))
			{
				casasQueEstaoEmAtaque.add(casaTemporaria);
		    }
		}	
		
		// Verifica se pode mover para a cima e para direita.
		casaTemporaria = bordas.getCasa(x+1,y-1);
		if (casaTemporaria!=null)
		{
			if (casaTemporaria.temPeca(Color.BLACK))
			{
				casasQueEstaoEmAtaque.add(casaTemporaria);
			// verifica se é passante.
			}
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
