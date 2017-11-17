package com.oxentgames;

import com.jtattoo.plaf.texture.TextureLookAndFeel;
import com.oxentgames.utilitarios.GeralImagens;
import com.oxentgames.utilitarios.ImagemDeFundo;
import com.oxentgames.xadrez.aplicativos.IniciarJogo;
import org.jdesktop.swingx.JXButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Properties;

public class Main extends JFrame {
    private static final long serialVersionUID = 1346595952955948075L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Properties props = new Properties();
                props.put("logoString", "David");
                props.put("licenseKey", "00");
                TextureLookAndFeel.setCurrentTheme(props);
                UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                Main frame = new Main();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Main() {
        super("Oxente-Mate Lampião");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setSize(new Dimension(500, 600));
        super.setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JXButton btnIniciar = new JXButton("Iniciar");
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBackground(Color.decode("#5a2e12"));

        btnIniciar.addActionListener(arg0 -> {
            IniciarJogo ini = new IniciarJogo();
            ini.setVisible(true);
            dispose();
        });
        btnIniciar.setBounds(371, 168, 98, 37);
        contentPane.add(btnIniciar);

        JXButton btnSair = new JXButton("Sair");
        btnSair.setForeground(Color.WHITE);
        btnSair.setBackground(Color.decode("#5a2e12"));

        btnSair.addActionListener(a -> {
            int ret = JOptionPane.showConfirmDialog(contentPane, "Vai Correr é? ", "Oxente Cabra", JOptionPane.YES_NO_OPTION);
            if (ret == JOptionPane.YES_OPTION) {
                System.exit(-1);
            }
        });

        btnSair.setBounds(371, 227, 98, 37);
        contentPane.add(btnSair);
        contentPane.add(new ImagemDeFundo(GeralImagens.BACKGROUND, 0));

    }
}
