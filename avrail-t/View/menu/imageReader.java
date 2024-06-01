package View.menu;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class imageReader{

    public static JButton GenerateIcon(URL path){
        ImageIcon imageIcon = new ImageIcon(path); 
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
        return new JButton(imageIcon);
    }
    
}
