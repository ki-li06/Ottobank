package GUI;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterComboBox;
import GUI.BetterComponents.BetterInputField;
import GUI.BetterComponents.BetterTextField;
import GUI.Listeners.MoneyKeyListener;
import GUI.UIs.UIButtonMethod;
import GUI.UIs.UIComboBoxMethod;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;

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

        createAdminMainPage();

        createAdminSettingsPage();

        layerManager.showPlane(PAGES.LOGIN_PAGE);
        mainFrame.add(layerManager);
        mainFrame.setVisible(true);

    }
    public StackedWidget getWindow() {
        return layerManager;
    }

    void createLoginPage(){
        StackedPane loginPage = new StackedPane(layerManager, new Dimension(400, 375));

        layerManager.registerPane(loginPage, PAGES.LOGIN_PAGE);

        //Info Label
        JLabel infoLabel = new JLabel("<html><center>Welcome to<br>Ottobank</center></html>", JLabel.CENTER);
        int LabelHeight = infoLabel.getPreferredSize().height;
        infoLabel.setBounds(0, 40, 400, LabelHeight);
        loginPage.registerComponent(infoLabel, COMPONENTS.PRIVATE);

        //Email Text Field
        BetterInputField emailTextField = new BetterInputField();
        emailTextField.setEchoChar((char) 0);
        emailTextField.putClientProperty("JTextField.placeholderText", "Email");
        emailTextField.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        loginPage.registerComponent(emailTextField, COMPONENTS.EMAIL_INPUT);

        //PIN Text Field
        BetterInputField pinTextField = new BetterInputField();
        pinTextField.putClientProperty("JTextField.placeholderText", "PIN");
        pinTextField.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        pinTextField.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 170, 250, 30);
        loginPage.registerComponent(pinTextField, COMPONENTS.PIN_INPUT);

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
        loginPage.registerComponent(loginButton, COMPONENTS.LOGIN_BUTTON);

        BetterButton registerButton = new BetterButton();
        registerButton.setText("Register");
        registerButton.setBounds(loginPage.getWidth() / 2 - 250 / 2 - 8, 270, 250, 30);
        registerButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                layerManager.showPlane(PAGES.REGISTER_PAGE);
            }
        });
        loginPage.registerComponent(registerButton, COMPONENTS.PRIVATE);
    }

    void createRegisterPage(){
        //register Page
        StackedPane registerPage = new StackedPane(layerManager, new Dimension(400,500));
        layerManager.registerPane(registerPage, PAGES.REGISTER_PAGE);

        JLabel registerInfoLabel = new JLabel("Register your account", JLabel.CENTER);
        int LabelHeight = registerInfoLabel.getPreferredSize().height;
        registerInfoLabel.setBounds(0, 40, 400, LabelHeight);
        registerPage.registerComponent(registerInfoLabel, COMPONENTS.PRIVATE);


        //Vorname Text Field
        BetterInputField vornameTextField = new BetterInputField();
        vornameTextField.setEchoChar((char) 0);
        vornameTextField.putClientProperty("JTextField.placeholderText", "Vorname");
        vornameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        registerPage.registerComponent(vornameTextField, COMPONENTS.VORNAME_INPUT);

        //Nachname Text Field
        BetterInputField nachnameTextField = new BetterInputField();
        nachnameTextField.setEchoChar((char) 0);
        nachnameTextField.putClientProperty("JTextField.placeholderText", "Nachname");
        nachnameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 170, 250, 30);
        registerPage.registerComponent(nachnameTextField, COMPONENTS.NACHNAME_INPUT);

        //Email Text Field
        BetterInputField emailTextField = new BetterInputField();
        emailTextField.setEchoChar((char) 0);
        emailTextField.putClientProperty("JTextField.placeholderText", "Email");
        emailTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 210, 250, 30);
        registerPage.registerComponent(emailTextField, COMPONENTS.EMAIL_INPUT);

        //Pin Text Field
        BetterInputField pinTextField = new BetterInputField();
        pinTextField.setEchoChar((char) 0);
        pinTextField.putClientProperty("JTextField.placeholderText", "PIN");
        pinTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 250, 250, 30);
        registerPage.registerComponent(pinTextField, COMPONENTS.PIN_INPUT);

        //Confirm Pin Text Field
        BetterInputField pinConfirmRegisterTextField = new BetterInputField();
        pinConfirmRegisterTextField.setEchoChar((char) 0);
        pinConfirmRegisterTextField.putClientProperty("JTextField.placeholderText", "Confirm PIN");
        pinConfirmRegisterTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 290, 250, 30);
        registerPage.registerComponent(pinConfirmRegisterTextField, COMPONENTS.CONFIRM_PIN_INPUT);

        //Register Button
        BetterButton registerButton = new BetterButton();
        registerButton.setText("Register");
        registerButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 330, 250, 30);
        registerPage.registerComponent(registerButton, COMPONENTS.REGISTER_BUTTON);

        //Back to Login-Window Button
        BetterButton loginButton = new BetterButton();
        loginButton.setText("Back to log in");
        loginButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 390, 250, 30);
        loginButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                layerManager.showPlane(PAGES.LOGIN_PAGE);
            }
        });
        registerPage.registerComponent(loginButton, COMPONENTS.PRIVATE);

    }

    void createMainPage(){
        StackedPane mainPage = new StackedPane(layerManager, new Dimension(550, 350));
        layerManager.registerPane(mainPage, PAGES.MAIN_PAGE);

        BetterButton settingsButton = new BetterButton();
        settingsButton.setBounds(mainPage.getWidth()-75, 10, 45, 45);
        settingsButton.setIcon(new ImageIcon("src/main/resources/icons/settings.png"));
        mainPage.registerComponent(settingsButton, COMPONENTS.SETTINGS_BUTTON);

        String[] optionsToChoose = {"OPT_1","OPT_2","OPT_3"};
        BetterComboBox kontowechselBox = new BetterComboBox(optionsToChoose);
        kontowechselBox.setBounds(settingsButton.getX()-210, 10, 200, 45);
        kontowechselBox.addMethod(new UIComboBoxMethod() {
            @Override
            public void performMethod(String data) {
                System.out.println(data);
            }
        });
        mainPage.registerComponent(kontowechselBox, COMPONENTS.KONTO_WECHSEL_BOX);

        BetterTextField nameLabelFix = new BetterTextField("Name:", JLabel.LEFT);
        int LabelHeightFix = nameLabelFix.getPreferredSize().height;
        nameLabelFix.setBounds(30, 15, 80, LabelHeightFix);
        mainPage.registerComponent(nameLabelFix, COMPONENTS.NAME_LABEL_FIX);

        BetterTextField nameLabelParam = new BetterTextField("Thomas Müller", JLabel.LEFT);
        int LabelHeightParam = nameLabelParam.getPreferredSize().height;
        nameLabelParam.setBounds(95, 15, 190, LabelHeightParam);
        mainPage.registerComponent(nameLabelParam, COMPONENTS.NAME_LABEL_PARAM);

        BetterTextField kontonummerFix = new BetterTextField("Kontonummer: ", JLabel.LEFT);
        int LabelHeightKontonummerFix = kontonummerFix.getPreferredSize().height;
        kontonummerFix.setBounds(30, 75, 150, LabelHeightKontonummerFix);
        mainPage.registerComponent(kontonummerFix, COMPONENTS.KONTONUMMER_FIX);

        BetterTextField kontonummerParam = new BetterTextField("NUMMER", JLabel.LEFT);
        int LabelHeightKontonummerParam = kontonummerParam.getPreferredSize().height;
        kontonummerParam.setBounds(190, 75, 100, LabelHeightKontonummerParam);
        mainPage.registerComponent(kontonummerParam, COMPONENTS.KONTONUMMER_PARAM);

        BetterTextField kontostandFix = new BetterTextField("Kontostand: ", JLabel.LEFT);
        int LabelHeightKontostandFix = kontostandFix.getPreferredSize().height;
        kontostandFix.setBounds(30, 105, 150, LabelHeightKontostandFix);
        mainPage.registerComponent(kontostandFix, COMPONENTS.KONTOSTAND_FIX);

        BetterTextField kontostandParam = new BetterTextField("1234567,89€", JLabel.LEFT);
        int LabelHeightKontostandParam = kontostandParam.getPreferredSize().height;
        kontostandParam.setBounds(190, 105, 300, LabelHeightKontonummerParam);
        mainPage.registerComponent(kontostandParam, COMPONENTS.KONTOSTAND_PARAM);


        BetterButton freeMoneyButton = new BetterButton();
        freeMoneyButton.setText("Einzahlen");
        freeMoneyButton.setBounds(25,160,490,30);
        mainPage.registerComponent(freeMoneyButton, COMPONENTS.EINZAHLEN_BUTTON);

        BetterButton deleteMoneyButton = new BetterButton();
        deleteMoneyButton.setText("Abheben");
        deleteMoneyButton.setBounds(25,200,490,30);
        mainPage.registerComponent(deleteMoneyButton, COMPONENTS.ABHEBEN_BUTTON);

        BetterButton logoutButton = new BetterButton();
        logoutButton.setText("Abmelden");
        logoutButton.setBounds(25, 260, 490, 30);
        mainPage.registerComponent(logoutButton, COMPONENTS.LOGOUT_BUTTON);

    }

    void createSettingsPage() {
        //register Page
        StackedPane registerPage = new StackedPane(layerManager, new Dimension(400,500));
        layerManager.registerPane(registerPage, PAGES.SETTINGS_PAGE);

        JLabel registerInfoLabel = new JLabel("Edit your account", JLabel.CENTER);
        int LabelHeight = registerInfoLabel.getPreferredSize().height;
        registerInfoLabel.setBounds(0, 40, 400, LabelHeight);
        registerPage.registerComponent(registerInfoLabel, COMPONENTS.PRIVATE);


        //Account delete choice
        BetterComboBox accountDeleteChoice = new BetterComboBox(new String[] {"K1","K2","K3"});
        accountDeleteChoice.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        registerPage.registerComponent(accountDeleteChoice, COMPONENTS.ACCOUNT_DELETE_CHOICE);
        //Account delete button
        BetterButton accountDelete = new BetterButton();
        accountDelete.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 170, 250, 30);
        accountDelete.setText("DELETE?");
        registerPage.registerComponent(accountDelete,COMPONENTS.ACCOUNT_DELETE_BUTTON);
        //Account create
        BetterInputField accountCreateField = new BetterInputField();
        accountCreateField.setEchoChar((char) 0);
        accountCreateField.putClientProperty("JTextField.placeholderText","Account name");
        accountCreateField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 210, 250, 30);
        registerPage.registerComponent(accountCreateField,COMPONENTS.ACCOUNT_CREATE_INPUT);
        //Create Button
        BetterButton accountCreateButton = new BetterButton();
        accountCreateButton.setText("Create new Account");
        accountCreateButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 250, 250, 30);
        registerPage.registerComponent(accountCreateButton,COMPONENTS.ACCOUNT_CREATE_BUTTON);
        //Confirm Pin Text Field
        BetterInputField pinConfirmRegisterTextField = new BetterInputField();
        pinConfirmRegisterTextField.setEchoChar('*');
        pinConfirmRegisterTextField.putClientProperty("JTextField.placeholderText", "Neue PIN");
        pinConfirmRegisterTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 290, 250, 30);
        registerPage.registerComponent(pinConfirmRegisterTextField, COMPONENTS.NEW_PIN_INPUT);

        //Register Button
        BetterButton registerButton = new BetterButton();
        registerButton.setText("Save Changes");
        registerButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 330, 250, 30);
        registerPage.registerComponent(registerButton, COMPONENTS.SAVE_BUTTON);

        //Back to Login-Window Button
        BetterButton loginButton = new BetterButton();
        loginButton.setText("Back to main Page");
        loginButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 390, 250, 30);
        loginButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                layerManager.showPlane(PAGES.MAIN_PAGE);
            }
        });
        registerPage.registerComponent(loginButton, COMPONENTS.PRIVATE);
    }

    public ArrayList<String> einzahlenPopUp(StackedPane page){
        CustomPopup cb = new CustomPopup();
        JLabel infoLabel = new JLabel("Betrag (€) zum Einzahlen eingeben");
        cb.addWidget(infoLabel, cb.createConstraints(0,0,GridBagConstraints.VERTICAL, 2));
        BetterInputField betragEntry = new BetterInputField();
        betragEntry.setEchoChar((char) 0);
        betragEntry.putClientProperty("JTextField.placeholderText", "00,00");
        betragEntry.setHorizontalAlignment(JTextField.LEFT);
        betragEntry.addKeyListener(new MoneyKeyListener(betragEntry));
        betragEntry.setPreferredSize(new Dimension(300, 35)); // Adjust the height here
        cb.addWidget(betragEntry, cb.createConstraints(0,1,GridBagConstraints.HORIZONTAL, 1));
        return cb.displayPopup(page, "Einzahlen");
    }

    public ArrayList<String> abhebenPopUp(StackedPane page){
        CustomPopup cb = new CustomPopup();
        JLabel infoLabel = new JLabel("Betrag (€) zum Abheben eingeben");
        cb.addWidget(infoLabel, cb.createConstraints(0,0,GridBagConstraints.VERTICAL, 2));
        BetterInputField abhebenEntry = new BetterInputField();
        abhebenEntry.setEchoChar((char) 0);
        abhebenEntry.putClientProperty("JTextField.placeholderText", "00,00");
        abhebenEntry.setHorizontalAlignment(JTextField.LEFT);
        abhebenEntry.addKeyListener(new MoneyKeyListener(abhebenEntry));
        abhebenEntry.setPreferredSize(new Dimension(300, 35)); // Adjust the height here
        cb.addWidget(abhebenEntry, cb.createConstraints(0,1,GridBagConstraints.HORIZONTAL, 1));
        return cb.displayPopup(page, "Abheben");
    }


    void createAdminMainPage() {
        StackedPane adminControls = new StackedPane(layerManager, new Dimension(300,260));
        layerManager.registerPane(adminControls, PAGES.ADMIN_MAIN_PAGE);

        JLabel infoLabel = new JLabel("<html><center>Access user accounts:</center></html>", JLabel.CENTER);
        int LabelHeight = infoLabel.getPreferredSize().height;
        infoLabel.setBounds(0, 60, 300, LabelHeight);
        adminControls.registerComponent(infoLabel, COMPONENTS.PRIVATE);

        BetterInputField accountName = new BetterInputField();
        accountName.setEchoChar((char) 0);
        accountName.putClientProperty("JTextField.placeholderText","Account Email");
        accountName.setBounds(adminControls.getWidth() / 2 - 250 / 2 - 8, 90, 250, 30);
        adminControls.registerComponent(accountName, COMPONENTS.USER_NAME);

        BetterButton accountLogin = new BetterButton();
        accountLogin.setText("als Kunde anmelden");
        accountLogin.setBounds(adminControls.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        adminControls.registerComponent(accountLogin, COMPONENTS.LOGIN_BUTTON);

        BetterButton logout = new BetterButton();
        logout.setText("Abmelden");
        logout.setBounds(adminControls.getWidth()/2 - 250/2 - 8, 180, 250, 30);
        adminControls.registerComponent(logout, COMPONENTS.LOGOUT_BUTTON);

        BetterButton settingsButton = new BetterButton();
        settingsButton.setBounds(adminControls.getWidth()-75, 10, 45, 45);
        settingsButton.setIcon(new ImageIcon("src/main/resources/icons/settings.png"));
        settingsButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                layerManager.showPlane(PAGES.ADMIN_MAIN_PAGE);
            }
        });


        adminControls.registerComponent(settingsButton, COMPONENTS.PRIVATE);
    }

    void createAdminSettingsPage() {
        StackedPane registerPage = new StackedPane(layerManager, new Dimension(400,500));
        layerManager.registerPane(registerPage, PAGES.ADMIN_REGISTER_PAGE);

        JLabel registerInfoLabel = new JLabel("Register new admin", JLabel.CENTER);
        int LabelHeight = registerInfoLabel.getPreferredSize().height;
        registerInfoLabel.setBounds(0, 40, 400, LabelHeight);
        registerPage.registerComponent(registerInfoLabel, COMPONENTS.PRIVATE);


        //Vorname Text Field
        BetterInputField vornameTextField = new BetterInputField();
        vornameTextField.setEchoChar((char) 0);
        vornameTextField.putClientProperty("JTextField.placeholderText", "Vorname");
        vornameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 130, 250, 30);
        registerPage.registerComponent(vornameTextField, COMPONENTS.VORNAME_INPUT);

        //Nachname Text Field
        BetterInputField nachnameTextField = new BetterInputField();
        nachnameTextField.setEchoChar((char) 0);
        nachnameTextField.putClientProperty("JTextField.placeholderText", "Nachname");
        nachnameTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 170, 250, 30);
        registerPage.registerComponent(nachnameTextField, COMPONENTS.NACHNAME_INPUT);

        //Email Text Field
        BetterInputField emailTextField = new BetterInputField();
        emailTextField.setEchoChar((char) 0);
        emailTextField.putClientProperty("JTextField.placeholderText", "Email");
        emailTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 210, 250, 30);
        registerPage.registerComponent(emailTextField, COMPONENTS.EMAIL_INPUT);

        //Pin Text Field
        BetterInputField pinTextField = new BetterInputField();
        pinTextField.setEchoChar((char) 0);
        pinTextField.putClientProperty("JTextField.placeholderText", "PIN");
        pinTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 250, 250, 30);
        registerPage.registerComponent(pinTextField, COMPONENTS.PIN_INPUT);

        //Confirm Pin Text Field
        BetterInputField pinConfirmRegisterTextField = new BetterInputField();
        pinConfirmRegisterTextField.setEchoChar((char) 0);
        pinConfirmRegisterTextField.putClientProperty("JTextField.placeholderText", "Confirm PIN");
        pinConfirmRegisterTextField.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 290, 250, 30);
        registerPage.registerComponent(pinConfirmRegisterTextField, COMPONENTS.CONFIRM_PIN_INPUT);

        //Register Button
        BetterButton registerButton = new BetterButton();
        registerButton.setText("Register");
        registerButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 330, 250, 30);
        registerPage.registerComponent(registerButton, COMPONENTS.REGISTER_BUTTON);

        //Back to Login-Window Button
        BetterButton loginButton = new BetterButton();
        loginButton.setText("Back to main Page");
        loginButton.setBounds(registerPage.getWidth() / 2 - 250 / 2 - 8, 390, 250, 30);
        loginButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                layerManager.showPlane(PAGES.MAIN_PAGE);
            }
        });
        registerPage.registerComponent(loginButton, COMPONENTS.PRIVATE);
    }
    
    public static void main(String[] args) {
        MainWindow mw = WindowManager.getWindow();
        ((BetterButton) (mw.getWindow().getElement(PAGES.LOGIN_PAGE, COMPONENTS.LOGIN_BUTTON))).addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                mw.getWindow().showPlane(PAGES.REGISTER_PAGE);
            }
        });
        ((BetterButton) (mw.getWindow().getElement(PAGES.REGISTER_PAGE, COMPONENTS.REGISTER_BUTTON))).addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                mw.getWindow().showPlane(PAGES.MAIN_PAGE);
            }
        });
        ((BetterButton) (mw.getWindow().getElement(PAGES.MAIN_PAGE, COMPONENTS.SETTINGS_BUTTON))).addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                mw.getWindow().showPlane(PAGES.SETTINGS_PAGE);
            }
        });
        ((BetterButton) (mw.getWindow().getElement(PAGES.MAIN_PAGE, COMPONENTS.EINZAHLEN_BUTTON))).addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                mw.einzahlenPopUp(mw.getWindow().getFrame(PAGES.MAIN_PAGE));
                System.out.println("eingabe: " + mw.einzahlenPopUp(mw.getWindow().getFrame(PAGES.MAIN_PAGE)));
            }
        });
        ((BetterButton) (mw.getWindow().getElement(PAGES.MAIN_PAGE, COMPONENTS.ABHEBEN_BUTTON))).addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                mw.abhebenPopUp(mw.getWindow().getFrame(PAGES.MAIN_PAGE));
            }
        });
        ((BetterButton) (mw.getWindow().getElement(PAGES.SETTINGS_PAGE, COMPONENTS.ACCOUNT_DELETE_BUTTON))).addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                mw.getWindow().showPlane(PAGES.ADMIN_MAIN_PAGE);
            }
        });
    }

    public static String formatLabelString(String original) {
        return "<html><center>" + original + "</center></html>";
    }
}
