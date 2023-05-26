public interface BinaryTreeInterface<T> {
    Node<T> root();
    int size(); // số nút trong cây
    boolean isEmpty();
    int numChildren(Node<T> p); // số con của phần tử p
    Node<T> parent(Node<T> p); // trả về cha của p
    Node<T> left(Node<T> p); // trả về con trái của p
    Node<T> right(Node<T> p); // trả về con phải của p
    Node<T> sibling(Node<T> p); // trả về anh/chị em của p
}