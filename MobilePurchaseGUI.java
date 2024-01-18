import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MobilePurchaseGUI implements ActionListener {

    private JFrame frame;
    private JComboBox<String> brandComboBox;
    private JRadioButton year2018, year2019, year2020;
    private JTextField modelTextField, priceTextField;
    private JTextArea resultTextArea;

    public MobilePurchaseGUI() {
        frame = new JFrame("Online Mobile Shopping");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Flash message using multithreading
        JLabel flashLabel = new JLabel("ONLINE MOBILE SHOPPING",SwingConstants.CENTER);
        frame.add(flashLabel, BorderLayout.NORTH);
        Thread flashThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String text = flashLabel.getText();
                flashLabel.setText(text.substring(1) + text.charAt(0));
            }
        });
        flashThread.start();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        // Brand Name dropdown
        String[] brands = {"SAMSUNG", "SONY", "MOTOROLA"};
        brandComboBox = new JComboBox<>(brands);
        inputPanel.add(new JLabel("Brand Name:"));
        inputPanel.add(brandComboBox);

        // Year of Product Release radio buttons
        ButtonGroup yearGroup = new ButtonGroup();
        year2018 = new JRadioButton("2018");
        year2019 = new JRadioButton("2019");
        year2020 = new JRadioButton("2020");
        yearGroup.add(year2018);
        yearGroup.add(year2019);
        yearGroup.add(year2020);
        inputPanel.add(new JLabel("Year of Product Release:"));
        inputPanel.add(year2018);
        inputPanel.add(new JLabel(""));
        inputPanel.add(year2019);
        inputPanel.add(new JLabel(""));
        inputPanel.add(year2020);

        // Model text field
        modelTextField = new JTextField();
        inputPanel.add(new JLabel("Model:"));
        inputPanel.add(modelTextField);

        // Price text field
        priceTextField = new JTextField();
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceTextField);

        // Submit button
        JButton submitButton = new JButton("Submit");
        inputPanel.add(new JLabel(""));
        inputPanel.add(submitButton);

        frame.add(inputPanel, BorderLayout.CENTER);

        // Result text area
        resultTextArea = new JTextArea();
        frame.add(new JScrollPane(resultTextArea), BorderLayout.SOUTH);

        submitButton.addActionListener(this);
    }

    private void validateInputs() throws Exception {
        // Validate price range
        int price = Integer.parseInt(priceTextField.getText());
        if (price < 10000 || price > 50000) {
            throw new Exception("PRODUCT NOT AVAILABLE IN THIS RANGE");
        }

        // You can add more validations as needed
    }

    private void displayResult() {
        String result = "Brand: " + brandComboBox.getSelectedItem() +
                "\nYear of Release: " + getSelectedYear() +
                "\nModel: " + modelTextField.getText() +
                "\nPrice: " + priceTextField.getText();
        resultTextArea.setText(result);
    }

    private String getSelectedYear() {
        if (year2018.isSelected()) return "2018";
        else if (year2019.isSelected()) return "2019";
        else if (year2020.isSelected()) return "2020";
        else return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            validateInputs();
            displayResult();
        } catch (Exception ex) {
            resultTextArea.setText(ex.getMessage());
        }
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MobilePurchaseGUI mobilePurchaseGUI = new MobilePurchaseGUI();
            mobilePurchaseGUI.show();
        });
    }
}
