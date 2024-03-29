//https://leetcode.com/problems/coin-change/description/
//https://www.naukri.com/code360/problems/minimum-elements_3843091?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM

import java.util.*;
import java.io.*;

public class Solution {
    public static int helper(int idx, int x, int[] nums, int[][] dp) {
        if (idx == 0) {
            if (x % nums[0] == 0)
                return x / nums[0];
            else
                return (int) Math.pow(10, 9);
        }
        if (dp[idx][x] != -1)
            return dp[idx][x];
        int npick = 0 + helper(idx - 1, x, nums, dp);
        int pick = (int) Math.pow(10, 9);
        if (nums[idx] <= x) {
            pick = 1 + helper(idx, x - nums[idx], nums, dp);
        }
        dp[idx][x] = Math.min(pick, npick);
        return dp[idx][x];
    }

    public static int minimumElements(int nums[], int x) {

        Write your code here..
        int n = nums.length;
        int[][] dp = new int[n][x + 1];
        for(int i=0;i<n;i++){
        Arrays.fill(dp[i],-1);
        }
        return (helper(n-1,x,nums,dp)==(int)Math.pow(10,9)?-1:helper(n-1,x,nums,dp));

      
      
          int n = nums.length;
        int[][] dp = new int[n][x + 1];
        for (int i = 0; i <= x; i++) {
            if (i % nums[0] == 0) {
                dp[0][i] = i / nums[0];
            } else {
                dp[0][i] = (int) Math.pow(10, 9);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= x; j++) {
                int npick = 0 + dp[i-1][j];
                int pick = (int) Math.pow(10, 9);
                if (nums[i] <= j) {
                    pick = 1 +  dp[i][j-nums[i]];
                }
                dp[i][j] = Math.min(pick, npick);
            }
        }

        return (dp[n-1][x]>=(int)Math.pow(10,9)?-1:dp[n-1][x]);
    }

           int n = nums.length;
        int[] prev = new int[x + 1];
        for (int i = 0; i <= x; i++) {
            if (i % nums[0] == 0) {
                prev[i] = i / nums[0];
            } else {
                prev[i] = (int) Math.pow(10, 9);
            }
        }

        for (int i = 1; i < n; i++) {
               int[] temp = new int[x + 1];
            for (int j = 0; j <= x; j++) {
                int npick = 0 + prev[j];
                int pick = (int) Math.pow(10, 9);
                if (nums[i] <= j) {
                    pick = 1 +  temp[j-nums[i]];
                }
                temp[j] = Math.min(pick, npick);
            }

            prev=temp;
        }

        return (prev[x]>=(int)Math.pow(10,9)?-1:prev[x]);
    }

}
