import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a node in the file system, either a folder or a file
class TreeNode {
    String name;
    boolean isFile;
    List<TreeNode> children;

// Constructor to initialize a node with a name and type (file or folder)
    public TreeNode(String name, boolean isFile) {
        this.name = name;
        this.isFile = isFile;
        this.children = new ArrayList<>();
    }

// Adds a child node to this node's list of children
    public void addChild(TreeNode child) {
        children.add(child);
    }

// Removes a child node with the specified name
    public boolean removeChild(String name) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).name.equalsIgnoreCase(name)) {
                children.remove(i);
                return true;
            }
        }
        return false;
    }

// Recursively displays the tree structure starting from this node
    public void display(String indent) {
        System.out.println(indent + (isFile ? "File: " : "Folder: ") + name);
        for (TreeNode child : children) {
            child.display(indent + "  ");
        }
    }
}

// Manages the entire file system, including the root node and user interactions
public class FileSystemTree {
    private static final Scanner scanner = new Scanner(System.in);
    private TreeNode root;

// Constructor to initialize the file system with a root folder
    public FileSystemTree(String rootName) {
        root = new TreeNode(rootName, false);
    }

// Finds a node with a specified name, starting from the given currentNode
    public TreeNode findNode(TreeNode currentNode, String name) {
        if (currentNode.name.equalsIgnoreCase(name)) {
            return currentNode;
        }
        for (TreeNode child : currentNode.children) {
            TreeNode result = findNode(child, name);
            if (result != null) return result;
        }
        return null;
    }

// Deletes a node with the given name under the specified parent node
    public boolean deleteNode(String parentName, String name) {
        TreeNode parentNode = findNode(root, parentName);
        if (parentNode != null && !parentNode.isFile) {
            boolean removed = parentNode.removeChild(name);
            if (removed) {
                System.out.println("Node " + name + " deleted successfully.");
                return true;
            } else {
                System.out.println("Node " + name + " not found under " + parentName + ".");
            }
        } else {
            System.out.println("Parent folder not found or is a file.");
        }
        return false;
    }

// Displays the entire tree structure starting from the root
    public void displayTree() {
        root.display("");
    }

    public void start() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Tree");
            System.out.println("2. Add Folder/File");
            System.out.println("3. Delete Folder/File");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
				// Display the entire file structure
                case 1:
                    displayTree();
                    break;
				// Add a new folder or file
                case 2:
                    System.out.print("Enter parent folder/file: ");
                    String parentName = scanner.nextLine().trim();

                    System.out.print("Enter name of new folder/file: ");
                    String name = scanner.nextLine().trim();

                    System.out.print("Is it a file? (yes/no): ");
                    boolean isFile = scanner.nextLine().trim().equalsIgnoreCase("yes");

                    // Find or create parent node
                    TreeNode parentNode = findNode(root, parentName);
                    if (parentNode == null) {
                        // If parent doesn't exist, create it as a folder
                        System.out.println("Parent folder not found. Creating it.");
                        parentNode = new TreeNode(parentName, false);
                        root.addChild(parentNode);
                    } else if (parentNode.isFile) {
                        System.out.println("Error: Parent is a file, cannot add a child.");
                        break;
                    }

                    // Add the new node
                    TreeNode newNode = new TreeNode(name, isFile);
                    parentNode.addChild(newNode);
                    System.out.println((isFile ? "File " : "Folder ") + name + " added under " + parentName + ".");
                    break;

                case 3: // Delete Folder/File
                    System.out.print("Enter parent folder name: ");
                    parentName = scanner.nextLine().trim();

                    System.out.print("Enter name of folder/file to delete: ");
                    name = scanner.nextLine().trim();

                    deleteNode(parentName, name);
                    break;

                case 4: // Exit
                    System.out.println("Exiting.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
//The main method
    public static void main(String[] args) {
        FileSystemTree fileSystem = new FileSystemTree("Root");
        fileSystem.start();
    }
}
