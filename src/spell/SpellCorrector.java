package spell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {

    public void useDictionary(String dictionaryFileName) throws IOException {
        File file = new File(dictionaryFileName);
        Scanner scanner = new Scanner(file);
        Trie trie = new Trie();
        String yeah = "yeah";

        while(scanner.hasNext()) {
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

    public String suggestSimilarWord(String inputWord) {

        return inputWord;
    }
}
