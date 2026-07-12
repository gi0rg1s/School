class Link:
    def __init__(self, ra, rb, p):
        self.ra = ra
        self.rb = rb
        self.p = p

topology = [Link('R1', 'R2', 2), Link('R2', 'R1', 4), Link('R2', 'R3', 2), 
            Link('R3', 'R2', 5), Link('R3', 'R4', 3), Link('R4', 'R3', 1)]

if __name__ == '__main__':
    szHops = []

    chS = input('Digit source router (1..4): ')
    chD = input('Digit destination router (1..4): ')
    
    rm = int(chS)
    rd = int(chD)
    r = -1

    szHops = f'R{rm} '
    n = len(szHops)

    while(rm != rd and n < 70):
        ok = -1
        for  i in range(0, len(topology)):
            if int(topology[i].ra[1]) == rm: 
                ok = i
                if int(topology[i].rb[1]) != r:
                    ok = -2
                    r = rm
                    rm = int(topology[i].rb[1])
                    break
        if ok >= 0:
            r  = rm
            rm = int(topology[ok].rb[1])
        if ok == -1:
            print('impossible to find a route')

        szHops += f'R{rm} '
        n += len(f'R{rm} ')
        print(f'Trace: {szHops}')
    print(' ok' if rm == rd else '\nError TTL!') 