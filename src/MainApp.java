import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {
    private JTextArea inputArea;
    private JTextArea outputArea;

    public MainApp() {
        setTitle("Sorting Algorithms and Search");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(Color.decode("#202020"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel inputLabel = new JLabel("Enter an array:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 33));
        inputLabel.setForeground(Color.WHITE);

        JLabel outputLabel = new JLabel("Output:");
        outputLabel.setFont(new Font("Arial", Font.BOLD, 26));
        outputLabel.setForeground(Color.WHITE);

        inputArea = new JTextArea(3, 40);
        outputArea = new JTextArea(2, 40);
        inputArea.setFont(new Font("Arial", Font.PLAIN, 50));
        outputArea.setFont(new Font("Arial", Font.PLAIN, 50));
        inputArea.setBackground(Color.decode("#323232"));
        inputArea.setForeground(Color.WHITE);
        outputArea.setBackground(Color.decode("#323232"));
        outputArea.setForeground(Color.WHITE);
        outputArea.setEditable(false);

        Font buttonFont = new Font("Arial", Font.BOLD, 20);

        JButton bubbleSortButton = createButton("Bubble Sort", buttonFont);
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arr = getArrayFromInput();
                Sorting.bubbleSort(arr);
                displayArray(arr);
            }
        });

        JButton insertionSortButton = createButton("Insertion Sort", buttonFont);
        insertionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arr = getArrayFromInput();
                Sorting.insertionSort(arr);
                displayArray(arr);
            }
        });

        JButton selectionSortButton = createButton("Selection Sort", buttonFont);
        selectionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arr = getArrayFromInput();
                Sorting.selectionSort(arr);
                displayArray(arr);
            }
        });

        JButton quickSortButton = createButton("Quick Sort", buttonFont);
        quickSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arr = getArrayFromInput();
                Sorting.quickSort(arr, 0, arr.length - 1);
                displayArray(arr);
            }
        });

        JButton mergeSortButton = createButton("Merge Sort", buttonFont);
        mergeSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arr = getArrayFromInput();
                Sorting.mergeSort(arr);
                displayArray(arr);
            }
        });

        JButton heapSortButton = createButton("Heap Sort", buttonFont);
        heapSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arr = getArrayFromInput();
                Sorting.heapSort(arr);
                displayArray(arr);
            }
        });

        JButton binarySearchButton = createButton("Binary Search", buttonFont);
        binarySearchButton.setPreferredSize(new Dimension(200, 50));
        binarySearchButton.setBackground(Color.decode("#202020"));
        binarySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arr = getArrayFromInput();
                String input = JOptionPane.showInputDialog("Enter the number to search for (Binary Search):");
                if (input != null && !input.trim().isEmpty()) {
                    try {
                        int target = Integer.parseInt(input.trim());
                        int result = Search.binarySearch(arr, target);
                        displaySearchResult(result, target);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton linearSearchButton = createButton("Linear Search", buttonFont);
        linearSearchButton.setPreferredSize(new Dimension(200, 50));
        linearSearchButton.setBackground(Color.decode("#202020"));
        linearSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arr = getArrayFromInput();
                String input = JOptionPane.showInputDialog("Enter the number to search for (Linear Search):");
                if (input != null && !input.trim().isEmpty()) {
                    try {
                        int target = Integer.parseInt(input.trim());
                        int result = Search.linearSearch(arr, target);
                        displaySearchResult(result, target);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton clearButton = createButton("Clear", buttonFont);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
            }
        });

        JPanel sortButtonPanel = new JPanel();
        sortButtonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        sortButtonPanel.setBackground(Color.decode("#202020"));
        sortButtonPanel.add(bubbleSortButton);
        sortButtonPanel.add(insertionSortButton);
        sortButtonPanel.add(selectionSortButton);
        sortButtonPanel.add(quickSortButton);
        sortButtonPanel.add(mergeSortButton);
        sortButtonPanel.add(heapSortButton);

        JPanel searchButtonPanel = new JPanel();
        searchButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchButtonPanel.setBackground(Color.decode("#202020"));
        searchButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        searchButtonPanel.add(binarySearchButton);
        searchButtonPanel.add(linearSearchButton);

        JPanel clearButtonPanel = new JPanel();
        clearButtonPanel.setBackground(Color.decode("#202020"));
        clearButtonPanel.add(clearButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout(10, 10));
        buttonPanel.setBackground(Color.decode("#202020"));
        buttonPanel.add(sortButtonPanel, BorderLayout.NORTH);
        buttonPanel.add(searchButtonPanel, BorderLayout.CENTER);
        buttonPanel.add(clearButtonPanel, BorderLayout.SOUTH);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(Color.decode("#202020"));
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBackground(Color.decode("#202020"));
        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(outputPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private JButton createButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(Color.decode("#323232"));
        button.setForeground(Color.WHITE);
        return button;
    }

    private int[] getArrayFromInput() {
        String[] tokens = inputArea.getText().split("\\s+");
        int[] arr = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        return arr;
    }

    private void displayArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        outputArea.setText(sb.toString());
    }

    private void displaySearchResult(int result, int target) {
        if (result != -1) {
            outputArea.setText("Element " + target + " found at index: " + result);
        } else {
            outputArea.setText("Element " + target + " not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.setVisible(true);
        });
    }
}
