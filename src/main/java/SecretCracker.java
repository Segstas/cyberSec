import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SecretCracker {

    private VijnerEncoder vijnerEncoder = new VijnerEncoder();
    private CezarEncoder cezarEncoder = new CezarEncoder();
    private PermutationsEncoder permutationsEncoder = new PermutationsEncoder();

    private boolean isDifferent(char[] arr) {
        char[] copied = arr.clone();
        Arrays.sort(copied);
        for (int i = 1; i < copied.length; i++) {
            if (copied[i] == copied[i - 1]) {
                return false;
            }
        }
        return true;

    }

    public void fillInCombinations(char[] combination, List<String> combinations, int amountOfSymbols) {
        if (amountOfSymbols == 1) {
            for (int i = 0; i < 64; i++) {
                combinations.add(new String(combination));

/*                if (isDifferent(combination)) {
                    combinations.add(new String(combination));
                }*/
                ++combination[combination.length - amountOfSymbols];
            }
            combination[combination.length - amountOfSymbols] = 'А';
        } else {
            for (int i = 0; i < 64; i++) {
                fillInCombinations(combination, combinations, amountOfSymbols - 1);
                ++combination[combination.length - amountOfSymbols];
            }
            combination[combination.length - amountOfSymbols] = 'А';
        }
    }

    private List<String> getCombinations(int amountOfSymbols) {
        List<String> list = new LinkedList<>();
        char[] combination = new char[amountOfSymbols];
        for (int i = 0; i < amountOfSymbols; i++) {
            combination[i] = 'А';
        }
        fillInCombinations(combination, list, amountOfSymbols);
        return list;
    }

    public String broodForceVijner(String beforeString, String outPart, int length) {
        List <String> list = getCombinations(length);
        for (String maybeKey : list) {
            if  (vijnerEncoder.decode(beforeString, maybeKey).contains(outPart)){
                return maybeKey;
            }
        }
        return "";
    }

    public String broodForceCesar(String beforeString, String outPart, int max) {

        for (int i = 0; i < max; i++) {
            if  (cezarEncoder.decode(beforeString, i).contains(outPart)){
                return  Integer.toString(i);
            }
        }
        return "";
    }

    public String broodForcePermutations(String beforeString, String outPart, int max) {

        for (int i = 0; i < max; i++) {
            if  (permutationsEncoder.decode(beforeString, i).contains(outPart)){
                return  Integer.toString(i);
            }
        }
        return "";
    }
}
