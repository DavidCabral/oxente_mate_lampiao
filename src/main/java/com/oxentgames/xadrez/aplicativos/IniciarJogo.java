package com.oxentgames.xadrez.aplicativos;

import com.oxentgames.Main;
import com.oxentgames.utilitarios.CarregaImagemUtil;
import com.oxentgames.utilitarios.GeralImagens;
import com.oxentgames.utilitarios.ImagemDeFundo;
import com.oxentgames.xadrez.Borda;
import com.oxentgames.xadrez.MovimentoEPosicaoInvalida;
import com.oxentgames.xadrez.gui.Casa;
import com.oxentgames.xadrez.gui.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

public class IniciarJogo extends JFrame implements ActionListener {

	private Color vezDoJogador = Color.BLACK;
	private Vector casasTabuleiro = new Vector();
	private Container c;
	private JMenuBar menuBar;
	private JMenu menuJogo;
	private JMenuItem mItemNovo, mItemSair, mItemVoltarTela;
	private Tabuleiro tabuleiro;	
	private Component popupMenu;	
	private JButton lampDesistir,lampEmpatar,deleDesistir,deleEmpatar;
	private ImagemDeFundo back;
	private JLabel bordaJLabel;
	
	public IniciarJogo()
	{	
		super("Oxente-Mate Lampião");
		super.setResizable(false);
		super.setSize(new Dimension(800, 750));
		super.setLocationRelativeTo(null); 
		      
       //Adiciona o Botão para Lampião Desistir da partida
       lampDesistir = new JButton("Desistir");//cria um JButton
       lampDesistir.setBackground(Color.ORANGE);//JButton com a cor de fundo laranja
       lampDesistir.setPreferredSize(new Dimension(100, 20));//JButton com 100 pixels de largura
       lampDesistir.setBounds(660, 588, 115, 25);
       lampDesistir.setToolTipText("Lampião desiste do jogo e o delegado vence");
       lampDesistir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
					System.out.println("lamp desis");
					int ret = JOptionPane.showConfirmDialog(popupMenu,"Voce deseja realmente Desistir da partida?","Vai Correr Lampião?",JOptionPane.YES_NO_OPTION);
					if(ret == JOptionPane.YES_OPTION){
						String mensagem = "Policiais Vencem o jogo";
						JOptionPane.showMessageDialog(null,mensagem,"oxente bichim correu num foi ",JOptionPane.INFORMATION_MESSAGE,
						CarregaImagemUtil.createImageIcon(GeralImagens.IMG_DELEGADO));
						tabuleiro.setFimDeJogo(true);
				    }
			}
		});
           
       //Adicinoa o Botão para Lampião Empatar
       lampEmpatar = new JButton("Empatar");//cria um JButton
       lampEmpatar.setBackground(Color.ORANGE);//JButton com a cor de fundo laranja
       lampEmpatar.setPreferredSize(new Dimension(100, 20));//JButton com 100 pixels de largura
       lampEmpatar.setBounds(660, 613, 115, 25);
       lampEmpatar.setToolTipText("Lampião pede o empate do jogo");
       lampEmpatar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				int ret = JOptionPane.showConfirmDialog(popupMenu,"Voce deseja Pedir Empate ao Delegado?","Enviando mensagem ao Delegado",JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.YES_OPTION){
					int ret2 = JOptionPane.showConfirmDialog(popupMenu,"Lampião esta pedindo Empate..Você aceita? ","Mensagem de Lampião",JOptionPane.YES_NO_OPTION);
					if(ret2 == JOptionPane.YES_OPTION){
					String mensagem = "JOGO EMPATADO";
					JOptionPane.showMessageDialog(null,mensagem,"EMPATE ",JOptionPane.INFORMATION_MESSAGE,
					CarregaImagemUtil.createImageIcon(GeralImagens.IMG_EMPATE));

					tabuleiro.setFimDeJogo(true);
					}		
			     }
			}
		});
         
       //Adiciona o Botão para o Delegado Desistir da partida
       deleDesistir = new JButton("Desistir");//cria um JButton
       deleDesistir.setBackground(Color.ORANGE);//JButton com a cor de fundo laranja
       deleDesistir.setBounds(660,190,115,25);
       deleDesistir.setToolTipText("Delegado desiste do jogo e o Lampião vence");
       deleDesistir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				//System.out.println("dele desiste");
				int ret = JOptionPane.showConfirmDialog(popupMenu,"Voce deseja realmente Desistir da partida?","Vai Correr Delegado?",JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.YES_OPTION){
					String mensagem = "Cangaceiros Vencem o jogo";
					JOptionPane.showMessageDialog(null,mensagem,"Comigo é na Inhanha ",JOptionPane.INFORMATION_MESSAGE,
					CarregaImagemUtil.createImageIcon(GeralImagens.IMG_CANG));
					tabuleiro.setFimDeJogo(true);
			    }
			}
		});
              
       //Adiciona o Botão para Delegado Empatar
       deleEmpatar = new JButton("Empatar");//cria um JButton
       deleEmpatar.setBackground(Color.ORANGE);//JButton com a cor de fundo laranja
       deleEmpatar.setBounds(660, 215,115, 25);
       deleEmpatar.setToolTipText("Lampião pede o empate do jogo");
       deleEmpatar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				int ret = JOptionPane.showConfirmDialog(popupMenu,"Voce deseja Pedir Empate a Lampião?","Enviando mensagem a Lampião",JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.YES_OPTION){
					int ret2 = JOptionPane.showConfirmDialog(popupMenu,"O Delegado esta pedindo Empate..Você aceita? ","Mensagem do Delegado",JOptionPane.YES_NO_OPTION);
					if(ret2 == JOptionPane.YES_OPTION){
					String mensagem = "JOGO EMPATADO";
					JOptionPane.showMessageDialog(null,mensagem,"EMPATE ",JOptionPane.INFORMATION_MESSAGE,
					CarregaImagemUtil.createImageIcon(GeralImagens.IMG_EMPATE));

					tabuleiro.setFimDeJogo(true);
					}
			     }
			}
       	});
             
		// cria a barra de menu.
		menuBar = new JMenuBar();
		
		// cria o subMenu novo
		mItemNovo = new JMenuItem("Novo Jogo");
		mItemNovo.setMnemonic('N');
		mItemNovo.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					vezDoJogador = Color.BLACK;
					mudaVezDoJogador();
					tabuleiro.removeTodasAsPecas();
					tabuleiro.setFimDeJogo(false);
					tabuleiro.adicionaPecasAoTabXadrez();
				}
			}
		);
				
		// cria subMenu voltar para tela inicial do jogo(tela principal).
		mItemVoltarTela = new JMenuItem("Voltar tela principal");
		mItemVoltarTela.setMnemonic('V');
		mItemVoltarTela.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
	            	if (event.getSource() == mItemVoltarTela){  
						int ret = JOptionPane.showConfirmDialog(popupMenu,"Voce deseja realmente voltar para a tela principal?","Oxente",JOptionPane.YES_NO_OPTION);
						if(ret == JOptionPane.YES_OPTION){	
							setVisible(false);
							Main m = new Main();							
							m.setVisible(true);
						}
	         	     }
				}
			}
		);
				
		// cria subMenu Sair
		mItemSair = new JMenuItem("Sair");
		mItemSair.setMnemonic('S');
		mItemSair.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					int ret = JOptionPane.showConfirmDialog(popupMenu,"Voce deseja realmente Fugir?","Vai Correr?",JOptionPane.YES_NO_OPTION);
					if(ret == JOptionPane.YES_OPTION){
						System.exit(0);
					}
	        		
				}
			}
		);
			
		// cria o menu Jogo
		menuJogo = new JMenu("Menu");
		menuJogo.setMnemonic('M');
		menuJogo.add(mItemNovo);
		menuJogo.add(mItemVoltarTela);
		menuJogo.add(mItemSair);
						
		menuBar.add(menuJogo);
        		
		super.setJMenuBar(menuBar);
				
		tabuleiro = new Tabuleiro();
		tabuleiro.adicionaPecasAoTabXadrez();
				
		tabuleiro.addMouseListener(
			new MouseAdapter()
			{
				private int x;
				private int y;
				public void mouseClicked(MouseEvent event)
				{
					try{
					x = event.getX();
					y = event.getY();
					
					Casa casaTemporaria = (Casa)event.getComponent().getComponentAt(x,y);
					
					// se clicou numa peçaa pela primeira vez
					if (!tabuleiro.fimDoJogo())
					{
						if ((casaTemporaria.getPeca()!=null) && (casasTabuleiro.isEmpty())	&& (vezDoJogador==casaTemporaria.getPeca().getCor()))
						{
							casaTemporaria.pintaBorda(); 
							casasTabuleiro.add(casaTemporaria);
							// se clicou numa casa diferente da anterior e clicou pela segunda vez
						}
						else if ((!casasTabuleiro.contains(casaTemporaria)) && !(casasTabuleiro.isEmpty()))
						{
							Iterator it = casasTabuleiro.iterator();
							Casa temporaria = (Casa)it.next();
							try
							{
								temporaria.getPeca().mover(temporaria,casaTemporaria, tabuleiro);
								casasTabuleiro.removeAllElements()/*removetodosElementos da classe Vector*/;
								mudaVezDoJogador();
								if (vezDoJogador==Color.BLACK)
								{									
									movimentoPassantePreto(tabuleiro);
								}
								
								else
								{
									movimentoPassanteBranco(tabuleiro);
								}								
							   }																					
							catch(MovimentoEPosicaoInvalida e)
							{
								temporaria.removeBorda();
								casasTabuleiro.removeAllElements()/*removetodosElementos da classe Vector*/;
							}	
						}
					}
					}
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(popupMenu, "Você clicou fora do tabuleiro!","ERRO",JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		);
		back = new ImagemDeFundo(GeralImagens.IMG_LAMPxDELE, 640);// instancia a classe imagemdefundotabuleiro
		bordaJLabel  = new JLabel(CarregaImagemUtil.createImageIcon(GeralImagens.IMG_BORDA_DESEN));
		//c.setLayout(null);
		mudaVezDoJogador();		
	}
	
	public void mudaVezDoJogador()
	{
		if (vezDoJogador == Color.BLACK)
		{			
			vezDoJogador = Color.WHITE;						
		}
		else{
			vezDoJogador = Color.BLACK;				
		}
		
		c = getContentPane();
		c.add(tabuleiro);
		if(vezDoJogador == Color.WHITE){
			c.remove(deleEmpatar);
			c.remove(deleDesistir);
			c.remove(bordaJLabel);
			c.repaint();
			c.add(bordaJLabel).setBounds(610,380,210,210);
			c.add(lampDesistir);
			c.add(lampEmpatar);			
		}
		else{
			c.remove(lampDesistir);
			c.remove(lampEmpatar);
			c.remove(bordaJLabel);
			c.repaint();
			c.add(bordaJLabel).setBounds(610,-20,210,210);
			c.add(deleEmpatar);
			c.add(deleDesistir);
		}		
		//c.add(bordaJLabel).setBounds(680, 550,80,80);
		//System.out.println(vezDoJogador);
		c.add(back);// adiciona a imagem no tabuleiro		
	}
	
	//Realiza a jogada passante.
	private void movimentoPassanteBranco(Borda bordas)
	{
		for (int x = 0; x < 8 ; x++)
			bordas.getCasa(x,5).setMovimentoPassante(false);
	}
	
	//Realiza a jogada passante.
	private void movimentoPassantePreto(Borda bordas)
	{
		for (int x = 0; x < 8 ; x++)
			bordas.getCasa(x,2).setMovimentoPassante(false);
	}
	
	//Muda a vez do jogodor.

	public void actionPerformed(ActionEvent e) {
	{
		    //setSize(new Dimension(900, 900));
	}
	}
}
