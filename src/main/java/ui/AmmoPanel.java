/**
 * Copyright 2019, Titans Inc.
 *
 * @author Maxx Persin (maxxpersin@gmail.com) | Devi Prasad (deviprsd21@gmail.com)
 * @version v0.0.1-alpha07151750
 *
 * TODO: DESCRIPTION and COMMENTS
 */

package ui;

import beans.Ammo;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.collections4.MultiValuedMap;
import util.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class AmmoPanel extends JPanel implements ActionListener {
    private final Object[] columnNames = {
            "Caliber", "Name", "Damage", "Penetration Value", "Armour Damage %",
            "Fragmentation Chance", "Vs Class 1", "Vs Class 2", "Vs Class 3", "Vs Class 4", "Vs Class 5",
            "Vs Class 6"
    };

    private String[] ammoTypes;
    private Object[][] rowData;
    private JTable table;
    private HashMap<String, LinkedList<Ammo>> chartData;

    private JButton btnSearch, btnCompare;
    private JComboBox<String> ammoComboBox;

    public AmmoPanel() {
        super();

        chartData = new HashMap<>();
        readAmmoCSV();

        Set<String> dynamicAmmoTypes = chartData.keySet();
        ammoTypes = chartData.keySet().toArray(new String[dynamicAmmoTypes.size()]);

        rowData = new Object[14][columnNames.length];
        table = new JTable(rowData, columnNames);

        setupUI();
    }

    private void readAmmoCSV() {
        try {
            List<Ammo> ammos = new CsvToBeanBuilder<Ammo>(new FileReader(new File(
                    Objects.requireNonNull(getClass().getClassLoader().getResource("tarkov_ammo_chart.csv")).getFile()
            ))).withType(Ammo.class).build().parse();

            for (Ammo ammo : ammos) {
                if(!chartData.containsKey(ammo.getCaliber())) {
                    chartData.put(ammo.getCaliber(), new LinkedList<>());
                }
                chartData.get(ammo.getCaliber()).add(ammo);
            }
        } catch (Exception e) {
            Logger.err(e);
            JOptionPane.showMessageDialog(null, "Error reading ammo chart file");
            System.exit(-1);
        }
    }

    private void setupUI() {
        JLabel lblCaliber = new JLabel("Caliber");
        lblCaliber.setBounds(7, 12, 72, 14);

        ammoComboBox = new JComboBox<>(ammoTypes);
        ammoComboBox.setBounds(89, 9, 87, 20);

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
        this.add(ammoComboBox);
        this.add(scrollPane);
        this.add(btnSearch);
        this.add(btnCompare);
    }

    private void updateTable(Object[][] r, Object item) {
        LinkedList<Ammo> ammos = chartData.get(item.toString());
        MultiValuedMap<String, Integer> effectiveness;

        for(int idx = 0; idx < ammos.size(); idx++) {
            Ammo a = ammos.get(idx);
            r[idx][0] = a.getCaliber();
            r[idx][1] = a.getName();
            r[idx][2] = a.getDamage();
            r[idx][3] = a.getPenetration();
            r[idx][4] = (int)a.getArmourDamage() + "%";
            r[idx][5] = a.getFragmentationChance();

            effectiveness = a.getEffectiveness();
            r[idx][6] = effectiveness.get("Class 1").toArray()[0];
            r[idx][7] = effectiveness.get("Class 2").toArray()[0];
            r[idx][8] = effectiveness.get("Class 3").toArray()[0];
            r[idx][9] = effectiveness.get("Class 4").toArray()[0];
            r[idx][10] = effectiveness.get("Class 5").toArray()[0];
            r[idx][11] = effectiveness.get("Class 6").toArray()[0];
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
        updateTable(rowData, Objects.requireNonNull(ammoComboBox.getSelectedItem()));
        table.updateUI();
    }

    private void onCompareClick() {

    }
}
