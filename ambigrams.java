/**
 * @author hayden coffing
 * 2021
 * ambigrams.java
 * create a word perfect ambigram, as in all letters are used in the input string.
 */
import java.util.*;

public class ambigrams {
    private Map<String,String> rotationMap;
    private HashSet<String> wordsAlreadyFound;
    private HashSet<String> words; 

    public ambigrams(String wordIn, HashSet<String> wordSet) {
        wordsAlreadyFound = new HashSet<String>();
        rotationMap = populateMap();
        this.words = wordSet;
        formAnagrams(wordIn);
    }

    /**
     * Populate the rotation map with possible letter rotations, 
     * if the letter has no possible rotations, it will be mapped to itself.
     * For example, 'd' will map to 'p' because it d rotated 180 degrees looks like p,
     * while 'o' maps to 'o' as it has prefect rotational symetry.
     * @return a map contianing the possible permutations of each letter.
     */
    private Map<String,String> populateMap() {
        Map<String,String> tempMap = new HashMap<String,String>();
        // define a map with temporary values.
        for (char let = 'a'; let <= 'z'; let++) {
            String curr = let+"";
            tempMap.put(curr,curr);
        }

        // define the special rotations
        // note the symmetry in these pairs, d maps to p, p maps to d.
        tempMap.put("d", "p");
        tempMap.put("p", "d");
        // b to q
        tempMap.put("b","q");
        tempMap.put("q","b");
        //u to n **this might not work for every font**
        tempMap.put("u","n");
        tempMap.put("n","u");
        // w to m 
        tempMap.put("w","m");
        tempMap.put("m","w");

        return tempMap;
    }

    
    
    /**
     * the driver function that does the setup for the helper function
     * @param input the string that the user inputted. 
     */
    private void formAnagrams(String input) {
        // populate an arraylist of strings with each element being a single letter of the input word.
        for (String word : input.split(" ")) {
            String[] splitInput = word.split("");
            ArrayList<String> splitList = new ArrayList<String>(Arrays.asList(splitInput));
            //generate output
            ArrayList<String> output = new ArrayList<String>();
            System.out.println("Anagrams found:");
            anagramHelper(splitList,output,0,false,input.length());
            anagramHelper(splitList,output,0,true,input.length());
        }
        
    }

    /**
     * The function that actually forms the ambigrams
     * @param letters the letters from the given input word.
     * @param outputSoFar the running output of the function
     * @param index the index of the word, where 0 is the first letter and word.length-1 is the last index.
     * @param inverted if we are using the normal dict, as in p -> p or the inverted one, p -> d and so on
     * @param desiredLength the desired length of the word. 
     */
    private void anagramHelper(ArrayList<String> letters, ArrayList<String> outputSoFar, int index, boolean inverted, int desiredLength) {
        if (index == desiredLength) {
            checkOutput(outputSoFar);
        } else {
            // go through each possibility 
            for (int i = 0; i < letters.size()+index; i++) {
                // pull a letter from the word
                String letter = letters.remove(0);
                // rotated case
                if (inverted) {
                    letter = rotationMap.get(letter);
                }
                // add it to the output so far
                outputSoFar.add(letter);
                // recurse on the remaining letters
                anagramHelper(letters, outputSoFar, ++index,inverted,desiredLength);
                // undo 
                index--;
                outputSoFar.remove(letter);
                letters.add(letter);
            }
        }
    }

    /**
     * This function will check an arraylist of character long strings and checks if the combination of them
     * is actually a word in the dictionary.
     * @param possibleWord An arraylist of character long strings, a possible ambigram
     */
    private void checkOutput(ArrayList<String> possibleWord) {
        String wordStr = "";
        for (String letter : possibleWord) {
            wordStr += letter;
        }
        if (wordsAlreadyFound.contains(wordStr)) {
            return;
        }
        if (words.contains(wordStr)) {
            wordsAlreadyFound.add(wordStr);
            System.out.println(wordStr);
        }
    }
}
