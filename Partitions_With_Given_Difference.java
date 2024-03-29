import java.util.* ;
import java.io.*; 
public class Solution {

	  static int mod = 1000000007;

	  public static int helper(int[]nums,int idx,int tar,int[][] dp){
    
        if(idx==0){
            if(tar==0 && nums[0]==0){
                return 2;
            } 
            if(tar==0 || nums[0]==tar){
                return 1;
            }

            return 0;
        }
     
     if(dp[idx][tar]!=-1) return dp[idx][tar];
        int pick =0;
        if(tar>=nums[idx]){
            pick=helper(nums, idx-1, tar-nums[idx],dp);
        }

        int npick =helper(nums, idx-1, tar,dp);
    dp[idx][tar]=(pick+npick)%mod;
        return   dp[idx][tar];
    }
	public static int countPartitions(int n, int d, int[] arr) {
      int totalSum=0;
	  for(int i:arr){
		  totalSum+=i;
	  }

	  int tar =(totalSum-d)/2;
	  if(totalSum-d<0 || (totalSum-d)%2!=0){
       return 0;
	  }

    //Memoizaton Function Call
      int[][] dp =new int[n][tar+1];
      for(int i=0;i<n;i++){
          Arrays.fill(dp[i],-1);
      }

	  return helper(arr,n-1,tar,dp)%mod;


    //Tabulation

   int[][] dp =new int[n][tar+1];
       if(arr[0]==0) dp[0][0]=2;
	   else dp[0][0]=1;
	if( arr[0]!=0 && arr[0]<=tar){
		dp[0][arr[0]]=1;
	}

	for(int i=1;i<n;i++){
		for(int j=0;j<=tar;j++){
			 int pick =0;
        if(j>=arr[i]){
            pick=dp[i-1][j-arr[i]];
        }

        int npick =dp[i-1][j];
         dp[i][j]=(pick+npick)%mod;
		}
	}

	return dp[n-1][tar];

      //Tabulation With Space Optimization

  int[] prev =new int[tar+1];
       if(arr[0]==0) prev[0]=2;
	   else prev[0]=1;
	if( arr[0]!=0 && arr[0]<=tar){
		prev[arr[0]]=1;
	}

	for(int i=1;i<n;i++){
         int[] temp =new int[tar+1];
		for(int j=0;j<=tar;j++){
			 int pick =0;
        if(j>=arr[i]){
            pick=prev[j-arr[i]];
        }

        int npick =prev[j];
         temp[j]=(pick+npick)%mod;
		}

        prev=temp;
	}

 	return prev[tar];

    
	}
}
