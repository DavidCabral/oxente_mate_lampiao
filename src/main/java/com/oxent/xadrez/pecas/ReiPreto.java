package com.oxent.xadrez.pecas;

import com.oxent.utilitarios.GeralImagens;
import com.oxent.xadrez.Borda;
import com.oxent.xadrez.CasaItf;
import com.oxent.xadrez.MovimentoEPosicaoInvalida;
import com.oxent.xadrez.Peca;
import com.oxent.xadrez.gui.Casa;
import com.oxent.xadrez.gui.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;


//Classe que representa a rei preto do xadrez.
public class ReiPreto extends PecaAbstrata {

    private boolean movimentou = false;
    private Component popupMenu;

    public ReiPreto(int x, int y) {
        super(GeralImagens.IMG_REI_PRETO);
        super.x = x;
        super.y = y;
        super.cor = Color.BLACK;
    }

    public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas) throws MovimentoEPosicaoInvalida {
        if ((x != 4) && (y != 0)) {
            movimentou = true;
        }
        Vector casasPossiveis = new Vector();
        Vector casasTemporarias = new Vector();

        // Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.
        casasTemporarias.add(bordas.getCasa(x + 1, y + 1));
        casasTemporarias.add(bordas.getCasa(x + 1, y - 1));
        casasTemporarias.add(bordas.getCasa(x + 1, y));
        casasTemporarias.add(bordas.getCasa(x - 1, y + 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y - 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y));
        casasTemporarias.add(bordas.getCasa(x, y + 1));
        casasTemporarias.add(bordas.getCasa(x, y - 1));

        // possíveis posições.
        CasaItf casaTemporaria = null;
        Iterator it = casasTemporarias.iterator();
        while (it.hasNext()) {
            casaTemporaria = (CasaItf) it.next();
            if (casaTemporaria != null) {
                if (!((Casa) casaTemporaria).ehAtaqueBranco() && !casaTemporaria.temPeca(Color.BLACK)) {
                    casasPossiveis.add(casaTemporaria);
                }
            }
        }

        // verifica se pode fazer roque do lado direito.
        if ((!reiMovimentou()) && (casaDeDestino.getPosicaoY() == 0) &&
                (casaDeDestino.getPosicaoX() == 6) && !((Tabuleiro) bordas).ehChequePreto()) {
            Peca pecaTemporaria = bordas.getCasa(7, 0).getPeca();
            CasaItf casa6_0 = bordas.getCasa(6, 0);
            CasaItf casa5_0 = bordas.getCasa(5, 0);
            if ((pecaTemporaria != null) && (pecaTemporaria instanceof TorrePreta) && (!((TorrePreta) pecaTemporaria).torreMovimentou()) &&
                    (casa6_0.getPeca() == null) && !((Casa) casa6_0).ehAtaqueBranco() &&
                    (casa5_0.getPeca() == null) && !((Casa) casa5_0).ehAtaqueBranco()) {
                casasPossiveis.add(bordas.getCasa(6, 0));
            }
        }

        // verifica se pode fazer roque do lado esquerdo.
        if ((!reiMovimentou()) && (casaDeDestino.getPosicaoY() == 0) &&
                (casaDeDestino.getPosicaoX() == 2) && !((Tabuleiro) bordas).ehChequePreto()
        ) {
            Peca pecaTemporaria = bordas.getCasa(0, 0).getPeca();
            CasaItf casa2_0 = bordas.getCasa(2, 0);
            CasaItf casa3_0 = bordas.getCasa(3, 0);
            CasaItf casa1_0 = bordas.getCasa(1, 0);
            if ((pecaTemporaria != null) && (pecaTemporaria instanceof TorrePreta) &&
                    (!((TorrePreta) pecaTemporaria).torreMovimentou()) &&
                    (casa2_0.getPeca() == null) && !((Casa) casa2_0).ehAtaqueBranco() &&
                    (casa3_0.getPeca() == null) && !((Casa) casa3_0).ehAtaqueBranco() &&
                    (casa1_0.getPeca() == null)) {
                casasPossiveis.add(bordas.getCasa(2, 0));
            }
        }

        if (casasPossiveis.contains(casaDeDestino)) {
            if (!reiMovimentou() && (casaDeDestino.getPosicaoX() == 6) && (casaDeDestino.getPosicaoY() == 0)) {
                casaDeOrigem.removePeca();
                casaDeDestino.setPeca(this);
                Peca torre = bordas.getCasa(7, 0).getPeca();
                bordas.getCasa(5, 0).setPeca(torre);
                bordas.getCasa(7, 0).removePeca();
                movimentou = true;
                Tabuleiro tabuleiroTemporario = (Tabuleiro) bordas;
                tabuleiroTemporario.removeTodosAtaques();
                tabuleiroTemporario.atualizaAtaqueBranco();
                tabuleiroTemporario.atualizaAtaquePreto();
            } else if (!reiMovimentou() && (casaDeDestino.getPosicaoX() == 2) && (casaDeDestino.getPosicaoY() == 0)) {
                casaDeOrigem.removePeca();
                casaDeDestino.setPeca(this);
                Peca torre = bordas.getCasa(0, 0).getPeca();
                bordas.getCasa(3, 0).setPeca(torre);
                bordas.getCasa(0, 0).removePeca();
                movimentou = true;
                Tabuleiro tabuleiroTemporario = (Tabuleiro) bordas;
                tabuleiroTemporario.removeTodosAtaques();
                tabuleiroTemporario.atualizaAtaqueBranco();
                tabuleiroTemporario.atualizaAtaquePreto();
            } else {
                Peca pecaTemporaria = casaDeDestino.getPeca();
                casaDeOrigem.removePeca();
                casaDeDestino.removePeca();
                casaDeDestino.setPeca(this);
                movimentou = true;
                Tabuleiro tabuleiroTemporario = (Tabuleiro) bordas;
                tabuleiroTemporario.removeTodosAtaques();
                tabuleiroTemporario.atualizaAtaqueBranco();
                tabuleiroTemporario.atualizaAtaquePreto();
                if (tabuleiroTemporario.ehChequeBranco()) {
                    casaDeOrigem.setPeca(this);
                    casaDeDestino.removePeca();
                    casaDeDestino.setPeca(pecaTemporaria);
                    tabuleiroTemporario.removeTodosAtaques();
                    tabuleiroTemporario.atualizaAtaqueBranco();
                    tabuleiroTemporario.atualizaAtaquePreto();

                    JOptionPane.showMessageDialog(popupMenu, "O Delegado está em xeque, está inválida", "ERRO", JOptionPane.ERROR_MESSAGE);
                    throw new MovimentoEPosicaoInvalida("O Delegado está em xeque");
                }
            }
        } else {
            JOptionPane.showMessageDialog(popupMenu, "Delegado: Oxente bixin, está inválida", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new MovimentoEPosicaoInvalida("Delegado: Oxente bixin, está inválida");
        }
    }

    public void setCasasQueEstaoEmAtaque(Borda bordas) {
        Vector casasTemporarias = new Vector();

        // Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.
        casasTemporarias.add(bordas.getCasa(x + 1, y + 1));
        casasTemporarias.add(bordas.getCasa(x + 1, y - 1));
        casasTemporarias.add(bordas.getCasa(x + 1, y));
        casasTemporarias.add(bordas.getCasa(x - 1, y + 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y - 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y));
        casasTemporarias.add(bordas.getCasa(x, y + 1));
        casasTemporarias.add(bordas.getCasa(x, y - 1));
        // possíveis posições.
        CasaItf casaTemporaria = null;
        Iterator it = casasTemporarias.iterator();
        while (it.hasNext()) {
            casaTemporaria = (CasaItf) it.next();
            if (casaTemporaria != null) {
                if (!((Casa) casaTemporaria).ehAtaqueBranco() && !casaTemporaria.temPeca(Color.BLACK)) {
                    ((Casa) casaTemporaria).setAtaquePreto(true);
                }
            }
        }
    }

    public boolean reiMovimentou() {
        return movimentou;
    }

    public Vector getCasasQueEstaoVisiveis(Borda bordas) {
        casasQueEstaoVisiveis = new Vector();
        Vector casasTemporarias = new Vector();

        // Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.
        casasTemporarias.add(bordas.getCasa(x + 1, y + 1));
        casasTemporarias.add(bordas.getCasa(x + 1, y - 1));
        casasTemporarias.add(bordas.getCasa(x + 1, y));
        casasTemporarias.add(bordas.getCasa(x - 1, y + 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y - 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y));
        casasTemporarias.add(bordas.getCasa(x, y + 1));
        casasTemporarias.add(bordas.getCasa(x, y - 1));

        // possíveis posições.
        CasaItf casaTemporaria = null;
        Iterator it = casasTemporarias.iterator();
        while (it.hasNext()) {
            casaTemporaria = (CasaItf) it.next();
            if (casaTemporaria != null) {
                casasQueEstaoVisiveis.add((Casa) casaTemporaria);
            }
        }
        return casasQueEstaoVisiveis;
    }

    public Vector getCasasQueEstaoEmAtaque(Borda bordas) {
        super.casasQueEstaoEmAtaque = new Vector();
        Vector casasTemporarias = new Vector();

        // Insere no vetor para logo abaixo, no while, verificar se a casa está disponível para mover.
        casasTemporarias.add(bordas.getCasa(x + 1, y + 1));
        casasTemporarias.add(bordas.getCasa(x + 1, y - 1));
        casasTemporarias.add(bordas.getCasa(x + 1, y));
        casasTemporarias.add(bordas.getCasa(x - 1, y + 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y - 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y));
        casasTemporarias.add(bordas.getCasa(x, y + 1));
        casasTemporarias.add(bordas.getCasa(x, y - 1));

        // possíveis posições.
        CasaItf casaTemporaria = null;
        Iterator it = casasTemporarias.iterator();
        while (it.hasNext()) {
            casaTemporaria = (CasaItf) it.next();
            if (casaTemporaria != null) {
                if (!((Casa) casaTemporaria).ehAtaqueBranco() && !casaTemporaria.temPeca(Color.BLACK)) {
                    casasQueEstaoEmAtaque.add(casaTemporaria);
                }
            }
        }
        return casasQueEstaoEmAtaque;
    }

    public boolean casaVisivel(Borda bordas, CasaItf casa) {
        casasQueEstaoVisiveis = this.getCasasQueEstaoVisiveis(bordas);
        return casasQueEstaoVisiveis.contains(casa);
    }
}
