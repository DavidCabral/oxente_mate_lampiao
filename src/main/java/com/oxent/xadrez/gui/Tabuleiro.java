package com.oxent.xadrez.gui;

import com.oxent.utilitarios.CarregaImagemUtil;
import com.oxent.utilitarios.GeralImagens;
import com.oxent.xadrez.Borda;
import com.oxent.xadrez.CasaItf;
import com.oxent.xadrez.Peca;
import com.oxent.xadrez.pecas.BispoBranco;
import com.oxent.xadrez.pecas.BispoPreto;
import com.oxent.xadrez.pecas.CavaloBranco;
import com.oxent.xadrez.pecas.CavaloPreto;
import com.oxent.xadrez.pecas.PeaoBranco;
import com.oxent.xadrez.pecas.PeaoPreto;
import com.oxent.xadrez.pecas.RainhaBranca;
import com.oxent.xadrez.pecas.RainhaPreta;
import com.oxent.xadrez.pecas.ReiBranco;
import com.oxent.xadrez.pecas.ReiPreto;
import com.oxent.xadrez.pecas.TorreBranca;
import com.oxent.xadrez.pecas.TorrePreta;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

//Classe que representa o tabuleiro do xadrez.
public class Tabuleiro extends JComponent implements Borda {

    // atributos.
    private boolean fimDoJogo = false;
    private Casa[][] casas;

    //Constroi o tabuleiro 8x8 Casas
    public Tabuleiro() {
        super.setSize(752, 690);
        casas = new Casa[8][8];
        int cont = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (x % 2 == cont) {
                    casas[x][y] = new Casa(x, y, Color.WHITE);
                } else {
                    casas[x][y] = new Casa(x, y, Color.BLACK);
                }
                add(casas[x][y]);
            }

            cont = cont == 0 ? 1 : 0;
        }
        super.repaint();
    }

    //Adiciona peças do jogo xadrez no tabuleiro.
    public void adicionaPecasAoTabXadrez() {
        // Peças pretas.
        casas[0][0].setPeca(new TorrePreta(0, 0));
        casas[1][0].setPeca(new CavaloPreto(1, 0));
        casas[2][0].setPeca(new BispoPreto(2, 0));
        casas[3][0].setPeca(new RainhaPreta(3, 0));
        casas[4][0].setPeca(new ReiPreto(4, 0));
        casas[5][0].setPeca(new BispoPreto(5, 0));
        casas[6][0].setPeca(new CavaloPreto(6, 0));
        casas[7][0].setPeca(new TorrePreta(7, 0));
        for (int x = 0; x < 8; x++) {
            casas[x][1].setPeca(new PeaoPreto(x, 1));
        }

        // Peças brancas.
        casas[0][7].setPeca(new TorreBranca(0, 7));
        casas[1][7].setPeca(new CavaloBranco(1, 7));
        casas[2][7].setPeca(new BispoBranco(2, 7));
        casas[3][7].setPeca(new RainhaBranca(3, 7));
        casas[4][7].setPeca(new ReiBranco(4, 7));
        casas[5][7].setPeca(new BispoBranco(5, 7));
        casas[6][7].setPeca(new CavaloBranco(6, 7));
        casas[7][7].setPeca(new TorreBranca(7, 7));
        for (int x = 0; x < 8; x++)
            casas[x][6].setPeca(new PeaoBranco(x, 6));
    }

    public CasaItf getCasa(int x, int y) {
        Casa casaTemporaria;
        for (int i = 0; i < super.getComponentCount(); i++) {
            casaTemporaria = (Casa) super.getComponent(i);
            if ((casaTemporaria.getPosicaoX() == x) && (casaTemporaria.getPosicaoY() == y)) {
                return casaTemporaria;
            }
        }
        return null;
    }

    //Remove todas as indicações de ataque nas casas.
    public void removeTodosAtaques() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                ((Casa) this.getCasa(x, y)).removeTodosAtaques();
            }
        }
    }

    //Remove todas as indicações de ataque nas casas brancas.
    public void removeTodosAtaquesBranco() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                ((Casa) this.getCasa(x, y)).setAtaqueBranco(false);
            }
        }
    }

    //Remove todas as indicações de ataque nas casas pretas.
    public void removeTodosAtaquesPreto() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                ((Casa) this.getCasa(x, y)).setAtaquePreto(false);
            }
        }
    }

    //Atualiza todas as casas que estáo sendo atacadas pelas peças pretas.
    public void atualizaAtaquePreto() {
        CasaItf casaTemporaria;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                casaTemporaria = this.getCasa(x, y);
                if ((casaTemporaria.getPeca() != null)
                        && (casaTemporaria.getPeca().getCor() == Color.BLACK))
                    casaTemporaria.getPeca().setCasasQueEstaoEmAtaque(this);
            }
        }
    }

    //Atualiza todas as casas que estáo sendo atacadas pelas peças brancas.
    public void atualizaAtaqueBranco() {
        CasaItf casaTemporaria;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                casaTemporaria = this.getCasa(x, y);
                if ((casaTemporaria.getPeca() != null) && (casaTemporaria.getPeca().getCor() == Color.WHITE)) {
                    casaTemporaria.getPeca().setCasasQueEstaoEmAtaque(this);
                }
            }
        }
    }

    //Verifica se o rei preto está em xeque.
    public boolean ehChequePreto() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((this.getCasa(x, y).getPeca() instanceof ReiPreto) && ((Casa) this.getCasa(x, y)).ehAtaqueBranco()) {
                    return true;
                }
            }
        }
        return false;
    }

    //Verifica se o rei branco está em xeque.
    public boolean ehChequeBranco() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((this.getCasa(x, y).getPeca() instanceof ReiBranco) && ((Casa) this.getCasa(x, y)).ehAtaquePreto()) {
                    return true;
                }
            }
        }
        return false;
    }

    //Verifica se as peças brancas sofreram xeque mate.
    public boolean ehChequeMateBranco() {
        ReiBranco reiBranco = null;
        Casa casaDoReiTemporario = null;
        Casa casaDoRei = null;
        // Pega o rei branco no tabuleiro.
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                casaDoReiTemporario = (Casa) this.getCasa(x, y);
                if ((casaDoReiTemporario.getPeca() instanceof ReiBranco)) {
                    casaDoRei = casaDoReiTemporario;
                    reiBranco = (ReiBranco) casaDoRei.getPeca();
                }
            }
        }

        // verifica se o rei está sendo atacado
        if (casaDoRei.ehAtaquePreto()) {
            // se o rei não pode mover-se
            if (reiBranco.getCasasQueEstaoEmAtaque(this).size() == 0) {
                // Veririca se o cavalo adversário dá xeque - mate.
                if (this.cavaloChequeMate(reiBranco)) {
                    return true;
                }

                Vector pecasQueEstaoAtacando = this.pecasQueEstaoAtacandoRei(reiBranco);

                // Verifica se mais de uma peça está atacando o rei.
                if (pecasQueEstaoAtacando.size() >= 2) {
                    return true;
                } else {
                    Peca peca = (Peca) pecasQueEstaoAtacando.get(0);
                    Casa casaTemporaria = (Casa) this.getCasa(peca.getPosicaoX(),
                            peca.getPosicaoY());
                    // verifica se a peça que está atacando o rei também está sendo atacada.
                    if (casaTemporaria.ehAtaqueBranco()) {
                        return false;
                    }
                    // verifica se uma peça branca impede que a peça preta der xeque - mate.
                    return !pecaBrancaImpedeCheque((Peca) pecasQueEstaoAtacando.get(0), reiBranco);
                }
            }
        }
        return false;
    }

    //Verifica se uma peça branca impede o xeque - mate no seu rei.
    private boolean pecaBrancaImpedeCheque(Peca pecaDoAdversario, ReiBranco reiBranco) {
        int xRei = reiBranco.getPosicaoX();
        int yRei = reiBranco.getPosicaoY();
        if (pecaDoAdversario.getPosicaoX() < xRei && pecaDoAdversario.getPosicaoY() < yRei) {
            xRei--;
            yRei--;
            while (pecaDoAdversario.getPosicaoX() < xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaqueBranco()) {
                    return true;
                }
                xRei--;
                yRei--;
            }
        } else if ((pecaDoAdversario.getPosicaoX() < xRei) && pecaDoAdversario.getPosicaoY() == yRei) {
            xRei--;
            while (pecaDoAdversario.getPosicaoX() < xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaqueBranco()) {
                    return true;
                }
                xRei--;
            }
        } else if ((pecaDoAdversario.getPosicaoX() < xRei) && pecaDoAdversario.getPosicaoY() > yRei) {
            xRei--;
            yRei++;
            while (pecaDoAdversario.getPosicaoX() < xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaqueBranco()) {
                    return true;
                }
                xRei--;
                yRei++;
            }
        } else if ((pecaDoAdversario.getPosicaoX() == xRei) && (pecaDoAdversario.getPosicaoY() > yRei)) {
            yRei++;
            while (pecaDoAdversario.getPosicaoY() > yRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaqueBranco()) {
                    return true;
                }
                yRei++;
            }
        } else if ((pecaDoAdversario.getPosicaoX() > xRei) && (pecaDoAdversario.getPosicaoY() > yRei)) {
            xRei++;
            yRei++;
            while (pecaDoAdversario.getPosicaoX() > xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaqueBranco()) {
                    return true;
                }
                xRei++;
                yRei++;
            }
        } else if ((pecaDoAdversario.getPosicaoX() > xRei) && (pecaDoAdversario.getPosicaoY() == yRei)) {
            xRei++;
            while (pecaDoAdversario.getPosicaoX() > xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaqueBranco()) {
                    return true;
                }
                xRei++;
            }
        } else if ((pecaDoAdversario.getPosicaoX() > xRei) && (pecaDoAdversario.getPosicaoY() < yRei)) {
            xRei++;
            yRei--;
            while (pecaDoAdversario.getPosicaoX() > xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaqueBranco()) {
                    return true;
                }
                xRei++;
                yRei--;
            }
        } else if ((pecaDoAdversario.getPosicaoX() == xRei) && (pecaDoAdversario.getPosicaoY() < yRei)) {
            yRei--;
            while (pecaDoAdversario.getPosicaoY() < yRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaqueBranco()) {
                    return true;
                }
                yRei--;
            }
        }
        return false;
    }

    //Verifica se uma peça preta impede o xeque - mate no seu rei.
    private boolean pecaPretaImpedeCheque(Peca pecaDoAdversario, ReiPreto reiPreto) {
        int xRei = reiPreto.getPosicaoX();
        int yRei = reiPreto.getPosicaoY();
        if (pecaDoAdversario.getPosicaoX() < xRei && pecaDoAdversario.getPosicaoY() < yRei) {
            xRei--;
            yRei--;
            while (pecaDoAdversario.getPosicaoX() < xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaquePreto()) {
                    return true;
                }
                xRei--;
                yRei--;
            }
        } else if ((pecaDoAdversario.getPosicaoX() < xRei) && pecaDoAdversario.getPosicaoY() == yRei) {
            xRei--;
            while (pecaDoAdversario.getPosicaoX() < xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaquePreto()) {
                    return true;
                }
                xRei--;
            }
        } else if ((pecaDoAdversario.getPosicaoX() < xRei) && pecaDoAdversario.getPosicaoY() > yRei) {
            xRei--;
            yRei++;
            while (pecaDoAdversario.getPosicaoX() < xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaquePreto()) {
                    return true;
                }
                xRei--;
                yRei++;
            }
        } else if ((pecaDoAdversario.getPosicaoX() == xRei) && (pecaDoAdversario.getPosicaoY() > yRei)) {
            yRei++;
            while (pecaDoAdversario.getPosicaoY() > yRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaquePreto()) {
                    return true;
                }
                yRei++;
            }
        } else if ((pecaDoAdversario.getPosicaoX() > xRei) && (pecaDoAdversario.getPosicaoY() > yRei)) {
            xRei++;
            yRei++;
            while (pecaDoAdversario.getPosicaoX() > xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaquePreto()) {
                    return true;
                }
                xRei++;
                yRei++;
            }
        } else if ((pecaDoAdversario.getPosicaoX() > xRei) && (pecaDoAdversario.getPosicaoY() == yRei)) {
            xRei++;
            while (pecaDoAdversario.getPosicaoX() > xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaquePreto()) {
                    return true;
                }
                xRei++;
            }
        } else if ((pecaDoAdversario.getPosicaoX() > xRei) && (pecaDoAdversario.getPosicaoY() < yRei)) {
            xRei++;
            yRei--;
            while (pecaDoAdversario.getPosicaoX() > xRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaquePreto()) {
                    return true;
                }
                xRei++;
                yRei--;
            }
        } else if ((pecaDoAdversario.getPosicaoX() == xRei) && (pecaDoAdversario.getPosicaoY() < yRei)) {
            yRei--;
            while (pecaDoAdversario.getPosicaoY() < yRei) {
                if (((Casa) getCasa(xRei, yRei)).ehAtaquePreto()) {
                    return true;
                }
                yRei--;
            }
        }
        return false;
    }

    //Pega todas as peças que estáo atacando o rei.
    private Vector pecasQueEstaoAtacandoRei(Peca rei) {
        Vector pecas = new Vector();
        Peca pecaTemp;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                pecaTemp = getCasa(x, y).getPeca();
                if (pecaTemp != null && pecaTemp.getCor() == Color.BLACK) {
                    if (pecaTemp.getCasasQueEstaoEmAtaque(this).contains(this.getCasa(rei.getPosicaoX(), rei.getPosicaoY()))) {
                        pecas.add(pecaTemp);
                    }
                } else if (pecaTemp != null && pecaTemp.getCor() == Color.WHITE) {
                    if (pecaTemp.getCasasQueEstaoEmAtaque(this).contains(this.getCasa(rei.getPosicaoX(), rei.getPosicaoY()))) {
                        pecas.add(pecaTemp);
                    }
                }
            }
        }

        return (Vector) pecas.clone();
    }

    //Verifica se o cavalo está dando xeque - mate.
    private boolean cavaloChequeMate(Peca reiBranco) {
        Vector casasTemporarias = new Vector();
        casasTemporarias.add(this.getCasa(reiBranco.getPosicaoX() - 1, reiBranco.getPosicaoY() - 2));
        casasTemporarias.add(this.getCasa(reiBranco.getPosicaoX() + 1, reiBranco.getPosicaoY() - 2));
        casasTemporarias.add(this.getCasa(reiBranco.getPosicaoX() - 2, reiBranco.getPosicaoY() + 1));
        casasTemporarias.add(this.getCasa(reiBranco.getPosicaoX() - 2, reiBranco.getPosicaoY() - 1));
        casasTemporarias.add(this.getCasa(reiBranco.getPosicaoX() + 2, reiBranco.getPosicaoY() + 1));
        casasTemporarias.add(this.getCasa(reiBranco.getPosicaoX() + 2, reiBranco.getPosicaoY() - 1));
        casasTemporarias.add(this.getCasa(reiBranco.getPosicaoX() - 1, reiBranco.getPosicaoY() + 2));
        casasTemporarias.add(this.getCasa(reiBranco.getPosicaoX() + 1, reiBranco.getPosicaoY() + 2));
        Iterator it = casasTemporarias.iterator();

        CasaItf casaTemporaria;
        while (it.hasNext()) {
            casaTemporaria = (CasaItf) it.next();
            if (reiBranco.getCor() == Color.WHITE) {
                if (casaTemporaria != null && casaTemporaria.getPeca() != null && (casaTemporaria.getPeca() instanceof CavaloPreto)) {
                    if (!((Casa) casaTemporaria).ehAtaqueBranco()) {
                        return true;
                    }
                }
            } else {
                if (casaTemporaria != null && casaTemporaria.getPeca() != null && (casaTemporaria.getPeca() instanceof CavaloBranco)) {
                    if (!((Casa) casaTemporaria).ehAtaquePreto()) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    //Verifica se o rei preto sofreu xeque - mate.
    public boolean ehChequeMatePreto() {
        ReiPreto reiPreto = null;
        Casa casaDoReiTemporaria = null;
        Casa casaDoRei = null;
        // Pega o rei branco no tabuleiro.
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                casaDoReiTemporaria = (Casa) this.getCasa(x, y);
                if ((casaDoReiTemporaria.getPeca() instanceof ReiPreto)) {
                    casaDoRei = casaDoReiTemporaria;
                    reiPreto = (ReiPreto) casaDoRei.getPeca();
                }
            }
        }

        // verifica se o rei está sendo atacado
        if (casaDoRei.ehAtaqueBranco()) {
            // se o rei não pode mover-se
            if (reiPreto.getCasasQueEstaoEmAtaque(this).size() == 0) {
                // Verifica se o cavalo adversário dá xeque - mate.
                if (this.cavaloChequeMate(reiPreto)) {
                    return true;
                }

                Vector pecasQueEstaoAtacando = this.pecasQueEstaoAtacandoRei(reiPreto);
                // Verifica se mais de uma peça está atacando o rei.
                if (pecasQueEstaoAtacando.size() >= 2) {
                    return true;
                } else {
                    Peca peca = (Peca) pecasQueEstaoAtacando.get(0);
                    Casa casaTemporaria = (Casa) this.getCasa(peca.getPosicaoX(), peca.getPosicaoY());
                    // verifica se a peça que está atacando o rei também está sendo atacada.
                    if (casaTemporaria.ehAtaquePreto()) {
                        return false;
                    }
                    // verifica se uma peça branca impede que a peça preta dá xeque - mate.
                    return !pecaPretaImpedeCheque((Peca) pecasQueEstaoAtacando.get(0), reiPreto);
                }
            }
        }
        return false;
    }

    //Remove todas as peças do tabuleiro.
    public void removeTodasAsPecas() {
        this.removeTodosAtaques();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (getCasa(x, y).getPeca() != null) {
                    getCasa(x, y).removePeca();
                }
            }
        }
    }

    //Fim do jogo.
    public boolean fimDoJogo() {
        return fimDoJogo;
    }

    public void setFimDeJogo(boolean b) {
        fimDoJogo = b;
    }

    //Mensagem do fim de jogo.
    public void mensagemNoFimDoJogo(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Equipe", JOptionPane.INFORMATION_MESSAGE,
                CarregaImagemUtil.createImageIcon(GeralImagens.IMG_EQUIPE));
        fimDoJogo = true;
        super.enable(false);
        super.repaint();
    }
}
