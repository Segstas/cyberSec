public class Main {
    public static void main(String[] args) {
        SecretCracker secretCracker = new SecretCracker();
        VijnerEncoder vijnerEncoder = new VijnerEncoder();
        String vik = vijnerEncoder.encode("УУУ я тебя люблю", "Вова");
        System.out.println(secretCracker.broodForceVijner(vik,"люблю", 1));




      //  System.out.println(secretCracker.broodForcePermutations(permutStr, "мама", 100));


    }
}
