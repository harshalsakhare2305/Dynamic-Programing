//https://leetcode.com/problems/the-earliest-and-latest-rounds-where-players-compete/?envType=problem-list-v2&envId=w06ut5md


class Solution {
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        int left=firstPlayer;
        int right=secondPlayer;


        if(left+right==n+1)return new int[]{1,1};

        if(left>n-right+1){
            int temp=n-left+1;
            left=n-right+1;
            right=temp;
        }

        int Earliest=Integer.MAX_VALUE;
        int latest=0;

        int nextround=(n+1)/2;

        if(right<=nextround){

            int surviormid=right-left-1;

            for(int surviorLeft=0;surviorLeft<=left-1;surviorLeft++){
                for(int surviorMid=0;surviorMid<=surviormid;surviorMid++){

                    int nextleft=surviorLeft+1;
                    int nextright=nextleft+surviorMid+1;

                    int[] result=earliestAndLatest(nextround,nextleft,nextright);

                    Earliest=Math.min(Earliest,result[0]+1);
                    latest=Math.max(latest,result[1]+1);

                }
            }
        }else{
           
             int fightright=n-right+1;
             int surviormid=fightright-left-1;
             int rMid=right-fightright-1;


            for(int surviorLeft=0;surviorLeft<=left-1;surviorLeft++){
                for(int surviorMid=0;surviorMid<=surviormid;surviorMid++){

                    int nextleft=surviorLeft+1;
                    int nextremain=(rMid+1)/2;
                    int nextright=nextleft+surviorMid+nextremain+1;

                    int[] result=earliestAndLatest(nextround,nextleft,nextright);

                    Earliest=Math.min(Earliest,result[0]+1);
                    latest=Math.max(latest,result[1]+1);

                }
            }
        }

        return new int[]{Earliest,latest};
    }
}
