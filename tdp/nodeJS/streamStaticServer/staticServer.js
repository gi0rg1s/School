const http = require("http");
const hostname = '127.0.0.1';
const port = 3000;
const path = require('path');
const fs = require('fs');

const mimeTypes = {
    '.html': 'text/html',
    '.css': 'text/css',
    '.js': 'text/javascript',
    '.jpg': 'image/jpeg'
};

function requestHandler(req, res) {
    let fileName = '';
    let statusCode = 200;

    if (req.url === "/") {
        fileName = '/index.html';
    } else if (req.url === "/img/leNottiBianche") {
        fileName = '/img/leNottiBianche.jpg';
    } else if (req.url === "/img/follia") {
        fileName = '/img/follia.jpg';
    } else if (req.url === "/img/unoNessunoCentomila") {
        fileName = '/img/unoNessunoCentomila.jpg';
    } else {
        fileName = '/error.html';
        statusCode = 404;
    }

    const filePath = path.join(__dirname, fileName);
    const ext = path.extname(filePath).toLowerCase();
    const contentType = mimeTypes[ext] || 'text/html';

    //check if the stream is readable
    fs.access(filePath, fs.constants.F_OK, (err) => {
        if (err) {
            res.writeHead(404, { 'Content-Type': 'text/plain; charset=utf-8' });
            return res.end("File non trovato");
        }

        // set response headers
        res.writeHead(statusCode, { 'Content-Type': contentType });

        // create the read stream 
        const stream = fs.createReadStream(filePath);

        stream.pipe(res);

        //handle stream errors
        stream.on('error', (streamErr) => {
            console.error("Errore nello stream:", streamErr);
            if (!res.headersSent) {
                res.writeHead(500, { 'Content-Type': 'text/plain; charset=utf-8' });
            }
            res.end("Errore interno del server");
        });
    });
}

const server = http.createServer(requestHandler);

server.listen(port, hostname, function () {
    console.log(`Server running at http://${hostname}:${port}/`);
});