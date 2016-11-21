def std(L):
    mean = sum(L)/len(L)
    total_top = 0
    for t in L:
        top = t - mean
        top = top ** 2
        total_top += top

    bottom = len(L)

    return (total_top/bottom) ** .5

def stdDevOfLengths(L):
    if(len(L) == 0):
        return float("nan") 

    listLengths = [len(string) for string in L]
    return std(listLengths)
L = [10, 4, 12, 15, 20, 5]
mean = sum(L)/len(L)
print(std(L)/mean)