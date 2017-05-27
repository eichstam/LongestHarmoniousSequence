import java.util.HashMap;
import java.util.Map;

/**
594. Longest Harmonious Subsequence

We define a harmonious array is an array where the difference between its
maximum value and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest
harmonious subsequence among all its possible subsequences.

Example 1:

Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].

Note: The length of the input array will not exceed 20,000. 
*/

public class Solution {
    public int findLHS(int[] nums) {
      int maxLength = 0;
      int a = 0;
      int b1 = 0;
      int b2 = 0;
      
      if(nums.length <= 1) return 0;
      for(int i = 0; i < nums.length; i++) {
        a = nums[i];  // first sequence value
        b1 = a - 1; // second sequence value, either +1 or -1
        b2 = a + 1;
        int cnt_a = 0;
        int cnt_b1 = 0; 
        int cnt_b2 = 0;
        int j = i;
        while(j < nums.length) {
            if(nums[j] == a) {
              cnt_a++;
            } else if(nums[j] == b1) {
              cnt_b1++;
            } else if (nums[j] == b2) {
              cnt_b2++;
            }
            j++;
        }
        if(cnt_a > 0 && cnt_b1 > 0) {
          if(cnt_a + cnt_b1 > maxLength) {
            maxLength = cnt_a + cnt_b1;
          }
        }
        if(cnt_a > 0 && cnt_b2 > 0) {
          if(cnt_a + cnt_b2 > maxLength) {
            maxLength = cnt_a + cnt_b2;
          }
        }
      }
      return maxLength;
    }

    public int findLHS2(int[] nums) {
      Map<Long, Integer> map = new HashMap<>();
      for (long num : nums) {
          map.put(num, map.getOrDefault(num, 0) + 1);
      }
      int result = 0;
      for (long key : map.keySet()) {
          if (map.containsKey(key + 1)) {
              result = Math.max(result, map.get(key + 1) + map.get(key));
          }
      }
      return result;
    }
    
    public static void main(String[] args) {
      Solution s = new Solution();
      int nums0[] = {1,3,2,2,5,2,3,7};
      int nums1[] = {3};
      int nums2[] = {3,5};
      int nums3[] = {1,1,1,1};
      int nums4[] = {2,2,2,2,2,2,2,3,1,0,0,0,3,1,-1,0,1,1,0,0,1,1,2,2,2,0,1,2,2,3,2}; // expected 20
      int nums5[] = {3999932,14095060,-38450324,-6006632,-6052261,34210012,5636226,-301414,19348094,6297539};
      int nums6[] = {-2147483648 , 2147483647}; //2147483647 + 1 = -2147483648 , and we don't want that.
      int tests[][] = {nums0, nums1, nums2, nums3, nums4, nums5, nums6};
      
      for(int i = 0; i < tests.length; i++) {
        System.out.print("Input: ");
        for(int j = 0; j < tests[i].length; j++) {
          System.out.print(tests[i][j] + " ");
        }
        System.out.println();
        System.out.println("Output: " + s.findLHS2(tests[i]));
      }
    }
}
