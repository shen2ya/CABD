public class Obfuscator {
    private static String inpString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static String outString = "kiMd6GvVlzUPwjg9734pSXbEqC5ZFOQxHyfIN8Bn0LoTA2rKaRhsmJtce1WYDu";


    public static String obf(String s) {
        char[] res = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            int ind = inpString.indexOf(chr);
            res[i] = ind != -1 ? outString.charAt(ind) : chr;
        }
        return new String(res);
    }

    public static String revert(String s) {
        char[] res = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            int ind = outString.indexOf(chr);
            res[i] = ind != -1 ? inpString.charAt(ind) : chr;
        }
        return new String(res);
    }
}
