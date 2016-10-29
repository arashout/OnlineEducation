
def longest_run(L):
    """
    Assumes L is a list of integers containing at least 2 elements.
    Finds the longest run of numbers in L, where the longest run can
    either be monotonically increasing or monotonically decreasing. 
    In case of a tie for the longest run, choose the longest run 
    that occurs first.
    Does not modify the list.
    Returns the sum of the longest run. 
    """
    def getLongest(listOflists):
        longest = []
        for current_list in list_runs:
            if len(current_list) > len(longest):
                longest = current_list
        return longest

    def backTrack(L, i, pos):
        while i > 0:
            if L[i] <= L[i+1] and pos:
                i -= 1
            elif L[i] >= L[i+1] and not pos:
                i -= 1
            else:
                break
        return i
        
    def allEqual(L, i):
        j = i
        while j > 0:
            if L[j] != L[j-1]:
                return False
            j -= 1
        return True

    pos = False
    if L[1] >= L[0]:
        pos = True

    i = 0
    current_list = []
    list_runs = []
    while i < len(L) - 1:
        if L[i+1] >= L[i] and pos:
            current_list.append(L[i])

        elif L[i+1] <= L[i] and not pos:
            current_list.append(L[i])

        elif L[i+1] >= L[i] and not pos:
            current_list.append(L[i])
            list_runs.append(current_list)
            current_list = []
            pos = True
            i = backTrack(L, i, pos)

        elif L[i+1] <= L[i] and pos:
            current_list.append(L[i])
            if allEqual(L, i):
                current_list.append(L[i+1])
            list_runs.append(current_list)
            current_list = []
            pos = False
            i = backTrack(L, i, pos)

        i += 1

    if L[i] >= L[i-1] and pos:
        current_list.append(L[i])

    elif L[i] <= L[i-1] and not pos:
        current_list.append(L[i])

    list_runs.append(current_list)
    return sum(getLongest(list_runs))

#print(longest_run([100, 10, 10, 10, 10, 10, 10, 10, 0]))
#print(longest_run([3, 3, 3, 3, 3, 3, 3, -10, 1, 2, 3, 4]))
longest_run([1, 2, 1, 2, 1, 2, 1, 2, 1])