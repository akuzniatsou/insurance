package com.dww.insurance.frame;

import static java.lang.String.format;

import com.dww.insurance.domain.DamageReport;
import com.dww.insurance.domain.DriverInfo;
import com.dww.insurance.domain.VehicleInfo;
import com.dww.insurance.domain.VehicleType;
import com.dww.insurance.service.DriverRepository;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Objects;


public class EditFrame extends JPanel {

    private JTextField surnameTextField;
    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JTextField passIdTextField;
    private JTextField addressTextField;
    private JTextField phoneTextField;
    private JTextField vehicleModelTextField;
    private JTextField vehicleBodyIdTextField;


    private JComboBox<VehicleType> vehicleTypeComboBox;

    private DriverRepository driverRepository;
    private IApplication app;

    public EditFrame(IApplication app) {
        this.app = app;
        driverRepository = new DriverRepository();
        initialize();
    }

    public void initialize() {
        removeAll();
        setLayout(null);

        JLabel lblDriverInfo = new JLabel("Driver Info");
        lblDriverInfo.setBounds(10, 11, 145, 35);
        add(lblDriverInfo);

        JLabel lblSurname = new JLabel("Surname");
        lblSurname.setBounds(10, 45, 70, 14);
        add(lblSurname);

        surnameTextField = new JTextField();
        surnameTextField.setBounds(90, 39, 135, 20);
        add(surnameTextField);
        surnameTextField.setColumns(10);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(10, 73, 70, 14);
        add(lblName);

        nameTextField = new JTextField();
        nameTextField.setBounds(90, 67, 135, 20);
        add(nameTextField);
        nameTextField.setColumns(10);

        JLabel lblPotronymic = new JLabel("Potronymic");
        lblPotronymic.setBounds(10, 103, 70, 14);
        add(lblPotronymic);

        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(90, 97, 135, 20);
        add(lastNameTextField);
        lastNameTextField.setColumns(10);

        JLabel lblPassId = new JLabel("Pass_ID");
        lblPassId.setBounds(254, 45, 105, 14);
        add(lblPassId);

        passIdTextField = new JTextField();
        passIdTextField.setBounds(369, 39, 160, 20);
        add(passIdTextField);
        passIdTextField.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(254, 73, 105, 14);
        add(lblAddress);

        addressTextField = new JTextField();
        addressTextField.setBounds(369, 67, 160, 20);
        add(addressTextField);
        addressTextField.setColumns(10);

        JLabel lblTelephone = new JLabel("Phone");
        lblTelephone.setBounds(254, 103, 105, 14);
        add(lblTelephone);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(369, 97, 160, 20);
        add(phoneTextField);
        phoneTextField.setColumns(10);

        JLabel lblVehicleInfo = new JLabel("Vehicle Info");
        lblVehicleInfo.setBounds(10, 170, 174, 14);
        add(lblVehicleInfo);

        JLabel lblName_1 = new JLabel("Model");
        lblName_1.setBounds(10, 210, 46, 14);
        add(lblName_1);

        vehicleModelTextField = new JTextField();
        vehicleModelTextField.setBounds(50, 207, 86, 20);
        add(vehicleModelTextField);
        vehicleModelTextField.setColumns(10);

        JLabel lblType = new JLabel("Type");
        lblType.setBounds(158, 213, 70, 14);
        add(lblType);

        vehicleTypeComboBox = new JComboBox<>(VehicleType.values());
        vehicleTypeComboBox.setBounds(206, 207, 120, 20);
        add(vehicleTypeComboBox);

        JLabel lblBodyid = new JLabel("Body_ID");
        lblBodyid.setBounds(370, 213, 86, 14);
        add(lblBodyid);

        vehicleBodyIdTextField = new JTextField();
        vehicleBodyIdTextField.setBounds(439, 210, 100, 20);
        add(vehicleBodyIdTextField);
        vehicleBodyIdTextField.setColumns(10);

        JLabel lblDamageInfo = new JLabel("Damage Info");
        lblDamageInfo.setBounds(10, 268, 100, 14);
        add(lblDamageInfo);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(saveDamageReport());
        saveButton.setBounds(341, 428, 89, 23);
        add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(440, 428, 89, 23);
        cancelButton.addActionListener(e -> {
            EditFrame.this.updateUI();
            app.search();
        });
        add(cancelButton);

        setVisible(true);
    }

    private DamageReport buildDamageReport() {
        DriverInfo driverInfo = null;
        if (Objects.isNull(nameTextField.getText()) || Objects.isNull(passIdTextField.getText())) {
            JOptionPane.showMessageDialog(this, format("Field %s cannot be empty", "NAME or PASS ID"), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            driverInfo = new DriverInfo();
            driverInfo.setName(nameTextField.getText());
            driverInfo.setPassId(passIdTextField.getText());
        }

        VehicleInfo vehicleInfo = null;
        if (Objects.isNull(vehicleModelTextField.getText()) || Objects.isNull(vehicleTypeComboBox.getSelectedItem()) || Objects.isNull(vehicleBodyIdTextField.getText())) {
            JOptionPane.showMessageDialog(this, format("Field %s cannot be empty", "MODEL, TYPE or BODY ID"), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            vehicleInfo = new VehicleInfo();
            vehicleInfo.setModel(vehicleModelTextField.getText());
            vehicleInfo.setType(vehicleTypeComboBox.getSelectedItem().toString());
            vehicleInfo.setBodyId(vehicleBodyIdTextField.getText());
        }

        if (driverInfo == null || vehicleInfo == null) {
            return null;
        } else {
            return new DamageReport(driverInfo, vehicleInfo);
        }

    }

    private ActionListener saveDamageReport() {
        return event -> {
            DamageReport damageReport = buildDamageReport();
            if (Objects.nonNull(damageReport)) {
                int driverId = driverRepository.insertDriverInfo(damageReport.getDriverInfo());
                driverRepository.insertVehicleInfo(driverId, damageReport.getVehicleInfo());
                app.search();
            }
        };
    }

}
