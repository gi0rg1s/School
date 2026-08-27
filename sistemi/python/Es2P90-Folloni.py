class Link:
    def __init__(self, ra, rb, p):
        self.ra = ra
        self.rb = rb
        self.p = p

class TDI:
    def __init__(self, address, port, hop, cost):
        self.address = address
        self.port = port
        self.hop = hop
        self.cost = cost

tdiR1 = [TDI('a', 1, 0, 0), TDI('b', 2, 1, 5), TDI('c', 2, 2, 2)]
tdiR2 = [TDI('a', 3, 1, 0), TDI('b', 7, 0, 5), TDI('c', 2, 1, 2)]
tdiR3 = [TDI('a', 9, 2, 0), TDI('b', 8, 1, 5), TDI('c', 9, 0, 0)]
tdi = []

linkRouter = [Link('R1', 'R2', 2), Link('R2', 'R1', 4), Link('R2', 'R3', 2), Link('R3', 'R2', 5)]

if __name__ == '__main__':
    szHops = []

    chS = input('Digit source address (a, b, c): ')
    chD = input('Digit destination address (a, b, c): ')
    
    r = 1

    szHops = f'{chS} '
    n = len(szHops)
    h = -1

    while(h):
        p = -1
        h = -1

        szHops += f'R{r} '
        
        #switch case
        if r == 1: tdi = tdiR1
        elif r == 2: tdi = tdiR2
        elif r == 3: tdi = tdiR3

        for t in tdi:
            if(chD == t.address):
                h = t.hop
                p = t.port
                break

        if h > 0:
            for link in linkRouter:
                if int(link.ra[1]) == r and link.p == p:
                    r = int(link.rb[1])
                    break
        else:
            if h == 0: 
                szHops += chD
            else: 
                print("Impossible to find a route")
                break

    print(f'Path: {szHops}')
        