//https://www.naukri.com/code360/problems/best-time-to-buy-and-sell-stock-with-transaction-fee_3118974?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
import java.util.*;

public class Solution {
    public static int MaxProfit(int idx, int buy, int[] prices, int[][] dp, int fee) {
        int n = prices.length;
        if (idx == n)
            return 0;

        if (dp[idx][buy] != -1)
            return dp[idx][buy];
        int profit = 0;
        if (buy == 1) {
            profit = Math.max(-prices[idx] + MaxProfit(idx + 1, 0, prices, dp, fee),
                    MaxProfit(idx + 1, 1, prices, dp, fee));
        } else {
            profit = Math.max(prices[idx] - fee + MaxProfit(idx + 1, 1, prices, dp, fee),
                    MaxProfit(idx + 1, 0, prices, dp, fee));
        }

        dp[idx][buy] = profit;
        return profit;
    }

    public static int maximumProfit(int[] prices, int n, int fee) {
        // Write your code here.

        // int[][] dp = new int[n + 1][2];
        // for(int i=0;i<n;i++){
        // Arrays.fill(dp[i],-1);
        // }

        // return MaxProfit(0,1,prices,dp,fee);

        // dp[n][0] = 0;
        // dp[n][1] = 0;

        // for (int i = n - 1; i >= 0; i--) {
        //     for (int j = 0; j <= 1; j++) {
        //         int profit = 0;
        //         if (j == 1) {
        //             profit = Math.max(-prices[i] +dp[i+1][0],dp[i+1][1]);
        //         } else {
        //             profit = Math.max(prices[i] - fee +dp[i+1][1],dp[i+1][0]);
        //         }

        //         dp[i][j] = profit;
        //     }
        // }

        // return dp[0][1];
         int[] next = new int[2];

         next[0] = 0;
        next[1] = 0;

        for (int i = n - 1; i >= 0; i--) {
              int[] curr = new int[2];
            for (int j = 0; j <= 1; j++) {
                int profit = 0;
                if (j == 1) {
                    profit = Math.max(-prices[i] +next[0],next[1]);
                } else {
                    profit = Math.max(prices[i] - fee +next[1],next[0]);
                }

                curr[j] = profit;
            }
            next=curr;
        }

        return next[1];
    }
}
