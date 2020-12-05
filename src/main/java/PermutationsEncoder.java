import java.util.ArrayList;

public class PermutationsEncoder {

    public String encode(String beforeStr, int key) {
        int size = beforeStr.length();
        int capacity;
        if (key == 0 || beforeStr.length() == 0) {
            capacity = 1;
        } else {
            capacity = size / key;
        }


        ArrayList<StringBuilder> stringBuilders = new ArrayList<>();
        for (int j = 0; j < key; j++) {
            stringBuilders.add(new StringBuilder());
        }
        char srcStr[] = beforeStr.toCharArray();
        StringBuilder s;
        for (int j = 0; j < size; j++) {
            s = stringBuilders.get(j % stringBuilders.size());
            s.append(srcStr[j]);
        }
        for (StringBuilder stringBuilder : stringBuilders) {
            if (stringBuilder.length() < capacity + 1) {
                stringBuilder.append('*');
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder stringBuilder1 : stringBuilders) {
            stringBuilder.append(stringBuilder1);
        }
        return stringBuilder.toString();
    }

    public String decode(String afterStr, int key) {
        int size = afterStr.length();
        int capacity;
        if (key == 0 || afterStr.length() == 0) {
            capacity = 1;
        } else {
            capacity = size / key;
        }

        ArrayList<ArrayList<String>> stringBuilders = new ArrayList<>();
        for (int j = 0; j < capacity; j++) {
            stringBuilders.add(new ArrayList<String>());
        }
        char srcStr[] = afterStr.toCharArray();
        ArrayList<String> collector = new ArrayList<>();
        for (char c : afterStr.toCharArray()) {
            collector.add(String.valueOf(c));
        }
        int subCounter = 0;
        int builderCounter = 0;

        ArrayList s;
        for (int j = 0; j < size; j++) {
            if (subCounter == capacity) {
                subCounter = 0;
                builderCounter = 0;
            }
            s = stringBuilders.get(builderCounter);
            if (!collector.get(j).equals("*")) {
                s.add(collector.get(j));
            }
            subCounter++;
            builderCounter++;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < stringBuilders.size(); i++) {
            for (int j = 0; j < stringBuilders.get(i).size(); j++) {
                stringBuilder.append(stringBuilders.get(i).get(j));
            }

        }
        return stringBuilder.toString();
    }

}
