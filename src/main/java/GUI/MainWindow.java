package GUI;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;


public class MainWindow {
    JFrame f;
    MainWindow(){
        FlatDarkLaf.setup();
        f=new JFrame();//creating instance of JFrame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        JLayeredPane pages = new JLayeredPane();
        pages.setPreferredSize(new Dimension(400, 400));
        BetterTextField a = new BetterTextField(new UITextFieldMethod() {
            @Override
            public void performMethod(String data) {
                System.out.println(data);
            }
        });
        pages.add(a, 2);
        a.setBounds(0,0,200,20);
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.RED);
        backgroundPanel.setBounds(0, 0, 400, 400);
        pages.add(backgroundPanel, Integer.valueOf(0)); // Add to layer 0

        // Create the foreground panel
        JPanel foregroundPanel = new JPanel();
        foregroundPanel.setBackground(Color.BLUE);
        foregroundPanel.setBounds(100, 100, 200, 200);
        pages.add(foregroundPanel, Integer.valueOf(1)); // Add to layer 1
        foregroundPanel.setVisible(false);


        // Add the layered pane to the frame
        f.add(pages);

        // Display the frame
        f.setVisible(true);

    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
