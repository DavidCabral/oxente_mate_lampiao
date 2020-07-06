package com.oxent.utilitarios;

import javax.swing.*;
import java.awt.*;

//Classe que carrega as imagens do jogo.
public final class CarregaImagemUtil {

    //Método que utiliza para pegar as imagens.
    public static ImageIcon createImageIcon(String path) {
        Image imagemURL = Toolkit.getDefaultToolkit().getImage(CarregaImagemUtil.class.getResource(path));
        return imagemURL != null ? new ImageIcon(imagemURL, "") : null;
    }

}
