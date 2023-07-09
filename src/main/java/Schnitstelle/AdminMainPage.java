package Schnitstelle;

import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterInputField;
import GUI.UIs.UIButtonMethod;

import static Schnitstelle.Main.mw;
import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;

public class AdminMainPage {
    public static final BetterInputField IMAN = (BetterInputField) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.USER_NAME);
    public static final BetterButton BMAL = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.LOGIN_BUTTON);

    public static void InitializeLoginButton(){
        BMAL.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if(IMAN.getText().equals("")){
                    PopUp.showError("Alle Eingaben müssen ausgefüllt sein!");
                }else{
                    mw.getWindow().showPlane(2);
                }
            }
        });
    }
}
