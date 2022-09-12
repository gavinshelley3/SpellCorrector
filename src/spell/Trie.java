package spell;

import java.util.HashSet;
import java.util.Set;

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
            if (n.getChildren()[ascii] == null) {   // If the node is null, create a new node
                n.getChildren()[ascii] = n2;
                setNodeCount();
                if (word.length() == 1) {       // If the word is only one character, increment the count
                    n2.incrementValue();
                    setWordCount();
                    return;
                } else {                // If the word is longer than one character, call addHelper again
                    String newWord = word.substring(1, word.length());
                    addHelper(n2, newWord);
                }
            }
            else {          // If the node already exists
                if (word.length() == 1) {           // If the word is only one character, increment the count
                    n.getChildren()[ascii].incrementValue();
                    if (n.getChildren()[ascii].getValue() == 1) {   // If the count was 0, increment the word count
                        setWordCount();
                    }
                }
                else  {             // If the word is longer than one character, call addHelper again
                    String newWord = word.substring(1, word.length());
                    addHelper(n.getChildren()[ascii], newWord);
                }
            }
        } else {        // If the word is empty, return
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

    public String bestWord(Set<String> set) {
        if (!set.isEmpty()) {
            int bestCount = 0;
            String bestWord = set.iterator().next();
            for (String s : set) {
                Node n = new Node();
                n = find(s);
                if (n.getValue() > bestCount) {
                    bestCount = n.getValue();
                    bestWord = s;
                }
            }
            return bestWord;
        }
        else {
            return "";
        }
    }


    public String build1EditWords(String inputWord) {
        // Build all words that are 1 edit away from inputWord
        Set<String> setFound = new HashSet<String>();
        Set<String> setTotal = new HashSet<String>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < inputWord.length(); j++) {
                String newWord = inputWord.substring(0, j) + (char) (i + 97) + inputWord.substring(j, inputWord.length());
                setTotal.add(newWord);
                if (find(newWord) != null) {
                    setFound.add(newWord);
                }
            }
        }
        for (String s : setFound) {
            System.out.println(s);
        }
        // Call find on each word
        // If the word is found, add it to a set
        // Return the word with the highest frequency
        // Return the set
        return inputWord;
    }

    public String build2EditWords(String inputWord) {
        // Build all words that are 2 edits away from inputWord
        // Call find on each word
        // If the word is found, add it to a list
        // Return the list
        return inputWord;
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
//      (wordCount * nodeCount) + nodeCount * # of first non-null Node in Trie
        for (int i = 0; i < 26; i++) {
            if (root.getChildren()[i] != null) {
                if (i == 0) {
                    return ((wordCount * nodeCount) + nodeCount) * 26;
                } else {
                    return ((wordCount * nodeCount) + nodeCount) * i;
                }
            }
        }
        return (wordCount * nodeCount * 26);
    }

    @Override
    public boolean equals(Object obj) {

//        Check for null
        if (obj == null) {
            return false;
        }
        else {
            Trie other = (Trie) obj;
            return equalsHelper(root, other.root);
        }
    }

    public boolean equalsHelper(Node n1, Node n2) {

        for (int i = 0; i < n1.getChildren().length; i++) {
            if (n1.getChildren()[i] != null && n2.getChildren()[i] != null) {
                    if (n1.getChildren()[i].getValue() == n2.getChildren()[i].getValue()) {
                        equalsHelper(n1.getChildren()[i], n2.getChildren()[i]);
                    } else {
                        return false;
                    }
            } else if (n1.getChildren()[i] == null && n2.getChildren()[i] == null) {
                continue;
            } else {
                return false;
            }
        }
        return true;
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
