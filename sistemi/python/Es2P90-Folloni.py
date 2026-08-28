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

#routing tables, one for each router
tdiR1 = [TDI('a', 1, 0, 0), TDI('b', 2, 1, 5), TDI('c', 2, 2, 2)]
tdiR2 = [TDI('a', 3, 1, 0), TDI('b', 7, 0, 5), TDI('c', 2, 1, 2)]
tdiR3 = [TDI('a', 9, 2, 0), TDI('b', 8, 1, 5), TDI('c', 9, 0, 0)]
tdi = []

#physical links between routers
linkRouter = [Link('R1', 'R2', 2), Link('R2', 'R1', 4), Link('R2', 'R3', 2), Link('R3', 'R2', 5)]

if __name__ == '__main__':
    szHops = []

    chS = input('Digit source address (a, b, c): ')
    chD = input('Digit destination address (a, b, c): ')

    #start routing from r1
    r = 1

    szHops = f'{chS} '
    n = len(szHops)
    #hop counter also used as loop condition
    h = -1

    while(h):
        p = -1
        h = -1

        #append router to the path
        szHops += f'R{r} '
        
        #switch case
        if r == 1: tdi = tdiR1
        elif r == 2: tdi = tdiR2
        elif r == 3: tdi = tdiR3

        #check the tdi of the selected router
        for t in tdi:
            if(chD == t.address):
                h = t.hop
                p = t.port
                break

        #check if the hop is higher than 0 so that the router is still further away to find the next router
        if h > 0:
            for link in linkRouter:
                if int(link.ra[1]) == r and link.p == p:
                    #move to the next hop router
                    r = int(link.rb[1])
                    break
        else:
            if h == 0: 
                szHops += chD
            else: 
                print("Impossible to find a route")
                break

    #print the full path
    print(f'Path: {szHops}')
        