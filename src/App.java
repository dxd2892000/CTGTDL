import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        // Integer[] array ={30, 10, 12, 45, 2, 3, 7, 6,13};
        // LinkListBinaryTree<Integer> link = new LinkListBinaryTree<Integer>();
        // link.setArray(array);
        // link.creatBinaryTree();
        // link.inOrder();
        // link.deleteKey(45);
        // //System.out.println(link.root().getElement());
        // System.out.println("");
        // link.inOrder();
        // System.out.println("");
        // link.preOder();
        // System.out.println("");
        // link.postOrder();
        // System.out.println("");
        SwingUtilities.invokeLater(() -> {
            BSTFrame<Integer> frame = new BSTFrame<Integer>();
            frame.setVisible(true);
        });
    }
}
