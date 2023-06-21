package GUI;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;


public class MainWindow{
    JFrame mainFrame;
    private StackedWidget layerManager;
    MainWindow() {
        FlatDarkLaf.registerCustomDefaultsSource("flatlaf_themes");
        FlatDarkLaf.setup();

        mainFrame = new JFrame();
        mainFrame.setTitle("GOBank");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        layerManager = new StackedWidget(mainFrame);
        StackedPane loginPage = new StackedPane(layerManager, new Dimension(400, 375));

        layerManager.registerPane(loginPage, "LOGIN_PAGE");

        //Info Label
        JLabel infoLabel = new JLabel("<html><center>Welcome to<br>GOBank</center></html>", JLabel.CENTER);
        int LabelHeight = infoLabel.getPreferredSize().height;
        infoLabel.setBounds(0, 40, 400, LabelHeight);
        loginPage.registerComponent(infoLabel, "PRIVATE");

        //Username Text Field
        BetterTextField emailTextField = new BetterTextField();
        emailTextField.setEchoChar((char) 0);
        emailTextField.putClientProperty("JTextField.placeholderText", "Email");
        emailTextField.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        loginPage.registerComponent(emailTextField, "EMAIL_INPUT");

        //Password Text Field
        BetterTextField passwordTextField = new BetterTextField();
        passwordTextField.putClientProperty("JTextField.placeholderText", "Password");
        passwordTextField.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        passwordTextField.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 170, 250, 30);
        loginPage.registerComponent(passwordTextField, "PASSWORD_INPUT");

        //Login Button
        BetterButton loginButton = new BetterButton();
        loginButton.setText("Log in");
        loginButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                JOptionPane.showMessageDialog(null, "Test", "TEST", JOptionPane.ERROR_MESSAGE);
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

        //register Page
        StackedPane registerPage = new StackedPane(layerManager, new Dimension(400,500));
        layerManager.registerPane(registerPage, "REGISTER_PAGE");

        JLabel registerInfoLabel = new JLabel("Register your account", JLabel.CENTER);
        LabelHeight = registerInfoLabel.getPreferredSize().height;
        registerInfoLabel.setBounds(0, 40, 400, LabelHeight);
        registerPage.registerComponent(registerInfoLabel, "PRIVATE");


        //vorname TextField
        BetterTextField vornameTextField = new BetterTextField();
        vornameTextField.setEchoChar((char) 0);
        vornameTextField.putClientProperty("JTextField.placeholderText", "Vorname");
        vornameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        registerPage.registerComponent(vornameTextField, "VORNAME_INPUT");

        //nachname TextField
        BetterTextField nachnameTextField = new BetterTextField();
        nachnameTextField.setEchoChar((char) 0);
        nachnameTextField.putClientProperty("JTextField.placeholderText", "Nachname");
        nachnameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 170, 250, 30);
        registerPage.registerComponent(nachnameTextField, "NACHNAME_INPUT");

        //email TextField
        BetterTextField emailRegisterTextField = new BetterTextField();
        emailRegisterTextField.setEchoChar((char) 0);
        emailRegisterTextField.putClientProperty("JTextField.placeholderText", "Email");
        emailRegisterTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 210, 250, 30);
        registerPage.registerComponent(emailRegisterTextField, "EMAIL_INPUT");

        //pin TextField
        BetterTextField pinRegisterTextField = new BetterTextField();
        pinRegisterTextField.setEchoChar((char) 0);
        pinRegisterTextField.putClientProperty("JTextField.placeholderText", "PIN");
        pinRegisterTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 250, 250, 30);
        registerPage.registerComponent(pinRegisterTextField, "PIN_INPUT");

        //pin2 TextField
        BetterTextField pin2RegisterTextField = new BetterTextField();
        pin2RegisterTextField.setEchoChar((char) 0);
        pin2RegisterTextField.putClientProperty("JTextField.placeholderText", "PIN2");
        pin2RegisterTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 290, 250, 30);
        registerPage.registerComponent(pin2RegisterTextField, "PIN2_INPUT");

        //Register Button
        BetterButton registerButton2 = new BetterButton();
        registerButton2.setText("Register");
        registerButton2.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 330, 250, 30);
        registerPage.registerComponent(registerButton2, "PRIVATE");

        //Back to Login Window Button
        BetterButton loginButton2 = new BetterButton();
        loginButton2.setText("Back to log in");
        loginButton2.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 390, 250, 30);
        loginButton2.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                layerManager.showPlane(0);
            }
        });
        registerPage.registerComponent(loginButton2, "PRIVATE");


        layerManager.showPlane(0);
        mainFrame.add(layerManager);
        mainFrame.setVisible(true);

    }
    public StackedWidget getWindow() {
        return layerManager;
    }
    public static void main(String[] args) {
        new MainWindow();
    }
}
