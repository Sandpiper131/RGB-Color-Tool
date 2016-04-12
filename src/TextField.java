import javax.swing.*;

/**
 * Created by Sandpiper on 4/8/2016.
 */
public class TextField extends JTextField {
    public TextField(int columns, String text) {
        setColumns(columns);
        setText(text);
    }
}
