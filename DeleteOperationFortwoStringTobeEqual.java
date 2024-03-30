//https://leetcode.com/problems/delete-operation-for-two-strings/description/
import java.util.*;
public class Solution {
     public static  int helper(int idx1,int idx2,String s,String t,int[][] dp){
		
   if(idx1<0 || idx2<0){
	   return 0;
   }
   if(dp[idx1][idx2]!=-1) return dp[idx1][idx2];
	
		if(s.charAt(idx1)==t.charAt(idx2)){
		 return 1+helper(idx1-1, idx2-1, s, t,dp);
		}
       dp[idx1][idx2]= Math.max(helper(idx1-1, idx2, s, t,dp),helper(idx1, idx2-1, s, t,dp));
		return dp[idx1][idx2];
	}
    public static int longestCommonSubsequence(String s, String t) {
      int n=s.length();
	  int m=t.length();
	  int[][]dp=new int[n][m];
	  for(int i=0;i<n;i++){
		  Arrays.fill(dp[i], -1);
	  }
	  return helper(n-1, m-1, s, t, dp);
    }
    public static int canYouMake(String s1, String s2) {
        // Write your code here.
        int n=s1.length();
        int m=s2.length();
        int lcs=longestCommonSubsequence(s1, s2);
        return ((n-lcs)+(m-lcs));
    }
}
