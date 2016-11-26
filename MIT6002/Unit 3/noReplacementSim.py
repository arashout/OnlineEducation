import random
def noReplacementSimulation(numTrials):
    '''
    Runs numTrials trials of a Monte Carlo simulation
    of drawing 3 balls out of a bucket containing
    3 red and 3 green balls. Balls are not replaced once
    drawn. Returns the a decimal - the fraction of times 3 
    balls of the same color were drawn.
    '''
    # Your code here
    threeInRow = 0
    for t in range(numTrials):
        L = [0, 0, 0, 1, 1, 1, 2, 2, 2]
        count = 1
        lastBall = 4
        for ball in L:
            i = random.randrange(0, len(L))
            ball = L.pop(i)
            if ball == lastBall:
                count += 1
                lastBall = ball
            else:
                lastBall = ball

            if count >= 3:
                threeInRow += 1
                break
    return threeInRow/numTrials

print(noReplacementSimulation(1000))