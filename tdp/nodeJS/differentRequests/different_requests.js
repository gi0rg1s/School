//script server.js

const http=require("http")
const hostname='192.168.185.31'
const port=3000;
const path = require('path')
const fs = require('fs')

function requestHandler(req,res) {

    if (req.url==="/") {
        const filePath = path.join(__dirname, 'index.html');
        fs.readFile(path.join(__dirname, 'index.html'), (err, data) => res.end(data))

    } else if (req.url==="/about") {
        const filePath = path.join(__dirname, 'about.html');
        fs.readFile(path.join(__dirname, 'about.html'), (err, data) => res.end(data))
    }
    else {
        const filePath = path.join(__dirname, 'notFound.html');
        fs.readFile(path.join(__dirname, 'notFound.html'), (err, data) => res.end(data))
    }
}

const server=http.createServer(requestHandler);
server.listen(port, hostname, function () {
console.log(`Server running at
http://${hostname}:${port}/`);

});