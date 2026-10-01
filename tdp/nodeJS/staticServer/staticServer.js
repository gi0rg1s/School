const http = require("http");
const hostname = '127.0.0.1';
const port = 3000;
const path = require('path');
const fs = require('fs');

function requestHandler(req, res) {
    let fileName = '';
    let statusCode = 200;

    if (req.url === "/") {
        fileName = 'index.html';
    } else if (req.url === "/about") {
        fileName = 'about.html';
    } else {
        fileName = 'notFound.html';
        statusCode = 404;
    }
    const filePath = path.join(__dirname, fileName);

    fs.readFile(filePath, (err, data) => {
        if (err) {
            res.writeHead(500, { 'Content-Type': 'text/plain' });
            return res.end("Errore interno del server");
        }
        res.writeHead(statusCode, { 'Content-Type': 'text/html' });
        res.end(data);
    });
}

const server = http.createServer(requestHandler);

server.listen(port, hostname, function () {
    console.log(`Server running at http://${hostname}:${port}/`);
});