import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.LineBorder;


public class LoginScreen extends JFrame {
    private static final String USER_DATA_FILE = "user_data.txt";
    private JTextField userField;
    private JPasswordField passField;
    private Map<String, String> userDatabase;

    public LoginScreen() {
        userDatabase = new HashMap<>();
        loadUserData(); // Load existing user data from a file

        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left panel with image and background color
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("tree.png");
                Image image = icon.getImage();
                int x = (getWidth() - image.getWidth(this)) / 2;
                int y = (getHeight() - image.getHeight(this)) / 2;
                g.drawImage(image, x, y, this);
            }
        };
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setPreferredSize(new Dimension(200, 0));
        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Right panel with form and background color
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.decode("#202020"));
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Font settings
        Font font = new Font("Arial", Font.PLAIN, 16);
        Color fontColor = Color.WHITE;

        // Username label and field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(fontColor);
        userLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        userField = new JTextField(15);
        userField.setFont(font);
        userField.setForeground(fontColor);
        userField.setBackground(Color.decode("#202020"));
        userField.setBorder(new LineBorder(Color.WHITE, 1, true));
        rightPanel.add(userField, gbc);

        // Password label and field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(fontColor);
        passLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        passField = new JPasswordField(15);
        passField.setFont(font);
        passField.setForeground(fontColor);
        passField.setBackground(Color.decode("#202020"));
        passField.setBorder(new LineBorder(Color.WHITE, 1, true));
        rightPanel.add(passField, gbc);

        // Login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.decode("#323232"));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        rightPanel.add(loginButton, gbc);

        // Sign Up button
        gbc.gridy = 3;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.decode("#323232"));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setBorderPainted(false);
        rightPanel.add(signUpButton, gbc);

        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Define action listeners for the buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    new MainApp().setVisible(true);
                    dispose(); // Close the login window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                    userField.setText("");
                    passField.setText("");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (userDatabase.containsKey(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists");
                } else {
                    userDatabase.put(username, password);
                    saveUserData(username, password);
                    JOptionPane.showMessageDialog(null, "Sign Up Successful");
                    userField.setText("");
                    passField.setText("");
                }
            }
        });

        add(mainPanel);
    }

    private void loadUserData() {
        File file = new File(USER_DATA_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    userDatabase.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUserData(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginScreen().setVisible(true);
    }
}
