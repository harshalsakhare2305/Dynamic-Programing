class Solution {
    public boolean comparePossible(String s,String t){
        if(s.length()!=t.length()+1) return false;
        int first=0;
        int second=0;
        while(first<s.length()){
            if(second<t.length() && s.charAt(first)==t.charAt(second)){
                first++;
                second++;
            }else{
                first++;
            }
        }

        return first==s.length() && second==t.length();
    }
   
    public int longestStrChain(String[] words) {
        int n=words.length;
       Arrays.sort(words,new Comparator<String>(){
          public int compare(String s1,String s2){
            return s1.length()-s2.length();
          }
        });
        int[] dp =new int[n];
       Arrays.fill(dp,1);
  int maxi =Integer.MIN_VALUE;
   for(int i=0;i<n;i++){
      for(int prev=0;prev<i;prev++){
          if(comparePossible(words[i],words[prev])){
               dp[i]=Math.max(dp[i],dp[prev]+1);
            }
       }

       maxi=Math.max(maxi,dp[i]);
   }
     return maxi;
    }
}

  
