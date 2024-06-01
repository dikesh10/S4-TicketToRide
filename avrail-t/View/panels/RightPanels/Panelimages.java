package View.panels.RightPanels;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.*;

public class Panelimages extends JPanel {

        public JLabel label = new JLabel();
        String imagePath;

        Panelimages(String imagePath) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage();
                int desiredWidth = 125, desiredHeight = 100;
                double scale = Math.min((double) desiredWidth / img.getWidth(null),
                                (double) desiredHeight / img.getHeight(null));
                int scaledWidth = (int) (img.getWidth(null) * scale),
                                scaledHeight = (int) (img.getHeight(null) * scale);
                Image scaledImg = img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaledImg));
                label.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
                add(label);
        }

        public Panelimages() {

        }

        public void setImages(String imagePath) {

                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage();
                int desiredWidth = 125, desiredHeight = 100;

                double scale = Math.min((double) desiredWidth / img.getWidth(null),
                                (double) desiredHeight / img.getHeight(null));
                int scaledWidth = (int) (img.getWidth(null) * scale),
                                scaledHeight = (int) (img.getHeight(null) * scale);
                Image scaledImg = img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

                label.setIcon(new ImageIcon(scaledImg));
                label.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
                add(label);

        }

}
