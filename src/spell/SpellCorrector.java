package spell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {

    public void useDictionary(String dictionaryFileName) throws IOException {
        // Throw ioexception if file not found
        File file = new File(dictionaryFileName);
        if (dictionaryFileName == null || !file.exists() || (dictionaryFileName.length() == 0)) {
            throw new IOException();
        } else {
            // Create trie
            Scanner scanner = new Scanner(file);
            Trie trie = new Trie();
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
//        Trie root.find(inputWord);

//        If the word is found, return the word
//        If the word is not found, return null




        return inputWord;
    }
}
