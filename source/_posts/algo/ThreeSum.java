
import java.util.*;





public class ThreeSum {


    public static void main(String[] args) {
        int[] input = new int[]{-1,0,1,2,-1,-4}; // expected: [[-1,-1,2],[-1,0,1]]
        //System.out.println(threeSum(input));
        System.out.println(solution(input));        
        System.out.println("=======================");        
        input = new int[]{-2,0,1,1,2};
        System.out.println(solution(input));                
        // System.out.println(threeSum(input));
        // System.out.println("=======================");                
        input = new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        System.out.println(solution(input));                
        // System.out.println(threeSum(input));        
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int N = nums.length;
        
        List<List<Integer>> result = new ArrayList<>();
        if(N < 3) return result;
        
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        
        for (int i=0; i < N - 1; i++) {
            int a = nums[i];
            System.out.println("*** a: " + a);
            
            int k = N - 1;
            for (int m = i+1, n = N-1; m < n; ) { 
                int b = nums[m], c = nums[n];
                System.out.println("m: " + m + " n : " + n);
                System.out.println("b: " + b + " c : " + c);                
                if (a + b + c > 0) n--;
                if (a + b + c < 0) m++;
                if (a + b + c == 0) {             
                    List<Integer> o = new ArrayList<>();
                    o.add(a);o.add(b);o.add(c);
                    if(!result.contains(o)) {
                        result.add(o);
                    }
                    n--;
                    m++;
                }
            }
        }
        return result;
        
    }

    private static List<List<Integer>> solution(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        
        int N = nums.length;

        for (int i = 0; i < N; i++) {
            int a = nums[i];
            int lo = i + 1, hi = N - 1;
            while (lo < hi) {
                int b = nums[lo], c = nums[hi];
                if (a + b + c == 0) {
                    List<Integer> o = new ArrayList<>();
                    o.add(a);o.add(b);o.add(c);
                    result.add(o);
                    while(lo < hi && nums[lo] == nums[lo+1]) lo++;
                    while(hi > lo && nums[hi] == nums[hi-1]) hi--;
                    lo++;
                    hi--;
                }
                else if (a + b + c > 0) hi--;
                else if (a + b + c < 0) lo++;
            }
            while(i < N - 1 && nums[i] == nums[i+1]) i++;
        }
        return result;
    }
}
