def general_poly (L):
    """ L, a list of numbers (n0, n1, n2, ... nk)
    Returns a function, which when applied to a value x, returns the value 
    n0 * x^k + n1 * x^(k-1) + ... nk * x^0 """
    #YOUR CODE HERE 
    def eval_general_poly(x):
        result = 0
        power = len(L) - 1
        for co_eff in L:
            result += co_eff * x ** power
            print(co_eff * x ** power)
            power -= 1
        return result

    return eval_general_poly
        

print(general_poly([1, 2, 3, 4])(10))