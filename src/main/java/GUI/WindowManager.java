package GUI;


public class WindowManager {
    private  static MainWindow win;
    public static MainWindow getWindow() {
        if (win == null) {
            win = new MainWindow();
        }
        return win;
    }
}
