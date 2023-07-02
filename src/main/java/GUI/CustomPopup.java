package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CustomPopup {
    JPanel mainPanel = new JPanel();
    CustomPopup(){
        mainPanel.setLayout(new GridBagLayout());
    }


    ArrayList<JComponent> widgets = new ArrayList<>();
    public void addWidget(JComponent widget, GridBagConstraints constraints){
        mainPanel.add(widget, constraints);
        widgets.add(widget);
    }

    public ArrayList displayPopup(JComponent parent, String title){
        ArrayList<String> result = new ArrayList<>();
        int options = JOptionPane.showConfirmDialog(parent,
                mainPanel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (options == JOptionPane.OK_OPTION){
            for (JComponent object : widgets){
                if (object instanceof JTextField){
                    result.add(((JTextField) object).getText());
                }

            }
        }
        return result;
    }
    public GridBagConstraints createConstraints(int gridx, int gridy, int fill, int gridwidth) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.fill = fill;
        constraints.gridwidth = gridwidth;
        constraints.insets = new Insets(5, 5, 5, 5);  // Optional: Set insets for spacing

        return constraints;
    }
}
