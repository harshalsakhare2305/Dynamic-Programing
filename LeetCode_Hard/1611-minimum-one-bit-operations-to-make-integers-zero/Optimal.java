//https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/?envType=problem-list-v2&envId=dynamic-programming

class Solution {
    public int minimumOneBitOperations(int n) {
        if(n==0)return 0;
        int[] F =new int[31];
        F[0]=1;
        for(int i=1;i<31;i++){
            F[i]=2*F[i-1]+1;
        }

        int sign=1;
        int count=0;

        int mask=n;


        for(int i=30;i>=0;i--){

            int bit = (mask & (1<<i));

            if(bit==0)continue;

            if(sign>0){
                count+=F[i];
            }else{
                count-=F[i];
            }

             sign=1-sign;
        }

      return count;
    }
}
