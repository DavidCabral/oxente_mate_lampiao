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

//Classe que representa o cavalo preto do xadrez.
public class CavaloPreto extends PecaAbstrata {
    private Component popupMenu;

    public CavaloPreto(int x, int y) {
        super(GeralImagens.IMG_CAVALO_PRETO);
        super.x = x;
        super.y = y;
        super.cor = Color.BLACK;
    }

    public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas)
            throws MovimentoEPosicaoInvalida {
        Vector casasTemporarias = this.getCasasQueEstaoEmAtaque(bordas);

        if (casasTemporarias.contains(casaDeDestino)) {
            Peca pecaCasaDestino = casaDeDestino.getPeca();
            casaDeOrigem.removePeca();
            casaDeDestino.removePeca();
            casaDeDestino.setPeca(this);

            Tabuleiro tabuleiroTemporario = (Tabuleiro) bordas;
            tabuleiroTemporario.removeTodosAtaques();
            tabuleiroTemporario.atualizaAtaqueBranco();
            tabuleiroTemporario.atualizaAtaquePreto();

            if (tabuleiroTemporario.ehChequeMateBranco()) {
                ((Tabuleiro) bordas).mensagemNoFimDoJogo("Xeque - mate! Policiais Vencem!");
            } else if (tabuleiroTemporario.ehChequePreto()) {
                casaDeOrigem.removePeca();
                casaDeOrigem.setPeca(this);
                casaDeDestino.removePeca();
                casaDeDestino.setPeca(pecaCasaDestino);

                JOptionPane.showMessageDialog(popupMenu, "O Delegado está em xeque, está inválida", "ERRO", JOptionPane.ERROR_MESSAGE);
                throw new MovimentoEPosicaoInvalida("O Delegado está em xeque, está inválida");
            }
        } else {
            JOptionPane.showMessageDialog(popupMenu, "Cavalo: Oxente bixin, está inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new MovimentoEPosicaoInvalida("Cavalo: Oxente bixin, está inválida!");
        }
    }

    public void setCasasQueEstaoEmAtaque(Borda bordas) {
        Vector casasTemporarias = new Vector();
        // 8 jogadas do cavalo
        casasTemporarias.add(bordas.getCasa(x - 1, y - 2));
        casasTemporarias.add(bordas.getCasa(x + 1, y - 2));
        casasTemporarias.add(bordas.getCasa(x - 2, y + 1));
        casasTemporarias.add(bordas.getCasa(x - 2, y - 1));
        casasTemporarias.add(bordas.getCasa(x + 2, y + 1));
        casasTemporarias.add(bordas.getCasa(x + 2, y - 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y + 2));
        casasTemporarias.add(bordas.getCasa(x + 1, y + 2));

        Iterator it = casasTemporarias.iterator();
        CasaItf casaTemporaria;
        while (it.hasNext()) {
            casaTemporaria = (CasaItf) it.next();
            if (casaTemporaria != null) {
                ((Casa) casaTemporaria).setAtaquePreto(true);
            }
        }
    }

    public Vector getCasasQueEstaoVisiveis(Borda bordas) {
        super.casasQueEstaoVisiveis = new Vector();
        Vector casasTemporarias = new Vector();
        // 8 jogadas do cavalo
        casasTemporarias.add(bordas.getCasa(x - 1, y - 2));
        casasTemporarias.add(bordas.getCasa(x + 1, y - 2));
        casasTemporarias.add(bordas.getCasa(x - 2, y + 1));
        casasTemporarias.add(bordas.getCasa(x - 2, y - 1));
        casasTemporarias.add(bordas.getCasa(x + 2, y + 1));
        casasTemporarias.add(bordas.getCasa(x + 2, y - 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y + 2));
        casasTemporarias.add(bordas.getCasa(x + 1, y + 2));

        Iterator it = casasTemporarias.iterator();
        CasaItf casaTemporaria;
        while (it.hasNext()) {
            casaTemporaria = (CasaItf) it.next();
            if (casaTemporaria != null) {
                casasQueEstaoVisiveis.add(casaTemporaria);
            }
        }
        return casasQueEstaoVisiveis;
    }

    public Vector getCasasQueEstaoEmAtaque(Borda bordas) {
        super.casasQueEstaoEmAtaque = new Vector();
        Vector casasTemporarias = new Vector();
        // 8 jogadas do cavalo
        casasTemporarias.add(bordas.getCasa(x - 1, y - 2));
        casasTemporarias.add(bordas.getCasa(x + 1, y - 2));
        casasTemporarias.add(bordas.getCasa(x - 2, y + 1));
        casasTemporarias.add(bordas.getCasa(x - 2, y - 1));
        casasTemporarias.add(bordas.getCasa(x + 2, y + 1));
        casasTemporarias.add(bordas.getCasa(x + 2, y - 1));
        casasTemporarias.add(bordas.getCasa(x - 1, y + 2));
        casasTemporarias.add(bordas.getCasa(x + 1, y + 2));

        Iterator it = casasTemporarias.iterator();
        CasaItf casaTemporaria;
        while (it.hasNext()) {
            casaTemporaria = (CasaItf) it.next();
            if (casaTemporaria != null) {
                if (casaTemporaria.temPeca() && (casaTemporaria.getPeca().getCor() == Color.WHITE)) {
                    casasQueEstaoEmAtaque.add(casaTemporaria);
                } else if (!casaTemporaria.temPeca()) {
                    casasQueEstaoEmAtaque.add(casaTemporaria);
                }
            }
        }
        return casasQueEstaoEmAtaque;
    }

    public boolean casaVisivel(Borda bordas, CasaItf casa) {
        Vector casasTemporarias = this.getCasasQueEstaoVisiveis(bordas);
        return casasTemporarias.contains(casa);
    }
}
