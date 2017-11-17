package com.oxentgames.utilitarios;

import javax.swing.*;
import java.awt.*;

//Classe que carrega as imagens do jogo.

public final class CarregaImagemUtil
{
    //Método que utiliza para pegar as imagens.
	public final static ImageIcon createImageIcon(String path)
	{
		Image imagemURL = Toolkit.getDefaultToolkit().getImage(CarregaImagemUtil.class.getResource(path));
		if (imagemURL != null)
		{		
			return new ImageIcon(imagemURL, "");	
		}
		else
		{
			return null;
		}
	}
	
	
	
}