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
        #check variable
        ok = -1
        n = 0
        for i in len(topology):
            #check if the sender is on the link
            if int(i.ra[1]) == rm: 
                #if it is, ok equals the index of the right topology in the array
                ok = n
                #check if the destination is different from the r 
                if int(i.rb[1]) != r:
                    ok = -2
                    r = rm
                    #assign to the sender the other router on the link
                    rm = int(i.rb[1])
                    break
            n += 1
        #if we found a link on the topology
        if ok >= 0:
            r  = rm
            rm = int(topology[ok].rb[1])
        #if not we do not find any possible route
        if ok == -1:
            print('impossible to find a route')

        szHops += f'R{rm} '
        n += len(f'R{rm} ')
        print(f'Trace: {szHops}')
    print(' ok' if rm == rd else '\nError TTL!') 