
//https://www.naukri.com/code360/problems/rod-cutting-problem_800284?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION
import java.util.*;

public class Solution {
	public static int helper(int idx, int tar, int[] price, int[][] dp) {
		if (idx == 0) {
			return price[0] * tar;
		}
		if (dp[idx][tar] != -1)
			return dp[idx][tar];
		int rodlen = idx + 1;
		int pick = Integer.MIN_VALUE;
		if (rodlen <= tar) {
			pick = price[idx] + helper(idx, tar - rodlen, price, dp);
		}

		int npick = helper(idx - 1, tar, price, dp);
		dp[idx][tar] = Math.max(pick, npick);
		return dp[idx][tar];
	}

	public static int cutRod(int price[], int n) {
		// // Write your code here.
		int[][] dp = new int[n][n + 1];
		for (int i = 0; i < n; i++) {
		Arrays.fill(dp[i], -1);
		}

		return helper(n - 1, n, price, dp);


    

		int[][] dp = new int[n][n + 1];
		for (int i = 0; i <= n; i++) {
			dp[0][i] = price[0] * i;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= n; j++) {
				int rodlen = i + 1;
				int pick = Integer.MIN_VALUE;
				if (rodlen <= j) {
					pick = price[i] + dp[i][j-rodlen];
				}

				int npick = dp[i-1][j]; 
				dp[i][j] = Math.max(pick, npick);
			}
		}

		return dp[n-1][n];
	}
}
