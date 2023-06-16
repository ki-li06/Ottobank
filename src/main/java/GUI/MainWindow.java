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
        f.setSize(850, 500);
        f.setResizable(false);
        f.setLayout(null);

        StackedWidget pages = new StackedWidget();
        pages.setBounds(f.getBounds());
        StackedPane a = new StackedPane(f);
        StackedPane b = new StackedPane(f);
        StackedPane c = new StackedPane(f);
        pages.registerPane(a);
        pages.registerPane(b);
        pages.registerPane(c);

        BetterTextField d = new BetterTextField(new UITextFieldMethod() {
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
        e.setText("Switch to next Page");
        a.registerComponent(e);
        b.registerComponent(d);
        pages.showPlane(0);

        f.add(pages);
        f.setVisible(true);

    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
