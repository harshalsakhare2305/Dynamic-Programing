///https://leetcode.com/problems/maximal-rectangle/   #85
class Solution {
     public int[] prevSmallerElements(int[] nums) {
        int n=nums.length;

        int[] res =new int[n];
        Stack<Integer> st =new Stack<>();
        
      
        for(int i=0;i<n;i++){
           while(!st.empty() && nums[st.peek()]>=nums[i]){
            st.pop();
           }

           if(st.empty()){ // there is no smaller element 
            res[i]=-1;
        }else{  //stack ka top is smaller than array element
            res[i]=st.peek();
        }

        st.push(i); 

       
    }
     return res;
}

public  int[] nextSmallerElements(int[] nums) {
        int n=nums.length;

        int[] res =new int[n];
        Stack<Integer> st =new Stack<>();
        
      
        for(int i=n-1;i>=0;i--){
           while(!st.empty() && nums[st.peek()]>=nums[i]){
            st.pop();
           }

           if(st.empty()){ // there is no smaller element 
            res[i]=-1;
        }else{  //stack ka top is smaller than array element
            res[i]=st.peek();
        }

        st.push(i); 

       
    }
     return res;
}

    public int largestRectangleArea(int[] heights) {
        int n =heights.length;

        int[] next =nextSmallerElements(heights);
        int [] prev = prevSmallerElements(heights);
        int area =Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(next[i]==-1){
                next[i]=n;
            }
            int b =next[i]-prev[i]-1;
            int l=heights[i];
            int newarea =l*b;
            area=Math.max(area,newarea);
        }

        return area;
    }
    public int maximalRectangle(char[][] matrix) {
        int[] heights=new int[matrix[0].length];
        int ans=-(int)1e9;

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]=='0'){
                    heights[j]=0;
                }else{
                       heights[j]+=(matrix[i][j]-'0');
                }
            }

            ans=Math.max(ans,largestRectangleArea(heights));
        }

        return ans;
    }
}
