package GUI;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;


public class MainWindow{
    JFrame mainFrame;
    private StackedWidget layerManager;
    MainWindow() {
        FlatDarkLaf.registerCustomDefaultsSource( "com.myapp.themes" );
        FlatDarkLaf.setup();

        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        layerManager = new StackedWidget(mainFrame);
        StackedPane loginPage = new StackedPane(layerManager, new Dimension(400, 500));

        layerManager.registerPane(loginPage, "LOGIN_PAGE");

        //Info Label
        JLabel infoLabel = new JLabel("Welcome to GOBank");
        int LabelWidth = infoLabel.getPreferredSize().width;
        int LabelHeight = infoLabel.getPreferredSize().height;
        infoLabel.setBounds((loginPage.getWidth()-LabelWidth)/2-8, 40, LabelWidth, LabelHeight);
        loginPage.registerComponent(infoLabel, "PRIVATE");

        //Username Text Field
        BetterTextField usernameTextField = new BetterTextField();
        usernameTextField.setEchoChar((char) 0);
        usernameTextField.putClientProperty("JTextField.placeholderText", "Username");
        usernameTextField.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 80, 250, 30);
        loginPage.registerComponent(usernameTextField, "USERNAME_INPUT");

        //Password Text Field
        BetterTextField passwordTextField = new BetterTextField();
        passwordTextField.putClientProperty("JTextField.placeholderText", "Password");
        passwordTextField.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        passwordTextField.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 150, 250, 30);
        loginPage.registerComponent(passwordTextField, "PASSWORD_INPUT");

        //Login Button
        BetterButton loginButton = new BetterButton();
        loginButton.setText("Log in");
        loginButton.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 220, 250, 30);
        loginPage.registerComponent(loginButton, "LOGIN_BUTTON");

        BetterButton registerButton = new BetterButton();
        registerButton.setText("Register");
        registerButton.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 290, 250, 30);
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

        JLabel registerInfoLabel = new JLabel("Register your account");
        LabelWidth = registerInfoLabel.getPreferredSize().width;
        LabelHeight = registerInfoLabel.getPreferredSize().height;
        registerInfoLabel.setBounds((registerPage.getWidth()-LabelWidth)/2-8, 40, LabelWidth, LabelHeight);
        registerPage.registerComponent(registerInfoLabel, "PRIVATE");


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
