
import javax.swing.*;
import java.awt.*;

public class JPanelWithBG extends JPanel {
    Image image;
    public JPanelWithBG(int num){
        ImageIcon img;
        img = new ImageIcon("Bank Background2.jpg");
        image= img.getImage();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
