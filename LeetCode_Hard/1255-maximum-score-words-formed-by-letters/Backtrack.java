//https://leetcode.com/problems/maximum-score-words-formed-by-letters/description/?envType=problem-list-v2&envId=dynamic-programming

public class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] count = new int[26];
        for (char c : letters) count[c - 'a']++;

        return backtrack(words, score, count, 0);
    }

    private int backtrack(String[] words, int[] score, int[] count, int pos) {
        if (pos == words.length) return 0;

        // Option 1: skip current word
        int notTake = backtrack(words, score, count, pos + 1);

        // Option 2: take current word (if possible)
        int wordScore = 0;
        boolean canTake = true;
        int[] used = new int[26];

        for (char c : words[pos].toCharArray()) {
            int idx = c - 'a';
            used[idx]++;
            if (used[idx] > count[idx]) {
                canTake = false;
                break;
            }
            wordScore += score[idx];
        }

        int take = 0;
        if (canTake) {
            for (int i = 0; i < 26; i++) count[i] -= used[i];
            take = wordScore + backtrack(words, score, count, pos + 1);
            for (int i = 0; i < 26; i++) count[i] += used[i];
        }

        return Math.max(take, notTake);
    }
}
