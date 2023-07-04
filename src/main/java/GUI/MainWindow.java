package GUI;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class MainWindow{
    JFrame mainFrame;
    private final StackedWidget layerManager;
    MainWindow() {
        FlatDarkLaf.registerCustomDefaultsSource("flatlaf_themes");
        FlatDarkLaf.setup();

        mainFrame = new JFrame();
        mainFrame.setTitle("Ottobank");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        layerManager = new StackedWidget(mainFrame);

        createLoginPage();

        createRegisterPage();

        createMainPage();

        createSettingsPage();

        layerManager.showPlane(2);
        mainFrame.add(layerManager);
        mainFrame.setVisible(true);

    }
    public StackedWidget getWindow() {
        return layerManager;
    }

    void createLoginPage(){
        StackedPane loginPage = new StackedPane(layerManager, new Dimension(400, 375));

        layerManager.registerPane(loginPage, "LOGIN_PAGE");

        //Info Label
        JLabel infoLabel = new JLabel("<html><center>Welcome to<br>Ottobank</center></html>", JLabel.CENTER);
        int LabelHeight = infoLabel.getPreferredSize().height;
        infoLabel.setBounds(0, 40, 400, LabelHeight);
        loginPage.registerComponent(infoLabel, "PRIVATE");

        //Email Text Field
        BetterTextField emailTextField = new BetterTextField();
        emailTextField.setEchoChar((char) 0);
        emailTextField.putClientProperty("JTextField.placeholderText", "Email");
        emailTextField.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        loginPage.registerComponent(emailTextField, "EMAIL_INPUT");

        //PIN Text Field
        BetterTextField pinTextField = new BetterTextField();
        pinTextField.putClientProperty("JTextField.placeholderText", "PIN");
        pinTextField.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        pinTextField.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 170, 250, 30);
        loginPage.registerComponent(pinTextField, "PIN_INPUT");

        //Login Button
        BetterButton loginButton = new BetterButton();
        loginButton.setText("Log in");
        loginButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                JOptionPane.showMessageDialog(null, "Test", "TEST", JOptionPane.WARNING_MESSAGE);
            }
        });
        loginButton.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 210, 250, 30);
        loginPage.registerComponent(loginButton, "LOGIN_BUTTON");

        BetterButton registerButton = new BetterButton();
        registerButton.setText("Register");
        registerButton.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 270, 250, 30);
        registerButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                layerManager.showPlane(1);
            }
        });
        loginPage.registerComponent(registerButton, "PRIVATE");
    }

    void createRegisterPage(){
        //register Page
        StackedPane registerPage = new StackedPane(layerManager, new Dimension(400,500));
        layerManager.registerPane(registerPage, "REGISTER_PAGE");

        JLabel registerInfoLabel = new JLabel("Register your account", JLabel.CENTER);
        int LabelHeight = registerInfoLabel.getPreferredSize().height;
        registerInfoLabel.setBounds(0, 40, 400, LabelHeight);
        registerPage.registerComponent(registerInfoLabel, "PRIVATE");


        //Vorname Text Field
        BetterTextField vornameTextField = new BetterTextField();
        vornameTextField.setEchoChar((char) 0);
        vornameTextField.putClientProperty("JTextField.placeholderText", "Vorname");
        vornameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        registerPage.registerComponent(vornameTextField, "VORNAME_INPUT");

        //Nachname Text Field
        BetterTextField nachnameTextField = new BetterTextField();
        nachnameTextField.setEchoChar((char) 0);
        nachnameTextField.putClientProperty("JTextField.placeholderText", "Nachname");
        nachnameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 170, 250, 30);
        registerPage.registerComponent(nachnameTextField, "NACHNAME_INPUT");

        //Email Text Field
        BetterTextField emailTextField = new BetterTextField();
        emailTextField.setEchoChar((char) 0);
        emailTextField.putClientProperty("JTextField.placeholderText", "Email");
        emailTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 210, 250, 30);
        registerPage.registerComponent(emailTextField, "EMAIL_INPUT");

        //Pin Text Field
        BetterTextField pinTextField = new BetterTextField();
        pinTextField.setEchoChar((char) 0);
        pinTextField.putClientProperty("JTextField.placeholderText", "PIN");
        pinTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 250, 250, 30);
        registerPage.registerComponent(pinTextField, "PIN_INPUT");

        //Confirm Pin Text Field
        BetterTextField pinConfirmRegisterTextField = new BetterTextField();
        pinConfirmRegisterTextField.setEchoChar((char) 0);
        pinConfirmRegisterTextField.putClientProperty("JTextField.placeholderText", "Confirm PIN");
        pinConfirmRegisterTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 290, 250, 30);
        registerPage.registerComponent(pinConfirmRegisterTextField, "CONFIRM_PIN_INPUT");

        //Register Button
        BetterButton registerButton = new BetterButton();
        registerButton.setText("Register");
        registerButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 330, 250, 30);
        registerPage.registerComponent(registerButton, "REGISTER_BUTTON");

        //Back to Login-Window Button
        BetterButton loginButton = new BetterButton();
        loginButton.setText("Back to log in");
        loginButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 390, 250, 30);
        loginButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                layerManager.showPlane(0);
            }
        });
        registerPage.registerComponent(loginButton, "PRIVATE");

    }

    void createMainPage(){
        StackedPane mainPage = new StackedPane(layerManager, new Dimension(450, 260));
        layerManager.registerPane(mainPage, "MAIN_PAGE");

        BetterButton settingsButton = new BetterButton();
        settingsButton.setBounds(mainPage.getWidth()-75, 10, 45, 45);
        settingsButton.setIcon(new ImageIcon("src/main/resources/icons/settings.png"));

        mainPage.registerComponent(settingsButton, "SETTINGS_BUTTON");

        String[] optionsToChoose = {"OPT_1","OPT_2","OPT_3"};
        BetterComboBox kontowechselBox = new BetterComboBox(optionsToChoose);

        kontowechselBox.setBounds(settingsButton.getX()-210, 10, 200, 45);
        kontowechselBox.addMethod(new UIComboBoxMethod() {
            @Override
            public void performMethod(String data) {
                System.out.println(data);
            }
        });

        mainPage.registerComponent(kontowechselBox, "KONTO_WECHSEL_BOX");

        JLabel moneyLabel = new JLabel(formatLabelString("Your Name :)"), JLabel.CENTER);
        int LabelHeight = moneyLabel.getPreferredSize().height;
        moneyLabel.setBounds(10, 15, 140, LabelHeight);
        mainPage.registerComponent(moneyLabel, "NAME_LABEL");

        BetterButton freeMoneyButton = new BetterButton();
        freeMoneyButton.setText("Einzahlen");
        freeMoneyButton.setBounds(25,60,390,30);
        freeMoneyButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                System.out.println(zinssatzPopUp(mainPage));
            }
        });
        mainPage.registerComponent(freeMoneyButton, "DEPOSIT_BUTTON");

        BetterButton transactionButton = new BetterButton();
        transactionButton.setText("Überweisen");
        transactionButton.setBounds(25,100,390,30);
        mainPage.registerComponent(transactionButton, "TRANSACTION_BUTTON");

        BetterButton deleteMoneyButton = new BetterButton();
        deleteMoneyButton.setText("Abheben");
        deleteMoneyButton.setBounds(25,140,390,30);
        mainPage.registerComponent(deleteMoneyButton, "WITHDRAW_BUTTON");

        BetterButton unknownUseButton = new BetterButton();
        unknownUseButton.setText("Zinssatz ändern");
        unknownUseButton.setBounds(25,180,390,30);
        mainPage.registerComponent(unknownUseButton, "???_BUTTON");
    }

    void createSettingsPage() {
        //register Page
        StackedPane registerPage = new StackedPane(layerManager, new Dimension(400,500));
        layerManager.registerPane(registerPage, "SETTINGS_PAGE");

        JLabel registerInfoLabel = new JLabel("Edit your account", JLabel.CENTER);
        int LabelHeight = registerInfoLabel.getPreferredSize().height;
        registerInfoLabel.setBounds(0, 40, 400, LabelHeight);
        registerPage.registerComponent(registerInfoLabel, "PRIVATE");


        //Vorname Text Field
        BetterTextField vornameTextField = new BetterTextField();
        vornameTextField.setEchoChar((char) 0);
        vornameTextField.putClientProperty("JTextField.placeholderText", "Vorname");
        vornameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        registerPage.registerComponent(vornameTextField, "VORNAME_INPUT");

        //Nachname Text Field
        BetterTextField nachnameTextField = new BetterTextField();
        nachnameTextField.setEchoChar((char) 0);
        nachnameTextField.putClientProperty("JTextField.placeholderText", "Nachname");
        nachnameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 170, 250, 30);
        registerPage.registerComponent(nachnameTextField, "NACHNAME_INPUT");

        //Email Text Field
        BetterTextField emailTextField = new BetterTextField();
        emailTextField.setEchoChar((char) 0);
        emailTextField.putClientProperty("JTextField.placeholderText", "Email");
        emailTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 210, 250, 30);
        registerPage.registerComponent(emailTextField, "EMAIL_INPUT");

        //Pin Text Field
        BetterTextField pinTextField = new BetterTextField();
        pinTextField.setEchoChar((char) 0);
        pinTextField.putClientProperty("JTextField.placeholderText", "Alte PIN");
        pinTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 250, 250, 30);
        registerPage.registerComponent(pinTextField, "PIN_INPUT");

        //Confirm Pin Text Field
        BetterTextField pinConfirmRegisterTextField = new BetterTextField();
        pinConfirmRegisterTextField.setEchoChar((char) 0);
        pinConfirmRegisterTextField.putClientProperty("JTextField.placeholderText", "Neue PIN");
        pinConfirmRegisterTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 290, 250, 30);
        registerPage.registerComponent(pinConfirmRegisterTextField, "CONFIRM_PIN_INPUT");

        //Register Button
        BetterButton registerButton = new BetterButton();
        registerButton.setText("Save Changes");
        registerButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 330, 250, 30);
        registerPage.registerComponent(registerButton, "SAVE_BUTTON");

        //Back to Login-Window Button
        BetterButton loginButton = new BetterButton();
        loginButton.setText("Back to main Page");
        loginButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 390, 250, 30);
        loginButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                layerManager.showPlane(2);
            }
        });
        registerPage.registerComponent(loginButton, "PRIVATE");
    }
    public ArrayList<String> einzahlenPopUp(StackedPane page){
        CustomPopup cb = new CustomPopup();
        JLabel infoLabel = new JLabel("Betrag zum Einzahlen eingeben");
        cb.addWidget(infoLabel, cb.createConstraints(0,0,GridBagConstraints.VERTICAL, 2));
        JTextField betragEntry = new JTextField();
        betragEntry.addKeyListener(new IntegerInputKeyListener());
        betragEntry.setHorizontalAlignment(JTextField.CENTER);
        betragEntry.setPreferredSize(new Dimension(300, 35)); // Adjust the height here
        cb.addWidget(betragEntry, cb.createConstraints(0,1,GridBagConstraints.HORIZONTAL, 1));
        return cb.displayPopup(page, "Einzahlen");
    }
    public ArrayList<String> ueberweisenPopUp(StackedPane page){
        CustomPopup cb = new CustomPopup();
        JLabel infoLabel = new JLabel("Betrag zum Überweisen eingeben");
        cb.addWidget(infoLabel, cb.createConstraints(0,0,GridBagConstraints.VERTICAL, 2));
        JTextField betragEntry = new JTextField();
        betragEntry.addKeyListener(new IntegerInputKeyListener());
        betragEntry.setHorizontalAlignment(JTextField.CENTER);
        betragEntry.setPreferredSize(new Dimension(400, 35)); // Adjust the height here
        cb.addWidget(betragEntry, cb.createConstraints(0,1,GridBagConstraints.HORIZONTAL, 1));
        JLabel kontonummerLabel = new JLabel("Kontonummer des anderen Kontos eingeben");
        cb.addWidget(kontonummerLabel, cb.createConstraints(0,2,GridBagConstraints.VERTICAL, 2));
        JTextField kontonummerTextField = new JTextField();
        //kontonummerTextField.addKeyListener(new IntegerInputKeyListener());
        kontonummerTextField.setHorizontalAlignment(JTextField.CENTER);
        kontonummerTextField.setPreferredSize(new Dimension(300, 35)); // Adjust the height here
        cb.addWidget(kontonummerTextField, cb.createConstraints(0,3,GridBagConstraints.HORIZONTAL, 1));
        return cb.displayPopup(page, "Überweisen");
    }
    public ArrayList<String> abhebenPopUp(StackedPane page){
        CustomPopup cb = new CustomPopup();
        JLabel infoLabel = new JLabel("Betrag zum Abheben eingeben");
        cb.addWidget(infoLabel, cb.createConstraints(0,0,GridBagConstraints.VERTICAL, 2));
        JTextField abhebenEntry = new JTextField();
        abhebenEntry.addKeyListener(new IntegerInputKeyListener());
        abhebenEntry.setHorizontalAlignment(JTextField.CENTER);
        abhebenEntry.setPreferredSize(new Dimension(300, 35)); // Adjust the height here
        cb.addWidget(abhebenEntry, cb.createConstraints(0,1,GridBagConstraints.HORIZONTAL, 1));
        return cb.displayPopup(page, "Abheben");
    }
    public ArrayList<String> zinssatzPopUp(StackedPane page){
        CustomPopup cb = new CustomPopup();
        JLabel infoLabel = new JLabel("Neuen Zinssatz eingeben");
        cb.addWidget(infoLabel, cb.createConstraints(0,0,GridBagConstraints.VERTICAL, 2));
        JTextField zinssatzEntry = new JTextField();
        zinssatzEntry.addKeyListener(new IntegerInputKeyListener());
        zinssatzEntry.setHorizontalAlignment(JTextField.CENTER);
        zinssatzEntry.setPreferredSize(new Dimension(300, 35)); // Adjust the height here
        cb.addWidget(zinssatzEntry, cb.createConstraints(0,1,GridBagConstraints.HORIZONTAL, 1));
        return cb.displayPopup(page, "Zinssatz ändern");
    }
    public static void main(String[] args) {

        WindowManager.getWindow();
    }
    public static String formatLabelString(String original) {
        return "<html><center>" + original + "</center></html>";
    }
}
