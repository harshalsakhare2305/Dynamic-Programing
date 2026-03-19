//https://leetcode.com/problems/string-compression-ii/

//Just try to how long we can extend our curr char if any char if diff then delete it if we have sufficient k
class Solution {

    int dp[][];
    int INF=(int)1e9;

    public int helper(int i, int k, String s) {
        if (k < 0)
            return INF;

        if (i >= s.length())
            return 0;

        if (dp[i][k] != -1)
            return dp[i][k];

        int delete = helper(i + 1,k-1,s);

        int keepit = Integer.MAX_VALUE;
        int addition = 0;
        int deleted = 0;
        int freq=0;
        char ch = s.charAt(i);
        for (int j = i; j < s.length() && deleted <= k; j++) {

            if (s.charAt(j) == ch) {
                freq++;
                if (freq == 2 || freq == 10 || freq == 100)
                    addition++;
            } else {
                deleted++;
            }

            keepit=Math.min(keepit,addition+1+helper(j+1,k-deleted,s));
        }

        return dp[i][k] = Math.min(delete, keepit);

    }

    public int getLengthOfOptimalCompression(String s, int k) {
        dp = new int[101][101];

        for(int[]r:dp){
            Arrays.fill(r,-1);
        }
      

        return helper(0,k,s);

    }
}
