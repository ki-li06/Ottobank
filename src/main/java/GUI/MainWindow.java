package GUI;
import com.formdev.flatlaf.FlatDarkLaf;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow {
    JFrame f;
    MainWindow(){
        FlatDarkLaf.setup();
        f=new JFrame();//creating instance of JFrame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(null);


        StackedWidget pages = new StackedWidget(f);
        StackedPane a = new StackedPane(pages, new Dimension(400, 500));
        //a.setBackground(new Color(255,0,0));
        StackedPane b = new StackedPane(pages, new Dimension(800, 500));
        StackedPane c = new StackedPane(pages);
        pages.registerPane(a);
        pages.registerPane(b);
        pages.registerPane(c);
        //a.setLayout(null);
        BetterTextField dx = new BetterTextField(new UITextFieldMethod() {
            @Override
            public void performMethod(String data) {
                System.out.println(data);
            }
        });
        BetterTextField p = new BetterTextField(new UITextFieldMethod() {
            @Override
            public void performMethod(String data) {
                System.out.println(data);
            }
        });
        BetterButton e = new BetterButton(new UIButtonMethod() {
            @Override
            public void performMethod() {
                pages.showPlane(1);
            }
        });
        p.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Call your function or perform the desired action
                if (p.getText().equals("Password")) {
                    p.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (p.getText().isEmpty()) {
                    p.setText("Password");
                    p.setEchoChar((char)0);
                }

            }
        });
        BetterButton x = new BetterButton(new UIButtonMethod() {
            @Override
            public void performMethod() {
                pages.showPlane(0);
                /*f.setSize(700, 500);
                pages.setSize(700, 500);
                System.out.println(pages.getSize());*/
            }
        });
        BetterButton i = new BetterButton(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if (p.getEchoChar() == 0) {
                    p.setEchoChar('•');
                    i.setIcon(scaleIcon(new ImageIcon("src/main/java/GUI/icons/eye-slash.png"), 30, 30));
                } else {
                    p.setEchoChar((char) 0);
                    i.setIcon(scaleIcon(new ImageIcon("src/main/java/GUI/icons/eye.png"), 30, 30));
                }
            }
        });

        e.setText("Log in");
        Font font = new Font(dx.getFont().getFontName(), dx.getFont().getStyle(), 20);
        dx.setBounds(a.getWidth()/2-250/2-8, 80, 250, 30);
        dx.setFont(font);
        a.registerComponent(dx);
        p.setBounds(a.getWidth()/2-250/2-8, 150, 250, 30);
        p.setFont(font);
        a.registerComponent(p);
        System.out.println(p.getX()+50);
        i.setBounds(p.getX()+p.getWidth()+15, p.getY()+1, 28, 28);
        System.out.println(System.getProperty("user.dir"));
        i.setIcon(scaleIcon(new ImageIcon("src/main/java/GUI/icons/eye.png"), 30,30));
        a.registerComponent(i);
        e.setBounds(a.getWidth()/2-250/2-8, 220, 250, 30);
        a.registerComponent(e);
        b.registerComponent(x);
        pages.showPlane(0);

        f.add(pages);
        f.setVisible(true);

    }
    private static ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    public static void main(String[] args) {
        new MainWindow();
    }
}
