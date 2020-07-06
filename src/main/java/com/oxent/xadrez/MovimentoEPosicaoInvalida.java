package com.oxent.xadrez;

/**
 * Classe que vai tratar possíveis erros que verifica se o movimento e a está
 * são válidos, se não for correto não realizará a jogada.
 */
public class MovimentoEPosicaoInvalida extends Exception {

    public MovimentoEPosicaoInvalida(String mensagem) {
        super(mensagem);
    }

}
