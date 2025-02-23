import random 

random.seed(42)

def select(fitness_values):

    total_fitness = sum(fitness_values)
    # Normalize fitness values by dividing by total fitness
    normalized_fitness = [f / total_fitness for f in fitness_values]

    # Compute cumulative probabilities
    cumulative_prob = []
    cumulative_sum = 0
    for prob in normalized_fitness:
        cumulative_sum += prob
        cumulative_prob.append(cumulative_sum)

    # Perform selection
    selected_indices = []
    for _ in range(2):
        r = random.random()  # Random number between 0 and 1
        for i, cumulative in enumerate(cumulative_prob):
            if r <= cumulative:
                selected_indices.append(i)
                break

    return selected_indices


'''
S0 = 110101011001010		S5 = 110011010001111
S1 = 101100101011011		S6 = 011101001010110
S2 = 010110011100101		S7 = 100101111000011
S3 = 111000111010100		S8 = 001011100110101
S4 = 001101010111001		S9 = 111010010101101 
'''

#enter fitness values here
fitness_values = [...] 

print(select(fitness_values))
