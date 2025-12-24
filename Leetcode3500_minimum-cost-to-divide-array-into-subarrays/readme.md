MINIMUM COST PARTITIONING – DYNAMIC PROGRAMMING SOLUTION


PROBLEM STATEMENT

You are given two integer arrays nums[] and cost[], and an integer k.
You must partition the array into contiguous segments.

For a segment [st … ei], the cost is defined as:

(prefix[ei]) × (sum of cost in the segment)
+ k × (sum of cost from index st to n−1)

The task is to compute the minimum total cost over all valid partitions.



FORMULA DERIVATION

Define prefix sums:

prefix[i]     = nums[0] + nums[1] + ... + nums[i]
costPrefix[i] = cost[0] + cost[1] + ... + cost[i]

For a segment [st … ei]:

Sum of cost inside the segment:
cost(st, ei) = costPrefix[ei] − costPrefix[st−1]

Sum of cost from st to the end:
cost(st, n−1) = costPrefix[n−1] − costPrefix[st−1]

Segment cost contribution:
segmentCost(st, ei) =
    prefix[ei] × (costPrefix[ei] − costPrefix[st−1])
  + k × (costPrefix[n−1] − costPrefix[st−1])

This formula allows O(1) segment cost computation.



APPROACH OVERVIEW

We use Dynamic Programming with Memoization.

The idea is to try all valid segment boundaries while ensuring that:
- Every segment is closed
- No elements are skipped
- The total cost is minimized

Prefix sums are used to compute segment costs efficiently.



DP STATE DEFINITION

dp[st][ei]

st = starting index of the current open segment
ei = current index being processed

Meaning:
Minimum cost to process elements from index ei to n−1,
assuming a segment started at index st.



BASE CASES

If ei == n and st == n:
All elements are processed and no open segment remains.
Return 0.

If ei >= n:
We reached the end with an open segment.
This is invalid, return INF.

These base cases ensure every segment is closed and prevent invalid zero-cost paths.



STATE TRANSITIONS

At a given state (st, ei), two choices exist.

1) Close the current segment at index ei.

Compute:
segmentCost(st, ei)

Then start a new segment:
take = segmentCost(st, ei) + helper(ei + 1, ei + 1)

2) Extend the current segment.

Do not close the segment:
ntake = helper(st, ei + 1)

Choose the minimum:
dp[st][ei] = min(take, ntake)



FINAL ANSWER

The computation starts with:
helper(0, 0)

This represents starting the first segment at index 0.



CORRECTNESS ARGUMENT

- Every valid partition must close all segments
- DP explores all valid segment boundaries
- Invalid states are eliminated using base conditions
- Optimal substructure guarantees global optimality

Hence, the algorithm always produces the minimum total cost.



COMPLEXITY ANALYSIS

Time Complexity:  O(n²)
Space Complexity: O(n²)



