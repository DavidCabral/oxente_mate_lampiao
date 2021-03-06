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
import java.util.Vector;

//Classe que representa a torre preta do xadrez.
public class TorrePreta extends PecaAbstrata {

    private boolean movimentou = false;
    private Component popupMenu;

    public TorrePreta(int x, int y) {
        super(GeralImagens.IMG_TORRE_PRETA);
        super.x = x;
        super.y = y;
        super.cor = Color.BLACK;
    }

    public void mover(CasaItf casaDeOrigem, CasaItf casaDeDestino, Borda bordas)
            throws MovimentoEPosicaoInvalida {
        Vector casasEmAtaqueTemporarias = this.getCasasQueEstaoEmAtaque(bordas);
        // Verifica se a casa destino está contida numas das possíveis casas de movimento.
        if (casasEmAtaqueTemporarias.contains(casaDeDestino)) {
            Peca pecaTemporaria = casaDeDestino.getPeca();
            casaDeOrigem.removePeca();
            casaDeDestino.removePeca();
            casaDeDestino.setPeca(this);
            movimentou = true;

            Tabuleiro tabuleiroTemporario = (Tabuleiro) bordas;
            tabuleiroTemporario.removeTodosAtaques();
            tabuleiroTemporario.atualizaAtaqueBranco();
            tabuleiroTemporario.atualizaAtaquePreto();
            if (tabuleiroTemporario.ehChequeMateBranco()) {
                ((Tabuleiro) bordas).mensagemNoFimDoJogo("Xeque-mate! Políciais Vencem!");
            } else if (tabuleiroTemporario.ehChequePreto()) {
                casaDeOrigem.setPeca(this);
                casaDeDestino.removePeca();
                casaDeDestino.setPeca(pecaTemporaria);
                // atualiza tabuleiro
                tabuleiroTemporario.removeTodosAtaques();
                tabuleiroTemporario.atualizaAtaquePreto();
                tabuleiroTemporario.atualizaAtaqueBranco();

                JOptionPane.showMessageDialog(popupMenu, "O Delegado está em xeque, está inválida", "ERRO", JOptionPane.ERROR_MESSAGE);
                throw new MovimentoEPosicaoInvalida("O Delegado está em xeque, está inválida");
            }
        } else {
            JOptionPane.showMessageDialog(popupMenu, "Vigia: Oxente bixin, está inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new MovimentoEPosicaoInvalida("Vigia: Oxente bixin, está inválida!");
        }
    }

    public void setCasasQueEstaoEmAtaque(Borda bordas) {
        //verifica se pode mover para baixo
        int cont = 1;
        while (bordas.getCasa(x, y + cont) != null) {
            if (bordas.getCasa(x, y + cont).temPeca()) {
                ((Casa) bordas.getCasa(x, y + cont)).setAtaquePreto(true);
                break;
            } else {
                ((Casa) bordas.getCasa(x, y + cont)).setAtaquePreto(true);
            }
            cont++;
        }

        // verifica se pode mover para esquerda
        cont = 1;
        while (bordas.getCasa(x - cont, y) != null) {
            if (bordas.getCasa(x - cont, y).temPeca()) {
                ((Casa) bordas.getCasa(x - cont, y)).setAtaquePreto(true);
                break;
            } else {
                ((Casa) bordas.getCasa(x - cont, y)).setAtaquePreto(true);
            }
            cont++;
        }

        // verifica se pode mover para direita
        cont = 1;
        while (bordas.getCasa(x + cont, y) != null) {
            if (bordas.getCasa(x + cont, y).temPeca()) {
                ((Casa) bordas.getCasa(x + cont, y)).setAtaquePreto(true);
                break;
            } else {
                ((Casa) bordas.getCasa(x + cont, y)).setAtaquePreto(true);
            }
            cont++;
        }

        // verifica se pode mover para cima
        cont = 1;
        while (bordas.getCasa(x, y - cont) != null) {
            if (bordas.getCasa(x, y - cont).temPeca()) {
                ((Casa) bordas.getCasa(x, y - cont)).setAtaquePreto(true);
                break;
            } else {
                ((Casa) bordas.getCasa(x, y - cont)).setAtaquePreto(true);
            }
            cont++;
        }

    }

    //Verifica se a peça já foi movimentada
    public boolean torreMovimentou() {
        return movimentou;
    }

    public Vector getCasasQueEstaoVisiveis(Borda bordas) {
        super.casasQueEstaoVisiveis = new Vector();
        //verifica se pode mover para baixo
        int cont = 1;
        while (bordas.getCasa(x, y + cont) != null) {
            if (bordas.getCasa(x, y + cont).temPeca()) {
                casasQueEstaoVisiveis.add((Casa) bordas.getCasa(x, y + cont));
                break;
            } else {
                casasQueEstaoVisiveis.add((Casa) bordas.getCasa(x, y + cont));
            }
            cont++;
        }

        // verifica se pode mover para esquerda
        cont = 1;
        while (bordas.getCasa(x - cont, y) != null) {
            if (bordas.getCasa(x - cont, y).temPeca()) {
                casasQueEstaoVisiveis.add((Casa) bordas.getCasa(x - cont, y));
                break;
            } else {
                casasQueEstaoVisiveis.add((Casa) bordas.getCasa(x - cont, y));
            }
            cont++;
        }

        // verifica se pode mover para direita
        cont = 1;
        while (bordas.getCasa(x + cont, y) != null) {
            if (bordas.getCasa(x + cont, y).temPeca()) {
                casasQueEstaoVisiveis.add((Casa) bordas.getCasa(x + cont, y));
                break;
            } else {
                casasQueEstaoVisiveis.add((Casa) bordas.getCasa(x + cont, y));
            }
            cont++;
        }

        // verifica se pode mover para cima
        cont = 1;
        while (bordas.getCasa(x, y - cont) != null) {
            if (bordas.getCasa(x, y - cont).temPeca()) {
                casasQueEstaoVisiveis.add((Casa) bordas.getCasa(x, y - cont));
                break;
            } else {
                casasQueEstaoVisiveis.add((Casa) bordas.getCasa(x, y - cont));
            }
            cont++;
        }
        return casasQueEstaoVisiveis;


    }

    public Vector getCasasQueEstaoEmAtaque(Borda bordas) {
        // possíveis casas que o bispo pode ocupar.
        super.casasQueEstaoEmAtaque = new Vector();

        // verifica se pode mover para baixo
        int cont = 1;
        while (bordas.getCasa(x, y + cont) != null) {
            if (bordas.getCasa(x, y + cont).getPeca() != null) {
                if (bordas.getCasa(x, y + cont).getPeca().getCor() == Color.WHITE) {
                    casasQueEstaoEmAtaque.add(bordas.getCasa(x, y + cont));
                    break;
                }
                if (bordas.getCasa(x, y + cont).getPeca().getCor() == Color.BLACK) {
                    break;
                }
            }
            casasQueEstaoEmAtaque.add(bordas.getCasa(x, y + cont));
            cont++;
        }

        // verifica se pode mover para esquerda
        cont = 1;
        while (bordas.getCasa(x - cont, y) != null) {
            if (bordas.getCasa(x - cont, y).getPeca() != null) {
                if (bordas.getCasa(x - cont, y).getPeca().getCor() == Color.WHITE) {
                    casasQueEstaoEmAtaque.add(bordas.getCasa(x - cont, y));
                    break;
                }
                if (bordas.getCasa(x - cont, y).getPeca().getCor() == Color.BLACK) {
                    break;
                }
            }
            casasQueEstaoEmAtaque.add(bordas.getCasa(x - cont, y));
            cont++;
        }

        // verifica se pode mover para direita
        cont = 1;
        while (bordas.getCasa(x + cont, y) != null) {
            if (bordas.getCasa(x + cont, y).getPeca() != null) {
                if (bordas.getCasa(x + cont, y).getPeca().getCor() == Color.WHITE) {
                    casasQueEstaoEmAtaque.add(bordas.getCasa(x + cont, y));
                    break;
                }
                if (bordas.getCasa(x + cont, y).getPeca().getCor() == Color.BLACK) {
                    break;
                }
            }
            casasQueEstaoEmAtaque.add(bordas.getCasa(x + cont, y));
            cont++;
        }

        // verifica se pode mover para cima
        cont = 1;
        while (bordas.getCasa(x, y - cont) != null) {
            if (bordas.getCasa(x, y - cont).getPeca() != null) {
                if (bordas.getCasa(x, y - cont).getPeca().getCor() == Color.WHITE) {
                    casasQueEstaoEmAtaque.add(bordas.getCasa(x, y - cont));
                    break;
                }
                if (bordas.getCasa(x, y - cont).getPeca().getCor() == Color.BLACK) {
                    break;
                }
            }
            casasQueEstaoEmAtaque.add(bordas.getCasa(x, y - cont));
            cont++;
        }
        return casasQueEstaoEmAtaque;
    }

    public boolean casaVisivel(Borda bordas, CasaItf casa) {
        Vector casaTemporaria = this.getCasasQueEstaoVisiveis(bordas);
        return casaTemporaria.contains(casa);
    }
}
