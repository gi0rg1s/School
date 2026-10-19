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
    let fileName = '/input.txt';
    let statusCode = 200;

    if (req.url !== "/") {
        fileName = '/error.html';
        statusCode = 404;
    }

    const filePath = path.join(__dirname, fileName);
    const ext = path.extname(filePath).toLowerCase();
    const contentType = mimeTypes[ext] || 'text/plain';

    if (statusCode === 200) {
        const inputPath = path.join(__dirname, 'input.txt');
        const outputPath = path.join(__dirname, 'output.txt');

        const readStream = fs.createReadStream(inputPath);
        const writeStream = fs.createWriteStream(outputPath);

        readStream.pipe(writeStream);

        res.writeHead(statusCode, { 'Content-Type': contentType });
        readStream.pipe(res);

        //if any error was caught
        readStream.on('error', (err) => {
            res.writeHead(500, { 'Content-Type': 'text/plain' });
            res.end('Errore del server durante la lettura del file.');
        });
    } else {

        // error page management
        res.writeHead(statusCode, { 'Content-Type': contentType });
        const errorStream = fs.createReadStream(filePath);
        errorStream.pipe(res);
        
        errorStream.on('error', () => {
            res.end('404 Not Found');
        });
    }
}

const server = http.createServer(requestHandler);

server.listen(port, hostname, () => {
    console.log(`Server attivo su http://${hostname}:${port}/`);
});