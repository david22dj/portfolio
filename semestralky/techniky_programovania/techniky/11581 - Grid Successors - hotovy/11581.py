'''with open("text.txt", "r") as f:
    lines = f.read().splitlines()

T = int(lines[0])
testy = []

idx = 1
for _ in range(T):
    while idx < len(lines) and lines[idx].strip() == "":
        idx += 1
    grid = []
    for _ in range(3):
        riadok = list(map(int, lines[idx].strip()))
        grid.append(riadok)
        idx += 1
    testy.append(grid)'''

T = int(input())
testy = []

for _ in range(T):
    input()  # Prázdny riadok
    grid = []
    for _ in range(3):
        riadok = list(map(int, input().strip()))
        grid.append(riadok)
    testy.append(grid)

def transformuj(grid):
    nova = [[0]*3 for _ in range(3)]
    for i in range(3):
        for j in range(3):
            sucet = 0
            if i > 0:
                sucet += grid[i-1][j]
            if i < 2:
                sucet += grid[i+1][j]
            if j > 0:
                sucet += grid[i][j-1]
            if j < 2:
                sucet += grid[i][j+1]
            nova[i][j] = sucet % 2
    return nova

def grid_to_tuple(grid):
    return tuple(tuple(row) for row in grid)

for g in testy:
    visited = set()
    current = g
    visited.add(grid_to_tuple(current))
    index = -1  # Počet transformácií

    while True:
        next_grid = transformuj(current)
        next_tuple = grid_to_tuple(next_grid)
        index += 1
        if next_tuple in visited:
            if index == 0:
                print(-1)  # Ak prvá transformácia dá rovnakú mriežku
            else:
                print(index - 1)  # Počet transformácií pred cyklom
            break
        else:
            visited.add(next_tuple)
            current = next_grid
