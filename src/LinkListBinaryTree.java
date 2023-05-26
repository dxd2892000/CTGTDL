import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;

public class LinkListBinaryTree<T extends Comparable<T>> implements BinaryTreeInterface<T>{
    private int size;
    private Node<T> root;
    private T[] array;
    // khởi tạo
    public LinkListBinaryTree(){
        size = 0;
        root = null;
    }

    public void setArray(T[] a){
        this.array = a;
    }
    
    // tạo Binarytree theo mảng cho sẵn
    public void creatBinaryTree(){
        int lenght = array.length;
        for(int i = 0; i < lenght; i+=1){
            Node<T> node = insertNode(root, array[i]);
        }
    }

    public Node<T> insert(T key){
        return root = insertNode(root, key);
    }

    // xóa node theo giá trị
    public void deleteKey(T key){
        root = deleteNode(root, key);
    }

    // in postorder
    public void postOrder(){
        postoderNode(root);
    }

    // In preOder
    public void preOder(){
        preoderNode(root);
    }

    // In Inoder
    public void inOrder(){
        inorderNode(root);
    }

    // Kiểm tra node có tồn tại trong tree
    public boolean search(T key){
        Node<T> node = searchNode(root, key); 
        if (node!= null)
            return true;
        else
            return false;
    }

    @Override
    public Node<T> root() { // lấy root
        return root;
    }

    @Override
    public int size() { // Lấy kích thước
        return this.size;
    }

    @Override
    public boolean isEmpty() { //Kiểm tra tree rỗng
        return size == 0;
    }

    @Override
    public int numChildren(Node<T> p) { //Số con của node
        int count = 0;
        if(p.getLeft() != null) count+=1;
        if(p.getRight() != null) count+= 1;
        return count;
    }

    @Override
    public Node<T> parent(Node<T> p) { //Lấy parent
        return p.getParent();
    }

    @Override
    public Node<T> left(Node<T> p) { // Lấy con trái
        return p.getLeft();
    }

    @Override
    public Node<T> right(Node<T> p) { // Lấy con phải
        return p.getRight();
    }

    @Override
    public Node<T> sibling(Node<T> p) {
        Node<T> parent = p.getParent();
        if(parent == null) return null;
        if(p == parent.getLeft()) return parent.getRight();
        else return parent.getLeft();
    }

    // Xóa một node trong tree
    private Node<T> deleteNode(Node<T> root, T key) {
        if (root == null) {System.out.println("Tree is null");} //Kiểm tra cây có rỗng ko
        //if (key == null) {return null;}
        if (key.compareTo(root.getElement()) < 0) // Giá trị cần xóa bé hơn giá trị node đang xét thì kiểm tra nhánh bên trái
            root.setLeft(deleteNode(root.getLeft(), key)); 
        else if (key.compareTo(root.getElement()) > 0) // Giá trị cần xóa lớn hơn giá trị node đang xét thì kiểm tra nhánh bên phải
            root.setRight(deleteNode(root.getRight(), key)); 
        else {
            // Tìm được node cần xóa
            if(root.getLeft() == null && root.getRight() == null){
                if (root.getElement().compareTo(root.getParent().getElement()) > 0){
                    root.setRight(null);
                    size -= 1;
                    return null;
                } else {
                    root.setLeft(null);
                    size -= 1;
                    return null;
                }
            }else{ 
                if (root.getLeft() == null){
                    root.getRight().setParent(root.getParent());
                    root.getParent().setLeft(root.getRight());
                    size -= 1;
                    return null;
                }
                else if (root.getRight() == null) {
                    root.getLeft().setParent(root.getParent());
                    root.getParent().setRight(root.getLeft());
                    size -= 1;
                    return null;
                }else {
                    T min = minValue(root.getRight());
                    deleteNode(root, min);
                    root.setElement(min); 
                }
            }
             
        } 
        
        return root; 
    }  
    

    // insert Node
    private Node<T> insertNode(Node<T> root, T key){
        if(root == null){
            root = addRoot(key);
            return root;
        }
        if(key.compareTo(root.getElement()) < 0){ // Bé hơn thêm vào nhánh trái
            if(root.getLeft() == null){
                root.setLeft(addLeft(root, key));
            }else{
                insertNode(root.getLeft(), key);
            }
        }else if (key.compareTo(root.getElement()) > 0){ // Lớn hơn thêm vào nhánh phải
            if(root.getRight() == null){
                root.setRight(addRight(root, key));
            }else{
                insertNode(root.getRight(), key);
            }
        }else {
            return null;
        }
        return root; // Trả về thằng root
    }

    // Tìm kiếm node
    private Node<T> searchNode(Node<T> root, T key)  {  
        if (root==null || root.getElement()==key) 
            return root; 
        if (root.getElement().compareTo(key) > 0) 
            return searchNode(root.getLeft(), key); 
        return searchNode(root.getRight(), key); 
    }   
    
    // Duyệt inorder
    private void inorderNode(Node<T> root) { 
        if (root != null) { 
            inorderNode(root.getLeft()); 
            System.out.print(root.getElement() + " "); 
            inorderNode(root.getRight()); 
        } 
    } 

    // Duyệt preOder
    private void preoderNode(Node<T> root){
        if(root != null){
            System.out.print(root.getElement()+ " ");
            preoderNode(root.getLeft());
            preoderNode(root.getRight());
        }
    }

    // Duyệt postOder
    private void postoderNode(Node<T> root){
        if(root != null){
            postoderNode(root.getLeft());
            postoderNode(root.getRight());
            System.out.print(root.getElement()+ " ");
        }
    }

    // Tạo root
    private Node<T> addRoot(T key){
        if(!isEmpty()) {
            throw new IllegalStateException("Tree is not empty");
        }else {
            root = new Node<T>(key, null, null, null);
            size += 1;
        }
        return root;
    }

    //Thêm con trái
    private Node<T> addLeft(Node<T> p, T key){
        if(p.getLeft() != null){
            throw new IllegalArgumentException("Node already has a left child");
        }else {
            Node<T> leftChild = new Node<T>(key, p, null, null);
            size += 1;
            return leftChild;
        }
    }
    
    // Thêm con phải
    private Node<T> addRight(Node<T> p, T key){
        if(p.getRight() != null){
            throw new IllegalArgumentException("Node already has a right child");
        }else {
            Node<T> rightChild = new Node<T>(key, p, null, null);
            p.setRight(rightChild);
            size += 1;
            return rightChild;
        }
    }

    // Tìm giá trị nhỏ nhất
    private T minValue(Node<T> root)  { 
        T minval = root.getElement(); 
        // Duyệt nhánh bên trái
        while (root.getLeft() != null)  { 
            minval = root.getLeft().getElement(); 
            root = root.getLeft(); 
        } 
        return minval; 
    } 

    // Hàm tạo đồ họa cho cây nhị phân
    JPanel createBinaryTreePanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTree(g, getWidth() / 2, 30, root, getWidth() / 4);
            }
        };
        panel.setPreferredSize(new Dimension(800, 600));
        return panel;
    }

    // Hàm vẽ cây nhị phân
    private void drawTree(Graphics g, int x, int y, Node<T> node, int xOffset) {
        if (node != null) {
            g.setColor(Color.WHITE);
            g.fillOval(x - 20, y - 20, 40, 40);
            g.setColor(Color.BLACK);
            g.drawOval(x - 20, y - 20, 40, 40);
            g.drawString(node.getElement().toString(), x - 6, y + 6);
    
            if (node.getLeft() != null) {
                int xLeft = x - xOffset;
                int yLeft = y + 60;
                g.setColor(Color.BLACK);
                g.drawLine(x, y + 20, xLeft, yLeft - 20);
                drawTree(g, xLeft, yLeft, node.getLeft(), xOffset / 2);
            }
    
            if (node.getRight() != null) {
                int xRight = x + xOffset;
                int yRight = y + 60;
                g.setColor(Color.BLACK);
                g.drawLine(x, y + 20, xRight, yRight - 20);
                drawTree(g, xRight, yRight, node.getRight(), xOffset / 2);
            }
        }
    }
    
    // Phương thức in-order ghi vào file
    void printInOrderToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("In-order traversal: ");
            printInOrderToFile(root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Phương thức print in-order
    void printInOrderToFile(Node<T> node, FileWriter writer) throws IOException {
        if (node == null)
            return;

        printInOrderToFile(node.getLeft(), writer);
        writer.write(node.getElement() + " ");
        printInOrderToFile(node.getRight(), writer);
    }

    // Phương thức pre-order ghi vào file
    void printPreOrderToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Pre-order traversal: ");
            printPreOrderToFile(root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printPreOrderToFile(Node<T> node, FileWriter writer) throws IOException {
        if (node == null)
            return;

        printPreOrderToFile(node.getParent(), writer);
        writer.write(node.getElement() + " ");
        printPreOrderToFile(node.getRight(), writer);
    }

    // Phương thức print post-order
    void printPostOrderToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Pre-order traversal: ");
            printPostOrderToFile(root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printPostOrderToFile(Node<T> node, FileWriter writer) throws IOException {
        if (node == null)
            return;

        printPostOrderToFile(node.getLeft(), writer);
        printPostOrderToFile(node.getRight(), writer);
        writer.write(node.getElement() + " ");
    }
}
