import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JPanel {

    private BufferedImage image;

    public Main(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            // handle exception
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        double scaleX = (double) width / image.getWidth();
        double scaleY = (double) height / image.getHeight();
        double scale = Math.min(scaleX, scaleY);
        int x = (int) ((width - image.getWidth() * scale) / 2);
        int y = (int) ((height - image.getHeight() * scale) / 2);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, x, y, (int) (image.getWidth() * scale), (int) (image.getHeight() * scale), null);
        g2d.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        Main panel = new Main("src/FNHeightMap.jpg");
        panel.setPreferredSize(new Dimension(500, 500));
        frame.add(panel);
        // Load your image file
        ImageIcon icon = new ImageIcon("src/FNHeightMap.jpg");
        // Set the icon image
        frame.setIconImage(icon.getImage());
        Toolkit.getDefaultToolkit().getImage("src/FNHeightMap.jpg");

        frame.pack();
        panel.setBackground(Color.BLACK);
        frame.setVisible(true);
    }

}