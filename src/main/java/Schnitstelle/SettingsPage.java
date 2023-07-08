package Schnitstelle;

import GUI.*;

import static Schnitstelle.Main.mw;
import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;

public class SettingsPage {

    public static BetterInputField ISV = (BetterInputField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.VORNAME_INPUT);
    public static BetterInputField ISN = (BetterInputField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.NACHNAME_INPUT);
    public static BetterInputField ISE = (BetterInputField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.EMAIL_INPUT);
    public static BetterInputField ISP = (BetterInputField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.PIN_INPUT);
    public static BetterInputField ISC = (BetterInputField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.CONFIRM_PIN_INPUT);
    public static BetterButton BSS = (BetterButton) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.SAVE_BUTTON);

    public static void InitializeSaveButton(){

    }
}
