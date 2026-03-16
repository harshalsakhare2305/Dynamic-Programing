// https://leetcode.com/problems/count-the-repetitions/description/


//
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        
        if((s1.length()*n1)<(s2.length()*n2))return 0;
        
        int index=0;
        int indexr[]=new int[n1+1];
        int countr[]=new int[n1+1];
      
        int count=0;

        for(int i=0;i<n1;i++){
            for(int j=0;j<s1.length();j++){
                if(s1.charAt(j)==s2.charAt(index)){
                    index++;
                }

                if(index==s2.length()){
                    count++;
                    index=0;
                }
            }

            indexr[i]=index;
            countr[i]=count;

            for(int k=0;k<i;k++){
                if(index==indexr[k]){
                    int prefix=countr[k];
                    int pattern =(countr[i]-countr[k])*((n1-1-k)/(i-k));
                    int remaining = (countr[k+(n1-1-k)%(i-k)]-countr[k]);

                    return  (((prefix+pattern+remaining)/n2));
                }
            }
        }

        return countr[n1-1]/n2;
    }
}
