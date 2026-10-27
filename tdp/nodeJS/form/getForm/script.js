const http = require('http');
const fs = require('fs');
const path = require('path');
const { URL } = require('url');

const PORT = 3000;

// authorized users
const authorizedUsers = [
    { username: 'giorgis', password: '1aMth3bo$$' },
    { username: 'manuAlt', password: 'secret2024' },
    { username: 'Mira', password: 'IwantMyFoodMAOMAOMAAAAAo' }
];

// support function to verify credentials
function verificaCredenziali(username, password) {
    return authorizedUsers.some(u => u.username === username && u.password === password);
}

const server = http.createServer((req, res) => {
    const baseURL = `http://${req.headers.host}`;
    const parsedUrl = new URL(req.url, baseURL);

    const username = parsedUrl.searchParams.get('username');
    const password = parsedUrl.searchParams.get('password');

    // -------------- GET request ----------------
    if (username && password) {
        if (verificaCredenziali(username, password)) {
            fs.readFile(path.join(__dirname, 'personalArea.html'), 'utf8', (err, htmlData) => {
                if (err) {
                    res.writeHead(500, { 'Content-Type': 'text/plain' });
                    res.end('Server Error loading personal area page.');
                    return;
                }

                // Sostituiamo direttamente i valori nel template HTML lato Server
                const personalizedHTML = htmlData
                    .replace('id="welcome">', `id="welcome">Welcome ${username}!`)
                    .replace('id="method">', `id="method">Request Effettueted with GET method`);

                res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
                res.end(personalizedHTML); // Inviamo l'HTML modificato
            });
        } else {
            res.writeHead(401, { 'Content-Type': 'text/html; charset=utf-8' });
            res.end('<h1>Error: Unauthorized user.</h1>');
        }
    } 
    // return to the index page
    else {
        fs.readFile(path.join(__dirname, 'index.html'), (err, data) => {
            if (err) {
                res.writeHead(500, { 'Content-Type': 'text/plain' });
                res.end('Server Error loading page.');
                return;
            }
            res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
            res.end(data);
        });
    }
});

server.listen(PORT, () => {
    console.log(`GET Server active on http://localhost:${PORT}`);
});