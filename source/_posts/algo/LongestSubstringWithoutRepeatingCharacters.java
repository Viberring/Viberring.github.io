

public class LongestSubstring {

        public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring0("pwwkew"));        
    }

    public static  int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int N = s.length(), span = 0;
        
        for (int start=0, point=start+1; start < N;) {
            span = Math.max(point - start, span);       
            if (point == N) {
                start++;
                point = start+1;
                continue;
            }
            char alp = s.charAt(start);         
            boolean re = false;
            if (alp != s.charAt(point)) {
                for (int j=start+1; j < point; j++) {
                    if (s.charAt(j) == s.charAt(point)) {
                        re = true;
                    }
                }
                if (re) {
                    start++;
                    point = start+1;
                } else {
                    point++;
                }
            } else {
                start++;
                point = start + 1;
            }
        }
        return span;        
    }

    public static  int lengthOfLongestSubstring0(String s) {
        if (s == null || s.length() == 0) return 0;
        int N = s.length();
        
        Set<Character> window = new HashSet<>();
        int i = 0, j = 0, ans = 0;
        
        while (i < N && j < N) {            
            char a = s.charAt(j);
            if (!window.contains(a)) {
                j++;
                ans = Math.max(ans, j - i);
                window.add(a);
            } else {                
                window.remove(s.charAt(i++));
            }
        }

        return ans;
        
    }

    public static  int lengthOfLongestSubstring00(String s) {
        int N = s.length();

        Map<Character, Integer> window = new HashMap<>();
        int i = 0, ans = 0;

        for (int j = 0; j < N; j++) {
            if(window.containsKey(s.charAt(j))) {
                i = Math.max(i, window.get(s.charAt(j)));                
            }
            ans = Math.max(ans, j - i + 1);
            window.put(s.charAt(j), j + 1);            
        }

        return ans;
        
    }

    
}
