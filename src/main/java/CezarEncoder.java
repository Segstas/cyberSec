public class CezarEncoder  {

    public String encode(String beforeString, int shift) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char symbol : beforeString.toCharArray()) {
            if (symbol != ' ') {
                symbol = (char) (symbol - shift);
            }
            stringBuilder.append(symbol);
        }
        return stringBuilder.toString();
    }

    public String decode(String beforeString, int shift) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char symbol : beforeString.toCharArray()) {
            if (symbol != ' ') {
                symbol = (char) (symbol + shift);
            }
            stringBuilder.append(symbol);
        }
        return stringBuilder.toString();
    }
}
