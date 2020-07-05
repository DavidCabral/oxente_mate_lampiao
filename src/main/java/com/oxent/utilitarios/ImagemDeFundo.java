package com.oxent.utilitarios;

import javax.swing.*;
import java.awt.*;

public class ImagemDeFundo extends JComponent {
    public static final String graphics = null;
    private Image imagemDeFundo;
    private Toolkit toolkit;
    private MediaTracker mediaTracker;
    private int x;

    public ImagemDeFundo(String imagem, int x) {
        this.x = x;

        toolkit = Toolkit.getDefaultToolkit();
        imagemDeFundo = toolkit.getImage(ImagemDeFundo.class.getResource(imagem));

        mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(imagemDeFundo, 0);

        try {
            mediaTracker.waitForID(0);
        } catch (InterruptedException ie) {
            System.err.println(ie);
            System.exit(1);
        }

        super.setSize(imagemDeFundo.getWidth(null), imagemDeFundo.getHeight(null));

        super.setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(imagemDeFundo, x, 0, null);
    }
}
