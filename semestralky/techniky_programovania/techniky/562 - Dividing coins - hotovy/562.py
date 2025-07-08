testCase = []

n = int(input())  # počet problémov

for _ in range(n):
    m = int(input())  # počet mincí
    coins = list(map(int, input().split()))
    testCase.append(coins)

"""testCase = []

with open("C:\David Jurík/Uniza/2roc/leto/techniky/SKUSKA/562 - Dividing coins/vstup.txt", "r") as f:
    riadok = f.read().splitlines()

index = 0
n = int(riadok[index])
index += 1

for _ in range(n):
    m = int(riadok[index])
    index += 1
    coins = list(map(int, riadok[index].split()))
    index += 1
    testCase.append(coins)"""

for coins in testCase:
    sucet = sum(coins)
    ciel = sucet // 2

    moznosti = {0}

    for coin in coins:
        novaMoznost = set()
        for s in moznosti:
            novySucet = s + coin
            if novySucet <= ciel:
                novaMoznost.add(novySucet)
        moznosti.update(novaMoznost)

    najlepsi = max(moznosti)
    rozdiel = sucet - 2 * najlepsi
    print(rozdiel)