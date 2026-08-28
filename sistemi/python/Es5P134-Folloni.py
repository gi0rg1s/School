class privateIP:
    def __init__(self, id1, id2, netmaskLen):
        self.id1 = id1
        self.id2 = id2
        self.netmaskLen = netmaskLen

ips = [privateIP(10, 0, 8), privateIP(172, 16, 16), privateIP(192, 168, 24)]

#converts decimal number into a binary string 
def decToszBin(dec, length):
    sz_bin = ''
    for i_pos in range(length - 1, -1, -1):
        sz_bin = str(dec & 1) + sz_bin   # aggiungo il bit davanti
        dec = dec >> 1
    return sz_bin


if __name__ == '__main__':
    #hard coded net to analyze
    a = 192
    b = 168
    c = 0
    d = 0
    subnetMask = 26 

    idx = -1
    counter = 0
    #check which private range this address belongs to, based on the first octect
    for ip in ips:
        if (a == ip.id1): idx = counter
        counter += 1
    if idx != -1:

        #number of bits
        subnetLen = subnetMask - ips[idx].netmaskLen
        if subnetLen > 0:

            #remaining bits used for host addressing within each subnets
            hostLen = 32 - subnetMask
            subnetID = 1 << subnetLen
            szBin = []
            sz_bin = decToszBin(subnetID - 1, subnetLen)

            print(f'private net from group {ips[idx].id1}.{ips[idx].id2}.0.0 (netMask /{ips[idx].netmaskLen})')
            print(f'Subnet Mask /{subnetMask}; subnet ID: {sz_bin}')
            print('Available subnets:')

            #iterate for every possible subnet and print its network address 
            for i in range(0, subnetID):
                u = i << hostLen

                #switch case
                #10.0.0.0/8 
                if idx == 0:
                    print(f'{ips[idx].id1}.{(u & 0x00ff0000) >> 16}.{(u & 0x0000ff00) >> 8}.{u & 0x000000ff}')
                #172.16.0.0/16
                elif idx == 1:
                    print(f'{ips[idx].id1}.{ips[idx].id2}.{(u & 0x0000ff00) >> 8}.{u & 0x000000ff}')
                #192.168.0.0/24
                elif idx == 2:
                    print(f'{ips[idx].id1}.{ips[idx].id2}.{ips[idx].id2}.{u & 0x000000ff}')


