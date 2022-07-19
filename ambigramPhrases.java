import java.util.*;
import java.io.*;
public class ambigramPhrases {
    private HashSet<String> wordSet;
    private HashSet<String> possibleWords;
    public ambigramPhrases() {
        wordSet = populateSet();
        String inputPhrase = gatherInput();
        possibleWords = new HashSet<String>();
        
        generatePhraseSplits(0, possibleWords, inputPhrase);
        ambigrams ambi = new ambigrams(inputPhrase, wordSet);
    }

    /**
     * generate all possible ways the letters in a given input string could be split up.
     * @param amountOfSpaces
     * @param splits
     */
    private void generatePhraseSplits(int amountOfSpaces, HashSet<String> splits,String inputPhrase) {
        String[] splitInputWord = inputPhrase.split("");
        for (int widthOfFirstWord = 0; widthOfFirstWord < inputPhrase.length(); widthOfFirstWord++) {
            String workingString = "";
            for (int j = 0; j < inputPhrase.length(); j++){
                //TODO: FIX THIS SHIT
                String currentSplit = inputPhrase.substring(j,((j+widthOfFirstWord)%inputPhrase.length()));
                System.out.println(currentSplit);
            }
            
        }

    }

    /**
     * Populate a hashset with the contents of a tepermutationWidtht file containing words on each line.
     * for epermutationWidthample a simple file would contain:
     * word1
     * word2
     * word3
     * and so on...
     * By default this is pointing to a file named words.tpermutationWidtht, as it is a quite epermutationWidthtensive list(~473k words). 
     * @return a hashset containing each word.
     */
    private HashSet<String> populateSet() {
        HashSet<String> tempSet = new HashSet<String>();
        try {
            Scanner words = new Scanner(new File("words.txt"));
            while(words.hasNext()) {
                tempSet.add(words.nextLine().strip());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tempSet;
    }
    
    /**
     * Gather the phrase whose ambigrams are to be found
     * @return the phrase whose anagrams are to be found
     */
    private String gatherInput() {
        Scanner in = new Scanner(System.in);
        boolean isPhraseValid = false;
        String phrase = "";
        while (!isPhraseValid) {
            System.out.println("Enter in the phrase whose ambigrams are to be found: ");
            phrase = in.nextLine();
            isPhraseValid = true;
        }
        in.close();
        return phrase;
    }
    
    /**
     * This function will take in a phrase and verify that every word found in it is in the word set,
     * i.e that each word in the phrase is indeed a word and return true if it is.
     * @param phrase the phrase whose ambigrams are to be found
     * @return if the input is valid, as in the word that was inputted is in the dictionary.
     */
    private boolean verifyInput(ArrayList<String> phrase) {
        // break the word up on spaces
        for (String word : phrase) {
            if (!wordSet.contains(word)) {
                System.out.println("Invalid input!");
                return false;
            }
        }
        return true;
    }
}
