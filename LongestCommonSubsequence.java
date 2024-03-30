//https://leetcode.com/problems/longest-common-subsequence/description/
//https://www.naukri.com/code360/problems/longest-common-subsequence_624879?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM

import java.util.Arrays;

public class Solution {

	public static int helper(int idx1, int idx2, String s, String t, int[][] dp) {

		if (idx1 == 0 || idx2 == 0) {
			return 0;
		}
		if (dp[idx1][idx2] != -1)
			return dp[idx1][idx2];

		if (s.charAt(idx1 - 1) == t.charAt(idx2 - 1)) {
			return 1 + helper(idx1 - 1, idx2 - 1, s, t, dp);
		}
		dp[idx1][idx2] = Math.max(helper(idx1 - 1, idx2, s, t, dp), helper(idx1, idx2 - 1, s, t, dp));
		return dp[idx1][idx2];
	}

	public static int lcs(String s, String t) {
		int n = s.length();
		int m = t.length();
		int[][] dp = new int[n + 1][m + 1];
		for(int i=0;i<=n;i++){
		Arrays.fill(dp[i], -1);
		}
		return helper(n, m, s, t, dp);
      

	  	int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i <= m; i++) {
			dp[0][i] = 0;
		}
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 0;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					 dp[i][j]=1+dp[i-1][j-1];
				}else{
					dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
				}
			
			}
		}

		return dp[n][m];
		
      int[] prev =new int[m+1];
			for (int i = 0; i <= m; i++) {
			prev[i] = 0;
		}
	prev[0]=0;
		for (int i = 1; i <= n; i++) {
			  int[] temp=new int[m+1];
			for (int j = 1; j <= m; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					 temp[j]=1+prev[j-1];
				}else{
					temp[j]=Math.max(prev[j],temp[j-1]);
				}
			
			}

			prev=temp;
		}

		return prev[m];
	}

}
