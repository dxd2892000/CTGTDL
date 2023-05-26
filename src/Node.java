public class Node<T>{
    private T element;
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    public Node(T e, Node<T> above, Node<T> leftChild, Node<T> rightChild) {
        element = e;
        parent = above;
        left = leftChild;
        right = rightChild;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setElement(T e) {
        element = e;
    }

    public void setParent(Node<T> p) {
        parent = p;
    }

    public void setLeft(Node<T> l) {
        left = l;
    }

    public void setRight(Node<T> r) {
        right = r;
    }



}