//https://www.naukri.com/code360/problems/print-longest-common-subsequence_8416383?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
public class Solution {
    public static String findLCS(int n, int m, String s, String t){
        // Write your code here.
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

                int i=n;
                int j=m;
                String ans="";
                while(i>0 && j>0){
                   if(s.charAt(i-1)==t.charAt(j-1)){
                       ans=s.charAt(i-1)+ans;
                       i--;
                       j--;
                   }else if(dp[i-1][j]>dp[i][j-1]){
                       i--;
                   }else{
                       j--;
                   }
                }

                return ans;
	
    }
}
