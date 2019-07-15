/**
 * Copyright 2019, Titans Inc.
 *
 * @author Maxx Persin (maxxpersin@gmail.com) | Devi Prasad (deviprsd21@gmail.com)
 * @version v0.0.1-alpha07151750
 *
 * TODO: DESCRIPTION and COMMENTS
 */

package ui;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import res.Ammo;

public class AmmoPanel extends JPanel implements ActionListener {
    private final Object[] columnNames = {
            "Caliber", "Name", "Damage", "Penetration Value", "Armour Damage %",
            "Fragmentation Chance", "Vs Class 1", "Vs Class 2", "Vs Class 3", "Vs Class 4", "Vs Class 5",
            "Vs Class 6"
    };

    private String[] ammoTypes = {
            "12x70", "20x70", ".366 TKM", "4.6x30", "5.45x39", "5.56x45", "7.62x25tt", "7.62x39",
            "7.62x51", "7.62x54R", "9x18pm", "9x19", "9x21", "9x39"
    };

    private Object[][] rowData;
    private JTable table;
    private LinkedList<LinkedList<Ammo>> chartData;

    private JButton btnSearch, btnCompare;
    private JComboBox<String> comboBox;

    public AmmoPanel(LinkedList<LinkedList<Ammo>> data) {
        super();

        chartData = data;
        rowData = new Object[14][columnNames.length];
        table = new JTable(rowData, columnNames);

        setupCustomUI();
    }

    private void setupCustomUI() {
        JLabel lblCaliber = new JLabel("Caliber");
        lblCaliber.setBounds(7, 12, 72, 14);

        comboBox = new JComboBox<>(ammoTypes);
        comboBox.setBounds(89, 9, 87, 20);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(7, 34, 1303, 431);
        scrollPane.setViewportView(table);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(186, 8, 90, 23);
        btnSearch.addActionListener(this);

        btnCompare = new JButton("Compare");
        btnCompare.setBounds(1208, 8, 102, 23);
        btnCompare.addActionListener(this);

        this.setLayout(null);
        this.add(lblCaliber);
        this.add(comboBox);
        this.add(scrollPane);
        this.add(btnSearch);
        this.add(btnCompare);
    }

    private void updateTable(Object[][] r, Object item) {
        for (int i = 0, idx = 0; i < chartData.size(); i++) {
            for (int j = 0; j < chartData.get(i).size(); j++) {
                if (chartData.get(i).get(j).getRoundType().equals(item.toString())) {
                    Ammo a = chartData.get(i).get(j);
                    r[idx][0] = a.getRoundType();
                    r[idx][1] = a.getName();
                    r[idx][2] = a.getDamage();
                    r[idx][3] = a.getPenetration();
                    r[idx][4] = a.getArmourDamage();
                    r[idx][5] = a.getFragmentationChance();
                    r[idx][6] = a.getEffectiveness()[0];
                    r[idx][7] = a.getEffectiveness()[1];
                    r[idx][8] = a.getEffectiveness()[2];
                    r[idx][9] = a.getEffectiveness()[3];
                    r[idx][10] = a.getEffectiveness()[4];
                    r[idx][11] = a.getEffectiveness()[5];
                    idx++;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSearch) {
            onSearchClick();
        }

        if(e.getSource() == btnCompare) {
            onCompareClick();
        }
    }

    private void onSearchClick() {
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < rowData[i].length; j++) {
                rowData[i][j] = null;
            }
        }
        updateTable(rowData, comboBox.getSelectedItem());
        table.updateUI();
    }

    private void onCompareClick() {

    }
}
