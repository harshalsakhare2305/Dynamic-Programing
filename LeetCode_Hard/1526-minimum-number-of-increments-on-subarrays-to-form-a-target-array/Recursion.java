//https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/?envType=problem-list-v2&envId=dynamic-programming









//We are using the Divide and Conquer approch 
// In question it is mentioned that we have to any subarray

//If we have choose any subarray range [l,r] l ans r indices

// So now think what would be number of time we should decrement this subarray 1,2,3 ... upto maximum element time

//Lets we take maxelement times decrement this subarray 
//so element lesses than maxelement time now becommes less than 1
//so we can't decrement our target array more than minelement times.

//So the minele is our pivot our D&Conquer revolve around this
//we'll decrement our range minele time and then divide the whole in two i...minidx-1  and minidx+1..j
//why are we divinng rangle because now our minele became zero no further operation on that.
 

//  so our recursion/transition now is  minele+rec(i,minidx-1)+rec(minidx,j);

//  but now also how would we track which element is decremented how many times means there can be multiple operation can be performed on single element.If element is not minele then it will be processed or decrement multiple time so for than we take one more parameter as currDecrement which will track how many times we decremeted

//  so our updated recursion/transition now is  minele+rec(i,minidx-1,currDec)+rec(minidx,j,currDec);

//  so how will we manage the currDec
//  so we will decrement range minVal-currDec time because our minval can be opetated/decrement before

//  so newDec=currDec+(minVal-currDec)
//    newDec=minVal;

//    What about base case i==j means single ele is decrement arr[i]-currDec times

class Solution {
    SegmentTree t;

    class SegmentTree {
        int[] arr;
        int[] tree;
        int n;

        public SegmentTree(int[] arr) {
            this.arr = arr;
            n = arr.length;
            tree = new int[4 * n];
            build(0, n - 1, 1);
        }

        private void build(int start, int end, int node) {
            if (start == end) {
                tree[node] = start;
                return;
            }
            int mid = start + (end - start) / 2;
            build(start, mid, 2 * node);
            build(mid + 1, end, 2 * node + 1);
            tree[node] = (arr[tree[2 * node]] <= arr[tree[2 * node + 1]]) ? tree[2 * node] : tree[2 * node + 1];
        }

        public int query(int l, int r) {
            return query(0, n - 1, l, r, 1);
        }

        private int query(int start, int end, int l, int r, int node) {
            if (r < start || end < l) return -1;
            if (l <= start && end <= r) return tree[node];
            int mid = start + (end - start) / 2;
            int left = query(start, mid, l, r, 2 * node);
            int right = query(mid + 1, end, l, r, 2 * node + 1);
            if (left == -1) return right;
            if (right == -1) return left;
            return (arr[left] <= arr[right]) ? left : right;
        }

        public void update(int index, int value) {
            arr[index] = value;
            update(0, n - 1, index, 1);
        }

        private void update(int start, int end, int idx, int node) {
            if (start == end) {
                tree[node] = start;
                return;
            }
            int mid = start + (end - start) / 2;
            if (idx <= mid) update(start, mid, idx, 2 * node);
            else update(mid + 1, end, idx, 2 * node + 1);
            tree[node] = (arr[tree[2 * node]] <= arr[tree[2 * node + 1]]) ? tree[2 * node] : tree[2 * node + 1];
        }
    }

    public int rec(int i, int j, int currDec, int[] arr) {
        if (i > j) return 0;
        if (i == j) return arr[i] - currDec;

        int minidx = t.query(i, j);
        int minVal = arr[minidx];
        int ans = (minVal - currDec) 
                + rec(i, minidx - 1, minVal, arr) 
                + rec(minidx + 1, j, minVal, arr);

        return ans;
    }

    public int minNumberOperations(int[] target) {
        t = new SegmentTree(target);
        int n = target.length;
        return rec(0, n - 1, 0, target);
    }
}
