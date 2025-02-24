
def num_covered(schedule):
    tot = 0
    e1 = schedule[0:5]
    e2 = schedule[5:10]
    e3 = schedule[10:15]

    for i in range(0, 5):
        #print(i, e1[i], e2[i], e3[i])
        if int(e1[i]) + int(e2[i]) + int(e3[i]) >= 2:
            tot += 1

    return tot

def num_overtime(schedule):
    tot = 0
    e1 = schedule[0:5]
    e2 = schedule[5:10]
    e3 = schedule[10:15]

    for e in [e1, e2, e3]:
        if e.count("1") > 4:
            tot += 1

    return tot

def fitness(schedule):
    return num_covered(schedule) + num_overtime(schedule)

arr = ["110101011001010", "101100101011011", "010110011100101", "111000111010100", "001101010111001", "110011010001111", "011101001010110", "100101111000011", "001011100110101", "111010010101101", "011101001001010"]

for sched in arr:
    print(fitness(sched))


