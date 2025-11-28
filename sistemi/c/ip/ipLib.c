void parse_cli (int argc, char* argv[]){
    if(argc == 0) generate_ip(1);
    else if(argc >= 1){
        for (int i = 0; i < argc; i++){
            char* str = argv[i];        //temporary var
            if(str[0] == '-')  //parser for -n<number>

            switch (str[1])
            {
            case 'n':
                if(str[2] >= '0' && str[2] <= '9') generate_ip((int)str[2] - '0');
                break;
            
            case 'c':
                if(str[2] >= 'A' && str[2] <= 'E'){
                    ip_t ip;

                }
                break;

            case 'i':
            //not implemented yet
                break;

            default:
                break;
            }
        }
            
    }
}
uint8_t generate_ip (int n){

}