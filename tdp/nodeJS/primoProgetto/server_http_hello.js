// script server_http_hello.js

const http=require("http")
const hostname='127.0.0.1';
const port=3000;

//asincrono -> funzione dentro funzione
function requestHandler(request,response) {
    console.log("In comes a request to: "+request.url);
    response.writeHead(200,{'Content-Type':'text/plain'});
    response.end("Hello,World!");

}

const server=http.createServer(requestHandler);
server.listen(port, hostname, function () {
console.log(`Server running at http://${hostname}:${port}/`);

});