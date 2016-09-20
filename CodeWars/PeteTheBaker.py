def cakes(recipe, available):
    # TODO: insert code
    best = []
    for key, value in recipe.items():
        if key in available:
            if available[key] >= value:
                best.append(int(available[key]/value))
            else:
                return 0
        else:
            return 0

    return min(best)


recipe = {"flour": 500, "sugar": 200, "eggs": 1}
available = {"flour": 1200, "sugar": 1200, "eggs": 5, "milk": 200}
print(cakes(recipe, available))
print(cakes(recipe, available) == 2)

recipe = {"apples": 3, "flour": 300, "sugar": 150, "milk": 100, "oil": 100}
available = {"sugar": 500, "flour": 2000, "milk": 2000}
print(cakes(recipe, available) == 0)

recipe = {'flour': 300, 'sugar': 150, 'milk': 100, 'oil': 100, 'cream': 200}
available = {'flour': 20000, 'oil': 30000, 'cream': 5000, 'milk': 20000, 'sugar': 1700}
