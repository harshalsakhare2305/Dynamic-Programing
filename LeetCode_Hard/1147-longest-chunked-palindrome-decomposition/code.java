//https://leetcode.com/problems/longest-chunked-palindrome-decomposition/
class Solution {
    HashMap<String,Integer> memo;
    public int rec(String s){
        if(s.length()==0)return 0;
        if(s.length()==1)return 1;

        if(memo.containsKey(s))return memo.get(s);

        boolean isPossible=false;
        int ans=0;
        for(int i=1;i<(s.length()/2 )+1;i++){
            String subfront=s.substring(0,i);
            String subback=s.substring(s.length()-i);

            if(subfront.equals(subback)){
                isPossible=true;
            ans=Math.max(ans,2+rec(s.substring(i,s.length()-i)));
            }
        }

        if(!isPossible){
            memo.put(s,1);
            return 1;
        }

        memo.put(s,ans);
        return ans;

    }
    public int longestDecomposition(String text) {
        int n=text.length();
        memo=new HashMap<>();
        return rec(text);
    }
}
