class Node {
    int value;
    Node left, right;

    public Node(int value) {
        this.value = value;
        left = right = null;
    }
}

public class SimpleBinaryTree {

    // Root of the Binary Tree
    Node root;

    // Constructor to create a new tree
    public SimpleBinaryTree() {
        root = null;
    }

    // Function to insert a new node in the tree
    public void insert(int value) {
        root = insertRec(root, value);
    }

    // Recursive function to insert a new node in the tree
    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);  // Create a new node if the position is empty
            return root;
        }

        if (value < root.value) {
            root.left = insertRec(root.left, value);  // Insert in the left subtree
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);  // Insert in the right subtree
        }

        return root;
    }

    // Inorder Traversal (Left, Root, Right)
    public void inorderTraversal() {
        inorderRec(root);
    }

    // another function for inorder traversal
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);  // Visit left subtree
            System.out.print(root.value + " ");  // Visit root
            inorderRec(root.right);  // Visit right subtree
        }
    }

    public static void main(String[] args) {
        // Create a Binary Tree
        SimpleBinaryTree tree = new SimpleBinaryTree();

        // Insert nodes into the tree
        tree.insert(31);
        tree.insert(5);
        tree.insert(16);
        tree.insert(0);
        tree.insert(25);

        // Perform inorder traversal and print the result
        System.out.println("Inorder Traversal:");
        tree.inorderTraversal();  // Output: 3 5 7 10 15
    }
}
