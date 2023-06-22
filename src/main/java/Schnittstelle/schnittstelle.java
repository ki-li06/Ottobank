package Schnittstelle;

import GUI.*;

import java.util.Objects;

public class schnittstelle {
    public static void main(String[] args) {
        MainWindow mw = WindowManager.getWindow();
        BetterTextField t = (BetterTextField) mw.getWindow().getFrame("LOGIN_PAGE").getElement("EMAIL_INPUT");

       BetterButton b =  (BetterButton) mw.getWindow().getFrame("LOGIN_PAGE").getElement("LOGIN_BUTTON");
        b.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                System.out.println("Hello World");
                System.out.println(t.getText());
                if(Objects.equals(t.getText(), "moins"))
                {
                    System.out.println("HEY!");
                    mw.getWindow().showPlane(mw.getWindow().getPlaneIndex("REGISTER_PAGE"));
                }
            }
        });

    }
    public static void main(MainWindow mw) {

    }
}
