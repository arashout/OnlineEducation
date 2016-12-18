def drawing_without_replacement_sim(numTrials):
    '''
    Runs numTrials trials of a Monte Carlo simulation
    of drawing 3 balls out of a bucket containing
    4 red and 4 green balls. Balls are not replaced once
    drawn. Returns a float - the fraction of times 3 
    balls of the same color were drawn in the first 3 draws.
    '''
    # Your code here 
    # 1 = Red 2 = Green
    import random
    list_balls = [1, 1, 1, 1, 2, 2, 2, 2]
    num_draws = 3
    sameCount = 0
    for trial in range(numTrials):
        trial_balls = list_balls.copy()
        same = True
        prevBall = 0 # Null Value
        for draw in range(num_draws):
            ball = random.choice(trial_balls)
            trial_balls.remove(ball)
            if prevBall and prevBall != ball:
                same = False
                break
            prevBall = ball
        if same:
            sameCount += 1
    return sameCount/numTrials

print(drawing_without_replacement_sim(100))