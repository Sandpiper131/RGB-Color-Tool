import javax.swing.*;

/**
 * Created by Sandpiper on 4/8/2016.
 */
public class Slider extends JSlider {

    public Slider(int min, int max, int value) {
        setMinimum(min);
        setMaximum(max);
        setValue(value);
    }
}
