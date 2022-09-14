package spell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SpellCorrector implements ISpellCorrector {

    private Trie trie = new Trie();

    public void useDictionary(String dictionaryFileName) throws IOException {
        // Throw ioexception if file not found
        trie = new Trie();
        File file = new File(dictionaryFileName);
        if (dictionaryFileName == null || !file.exists() || (dictionaryFileName.length() == 0)) {
            throw new IOException();
        } else {
            // Create trie
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                if (word != null) {
                    trie.add(word);
                }
            }
        }
    }

    public String suggestSimilarWord(String inputWord) {
//      Suggest a word from the Trie that most closely matches inputWord.
//      Call trie.find(inputWord) on the trie
//      If the word is found, return the word
//      If it returns a word, then return that word

        Set<String> set = new HashSet<String>();
        String word = inputWord.toLowerCase();
        set.add(word);
        Set<String> setFound = new HashSet<String>();
        Set<String> setTotal = new HashSet<String>();
        for (String s : set) {
            if (trie.find(s) == null) {  // If the word is not found
                for (int i = 0; i < 26; i++) {  // For each letter in the alphabet
                    for (int j = 0; j < s.length() + 1; j++) {  // For each position in the word
                        if (s.length() == 0) {
//                            add
//                            delete
//                            replace
//                            transpose
                        }
                        else if (s.length() == 1) {
//                            add (abc, s)
                            String addWord = (char) (i + 97) + s;
                            setTotal.add(addWord);
                            if (trie.find(addWord) != null) {
                                setFound.add(addWord);
                            }
//                            add2 (s, abc)
                            String addWord2 = s + (char) (i + 97);
                            setTotal.add(addWord2);
                            if (trie.find(addWord2) != null) {
                                setFound.add(addWord2);
                            }
//                            delete
//                            replace
//                            transpose
                        }
                        else if (j == s.length() - 1 || j == s.length()) {
//                            add
                            String addWord = s.substring(0, j) + (char) (i + 97) + s.substring(j, s.length());
                            setTotal.add(addWord);
                            if (trie.find(addWord) != null) {
                                setFound.add(addWord);
                            }
//                            delete
                            String deleteWord = s.substring(0, j);
                            setTotal.add(deleteWord);
                            if (trie.find(deleteWord) != null) {
                                setFound.add(deleteWord);
                            }
//                            replace
                            String replaceWord = s.substring(0, j) + (char) (i + 97);
                            setTotal.add(replaceWord);
                            if (trie.find(replaceWord) != null) {
                                setFound.add(replaceWord);
                            }
//                            transpose
                        }
                        else if (j == s.length() - 2) {
//                            add
                            String addWord = s.substring(0, j) + (char) (i + 97) + s.substring(j, s.length());
                            setTotal.add(addWord);
                            if (trie.find(addWord) != null) {
                                setFound.add(addWord);
                            }
//                            delete
                            String deleteWord = s.substring(0, j) + s.substring(j + 1, s.length());
                            setTotal.add(deleteWord);
                            if (trie.find(deleteWord) != null) {
                                setFound.add(deleteWord);
                            }
//                            replace
                            String replaceWord = s.substring(0, j) + (char) (i + 97) + s.substring(j + 1, s.length());
                            setTotal.add(replaceWord);
                            if (trie.find(replaceWord) != null) {
                                setFound.add(replaceWord);
                            }
//                            transpose
                            String transposeWord = s.substring(0, j) + s.substring(j + 1, j + 2) + s.substring(j, j + 1) + s.substring(j + 2, s.length());
                            setTotal.add(transposeWord);
                            if (trie.find(transposeWord) != null) {
                                setFound.add(transposeWord);
                            }
                        }
                        else {
//                            add
                            String addWord = s.substring(0, j) + (char) (i + 97) + s.substring(j, s.length());
                            setTotal.add(addWord);
                            if (trie.find(addWord) != null) {
                                setFound.add(addWord);
                            }
//                            delete
                            String deleteWord = s.substring(0, j) + s.substring(j + 1, s.length());
                            setTotal.add(deleteWord);
                            if (trie.find(deleteWord) != null) {
                                setFound.add(deleteWord);
                            }
//                            replace
                            String replaceWord = s.substring(0, j) + (char) (i + 97) + s.substring(j + 1, s.length());
                            setTotal.add(replaceWord);
                            if (trie.find(replaceWord) != null) {
                                setFound.add(replaceWord);
                            }
//                            transpose
                            String transposeWord = s.substring(0, j) + s.substring(j + 1, j + 2) + s.substring(j, j + 1) + s.substring(j + 2, s.length());
                            setTotal.add(transposeWord);
                            if (trie.find(transposeWord) != null) {
                                setFound.add(transposeWord);
                            }
                        }
                    }
                }
                if (!setFound.isEmpty()) {  // If the set of found words is not empty
                    if (trie.bestWord(setFound) != null) {   // If the best word is not empty
                        return trie.bestWord(setFound);  // Return the best word
                    }
                } else {    // If the best word is empty
//              Build all words that are 1 edit away from inputWord
//              Call trie.find on each word
                    if (!setTotal.isEmpty()) {
                        Set<String> setTotalTwo = new HashSet<String>();
                        for (String st : setTotal) {    // If the set of total words is not empty
                            for (int i = 0; i < 26; i++) {  // For each letter in the alphabet
                                for (int j = 0; j < st.length() + 1; j++) {
                                    if (st.length() == 0) {
//                                      add
//                                      delete
//                                      replace
//                                      transpose
                                    }
                                    else if (st.length() == 1) {
//                                      add (abc, s)
                                        String addWord = (char) (i + 97) + s;
                                        setTotalTwo.add(addWord);
                                        if (trie.find(addWord) != null) {
                                            setFound.add(addWord);
                                        }
//                                      add2 (s, abc)
                                        String addWord2 = s + (char) (i + 97);
                                        setTotalTwo.add(addWord2);
                                        if (trie.find(addWord2) != null) {
                                            setFound.add(addWord2);
                                        }
//                                      delete
//                                      replace
//                                      transpose
                                    }
                                    else if (j == st.length() - 1 || j == st.length()) {
//                                      add
                                        String addWord = st.substring(0, j) + (char) (i + 97) + st.substring(j, st.length());
                                        setTotalTwo.add(addWord);
                                        if (trie.find(addWord) != null) {
                                            setFound.add(addWord);
                                        }
//                                      delete
                                        String deleteWord = st.substring(0, j);
                                        setTotalTwo.add(deleteWord);
                                        if (trie.find(deleteWord) != null) {
                                            setFound.add(deleteWord);
                                        }
//                                      replace
                                        String replaceWord = st.substring(0, j) + (char) (i + 97);
                                        setTotalTwo.add(replaceWord);
                                        if (trie.find(replaceWord) != null) {
                                            setFound.add(replaceWord);
                                        }
//                                      transpose
                                    }
                                    else if (j == st.length() - 2) {
//                                      add
                                        String addWord = st.substring(0, j) + (char) (i + 97) + st.substring(j, st.length());
                                        setTotalTwo.add(addWord);
                                        if (trie.find(addWord) != null) {
                                            setFound.add(addWord);
                                        }
//                                      delete
                                        String deleteWord = st.substring(0, j) + st.substring(j + 1, st.length());
                                        setTotalTwo.add(deleteWord);
                                        if (trie.find(deleteWord) != null) {
                                            setFound.add(deleteWord);
                                        }
//                                      replace
                                        String replaceWord = st.substring(0, j) + (char) (i + 97) + st.substring(j + 1, st.length());
                                        setTotalTwo.add(replaceWord);
                                        if (trie.find(replaceWord) != null) {
                                            setFound.add(replaceWord);
                                        }
//                                      transpose
                                        String transposeWord = st.substring(0, j) + st.substring(j + 1, j + 2) + st.substring(j, j + 1) + st.substring(j + 2, st.length());
                                        setTotalTwo.add(transposeWord);
                                        if (trie.find(transposeWord) != null) {
                                            setFound.add(transposeWord);
                                        }
                                    }
                                    else {
//                                      add
                                        String addWord = st.substring(0, j) + (char) (i + 97) + st.substring(j, st.length());
                                        setTotalTwo.add(addWord);
                                        if (trie.find(addWord) != null) {
                                            setFound.add(addWord);
                                        }
//                                      delete
                                        String deleteWord = st.substring(0, j) + st.substring(j + 1, st.length());
                                        setTotalTwo.add(deleteWord);
                                        if (trie.find(deleteWord) != null) {
                                            setFound.add(deleteWord);
                                        }
//                                      replace
                                        String replaceWord = st.substring(0, j) + (char) (i + 97) + st.substring(j + 1, st.length());
                                        setTotalTwo.add(replaceWord);
                                        if (trie.find(replaceWord) != null) {
                                            setFound.add(replaceWord);
                                        }
//                                      transpose
                                        String transposeWord = st.substring(0, j) + st.substring(j + 1, j + 2) + st.substring(j, j + 1) + st.substring(j + 2, st.length());
                                        setTotalTwo.add(transposeWord);
                                        if (trie.find(transposeWord) != null) {
                                            setFound.add(transposeWord);
                                        }
                                    }
                                }
                            }
                        }
                        if (!setFound.isEmpty()) {  // If the set of found words is not empty
                            return trie.bestWord(setFound);  // Return the best word
                        } else {    // If the set of found words is empty
                            return null;    // Return the input word
                        }
                    } else {    // If the set of total words is empty
                        return null;    // Return the input word
                    }
                }
            } else {    // If the word is found
                return s;   // Return the input word
            }
        }
        return null;
    }
}
