import data.Node;
import structure.BinarySearchTree;
import structure.BinaryTree;

public class App {

    public static void main(String[] args) {
        System.out.println("Binary Tree");

        // creates the binary tree
        BinaryTree binaryTree = new BinaryTree();

        // adds nodes to the tree
        binaryTree.setRoot(new Node(1));
        binaryTree.getRoot().setLeft(new Node(2));
        binaryTree.getRoot().setRight(new Node(3));
        binaryTree.getRoot().getLeft().setLeft(new Node(4));

        System.out.println("inorder");
        binaryTree.inOrderTraverse(binaryTree.getRoot());

        System.out.println();
        System.out.println("postorder");
        binaryTree.postOrderTraverse(binaryTree.getRoot());

        System.out.println();
        System.out.println("preorder");
        binaryTree.preOrderTraverse(binaryTree.getRoot());

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.add(5);
        binarySearchTree.add(7);
        binarySearchTree.add(3);
        binarySearchTree.add(10);
    }
}
