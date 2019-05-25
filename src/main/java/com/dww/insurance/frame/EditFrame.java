package com.dww.insurance.frame;

import static java.lang.String.format;

import com.dww.insurance.domain.Damage;
import com.dww.insurance.domain.DamageInfo;
import com.dww.insurance.domain.DamageReport;
import com.dww.insurance.domain.DriverInfo;
import com.dww.insurance.domain.VehicleInfo;
import com.dww.insurance.domain.VehicleType;
import com.dww.insurance.service.DriverRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;


public class EditFrame extends JPanel {

    private static final int BASE_LINE = 180;
    private static final int BASE_HEIGHT = 20;

    private JTextField ownerIdTextField = new JTextField(22);
    private JTextField surnameTextField = new JTextField(22);
    private JTextField nameTextField = new JTextField(22);
    private JTextField lastNameTextField = new JTextField(22);
    private JTextField passIdTextField = new JTextField(22);
    private JTextField addressTextField = new JTextField(22);
    private JTextField phoneTextField = new JTextField(22);
    private JTextField vehicleModelTextField = new JTextField(22);
    private JTextField vehicleBodyIdTextField = new JTextField(22);

    private JLabel wIcon;

    private JLabel zone01 = new JLabel();
    private JLabel zone02 = new JLabel();
    private JLabel zone03 = new JLabel();
    private JLabel zone04 = new JLabel();
    private JLabel zone05 = new JLabel();
    private JLabel zone06 = new JLabel();
    private JLabel zone07 = new JLabel();
    private JLabel zone08 = new JLabel();
    private JLabel zone09 = new JLabel();
    private JLabel zone10 = new JLabel();
    private JLabel zone11 = new JLabel();
    private JLabel zone12 = new JLabel();
    private JLabel zone13 = new JLabel();

    private JComboBox<VehicleType> vehicleTypeComboBox;

    private DriverRepository driverRepository;
    private IApplication app;

    public EditFrame(IApplication app) {
        this.app = app;
        driverRepository = new DriverRepository();
        initialize(null);
    }

    public void initialize(DamageReport report) {
        removeAll();
        setLayout(null);

        initDriverInfoTab(report == null ? null : report.getDriverInfo());
        initVehicleInfoTab(report == null ? null : report.getVehicleInfo());
        initDamageInfoTab(report == null ? null : report.getDamageInfo());
        initBottomPanel();

        setVisible(true);
    }

    private void initDriverInfoTab(DriverInfo driverInfo) {
        JPanel driverInfoPanel = new JPanel();
        driverInfoPanel.setBounds(20, 5, 350, 150);
        driverInfoPanel.setLayout(new BoxLayout(driverInfoPanel, BoxLayout.Y_AXIS));

        JPanel title = new JPanel(new BorderLayout());
        title.add(new JLabel("Driver Info"), BorderLayout.WEST);

        driverInfoPanel.add(title);
        driverInfoPanel.add(new JSeparator());
        driverInfoPanel.add("Surname", createComponent("Surname", driverInfo == null ? "" : driverInfo.getLastName(), surnameTextField));
        driverInfoPanel.add("Name", createComponent("Name", driverInfo == null ? "" : driverInfo.getName(), nameTextField));
        driverInfoPanel.add(createComponent("Address", driverInfo == null ? "" : driverInfo.getAddress(), addressTextField));
        driverInfoPanel.add(createComponent("Phone", driverInfo == null ? "" : driverInfo.getPhone(), phoneTextField));
        driverInfoPanel.add(createComponent("Pass ID", driverInfo == null ? "" : driverInfo.getPassId(), passIdTextField));
        driverInfoPanel.setVisible(true);
        add(driverInfoPanel);
    }

    private void initVehicleInfoTab(VehicleInfo vehicleInfo) {
        JPanel panel = new JPanel();
        panel.setBounds(400, 8, 350, 95);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel title = new JPanel(new BorderLayout());
        title.add(new JLabel("Vehicle Info"), BorderLayout.WEST);

        JPanel type = new JPanel(new BorderLayout());
        vehicleTypeComboBox = new JComboBox<>(VehicleType.values());
        vehicleTypeComboBox.setSelectedItem(vehicleInfo == null ? null : VehicleType.valueOf(vehicleInfo.getType()));
        type.add(vehicleTypeComboBox);

        panel.add(title);
        panel.add(new JSeparator());
        panel.add(createComponent("Model", vehicleInfo == null ? "" : vehicleInfo.getModel(), vehicleModelTextField));
        panel.add(type);
        panel.add(createComponent("Body ID", vehicleInfo == null ? "" : vehicleInfo.getBodyId(), vehicleBodyIdTextField));

        panel.setVisible(true);
        add(panel);
    }

    private JPanel createComponent(String label, String value, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.WEST);
        textField.setText(value);
        panel.add(textField, BorderLayout.EAST);
        return panel;
    }

    private void initDamageInfoTab(DamageInfo damageInfo) {
        JLabel lblDamage = new JLabel("Damage Info:");
        lblDamage.setBounds(20, BASE_LINE, 100, BASE_HEIGHT);
        add(lblDamage);

        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(20, BASE_LINE + 20, 530, 2);
        add(separator_3);

        BufferedImage wPic;
        try {
            wPic = ImageIO.read(getClass().getClassLoader().getResource("AutoShema.jpg"));

            ImageIcon imageIcon = new ImageIcon(wPic); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(500, 350, Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);

            wIcon = new JLabel(imageIcon);
            wIcon.setLayout(null);
            wIcon.setBounds(20, BASE_LINE + 30, 500, 350);

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
        damageZone(damageInfo);
        add(label1);
    }

    private void damageZone(DamageInfo damageInfo) {
        if (damageInfo != null) wIcon.removeAll();

        int topPosition = 42;
        wIcon.add(zoneLabel(155, topPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[9], zone10)); // zone 10
        wIcon.add(zoneLabel(216, topPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[8], zone09)); // zone 9
        wIcon.add(zoneLabel(275, topPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[7], zone08)); // zone 8
        wIcon.add(zoneLabel(343, topPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[6], zone07)); // zone 7

        int midPosition = 164;
        wIcon.add(zoneLabel(17, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[12], zone13)); // zone 13
        wIcon.add(zoneLabel(128, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[11], zone12)); // zone 12
        wIcon.add(zoneLabel(264, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[10], zone11)); // zone 11
        wIcon.add(zoneLabel(357, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[5], zone06)); // zone 6
        wIcon.add(zoneLabel(437, midPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[4], zone05)); // zone 5

        int botPosition = 288 ;
        wIcon.add(zoneLabel(150, botPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[0], zone01)); // zone 1
        wIcon.add(zoneLabel(216, botPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[1], zone02)); // zone 2
        wIcon.add(zoneLabel(277, botPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[2], zone03)); // zone 3
        wIcon.add(zoneLabel(344, botPosition, damageInfo != null && damageInfo.getDamage().getDamageZone()[3], zone04)); // zone 4

        wIcon.repaint();
    }

    private JLabel zoneLabel(int x, int y, boolean enabled, JLabel label) {
        SelectionPoint selectionPoint = new SelectionPoint(enabled);
        label.setIcon(selectionPoint);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectionPoint.switchColor();
                label.repaint();
            }
        });
        label.setBounds(x, y, 25, 25);
        return label;
    }

    private void initBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBounds(450, 520, 400, 40);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(saveDamageReport());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            EditFrame.this.updateUI();
            app.search();
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            EditFrame.this.updateUI();
            app.edit();
        });

        bottomPanel.add(saveButton);
        bottomPanel.add(cancelButton);
        bottomPanel.add(clearButton);
        add(bottomPanel);
    }

    private DamageReport buildDamageReport() {
        DriverInfo driverInfo = new DriverInfo();
        if (empty(nameTextField, surnameTextField, addressTextField, phoneTextField, passIdTextField,
            vehicleBodyIdTextField, vehicleModelTextField)) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        driverInfo.setName(nameTextField.getText());
        driverInfo.setLastName(surnameTextField.getText());
        driverInfo.setAddress(addressTextField.getText());
        driverInfo.setPhone(phoneTextField.getText());
        driverInfo.setPassId(passIdTextField.getText());

        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setModel(vehicleModelTextField.getText());
        vehicleInfo.setType(vehicleTypeComboBox.getSelectedItem().toString());
        vehicleInfo.setBodyId(vehicleBodyIdTextField.getText());

        DamageInfo damageInfo = new DamageInfo();
        damageInfo.setDamage(new Damage(new boolean[]{
            ((SelectionPoint) zone01.getIcon()).selected(),
            ((SelectionPoint) zone02.getIcon()).selected(),
            ((SelectionPoint) zone03.getIcon()).selected(),
            ((SelectionPoint) zone04.getIcon()).selected(),
            ((SelectionPoint) zone05.getIcon()).selected(),
            ((SelectionPoint) zone06.getIcon()).selected(),
            ((SelectionPoint) zone07.getIcon()).selected(),
            ((SelectionPoint) zone08.getIcon()).selected(),
            ((SelectionPoint) zone09.getIcon()).selected(),
            ((SelectionPoint) zone10.getIcon()).selected(),
            ((SelectionPoint) zone11.getIcon()).selected(),
            ((SelectionPoint) zone12.getIcon()).selected(),
            ((SelectionPoint) zone13.getIcon()).selected(),
        }));


        if (driverInfo == null || vehicleInfo == null) {
            return null;
        } else {
            return new DamageReport(driverInfo, vehicleInfo, damageInfo);
        }

    }

    private boolean empty(JTextField... textFields) {
        return Arrays.stream(textFields).map(JTextField::getText).anyMatch(String::isEmpty);
    }

    private ActionListener saveDamageReport() {
        return event -> {
            DamageReport damageReport = buildDamageReport();
            if (Objects.nonNull(damageReport)) {
                int driverId = driverRepository.insertDriverInfo(damageReport.getDriverInfo());
                int vehicleId = driverRepository.insertVehicleInfo(driverId, damageReport.getVehicleInfo());
                driverRepository.insertDamageInfo(vehicleId, damageReport.getDamageInfo());
                app.search();
            }
        };
    }

}
