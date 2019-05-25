package com.dww.insurance.frame;

import com.dww.insurance.domain.DamageInfo;
import com.dww.insurance.domain.DamageReport;
import com.dww.insurance.domain.SearchResult;
import com.dww.insurance.domain.QueryParam;
import com.dww.insurance.model.SearchResultTableModel;
import com.dww.insurance.service.SearchRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchFrame extends JPanel {

    private static final int BASE_LINE = 180;
    private static final int BASE_WIDTH = 240;
    private static final int BASE_HEIGHT = 20;

    private JTextField surnameTextField;
    private JTextField ownerTextField;
    private JTextField bodyTextField;
    private SearchRepository searchRepository;
    private DefaultListModel<SearchResult> listModel = new DefaultListModel<>();
    private IApplication app;

    private JLabel driverSurname;
    private JLabel driverName;
    private JLabel driverPassId;
    private JLabel driverAddress;
    private JLabel driverPhone;
    private JLabel vehicleModel;
    private JLabel vehicleType;
    private JLabel vehicleBodyId;

    private JLabel wIcon;


    private SearchResultTableModel tableModel;
    private List<SearchResult> tableData;
    JTable table;


    public SearchFrame(IApplication app) {
        this.app = app;
        searchRepository = new SearchRepository();
        initialize();
    }

    public void initialize() {
        removeAll();
        setLayout(null);

        initSearchResult();

        initSearchTab();

        // TODO fix position
        initDriverInfoTab();
        // TODO fix position
        initVehicleTab();

        JLabel lblDamage = new JLabel("Damage Info:");
        lblDamage.setBounds(260, BASE_LINE, 100, BASE_HEIGHT);
        add(lblDamage);

        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(250, BASE_LINE + 20, 530, 2);
        add(separator_3);

        BufferedImage wPic = null;
        try {
            wPic = ImageIO.read(getClass().getClassLoader().getResource("AutoShema.jpg"));
//            wPic = ImageIO.read(new File());

            ImageIcon imageIcon = new ImageIcon(wPic); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(500, 350, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);

            wIcon = new JLabel(imageIcon);
            wIcon.setLayout(null);
            wIcon.setBounds(270, BASE_LINE + 30, 500, 350);

            // TODO fix position
            add(wIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Label label1 = new Label() {
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.red);
                g.drawOval(400, 400, 100, 100);
            }
        };

        // TODO fix position
        add(label1);
        populateDamageReport(null);



        setVisible(true);
    }

    private void initSearchResult() {
        JSeparator searchResultSeparator = new JSeparator();
        searchResultSeparator.setBounds(5, 50, 785, 2);
        add(searchResultSeparator);

        tableData = new ArrayList<>();
        tableModel = new SearchResultTableModel(tableData);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int personId = (int) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);
                searchDriverInfo(personId);
            }
        });
        JScrollPane scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setBounds(20, 60, 750, 116);
        add(scrollPaneTable);
    }

    private void initDriverInfoTab() {
        JLabel driverInfoLabel = new JLabel("Driver Info:");
        driverInfoLabel.setBounds(20, BASE_LINE, 240, BASE_HEIGHT);
        add(driverInfoLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(18, BASE_LINE + 20, 240, 2);
        add(separator);

        driverSurname = new JLabel();
        driverSurname.setBounds(20, BASE_LINE + 25, BASE_WIDTH, BASE_HEIGHT);
        add(driverSurname);

        driverName = new JLabel();
        driverName.setBounds(20, BASE_LINE + 45, BASE_WIDTH, BASE_HEIGHT);
        add(driverName);

        driverAddress = new JLabel();
        driverAddress.setBounds(20, BASE_LINE + 65, BASE_WIDTH, BASE_HEIGHT);
        add(driverAddress);

        driverPhone = new JLabel();
        driverPhone.setBounds(20, BASE_LINE + 85, BASE_WIDTH, BASE_HEIGHT);
        add(driverPhone);

        driverPassId = new JLabel();
        driverPassId.setBounds(20, BASE_LINE + 105, BASE_WIDTH, BASE_HEIGHT * 2);
        add(driverPassId);
    }

    private void initVehicleTab() {
        JLabel lblVehicleInfo = new JLabel("Vehicle Info:");
        lblVehicleInfo.setBounds(20, 340, 200, 20);
        add(lblVehicleInfo);

        vehicleModel = new JLabel();
        vehicleModel.setBounds(20, 360, 200, 20);
        add(vehicleModel);

        vehicleType = new JLabel();
        vehicleType.setBounds(20, 380, 200, 20);
        add(vehicleType);

        vehicleBodyId = new JLabel();
        vehicleBodyId.setBounds(20, 400, 200, 40);
        add(vehicleBodyId);
    }

    private void initSearchTab() {
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBounds(20, 5, 750, 40);

        searchPanel.add(new JLabel("Surname"));

        surnameTextField = new JTextField(10);
        searchPanel.add(surnameTextField);

        searchPanel.add(new JLabel("Owner ID"));

        ownerTextField = new JTextField(10);
        searchPanel.add(ownerTextField);

        searchPanel.add(new JLabel("Body ID"));

        bodyTextField = new JTextField(10);
        searchPanel.add(bodyTextField);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(event -> search());
        searchPanel.add(btnSearch);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> {
            SearchFrame.this.updateUI();
            app.edit();
        });
        searchPanel.add(btnAdd);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            surnameTextField.setText("");
            ownerTextField.setText("");
            bodyTextField.setText("");
        });
        searchPanel.add(clearButton);

        add(searchPanel);
    }

    private void search() {
        listModel.clear();
        if (surnameTextField.getText().isEmpty() && ownerTextField.getText().isEmpty() && bodyTextField.getText().isEmpty()) {
            List<SearchResult> searchResults = searchRepository.searchAll();
            searchResults.forEach(person -> listModel.addElement(person));
            table.setModel(new SearchResultTableModel(searchResults));
        } else {
            List<SearchResult> searchResults = searchRepository
                .find(new QueryParam(surnameTextField.getText(), ownerTextField.getText(), bodyTextField.getText()));
            searchResults.forEach(person -> listModel.addElement(person));
            table.setModel(new SearchResultTableModel(searchResults));

        }
    }

    private void searchDriverInfo(int personId) {
        DamageReport damageReport = searchRepository.searchDriverInfo(personId);
        populateDamageReport(damageReport);
    }

    private void populateDamageReport(DamageReport report) {
        driverPassId.setText("<html>Pass ID : " + (report == null ? "" : "<br>" + report.getDriverInfo().getPassId())+"</html>");
        driverSurname.setText("Last Name : " + (report == null ? "" : "\n" + report.getDriverInfo().getLastName()));
        driverName.setText("Name : " + (report == null ? "" : report.getDriverInfo().getName()));
        driverAddress.setText("Address : " + (report == null ? "" : "\n" + report.getDriverInfo().getAddress()));
        driverPhone.setText("Phone : " + (report == null ? "" : "\n" + report.getDriverInfo().getPhone()));

        vehicleModel.setText("Model : " + (report == null ? "" : "\n" + report.getVehicleInfo().getModel()));
        vehicleType.setText("Type : " + (report == null ? "" : "\n" + report.getVehicleInfo().getType()));
        vehicleBodyId.setText("<html>Body ID : " + (report == null ? "" : "<br>" + report.getVehicleInfo().getBodyId())+"</html>");
        damageZone(report == null ? null : report.getDamageInfo());
    }

    private void damageZone(DamageInfo damageInfo) {
        if (damageInfo != null) wIcon.removeAll();

        int topPosition = 42;
        wIcon.add(zoneLabel(155, topPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[9])); // zone 10
        wIcon.add(zoneLabel(216, topPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[8])); // zone 9
        wIcon.add(zoneLabel(275, topPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[7])); // zone 8
        wIcon.add(zoneLabel(343, topPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[6])); // zone 7

        int midPosition = 164;
        wIcon.add(zoneLabel(17, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[12])); // zone 13
        wIcon.add(zoneLabel(128, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[11])); // zone 12
        wIcon.add(zoneLabel(264, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[10])); // zone 11
        wIcon.add(zoneLabel(357, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[5])); // zone 6
        wIcon.add(zoneLabel(437, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[4])); // zone 5

        int botPosition = 288 ;
        wIcon.add(zoneLabel(150, botPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[0])); // zone 1
        wIcon.add(zoneLabel(216, botPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[1])); // zone 2
        wIcon.add(zoneLabel(277, botPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[2])); // zone 3
        wIcon.add(zoneLabel(344, botPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[3])); // zone 4

        wIcon.repaint();
    }

    private JLabel zoneLabel(int x, int y, boolean enabled) {
        SelectionPoint selectionPoint13 = new SelectionPoint(enabled);
        JLabel zoneLabel = new JLabel(selectionPoint13);

        zoneLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectionPoint13.switchColor();
                zoneLabel.repaint();
            }
        });
        zoneLabel.setBounds(x, y, 25, 25);
        return zoneLabel;
    }

}
