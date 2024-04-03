//https://www.naukri.com/code360/problems/best-time-to-buy-and-sell-stock-iii_1071012?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
import java.util.*;

public class Solution {
    public static int helper(int idx, int buy, int cap, int[] prices, int[][][] dp) {
        if (cap == 0)
            return 0;
        if (idx == prices.length)
            return 0;
        if (dp[idx][buy][cap] != -1)
            return dp[idx][buy][cap];
        int profit = 0;
        if (buy == 1) {
            profit = Math.max(-prices[idx] + helper(idx + 1, 0, cap, prices, dp),
                    0 + helper(idx + 1, 1, cap, prices, dp));
        } else {
            profit = Math.max(prices[idx] + helper(idx + 1, 1, cap - 1, prices, dp),
                    helper(idx + 1, 0, cap, prices, dp));
        }

        dp[idx][buy][cap] = profit;
        return profit;
    }

    public static int maxProfit(int[] prices) {
        // Write your code here.
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];
        for(int i=0;i<n;i++){
        Arrays.fill(dp[i][0],-1);
        Arrays.fill(dp[i][1],-1);
        }

        return helper(0,1,2,prices,dp);



        for (int i = 1; i <= n; i++) {
            dp[i][0][0] = 0;
            dp[i][1][0] = 0;
        }

        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 2; j++) {
                dp[n][i][j] = 0;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) {
                for (int k = 1; k <= 2; k++) {
                    int profit = 0;
                    if (j == 1) {
                        profit = Math.max(-prices[i] +dp[i+1][0][k],
                                dp[i+1][1][k] );
                    } else {
                        profit = Math.max(prices[i] +dp[i+1][1][k-1],
                                dp[i+1][0][k]);
                    }

                    dp[i][j][k] = profit;
                }
            }
        }
      return dp[0][1][2];

      //Space Optimization 


    int[][] next=new int[2][3];
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 2; j++) {
                next[i][j] = 0;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
             int[][] curr=new int[2][3];
             curr[0][0] = 0;
            curr[1][0] = 0;
            for (int j = 0; j <= 1; j++) {
                for (int k = 1; k <= 2; k++) {
                    int profit = 0;
                    if (j == 1) {
                        profit = Math.max(-prices[i] +next[0][k],
                                next[1][k] );
                    } else {
                        profit = Math.max(prices[i] +next[1][k-1],
                                next[0][k]);
                    }

                    curr[j][k] = profit;
                }
            }

            next=curr;
        }
      return next[1][2];
    }
}
