import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Gui extends JFrame {
    private DefaultListModel<String> listModel;
    private JPanel fileListPanel;
    private JButton addButton;
    private JButton prevButton;
    private JButton nextButton;
    private JTextField textField;
    private JPanel panelMain;

    public Gui() {
        initComponents();

        listModel = new DefaultListModel<>();

        addButton = new JButton("Přidej");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Gui.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    listModel.addElement(filePath);
                    updateFileListPanel();
                }
            }
        });

        prevButton = new JButton("Předchozí");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listModel.indexOf(textField.getText());
                if (index > 0) {
                    textField.setText(listModel.getElementAt(index - 1));
                }
            }
        });

        nextButton = new JButton("Další");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listModel.indexOf(textField.getText());
                if (index < listModel.getSize() - 1) {
                    textField.setText(listModel.getElementAt(index + 1));
                }
            }
        });

        zobrazGui();
        pridejTestovaciData();
    }

    public void zobrazGui() {
        textField = new JTextField(30);
        textField.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(new JLabel("Soubor:"));
        textFieldPanel.add(textField);

        fileListPanel = new JPanel();
        fileListPanel.setLayout(new BoxLayout(fileListPanel, BoxLayout.Y_AXIS));

        panelMain.setLayout(new BorderLayout());
        panelMain.add(buttonPanel, BorderLayout.SOUTH);
        panelMain.add(textFieldPanel, BorderLayout.NORTH);
    }

    public void initComponents() {
        panelMain = new JPanel(); // Inicializujte panelMain zde
        setContentPane(panelMain);
        setTitle("Gui");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setLocationRelativeTo(null); //zobrazi se to uprostred
    }

    public void updateFileListPanel() {
        fileListPanel.removeAll();
        for (int i = 0; i < listModel.getSize(); i++) {
            JLabel label = new JLabel(listModel.getElementAt(i));
            fileListPanel.add(label);
        }
        fileListPanel.revalidate();
        fileListPanel.repaint();
    }

    public void pridejTestovaciData() {
        listModel.addElement("C:\\Windows");
        listModel.addElement("C:\\Program Files");
        listModel.addElement("C:\\Program Files (x86)");
        updateFileListPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Gui fileListGUI = new Gui();
                fileListGUI.setVisible(true);
            }
        });
    }
}
