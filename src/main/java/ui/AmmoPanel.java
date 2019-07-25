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
import models.AmmoModel;
import org.apache.commons.collections4.MultiValuedMap;
import util.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import java.util.*;

public class AmmoPanel extends JPanel implements ActionListener {

    private String[] ammoTypes;

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

        table = new JTable(new AmmoModel());
        //table.setAutoCreateRowSorter(true);

        setupUI();
    }

    private void readAmmoCSV() {
        try {
            List<Ammo> ammos = new CsvToBeanBuilder<Ammo>(new InputStreamReader(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tarkov_ammo_chart.csv"))
            )).withType(Ammo.class).build().parse();

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
        ammoComboBox.setEditable(false);
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

    private void updateTable(Object item) {
        LinkedList<Ammo> ammos = chartData.get(item.toString());
        MultiValuedMap<String, Integer> effectiveness;
        AmmoModel ammoModel = (AmmoModel) table.getModel();

        for(int idx = 0; idx < ammoModel.getRowCount(); idx++) {
            if(idx < ammos.size()) {
                Ammo a = ammos.get(idx);
                ammoModel.setValueAt(a.getCaliber(), idx, 0);
                ammoModel.setValueAt(a.getName(), idx, 1);
                ammoModel.setValueAt(a.getDamage(), idx, 2);
                ammoModel.setValueAt(a.getPenetration(), idx, 3);
                ammoModel.setValueAt((int) a.getArmourDamage() + "%", idx, 4);
                ammoModel.setValueAt(a.getFragmentationChance(), idx, 5);

                effectiveness = a.getEffectiveness();
                for (int j = 6; j < 12; j++) {
                    ammoModel.setValueAt(effectiveness.get("Class " + (j - 5)).toArray()[0], idx, j);
                }
            } else {
                for (int j = 0; j < ammoModel.getColumnCount(); j++) {
                    ammoModel.setValueAt(null, idx, j);
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
        updateTable(Objects.requireNonNull(ammoComboBox.getSelectedItem()));
    }

    private void onCompareClick() {

    }
}
