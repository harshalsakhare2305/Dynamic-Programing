//https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/
import java.util.*;

class Solution {

    int[][] dp;

    int rec(int idx, int k, String s) {

        if (idx == s.length() && k == 0) return 0;
        if (idx == s.length() || k == 0) return (int)1e9;

        if (dp[idx][k] != -1) return dp[idx][k];

        int ans = (int)1e9;

        for (int j = idx; j < s.length(); j++) {

            int len = j - idx + 1;

            int minLetterChange = (int)1e9;

      
            for (int d = 1; d < len; d++) {

                if (len % d == 0) {

                    int palindromelen = len / d;
                    int letterChange = 0;

                    for (int group = 0; group < d; group++) {

                        for (int i = 0; i < palindromelen / 2; i++) {

                            int first = group + i * d;
                            int last = group + (palindromelen - 1 - i) * d;

                            if (s.charAt(idx + first) != s.charAt(idx + last)) {
                                letterChange++;
                            }
                        }
                    }

                    minLetterChange = Math.min(minLetterChange, letterChange);
                }
            }

            // no valid divisor → skip
            if (minLetterChange == (int)1e9) continue;

            int next = rec(j + 1, k - 1, s);

            if (next != (int)1e9) {
                ans = Math.min(ans, minLetterChange + next);
            }
        }

        return dp[idx][k] = ans;
    }

    public int minimumChanges(String s, int k) {
        int n = s.length();
        dp = new int[n][k+1];

        for (int[] row : dp) Arrays.fill(row, -1);

        return rec(0, k, s); 
    }
}
