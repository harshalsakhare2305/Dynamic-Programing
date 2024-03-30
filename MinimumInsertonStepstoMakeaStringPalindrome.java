//https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
class Solution {
    public int helper(int idx1, int idx2, String s, String t, int[][] dp) {

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

    public int longestPalindromeSubseq(String s) {
        int n=s.length();
        StringBuilder sb =new StringBuilder(s);
        String t =sb.reverse().toString();
        int[][] dp = new int[n + 1][n + 1];
		for(int i=0;i<=n;i++){
		Arrays.fill(dp[i], -1);
		}
		return helper(n, n, s, t, dp);
    }
    public int minInsertions(String s) {
        int n=s.length();
        return (n- longestPalindromeSubseq(s));
    }
}
