def flatten(aList):
    # Define a helper function to mutate list
    def dig(item, flatL):
        # Base Case - if single element -> Result is that list is mutated!
        if type(item) is str or type(item) is int:
            return flatL.append(item)
        # If a list that we need to flatten
        else:
            for i in item:
                dig(i, flatL)

    flatList = []
    dig(aList, flatList)
    return flatList
