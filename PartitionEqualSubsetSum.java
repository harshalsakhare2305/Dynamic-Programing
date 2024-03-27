class Solution {
     public  boolean helper(int[] arr, int idx, int k, int[][] dp) {
        if (k == 0)
            return true;
        if (idx == 0)
            return (arr[0] == k);

        if (dp[idx][k] != -1)
            return (dp[idx][k] == 0 ? false : true);
        boolean npick = helper(arr, idx - 1, k, dp);
        boolean pick = false;
        if (k >= arr[idx]) {
            pick = helper(arr, idx - 1, k - arr[idx], dp);
        }
        dp[idx][k] = npick || pick ? 1 : 0;
        return (npick || pick);
    }
    public boolean canPartition(int[] nums) {
        int n=nums.length;
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        if(sum%2!=0){
            return false;
        }
        sum=sum/2;

   

        int[][] dp =new int[n][sum+1];
            
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }

        return helper(nums,n-1,sum,dp);
        
   //Tabulation
         boolean[][] dp =new boolean[n][sum+1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (nums[0] <= sum) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                boolean npick = dp[i-1][j];
                boolean pick = false;
                if (j >= nums[i]) {
                    pick = dp[i-1][j-nums[i]];
                }

                dp[i][j]=(pick||npick);
            }
        }

        return dp[n-1][sum];
      

          boolean[] prev =new boolean[sum+1];
            prev[0] = true;
        

        if (nums[0] <= sum) {
            prev[nums[0]] = true;
        }

        for (int i = 1; i < n; i++) {
             boolean[] temp =new boolean[sum+1];
            for (int j = 0; j <= sum; j++) {
                boolean npick = prev[j];
                boolean pick = false;
                if (j >= nums[i]) {
                    pick = prev[j-nums[i]];
                }

                temp[j]=(pick||npick);
            }
            prev=temp;
        }

        return prev[sum];

    }
}
