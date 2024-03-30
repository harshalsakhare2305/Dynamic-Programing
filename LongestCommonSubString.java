//https://www.naukri.com/code360/problems/longest-common-substring_1235207?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION
public class Solution {
    public static int lcs(String s, String t){
        int n=s.length();
        int m=t.length();

        int ans=0;
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
                     ans=Math.max(ans,dp[i][j]);
				}else{
					dp[i][j]=0;
				}
			
			}
		}

        return ans;

      //Space Optimization

           int ans=0;
        	int[] prev = new int[m + 1];
		for (int i = 0; i <= m; i++) {
		        prev[i] = 0;
		}
	
		for (int i = 1; i <= n; i++) {
            	int[] temp = new int[m + 1];
			for (int j = 1; j <= m; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					 temp[j]=1+prev[j-1];
                     ans=Math.max(ans,temp[j]);
				}else{
					temp[j]=0;
				}
			
			}

            prev=temp;
		}

        return ans;
    }
}
