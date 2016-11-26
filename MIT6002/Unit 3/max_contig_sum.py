def max_contig_sum(L):
    """ L, a list of integers, at least one positive
    Returns the maximum sum of a contiguous subsequence in L """
    i = curSum = maxSum = 0
    for j in range(len(L)):
        curSum += L[j]
        if curSum > maxSum:
            maxSum = curSum
        elif curSum < 0:
            curSum = 0
        else:
            pass
    return maxSum
