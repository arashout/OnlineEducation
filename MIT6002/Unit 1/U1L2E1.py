# generate all combinations of N items
def powerSetTrinary(items):
    N = len(items)
    # enumerate the 3**N possible combinations
    for i in range(3**N):
        bagA = []
        bagB = []
        for j in range(N):
            # test bit jth of integer i
            mod = i // 3**j % 3
            #Bag A
            if mod == 1:
                bagA.append(items[j])
            #Bag B
            elif mod == 2:
                bagB.append(items[j])
        yield (bagA, bagB)
