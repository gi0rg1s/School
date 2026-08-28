class tdi:
    def __init__(self, ia, ib, ic, iD, ma, mb, mc, md, ga, gb, gc, gd, ua, ub, uc, ud):
        #address column
        self.ia = ia
        self.ib = ib
        self.ic = ic
        self.id = iD
        #mask column
        self.ma = ma
        self.mb = mb
        self.mc = mc
        self.md = md
        #gateway column
        self.ga = ga
        self.gb = gb
        self.gc = gc
        self.gd = gd
        #use column (exit int)
        self.ua = ua
        self.ub = ub
        self.uc = uc
        self.ud = ud


tdiHost = [tdi(0, 0, 0, 0,   0, 0, 0, 0,    196, 13, 1, 254,    196, 13, 1, 1), 
           tdi(196, 13, 1, 0,   255, 255, 255, 255,    196, 13, 1, 1,    196, 13, 1, 1)]

def dottedToDecimal(a, b, c, d):
    return (a * 0x1000000) + (b * 0x10000) + (c * 0x100) + d

if __name__ == '__main__':
    #simulate a table on the terminal
    print('Address\tMask\t\tGateway\t\tUse')
    for host in tdiHost:
        print(f'{host.ia}.{host.ib}.{host.ic}.{host.id}\t{host.ma}.{host.mb}.{host.mc}.{host.md}\t{host.ga}.{host.gb}.{host.gc}.{host.gd}\t{host.ua}.{host.ub}.{host.uc}.{host.ud}\t')

#hard coded ip
    a = 196
    b = 13
    c = 2
    d = 5
    rules = 0

    for host in tdiHost:
        rules += 1
        print(f'Rule n.{rules} for {a}.{b}.{c}.{d}')
        #check rules
        u = dottedToDecimal(host.ma, host.mb, host.mc, host.md) & dottedToDecimal(a, b, c, d)

        if u == dottedToDecimal(host.ia, host.ib, host.ic, host.id): print('True')
        else: print('False')