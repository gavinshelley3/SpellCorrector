package spell;

public class Trie implements ITrie {
    private Node root;
    private int wordCount;
    private int nodeCount;

    public Trie() {
        root = new Node();
        wordCount = 0;
        nodeCount = 0;
    }

    public void add(String word) {
        addHelper(root, word);
    }

    public void addHelper(Node n, String word) {
        if (word.length() != 0) {
            char c = word.charAt(0);
            int ascii = (int) c - 97;
            Node n2 = new Node();
            if (n.getChildren()[ascii] == null) {
                n.getChildren()[ascii] = n2;
                setNodeCount();
                if (word.length() == 1) {
                    n2.incrementValue();
                    setWordCount();
                    return;
                } else {
                    String newWord = word.substring(1, word.length());
                    addHelper(n2, newWord);
                }
            }
            else {
                if (word.length() == 1) {
                    n.getChildren()[ascii].incrementValue();
                    if (n.getChildren()[ascii].getValue() == 1) {
                        setWordCount();
                    }
                }
                else  {
                    String newWord = word.substring(1, word.length());
                    addHelper(n.getChildren()[ascii], newWord);
                }
            }
        } else {
            return;
        }
    }


    public Node find(String word) {
        return findHelper(word, root);
    }

    public Node findHelper(String word, Node n) {
        if (word.length() != 0) {
            char c = word.charAt(0);
            int ascii = (int) c - 97;
            Node n2 = new Node();
            if (n.getChildren()[ascii] == null) {
                return null;
            } else {
                if (word.length() > 1) {
                    String newWord = word.substring(1, word.length());
                    return (findHelper(newWord, n.getChildren()[ascii]));
                } else {
                    if (n.getChildren()[ascii].getValue() == 0) {
                        return null;
                    } else {
                        return n.getChildren()[ascii];
                    }
                }
            }
        }  else {
            return null;
        }
    }


    public int getWordCount() {
        return this.wordCount;
    }

    public void setWordCount() {
        this.wordCount++;
    }

    public int getNodeCount() {
        return this.nodeCount;
    }

    public void setNodeCount() {
        this.nodeCount++;
    }

    @Override
    public int hashCode() {
//      wordCount * nodeCount * # of first non-null Node in Trie
        return (root.getValue() << 39);
    }

    @Override
    public boolean equals(Object obj) {

//        Check for null
//
        Trie other = (Trie)obj;
//        return equalsHelper(Node n1, Node n2);
        return true;
    }

    void equalsHelper(Node n1, Node n2) {
//        for (int i = 0; i < t.getChildren(); ++i) {
//
//            Node child = (Node)t.getChildren()[i];
//            if (child != null) {
//                if (n1.count != n2.count) {
//                    return;
//                } else if (n1.children != n2.getChildren()) {
//                    return;
//                }
//            }
//        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();                     //        1. Traverse Trie (PreOrder Traversal)
        StringBuilder currentWord = new StringBuilder();                //        2. Find end Node of first word
        toStringHelper(root, currentWord, output);                      //          a. Print out word and \n
        return output.toString();                                       //         3. Back out of Trie to next word
    }

    void toStringHelper(Node n, StringBuilder currentWord, StringBuilder output) {
        if (n.getValue() > 0) {                                         //        Output t's word if t's count != 0
            output.append(currentWord.toString() + "\n");
        }

        for (int i = 0; i < n.getChildren().length; ++i) {              //        Recurse on children to output their words
            Node child = (Node)n.getChildren()[i];
            if (child != null) {
                char childChar = (char) ('a' + i);
                currentWord.append(childChar);
                toStringHelper(child, currentWord, output);
                currentWord.deleteCharAt(currentWord.length() - 1);
            }
        }
    }
}