package vehicles;

import javax.swing.*;


public class AddRemoveCarsView extends JPanel {
    public AddRemoveCarsView(CarModel model) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JButton addButton = new JButton("Add car");
        addButton.addActionListener(e -> {
            model.addCar();
        });
        add(addButton);

        JButton removeButton = new JButton("Remove car");
        removeButton.addActionListener(e -> {
            model.removeCar();
        });
        add(removeButton);
    }

}
