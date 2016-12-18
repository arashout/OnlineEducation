import numpy as np


def find_combination(choices, total):
    """
    choices: a non-empty list of ints
    total: a positive int

    Returns result, a numpy.array of length len(choices) 
    such that
        * each element of result is 0 or 1
        * sum(result*choices) == total
        * sum(result) is as small as possible
    In case of ties, returns any result that works.
    If there is no result that gives the exact total, 
    pick the one that gives sum(result*choices) closest 
    to total without going over.
    """

    # All sets of possible choices
    sets = []
    for i in range(0, 2**len(choices)):
        length = len(choices)
        binaryString = bin(i)[2:].zfill(length)
        s = np.array(list(map(int, binaryString)))
        sets.append(s)

    choices = np.array(choices)
    # Tuple containing results, sum, difference
    tup_list = []
    for s in sets:
        choices_sum = sum(s * choices)
        if choices_sum == total:
            diff = 0
            tup_list.append((s,sum(s),diff))
        elif choices_sum < total:
            diff = total - choices_sum
            tup_list.append((s,sum(s),diff))

    sorted_tup = sorted(tup_list, key = lambda x: (x[2], x[1]), reverse = False)
    for s in sorted_tup:
        print(s)
    return sorted_tup[0][0]


print(find_combination([4, 6, 3, 5, 2], 10))
