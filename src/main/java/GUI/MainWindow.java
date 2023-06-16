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
        f.setSize(700, 500);
        f.setResizable(false);
        f.setLayout(null);


        StackedWidget pages = new StackedWidget();
        pages.setBounds(f.getBounds());
        StackedPane a = new StackedPane(f);
        //a.setBackground(new Color(255,0,0));
        StackedPane b = new StackedPane(f);
        StackedPane c = new StackedPane(f);
        pages.registerPane(a);
        pages.registerPane(b);
        pages.registerPane(c);
        a.setLayout(null);
        BetterTextField d = new BetterTextField(new UITextFieldMethod() {
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
        BetterTextField dx = new BetterTextField(new UITextFieldMethod() {
            @Override
            public void performMethod(String data) {
                System.out.println(1);
            }
        });
        BetterButton e = new BetterButton(new UIButtonMethod() {
            @Override
            public void performMethod() {
                pages.showPlane(1);
            }
        });

        BetterButton i = new BetterButton(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if (p.getEchoChar() == 0) {
                    p.setEchoChar('•');
                }
                else{
                    p.setEchoChar((char)0);
                }

            }
        });
        dx.setEchoChar((char)0);
        e.setText("Switch to next Page");
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
        //i.setText("X");
        a.registerComponent(i);

        a.registerComponent(e);
        b.registerComponent(d);
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
