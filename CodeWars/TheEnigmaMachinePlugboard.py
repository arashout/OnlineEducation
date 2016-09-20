class Plugboard(object):
    def __init__(self, wires):
        """
        wires: This is the mapping of pairs of characters
        """
        pass
                
    def process(self, c):
        """
        c: The single character to process
        """
        return None


Plugboard("ABCDEFGHIJKLMNOPQRSTUV")#"Too many wires defined"

plugboard = Plugboard("AB")
plugboard.process('A') == 'B'
plugboard.process('B') == 'A'
plugboard.process('C') == 'C'

