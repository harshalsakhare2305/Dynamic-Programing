//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
https://www.naukri.com/code360/problems/best-time-to-buy-and-sell-stock-ii_630282?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION
import java.util.*;

public class Solution {
    public static long MaxProfit(int idx, int buy, long[] prices, long[][] dp) {
        int n = prices.length;
        if (idx == n)
            return 0;

        if (dp[idx][buy] != -1)
            return dp[idx][buy];
        long profit = 0;
        if (buy == 1) {
            profit = Math.max(-prices[idx] + MaxProfit(idx + 1, 0, prices, dp), MaxProfit(idx + 1, 1, prices, dp));
        } else {
            profit = Math.max(prices[idx] + MaxProfit(idx + 1, 1, prices, dp), MaxProfit(idx + 1, 0, prices, dp));
        }

        dp[idx][buy] = profit;
        return profit;
    }

    public static long getMaximumProfit(int n, long[] prices) {
        Your code goes here.
        long[][] dp = new long[n + 1][2];
        for(int i=0;i<n;i++){
        Arrays.fill(dp[i],-1);
        }

        return MaxProfit(0,1,prices,dp);

      

        dp[n][0] = 0;
        dp[n][1] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) {
                long profit = 0;
                if (j == 1) {
                    profit = Math.max(-prices[i]+ dp[i+1][0],
                            dp[i+1][1]);
                } else {
                    profit = Math.max(prices[i] +dp[i+1][1],
                           dp[i+1][0]);
                }

                dp[i][j] = profit;
            }
        }

        return dp[0][1];

      


      long[] next = new long[2];
        next[0] = 0;
        next[1] = 0;
        for (int i = n - 1; i >= 0; i--) {
              long[] curr = new long[2];
            for (int j = 0; j <= 1; j++) {
                long profit = 0;
                if (j == 1) {
                    profit = Math.max(-prices[i]+ next[0],
                            next[1]);
                } else {
                    profit = Math.max(prices[i] +next[1],
                           next[0]);
                }

                curr[j] = profit;
            }
            next=curr;
        }

        return next[1];
    }
}
