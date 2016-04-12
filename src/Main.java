import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sandpiper on 4/4/2016.
 */
public class Main extends JFrame {

    TextField fieldRed, fieldGreen, fieldBlue;
    static Color color;
    Slider sliderRed, sliderGreen, sliderBlue;

    JButton buttonHex;

    JLabel labelHex;

    int red, green, blue;

    final String TITLE = "Color Picker";

    public Main() {
        createUI();

        setTitle(TITLE);
        setResizable(false);
        setSize(new Dimension(400, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createUI() {
        JPanel panelMain = new JPanel();
        getContentPane().add(panelMain);

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelMain.add(panelForm);

        GridBagConstraints c = new GridBagConstraints();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        labelUI(panelForm, c);
        sliderUI(panelForm, c);
        fieldUI(panelForm, c);
        buttonUI(panelForm, c);
    }

    //Refreshes color integers and sets color to the integers
    private void refreshColors() {
        red = sliderRed.getValue();
        if (red > 255) red = 255;

        green = sliderGreen.getValue();
        if (green > 255) green = 255;

        blue = sliderBlue.getValue();
        if (blue > 255) blue = 255;

        color = new Color(red, green, blue);

        labelHex.setText(
                "#" + Integer.toHexString(color.getRGB()).substring(2)
        );
    }

    private void buttonUI(JPanel panelForm, GridBagConstraints c) {
        c.gridy = 5;
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;

        buttonHex = new JButton("Copy Hex Value");
        buttonHex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard cb = toolkit.getSystemClipboard();
                StringSelection hex = new StringSelection(labelHex.getText());
                cb.setContents(hex, null);
            }
        });
        panelForm.add(buttonHex, c);
    }

    private void labelUI(JPanel panelForm, GridBagConstraints c) {
        c.gridy = 0;
        c.gridx = 0;
        c.anchor = GridBagConstraints.LINE_END;

        panelForm.add(new JLabel("Red: "), c);
        c.gridy++;

        panelForm.add(new JLabel("Green: "), c);
        c.gridy++;

        panelForm.add(new JLabel("Blue: "), c);
        c.gridy++;

        panelForm.add(new JLabel("Hex: "), c);

        c.gridx++;
        c.anchor = GridBagConstraints.LINE_START;
        labelHex = new JLabel("");
        panelForm.add(labelHex, c);
    }

    private void sliderUI(JPanel panelForm, GridBagConstraints c) {
        c.gridy = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;

        sliderRed = new Slider(0, 255, 0);
        sliderRed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                refreshColors();
                fieldRed.setText(Integer.toString(red));
            }
        });
        panelForm.add(sliderRed, c);
        c.gridy++;

        sliderGreen = new Slider(0, 255, 0);
        sliderGreen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                refreshColors();
                fieldGreen.setText(Integer.toString(green));
            }
        });
        panelForm.add(sliderGreen, c);
        c.gridy++;

        sliderBlue = new Slider(0, 255, 0);
        sliderBlue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                refreshColors();
                fieldBlue.setText(Integer.toString(blue));
            }
        });
        panelForm.add(sliderBlue, c);
        c.gridy++;
    }

    private void fieldUI(JPanel panelForm, GridBagConstraints c) {
        c.gridy = 0;
        c.gridx = 3;

        fieldRed = new TextField(3, "0");
        fieldRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Integer.valueOf(fieldRed.getText()) > 255) {
                    fieldRed.setText("255");
                }
                sliderRed.setValue(Integer.valueOf(fieldRed.getText()));
            }
        });
        panelForm.add(fieldRed, c);
        c.gridy++;

        fieldGreen = new TextField(3, "0");
        fieldGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Integer.valueOf(fieldGreen.getText()) > 255) {
                    fieldGreen.setText("255");
                }
                sliderGreen.setValue(Integer.valueOf(fieldGreen.getText()));
            }
        });
        panelForm.add(fieldGreen, c);
        c.gridy++;

        fieldBlue = new TextField(3, "0");
        fieldBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Integer.valueOf(fieldBlue.getText()) > 255) {
                    fieldBlue.setText("255");
                }
                sliderBlue.setValue(Integer.valueOf(fieldBlue.getText()));
            }
        });
        panelForm.add(fieldBlue, c);
        c.gridy++;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
