package spell;

public class Node implements INode {
    private int count;
    private Node[] children = new Node[26];

    public Node() {
        count = 0;
        Node[] children = new Node[26];
    }

    public int getValue() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementValue() {
        this.count++;
    }


    public Node[] getChildren() {
        return this.children;
    }

    public void setChildren(int ascii, Node node1) {
        this.children[ascii] = node1;
    }
}
