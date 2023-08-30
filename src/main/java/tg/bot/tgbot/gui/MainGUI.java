package tg.bot.tgbot.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainGUI {
    public static void enableGUI() {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

    private static class MainFrame extends JFrame {
        private MainFrame() {
            setTitle("Image Frame");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            try {
                ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/boton.jpg")));
                JLabel imageLabel = new JLabel(imageIcon);
                add(imageLabel, BorderLayout.CENTER);
                pack();
                setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}