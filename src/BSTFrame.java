import javax.swing.*;
import java.awt.*;

public class BSTFrame<T extends Comparable<T>> extends JFrame {
    private LinkListBinaryTree<T> binaryTree;
    private JPanel treePanel;
    private JTextField inputField;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton printButton;
    private JButton searchButton;


    public BSTFrame() {
        setTitle("Binary Search Tree");
        setSize(2000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        binaryTree = new LinkListBinaryTree<T>();

        treePanel = binaryTree.createBinaryTreePanel();
        add(treePanel, BorderLayout.CENTER);
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(10);

        // insert button
        insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            String input = inputField.getText();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a value.");
                return;
            }
            try {
                T value = parseInput(input);
                binaryTree.insert(value);
                treePanel.repaint();
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
            
        });

        // delete button
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            String input = inputField.getText();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a value.");
                return;
            }
            
            try {
                T value = parseInput(input);
                binaryTree.deleteKey(value);
                treePanel.repaint();
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        });

        
        // print button
        printButton = new JButton("Print");
        printButton.addActionListener(e -> {
            binaryTree.printInOrderToFile("in_order.txt");
            binaryTree.printPreOrderToFile("pre_order.txt");
            binaryTree.printPostOrderToFile("post_order.txt");
        });


        // search button
        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String input = inputField.getText();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a value.");
                return;
            }
            
            try {
                T value = parseInput(input);
                if (binaryTree.search(value)) {
                    JOptionPane.showMessageDialog(null, "Value found in the tree.");
                }else {
                    JOptionPane.showMessageDialog(null, "Value not found in the tree.");
                }
                treePanel.repaint();
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        });
        inputPanel.add(new JLabel("Enter a value:"));
        inputPanel.add(inputField);
        inputPanel.add(insertButton);
        inputPanel.add(deleteButton);
        inputPanel.add(printButton);
        inputPanel.add(searchButton);

        add(inputPanel, BorderLayout.SOUTH);
    }

    private T parseInput(String input) throws NumberFormatException {
        // Tùy thuộc vào kiểu dữ liệu generic cụ thể, bạn có thể thực hiện phân tích đầu vào theo yêu cầu của mình ở đây.
        // Ví dụ: Integer.parseInt(input) cho kiểu dữ liệu Integer, Double.parseDouble(input) cho kiểu dữ liệu Double, vv.
        // Trong ví dụ này, chúng ta giả sử giá trị kiểu dữ liệu đã được chuyển đổi trước đó.
        return (T) input;
    }
}