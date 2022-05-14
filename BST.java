import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST <K extends Comparable<K>, V> {
    private Node root;
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        Node node2 = new Node(key, val);
        if (root == null) {
            root = node2;
            return;
        }
        Node parent = null, node1 = root;
        while (node1 != null) {
            parent = node1;
            int cmp = key.compareTo(node1.key);
            if
            (cmp < 0) node1 = node1.left;
            else if (cmp > 0) node1 = node1.right;
            else {
                node1.val = val;
                return;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) parent.left = node2;
        else
            parent.right = node2;
    }

    public V get(K key) {
        Node node1 = root;
        while (node1 != null) {
            int comparator = key.compareTo(node1.key);
            if
            (comparator < 0) node1 = node1.left;
            else if (comparator > 0) node1 = node1.right;
            else return node1.val;
        }
        return null;
    }

    public void delete(K key) {
        if (this.root == null){
            return;
        }
        Node parent = null;
        Node currentNode = this.root;

        while (currentNode.key != null){
            if (currentNode.key.compareTo(key)>0){
                parent = currentNode;
                currentNode = currentNode.left;
            }else if(currentNode.key.compareTo(key)<0){
                parent = currentNode;
                currentNode = currentNode.right;
            }else{
                break;
            }
            if (currentNode ==null){
                return;
            }
        }

        if(currentNode.left != null && currentNode.right != null){

            Node oldNode = currentNode;
            parent = currentNode;
            currentNode = currentNode.left;
            while (currentNode.right != null){
                parent = currentNode;
                currentNode = currentNode.right;
            }

            oldNode.key = currentNode.key;
        }

        Node child = currentNode.left;
        if(child == null){
            child = currentNode.right;
        }
        if (parent== null){//Description is the root node
            this.root = child;
        }else if (parent.left == currentNode){
            parent.left = child;
        }else {
            parent.right = child;
        }
    }

    public Iterable<K> iterator() {
        Stack<Node> stack = new Stack<Node>();
        Queue<K> queue = new LinkedList<>();
        Node x = root;
        while (x != null || !stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            }
            else {
                x = stack.pop();
                queue.add(x.key);
                x = x.right;
            }
        }
        return queue;
    }
}