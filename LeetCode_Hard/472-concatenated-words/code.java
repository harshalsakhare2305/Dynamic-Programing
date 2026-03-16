//https://leetcode.com/problems/concatenated-words/
class Solution {
    HashSet<String> st;
    HashMap<String, Boolean> memo;

    public boolean rec(String s) {
        if (s.length() == 0) return true;

        if (memo.containsKey(s)) return memo.get(s);

        for (int i = 1; i <= s.length(); i++) {
            String left = s.substring(0, i);

            if (st.contains(left) && rec(s.substring(i))) {
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        return false;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        st = new HashSet<>();
        for (String w : words) st.add(w);

        List<String> ans = new ArrayList<>();

        for (String w : words) {

            st.remove(w); // when we check i==s.length() it sub==originalword so it always give true thats we are temp removing the word

            memo = new HashMap<>(); //It is neccesssary to clear the prev memo because they can interfare 

            if (rec(w)) ans.add(w);

            st.add(w);
        }

        return ans;
    }
}
