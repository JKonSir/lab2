package gui;

import functions.DocumentTabulatedFunction;
import functions.FunctionPoint;
import functions.TableForTabulatedFunction;
import functions.TabulateFunctionClassLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by gijoe on 6/25/2015.
 */
public class PrimaryWindowProgram extends JFrame /*implements ActionListener*/ {
    private JTable tableForShowFunction;
    private JPanel TabulatedFunction;
    private JTextField newPointX;
    private JTextField newPointY;
    private JButton addPointButton;
    private JButton deletePointButton;
    private JScrollPane scrollPaneForTable;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuTabulate;
    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private JMenuItem saveFileAs;
    private JMenuItem exit;
    private JMenuItem loadFunction;
    private JMenuItem tabulateFunction;

    private FunctionParameters functionParameters;
    private DocumentTabulatedFunction documentTabulatedFunction;
    private JFileChooser jFileChooser;
    private TabulateFunctionClassLoader tabulateFunctionClassLoader;

    public PrimaryWindowProgram() {

        this.functionParameters = new FunctionParameters();
        this.documentTabulatedFunction = new DocumentTabulatedFunction();
        this.documentTabulatedFunction.newFunction(-10, 10, 20);
        this.jFileChooser = new JFileChooser();
        this.tabulateFunctionClassLoader = new TabulateFunctionClassLoader();

        tableForShowFunction.setModel(new TableForTabulatedFunction(documentTabulatedFunction, this.TabulatedFunction));

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuTabulate = new JMenu("Tabulate");
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        saveFileAs = new JMenuItem("SaveAs");
        exit = new JMenuItem("Exit");
        loadFunction = new JMenuItem("Load");
        tabulateFunction = new JMenuItem("Tabulate");

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functionParameters.showDialog();
                if (functionParameters.getCloseValue() == functionParameters.getOK()) {
                    documentTabulatedFunction.newFunction(functionParameters.getLeftDomainBorder(), functionParameters.getRightDomainBorder(), functionParameters.getPointsCount());
                    tableForShowFunction.revalidate();
                    tableForShowFunction.repaint();
                }
            }
        });
        menuFile.add(newFile);

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = jFileChooser.showOpenDialog(TabulatedFunction);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    try {
                        documentTabulatedFunction.loadFunction(jFileChooser.getSelectedFile().getAbsolutePath());
                        tableForShowFunction.revalidate();
                        tableForShowFunction.repaint();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(TabulatedFunction, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        menuFile.add(openFile);

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (documentTabulatedFunction.isFileNameAssigned() == true) {
                        documentTabulatedFunction.saveFunction();
                    } else {
                        int ret = jFileChooser.showSaveDialog(TabulatedFunction);
                        if (ret == JFileChooser.APPROVE_OPTION) {
                            try {
                                documentTabulatedFunction.saveFunctionAs(jFileChooser.getSelectedFile().getAbsolutePath());
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(TabulatedFunction, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(TabulatedFunction, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        menuFile.add(saveFile);

        saveFileAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = jFileChooser.showSaveDialog(TabulatedFunction);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    try {
                        documentTabulatedFunction.saveFunctionAs(jFileChooser.getSelectedFile().getAbsolutePath());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(TabulatedFunction, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        menuFile.add(saveFileAs);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (documentTabulatedFunction.isModified() == true) {
                    int ret = JOptionPane.showConfirmDialog(TabulatedFunction, "Are you sure you want to exit?");
                    if (ret == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                } else {
                    dispose();
                }
            }
        });
        menuFile.add(exit);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (documentTabulatedFunction.isModified() == true) {
                    int ret = JOptionPane.showConfirmDialog(TabulatedFunction, "Are you sure you want to exit?");
                    if (ret == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                } else {
                    dispose();
                }
            }
        });

        loadFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = jFileChooser.showOpenDialog(TabulatedFunction);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    try {
                        documentTabulatedFunction.setTabulatedFunction(tabulateFunctionClassLoader.classLoader(jFileChooser.getSelectedFile().getAbsolutePath()));
                        tableForShowFunction.revalidate();
                        tableForShowFunction.repaint();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(TabulatedFunction, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        menuTabulate.add(loadFunction);

        tabulateFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = jFileChooser.showOpenDialog(TabulatedFunction);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    try {
                        functionParameters.showDialog();
                        double leftX = functionParameters.getLeftDomainBorder();
                        double rightX = functionParameters.getRightDomainBorder();
                        int pointsCount = functionParameters.getPointsCount();
                        documentTabulatedFunction.tabulatedFunction(tabulateFunctionClassLoader.classLoader(jFileChooser.getSelectedFile().getAbsolutePath()), leftX, rightX, pointsCount);
                        tableForShowFunction.revalidate();
                        tableForShowFunction.repaint();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(TabulatedFunction, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        menuTabulate.add(tabulateFunction);

        menuBar.add(menuFile);
        menuBar.add(menuTabulate);
        setJMenuBar(menuBar);

        addPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = Double.parseDouble(newPointX.getText());
                    double y = Double.parseDouble(newPointY.getText());
                    int tmp = documentTabulatedFunction.getPointsCount();
                    documentTabulatedFunction.addPoint(new FunctionPoint(x, y));
                    if (documentTabulatedFunction.getPointsCount() == tmp) {
                        JOptionPane.showMessageDialog(TabulatedFunction, "Incorrect data");
                    } else {
                        tableForShowFunction.revalidate();
                        tableForShowFunction.repaint();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TabulatedFunction, ex);
                }
            }
        });

        deletePointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tmp = documentTabulatedFunction.getPointsCount();
                documentTabulatedFunction.deletePoint(tableForShowFunction.getSelectedRow());
                if (tmp == documentTabulatedFunction.getPointsCount()) {
                    JOptionPane.showMessageDialog(TabulatedFunction, "Points count = 2");
                } else {
                    tableForShowFunction.revalidate();
                    tableForShowFunction.repaint();
                }
            }
        });

        setResizable(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(this.TabulatedFunction);
        pack();
        setVisible(true);
    }

//    public void actionPerformed(ActionEvent e){
//        tableForShowFunction.setValue(tableForShowFunction.getValueAt(tableForShowFunction.));
//    }
}
