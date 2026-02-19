public class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int n = words.length;

        int[] available = new int[26];
        for (char c : letters) available[c - 'a']++;

       
        int[][] wordCount = new int[n][26];
        int[] wordScore = new int[n];

        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                wordCount[i][c - 'a']++;
                wordScore[i] += score[c - 'a'];
            }
        }

        int[] dp = new int[1 << n];
        int ans = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            int[] used = new int[26];
            int curScore = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    for (int c = 0; c < 26; c++) {
                        used[c] += wordCount[i][c];
                        if (used[c] > available[c]) {
                            valid = false;
                            break;
                        }
                    }
                    curScore += wordScore[i];
                }
                if (!valid) break;
            }

            if (valid) {
                dp[mask] = curScore;
                ans = Math.max(ans, dp[mask]);
            }
        }

        return ans;
    }
}
