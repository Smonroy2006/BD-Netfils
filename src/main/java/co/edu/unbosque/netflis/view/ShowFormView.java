package co.edu.unbosque.netflis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ShowFormView extends JPanel {

    private JTextField txtShowId, txtType, txtTitle, txtDirector, txtCast, txtCountry, 
                       txtDate, txtReleaseYear, txtRating, txtDuration, txtListedIn;
    private JTextArea txtDescription;
    private JButton btnGuardar, btnLimpiar;

    public ShowFormView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabelAndField(panelForm, "Show ID:", txtShowId = new JTextField(10), 0, 0, gbc);
        addLabelAndField(panelForm, "Tipo (Movie/TV Show):", txtType = new JTextField(10), 2, 0, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panelForm.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtTitle = new JTextField(30);
        panelForm.add(txtTitle, gbc);

        gbc.gridwidth = 1;
        addLabelAndField(panelForm, "Director:", txtDirector = new JTextField(15), 0, 2, gbc);
        addLabelAndField(panelForm, "País:", txtCountry = new JTextField(15), 2, 2, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        panelForm.add(new JLabel("Reparto:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtCast = new JTextField(30);
        panelForm.add(txtCast, gbc);

        gbc.gridwidth = 1;
        addLabelAndField(panelForm, "Fecha (Month DD, YYYY):", txtDate = new JTextField(15), 0, 4, gbc);
        addLabelAndField(panelForm, "Año Lanzamiento:", txtReleaseYear = new JTextField(15), 2, 4, gbc);

        addLabelAndField(panelForm, "Rating:", txtRating = new JTextField(15), 0, 5, gbc);
        addLabelAndField(panelForm, "Duración:", txtDuration = new JTextField(15), 2, 5, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 1;
        panelForm.add(new JLabel("Categorías:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtListedIn = new JTextField(30);
        panelForm.add(txtListedIn, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        panelForm.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtDescription = new JTextArea(4, 30);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        panelForm.add(new JScrollPane(txtDescription), gbc);

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnGuardar = new JButton("Guardar Registro");
        btnGuardar.setActionCommand("GUARDAR");
        btnLimpiar = new JButton("Limpiar Formulario");
        btnLimpiar.setActionCommand("LIMPIAR_FORM");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void addLabelAndField(JPanel panel, String label, JTextField field, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x; gbc.gridy = y; gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = x + 1;
        panel.add(field, gbc);
    }

    public String getShowId() { return txtShowId.getText().trim(); }
    public String getType() { return txtType.getText().trim(); }
    public String getTitle() { return txtTitle.getText().trim(); }
    public String getDirector() { return txtDirector.getText().trim(); }
    public String getCast() { return txtCast.getText().trim(); }
    public String getCountry() { return txtCountry.getText().trim(); }
    public String getDate() { return txtDate.getText().trim(); }
    public String getReleaseYear() { return txtReleaseYear.getText().trim(); }
    public String getRating() { return txtRating.getText().trim(); }
    public String getDuration() { return txtDuration.getText().trim(); }
    public String getListedIn() { return txtListedIn.getText().trim(); }
    public String getDescription() { return txtDescription.getText().trim(); }

    public void limpiarCampos() {
        txtShowId.setText("");
        txtType.setText("");
        txtTitle.setText("");
        txtDirector.setText("");
        txtCast.setText("");
        txtCountry.setText("");
        txtDate.setText("");
        txtReleaseYear.setText("");
        txtRating.setText("");
        txtDuration.setText("");
        txtListedIn.setText("");
        txtDescription.setText("");
    }

    public void addActionListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
        btnLimpiar.addActionListener(listener);
    }
}
