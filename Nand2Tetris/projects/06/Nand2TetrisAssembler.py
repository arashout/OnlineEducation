import os #
import re


def is_instruction(string):
    """Function formats string to be only instructions in assembly, otherwise returns false"""
    comment = '//'
    index_comment = string.find(comment)

    #Return false if line is just comment
    if index_comment == 0:
        return False
    #Return false if line is whitespace
    elif string == '\n':
        return False
    #Strip in-line comments from instructions
    elif index_comment > 0:
        return string[:index_comment]
    #If pure instruction return string with newline stripped
    else:
        return string.rstrip('\n')


def a_2_machine_code(address):
    """
    Converts A-instructions to machine code
    """
    machine_instruction = '0' + "{0:015b}".format(address) + '\n'

    return machine_instruction


def c_2_machine_code(line):
    """Converts C-instruction into machine code"""

    machine_code_string = '111'

    dest_dict = {
        '':'000',
        'M':'001',
        'D':'010',
        'MD':'011',
        'A':'100',
        'AM':'101',
        'AD':'110',
        'AMD':'111',
    }
    comp_dict = {
        '0':'0101010',
        '1':'0111111',
        '-1':'0111010',
        'D':'0001100',
        'A':'0110000',
        'M':'1110000',
        '!D':'0001111',
        '!A':'0110001',
        '!M':'1110001',
        '-D':'0001111',
        '-A':'0110011',
        '-M':'1110011',
        'D+1':'0011111',
        'A+1':'0110111',
        'M+1':'1110111',
        'D-1':'0001110',
        'A-1':'0110010',
        'M-1':'1110010',
        'D+A':'0000010',
        'D+M':'1000010',
        'D-A':'0010011',
        'D-M':'1010011',
        'A-D':'0000111',
        'M-D':'1000111',
        'D&A':'0000000',
        'D&M':'1000000',
        'D|A':'0010101',
        'D|M':'1010101'
    }
    jump_dict = {
        '':'000',
        'JGT':'001',
        'JEQ':'010',
        'JGE':'011',
        'JLT':'100',
        'JNE':'101',
        'JLE':'110',
        'JMP':'111'
    }
    parser_regex = re.compile(r'(?:(.*)=)?([A-Z\+\d\-\!\&\|]{1,3})(?:;(.*))?')
    match_list = re.findall(parser_regex,line)[0]
    dest_entry = match_list[0]
    comp_entry = match_list[1]
    jump_entry = match_list[2]

    machine_code_string += comp_dict[comp_entry] + dest_dict[dest_entry] + jump_dict[jump_entry] + '\n'

    return machine_code_string


def convert2machine(lines):
    symbol_table = {
        'SP': 0,
        'LCL': 1,
        'ARG': 2,
        'THIS': 3,
        'THAT': 4,
        'R0': 0,
        'R1': 1,
        'R2': 2,
        'R3': 3,
        'R4': 4,
        'R5': 5,
        'R6': 6,
        'R7': 7,
        'R8': 8,
        'R9': 9,
        'R10': 10,
        'R11': 11,
        'R12': 12,
        'R13': 13,
        'R14': 14,
        'R15': 15,
        'SCREEN': 16384,
        'KBD': 24576
    }

    # FIRST PASS - FIND LABEL SYMBOLS
    pure_instructions = []
    count = 0

    for line in lines:
        instruction = is_instruction(line)  # REMOVE WHITESPACE AND COMMENTS
        # If is_instructions doesn't return false
        if instruction:
            instruction = instruction.replace(' ','')
            if instruction.startswith('('):  # LABEL SYMBOLS
                symbol_label = instruction[1:-1]
                symbol_table[symbol_label] = count
            else:
                pure_instructions.append(instruction)
                count += 1

    # SECOND PASS - FIND VARIABLE SYMBOLS and CONVERT TO MACHINE CODE

    machine_code = []
    n = 16
    for instruction in pure_instructions:
        if instruction.startswith('@'):  # A-INSTRUCTIONS
            try:
                decimal_address = int(instruction[1:])
            except ValueError:
                instruction_variable = instruction[1:]
                if instruction_variable in symbol_table:
                    decimal_address = symbol_table[instruction_variable]
                else:
                    symbol_table[instruction_variable] = n
                    decimal_address = n
                    n += 1
            machine_code.append(a_2_machine_code(decimal_address))
        else:  # C-INSTRUCTIONS
            machine_code.append(c_2_machine_code(instruction))

    return machine_code

if __name__ == "__main__":
    assembly_file_path = os.path.abspath(input("Enter the full path of assembly file to convert:\n"))

    [asm_dir, asm_name] = os.path.split(assembly_file_path)
    [asm_name, asm_ext] = asm_name.split('.')

    with open(assembly_file_path, 'r') as f:
        assembly_file_content_list = f.readlines()

    final_machine_code = convert2machine(assembly_file_content_list)
    new_hack_name = asm_name + '-Arash' + '.hack'
    new_hack_file_path = os.path.join(asm_dir,new_hack_name)
    with open(new_hack_file_path, 'w+') as f:
        f.writelines(final_machine_code)


