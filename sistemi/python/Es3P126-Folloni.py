class Classful:
    def __init__(self, mask, leading, netmask):
        self.mask = mask
        self.leading = leading
        self.netmask = netmask

    
class ipInfo:
    def __init__(self, a, b, c, d):
        self.a = a
        self.b = b
        self.c = c
        self.d = d
        self.ip = 0
        self.netId = 0


if __name__ == '__main__':

    classiIp = [Classful(0x80, 0x00, 0xff000000), Classful(0xc0, 0x80, 0xffff0000), Classful(0xe0, 0xc0, 0xffffff00)]
    
    ips = [ipInfo(10, 1, 0, 3), ipInfo(192, 168, 0, 1), ipInfo(137, 168, 0, 1), ipInfo(137, 168, 249, 17)]


    for ip in ips:
        counter = 0
        ip.ip = (ip.a * 0x1000000) + (ip.b  * 0x10000) + (ip.c * 0x100) + ip.d
        for ipClass in classiIp:
            if(ip.a & ipClass.mask) == ipClass.leading:
                ip.netId = ip.ip & ipClass.netmask
                print(f'{ip.a}.{ip.b}.{ip.c}.{ip.d} --> class {chr(ord('A') + counter)} (mask: {ipClass.netmask:#x}) and net id: {ip.netId:#x}')
                break
            counter += 1

    for ip in ips:
        for ip2 in ips:

            if ip.netId == ip2.netId:
                print(f'{ip.a}.{ip.b}.{ip.c}.{ip.d} and {ip2.a}.{ip2.b}.{ip2.c}.{ip2.d} are in the same network')