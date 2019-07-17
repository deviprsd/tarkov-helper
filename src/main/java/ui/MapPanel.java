package ui;

import beans.Map;
import com.opencsv.bean.CsvToBeanBuilder;
import util.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.awt.Image;

public class MapPanel extends JPanel implements ActionListener {
    private HashMap<String, Map> chartData;
    private String[] mapTypes;

    private JButton btnSelect;
    private JComboBox<String> mapComboBox;
    private JLabel mapLabel;
    private JScrollPane mapScrollPane;

    public MapPanel() {
        super();

        chartData = new HashMap<>();
        readMapCSV();

        Set<String> dynamicAmmoTypes = chartData.keySet();
        mapTypes = chartData.keySet().toArray(new String[dynamicAmmoTypes.size()]);

        this.setupUI();
    }

    private void readMapCSV() {
        try {
            List<Map> maps = new CsvToBeanBuilder<Map>(new FileReader(new File(
                    Objects.requireNonNull(getClass().getClassLoader().getResource("tarkov_map_chart.csv")).getFile())))
                            .withType(Map.class).build().parse();

            for (Map map : maps) {
                chartData.put(map.getName(), map);
            }
        } catch (Exception e) {
            Logger.err(e);
            JOptionPane.showMessageDialog(null, "Error reading map files");
            System.exit(-1);
        }
    }

    private void setupUI() {
        JLabel lblMapSelector = new JLabel("Map Selector");
        lblMapSelector.setBounds(20, 14, 87, 14);

        mapComboBox = new JComboBox<>(mapTypes);
        mapComboBox.setBounds(129, 11, 87, 20);

        btnSelect = new JButton("Select");
        btnSelect.setBounds(226, 10, 90, 23);
        btnSelect.addActionListener(this);

        ImageIcon currentMap = new ImageIcon();
        mapLabel = new JLabel(currentMap);
        //mapLabel.setSize(200, 200);
        //mapLabel.setLocation(116, 65);
        mapScrollPane = new JScrollPane(mapLabel);

        this.setLayout(null);
        this.add(lblMapSelector);
        this.add(mapComboBox);
        this.add(btnSelect);
        this.add(mapScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSelect) {
            displaySelectedMap();
        }
    }

    private void displaySelectedMap() {
        ImageIcon currentMap = new ImageIcon(chartData.get(mapComboBox.getSelectedItem()).getFile());
        mapLabel.setIcon(currentMap);
        System.out.println(mapLabel.getIcon().toString());
        
        mapLabel.setSize(currentMap.getIconWidth(), currentMap.getIconHeight());
        mapScrollPane.setSize(currentMap.getIconWidth(), currentMap.getIconHeight());
        mapScrollPane.setLocation((this.getWidth() - currentMap.getIconWidth()) / 2, (this.getHeight() - currentMap.getIconHeight()) / 2);
        mapLabel.setHorizontalAlignment(JLabel.CENTER);
        mapLabel.setVerticalAlignment(JLabel.CENTER);
        
        // mapLabel.setIcon(new
        // ImageIcon(chartData.get(mapComboBox.getSelectedItem()).getFile()));
        System.out.println(chartData.get(mapComboBox.getSelectedItem()).getFile());
       
        this.repaint();
    }
}
