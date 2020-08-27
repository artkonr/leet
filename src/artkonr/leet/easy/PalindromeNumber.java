package artkonr.leet.easy;

public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        // fast-track: if in [0; 10) -> true
        if (x >= 0 && x < 10) return true;

        // fast-track: if x is negative -> false
        if (x < 0) return false;

        // fast-track: if x ends with 0 -> false
        if (x % 10 == 0) return false;

        int ndigits = countDigits(x);
        boolean res = false;
        for (int i = 0; i < ndigits; i++) {
            int div = (int) Math.pow(10, ndigits - i - 2);
            int cur = x % div;
            int inv = x / (div * 10);
            if (cur != inv) break;
            res = true;
        }

        return res;

    }

    public static int countDigits(int x) {
        int res = 1;
        int cur = x / 10;
        while (cur != 0) {
            res++;
            cur = cur / 10;
        }
        return res;
    }

}
