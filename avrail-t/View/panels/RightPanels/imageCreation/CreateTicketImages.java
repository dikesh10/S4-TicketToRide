package View.panels.RightPanels.imageCreation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Une classe pour créer des images de tickets en ajoutant du texte sur une
 * image existante.
 */
public class CreateTicketImages {

    private BufferedImage existingImage; // Each instance will have its own BufferedImage
    private static final String referencePathsexistingImage = "Resources/destination-temp/";

    /**
     * Construit un nouvel objet CreateTicketImages avec les paramètres spécifiés.
     * 
     * @param ville1 Le nom de la première ville.
     * @param ville2 Le nom de la deuxième ville.
     * @param points Les points associés aux villes.
     */
    public CreateTicketImages(String ville1, String ville2, int points, int id) {
        this.existingImage = loadImage("Resources/reference/image.png");
        drawText(ville1, ville2, "point" + points, id);
    }

    public static void main(String[] args) {
        new CreateTicketImages("france", "USA", 0, 0);
    }

    private BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch (IOException e) {
            handleIOException("Erreur lors du chargement de l'image : ", e);
            return null;
        }
    }

    private void drawText(String helloText, String byeText, String points, int paths) {
        Graphics2D g2d = configureGraphics();
        positionText(g2d, helloText, byeText, points);
        g2d.dispose();
        String imagepaths = paths + ".png";
        saveImage(imagepaths);
    }

    private Graphics2D configureGraphics() {
        Graphics2D g2d = existingImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(new Font("Arial", Font.ITALIC, 18));
        g2d.setColor(Color.WHITE);
        return g2d;
    }

    private void positionText(Graphics2D g2d, String helloText, String byeText, String points) {
        int textWidth = g2d.getFontMetrics().stringWidth(helloText);
        int textHeight = g2d.getFontMetrics().getHeight();
        int x = (existingImage.getWidth() - textWidth) / 2;
        int y = (existingImage.getHeight() - textHeight * 2) / 2;
        drawTextOnImage(g2d, helloText, byeText, points, x, y);
    }

    private void drawTextOnImage(Graphics2D g2d, String helloText, String byeText, String points, int x, int y) {
        g2d.drawString(helloText, x, y + 10);
        g2d.drawString(byeText, x, y + 40);
        g2d.drawString(points, x + 55, y + 60);
    }

    private void saveImage(String fileName) {
        File outputFile = new File(referencePathsexistingImage + fileName);
        try {
            String format = fileName.substring(fileName.lastIndexOf('.') + 1);
            ImageIO.write(existingImage, format, outputFile);
        } catch (IOException e) {
            handleIOException("Erreur lors de l'enregistrement de l'image : ", e);
        }
    }

    private void handleIOException(String message, IOException e) {
        System.out.println(message + e.getMessage());
    }
}
