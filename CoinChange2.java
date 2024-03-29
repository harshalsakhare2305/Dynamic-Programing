import java.util.*;

public class Solution {

	public static long helper(int idx, int x, int[] nums, long[][] dp) {
		if (x == 0) {
			return 1;
		}
		if (idx == 0) {
			if (x % nums[0] == 0)
				return 1;
			else
				return 0;
		}
		if (dp[idx][x] != -1)
			return dp[idx][x];
		long npick = helper(idx - 1, x, nums, dp);
		long pick = 0;
		if (nums[idx] <= x) {
			pick = helper(idx, x - nums[idx], nums, dp);
		}
		dp[idx][x] = pick + npick;
		return dp[idx][x];
	}

	public static long countWaysToMakeChange(int coins[], int amount) {
	
		int n = coins.length;
		long[][] dp =new long[n][amount+1];
		for(int i=0;i<n;i++){
		Arrays.fill(dp[i],-1);
		}

		return helper(n-1,amount,coins,dp);

    
   //Tabulation Code
		long[][] dp = new long[n][amount + 1];
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}

		for (int i = 0; i <= amount; i++) {
			if (i % coins[0] == 0) {
				dp[0][i] = 1;
			} else {
				dp[0][i] = 0;
			}
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= amount; j++) {
				long npick = dp[i-1][j];
				long pick = 0;
				if (coins[i] <= j) {
					pick = dp[i][j-coins[i]];
				}
				dp[i][j] = pick + npick;
			}
		}
       return dp[n-1][amount];

    //Space Optimization Code

	long[]prev= new long[amount + 1];
		for (int i = 0; i < n; i++) {
			prev[0] = 1;
		}

		for (int i = 0; i <= amount; i++) {
			if (i % coins[0] == 0) {
				prev[i] = 1;
			} else {
				prev[i] = 0;
			}
		}

		for (int i = 1; i < n; i++) {
				long[]temp= new long[amount + 1];
			for (int j = 0; j <= amount; j++) {
				long npick = prev[j];
				long pick = 0;
				if (coins[i] <= j) {
					pick = temp[j-coins[i]];
				}
				temp[j] = pick + npick;
			}
			prev=temp;
		}
       return prev[amount];
	}

}
