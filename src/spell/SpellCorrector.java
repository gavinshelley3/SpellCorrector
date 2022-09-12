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
//            Trie trie = new Trie();
            String yeah = "yeah";

            while (scanner.hasNext()) {
                String word = scanner.next();
                if (word != null) {
                    trie.add(word);
                }
            }

            System.out.println(trie.getWordCount());
            System.out.println(trie.getNodeCount());
            System.out.println(trie.toString());
            if (trie.find(yeah) == null) {
                System.out.println("\n\nWord not found :(");
            } else {
                System.out.println("\n\nWord found!");
            }
        }

    }

    public String suggestSimilarWord(String inputWord) {
//        Suggest a word from the Trie that most closely matches inputWord.



//        Call find(inputWord) on the trie
//        If it returns null, then call build1EditWords(inputWord) and find the word with the highest frequency
//        If it returns a word, then return that word

        // Build all words that are 1 edit away from inputWord
        if (trie.find(inputWord) == null) {
            Set<String> setFound = new HashSet<String>();
            Set<String> setTotal = new HashSet<String>();
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < inputWord.length() + 1; j++) {
                    String newWord = inputWord.substring(0, j) + (char) (i + 97) + inputWord.substring(j, inputWord.length());
                    setTotal.add(newWord);
                    if (trie.find(newWord) != null) {
                        setFound.add(newWord);
                    }
                }
            }
            if (!setFound.isEmpty()) {
                for (String s : setTotal) {
                    System.out.println(s);
                }
                return trie.bestWord(setFound);
            }
            else {
                if (!setTotal.isEmpty()) {
                    for (int k = 0; k < setTotal.size(); k++) {
                        for (int i = 0; i < 26; i++) {
                            for (int j = 0; j < setTotal.iterator().length(); j++) {
                                String newWord = s.substring(0, j) + (char) (i + 97) + s.substring(j, s.length());
                                setTotal.add(newWord);
                                if (trie.find(newWord) != null) {
                                    setFound.add(newWord);
                                }
                            }
                        }
                    }
                }
                if (!setFound.isEmpty()) {
                    for (String s : setTotal) {
                        System.out.println(s);
                    }
                    return trie.bestWord(setFound);
                }
                else {
                    return inputWord;
                }
            }
        }
        else {
            return inputWord;
        }
    }
}
