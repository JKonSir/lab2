package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by gijoe on 6/23/2015.
 */
public class FunctionParameters extends JDialog {

    private JPanel FunctionParameters;
    private JTextField leftDomainBorderTField;
    private JTextField rightDomainBorderTField;
    private JButton cancelButton;
    private JButton okButton;
    private JSpinner pointsCountTField;
    private JLabel leftDomainBorderLabel;
    private JLabel rightDomainBorderLabel;
    private JLabel pointsCountLabel;

    static final int OK = 2581;
    static final int CANCEL = 3482;
    private int closeValue;

    public static int getOK() {
        return OK;
    }

    public static int getCANCEL() {
        return CANCEL;
    }

    public int getCloseValue() {

        return closeValue;
    }

    private double leftDomainBorder;
    private double rightDomainBorder;
    private int pointsCount;

    public FunctionParameters() {

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    leftDomainBorder = Double.parseDouble(leftDomainBorderTField.getText());
                    rightDomainBorder = Double.parseDouble(rightDomainBorderTField.getText());
                    pointsCount = (int)pointsCountTField.getValue();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Enter number");
                }
                if (leftDomainBorder >= rightDomainBorder) {
                    JOptionPane.showMessageDialog(null, "leftDomainBorder more than the rightDomainBorder");
                } else {
                    setVisible(false);
                    closeValue = OK;
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                closeValue = CANCEL;
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeValue = CANCEL;
                super.windowClosing(e);
            }
        });

        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setContentPane(FunctionParameters);
        pack();
        pointsCountTField.setModel(new SpinnerNumberModel(3, 2, Integer.MAX_VALUE, 1));
    }

    public int showDialog(){
        setVisible(true);

        return closeValue;
    }

    public JPanel getFunctionParameters() {
        return FunctionParameters;
    }

    public double getLeftDomainBorder() {
        return leftDomainBorder;
    }

    public double getRightDomainBorder() {
        return rightDomainBorder;
    }

    public int getPointsCount() {
        return pointsCount;
    }
}
