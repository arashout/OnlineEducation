trans = {'0':'ling', '1':'yi', '2':'er', '3':'san', '4': 'si',
      '5':'wu', '6':'liu', '7':'qi', '8':'ba', '9':'jiu', '10': 'shi'}
def convert_to_mandarin(us_num):
    '''
    us_num, a string representing a US number 0 to 99
    returns the string mandarin representation of us_num
    '''
    # FILL IN YOUR CODE HERE
    num_in_mandarin = ""
    
    if us_num == "0":
        return trans[us_num]

    actual_num = int(us_num)

    for i in range(len(us_num)):
        str_num = str(us_num[i])

        if str_num != "0":
            num_in_mandarin += trans[str_num] + ' '

        if actual_num >= 20 and i == 0:
            num_in_mandarin += trans['10'] + ' '
        elif actual_num >= 10 and i == 0:
            num_in_mandarin = trans['10'] + ' '

    return num_in_mandarin.rstrip(' ')

print(convert_to_mandarin('36'))
print(convert_to_mandarin('20'))
print(convert_to_mandarin('16'))
print(convert_to_mandarin('17'))
print(convert_to_mandarin('11'))

