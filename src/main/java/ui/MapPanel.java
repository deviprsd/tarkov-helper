package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapPanel extends JPanel implements ActionListener {
    public MapPanel() {
        super();

        this.setupUI();
    }

    private void setupUI() {
        JLabel lblMapSelector = new JLabel("Map Selector");
        lblMapSelector.setBounds(20, 14, 87, 14);

        JComboBox mapComboBox = new JComboBox(new Object[]{});
        mapComboBox.setBounds(129, 11, 87, 20);

        JButton btnSelect = new JButton("Select");
        btnSelect.addActionListener(this);
        btnSelect.setBounds(226, 10, 90, 23);

        this.setLayout(null);
        this.add(lblMapSelector);
        this.add(mapComboBox);
        this.add(btnSelect);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
