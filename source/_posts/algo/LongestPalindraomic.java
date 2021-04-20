
public class Longestpalindraomic {

    public static void main(String[] args) {
        //  System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println("===============");                    
        System.out.println(longestPalindrome("ac"));
        
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";        
        int N = s.length();

        String ans = "";
        int max = 0;

        boolean[][] dp = new boolean[N][N];

        for (int i=0; i < N; i++) {
            for (int j=0; j <= i; j++) {

                boolean judge = s.charAt(i) == s.charAt(j);

                dp[j][i] = i - j > 2 ? dp[j+1][i-1] && judge : judge;

                if (dp[j][i] && i - j + 1 > max) {
                    max = i - j + 1;
                    ans = s.substring(j, i+1);
                }
                
            }
        }
        
        
        return ans;
        
    }

    public static String longestPalindrome0(String s) {
        if (s == null || s.length() == 0) return "";        
        int N = s.length();
        
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
        
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;

        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }

        return R - L - 1;
        
    }
    
    
}
