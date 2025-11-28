void parse_cli (int argc, char* argv[]){
    if(argc == 0) generate_ip(1);
    else if(argc >= 1){
        char* str = argv[0];        //temporary var
        if(str[0] == '-' && str[1] == 'n' && str[2] >= '0' && str[2] <= '9')  //parser for -n<number>
            generate_ip(argv -'0');         //generate n ipv4
    }
}
//uint8_t generate_ip (int n){

//}