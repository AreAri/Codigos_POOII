import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Entry {
    private final String FILE_NAME = "Agenda.txt";

    public static void main(String[] args) {
        new Entry().init();
    }

    private void init() {
        JFrame frame = new JFrame("Agenda Telefónica");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); //vertical

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(2, 2, 2, 2));
        JLabel nombre = new JLabel("Nombre: ");
        JTextField txtNombre = new JTextField(40);
        JLabel telefono = new JLabel("Teléfono: ");
        MyTextField my = new MyTextField();
        form.add(nombre);
        form.add(txtNombre);
        form.add(telefono);
        form.add(my);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton anadir = new JButton("Añadir");
        anadir.setBackground(Color.lightGray);
        panelBoton.add(anadir);

        JPanel panelTabla = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombres");
        model.addColumn("Teléfonos");

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setGridColor(Color.BLACK);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setDefaultEditor(Object.class, null);
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton save = new JButton("Guardar");
        JButton delete = new JButton("Eliminar");
        save.setBackground(Color.lightGray);
        delete.setBackground(Color.lightGray);
        panelBotones2.add(save);
        panelBotones2.add(delete);

        // Agregar los paneles al panel principal
        mainPanel.add(form);
        mainPanel.add(panelBoton);
        mainPanel.add(panelTabla);
        mainPanel.add(panelBotones2);

        ArrayList<Contacts> contactos = cargarContactos();

        if (contactos != null) {
            for (Contacts contacto : contactos) {
                model.addRow(new Object[]{contacto.getNombre(), contacto.getTelefono()});
            }
        }

        anadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (my.getText().isEmpty() || txtNombre.getText().isEmpty()) return;
                if (!my.getText().matches("\\(\\d{3}\\) \\d{7}")) {
                    JOptionPane.showMessageDialog(frame, "El formato del número de teléfono es incorrecto o faltan números", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Contacts nuevoContacto = new Contacts(txtNombre.getText(), my.getText());
                contactos.add(nuevoContacto);
                model.addRow(new Object[]{txtNombre.getText(), my.getText()});
                my.setText("");
                txtNombre.setText("");
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarContactos(contactos);
                JOptionPane.showMessageDialog(frame, "La agenda ha sido actualizada", "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() > -1) {
                    contactos.remove(table.getSelectedRow());
                    model.removeRow(table.getSelectedRow());
                }
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private ArrayList<Contacts> cargarContactos() {
        ArrayList<Contacts> contactos = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error al crear el archivo " + FILE_NAME, e);
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(":");
                String nombre = partes[0];
                String telefono = partes[1];
                contactos.add(new Contacts(nombre, telefono));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo " + FILE_NAME, e);
        }
        return contactos;
    }

    private void guardarContactos(ArrayList<Contacts> contactos) {
        try (FileWriter fw = new FileWriter(FILE_NAME);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (Contacts contacto : contactos) {
                bw.write(contacto.getNombre() + ":" + contacto.getTelefono() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los contactos en el archivo " + FILE_NAME, e);
        }
    }
}