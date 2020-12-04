import javax.swing.*;
import java.util.stream.Collectors;

public class SpeedometerView extends JTextArea implements UpdateListener {
    private final CarModel model;

    public SpeedometerView(CarModel model) {
        this.model = model;
        updateText();
        model.addListener(this);
    }

    private void updateText() {
        String s = model.getVehicles().stream()
                .map(vehicle -> String.format("%s: %.2f", vehicle.getModelName(), vehicle.getCurrentSpeed()))
                .collect(Collectors.joining("\n"));
        setText(s);
    }

    @Override
    public void onUpdate() {
        updateText();
        repaint();
    }
}
