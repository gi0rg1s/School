const http = require('http');
const fs = require('fs');
const path = require('path');
const { URL } = require('url');

const PORT = 3000;

const regioni = ['valleAosta','piemonte','liguria','lombardia','trentino','veneto',
    'friuli','emilia','toscana','umbria','marche','lazio','abruzzo',
    'molise','campania','puglia','basilicata','calabria','sicilia','sardegna'];

// support function to send a file from the project folder
function sendFile(res, fileName, contentType, status = 200) {
    fs.readFile(path.join(__dirname, fileName), (err, data) => {
        if (err) {
            res.writeHead(500, { 'Content-Type': 'text/plain' });
            res.end('Server Error loading ' + fileName);
            return;
        }
        res.writeHead(status, { 'Content-Type': contentType });
        res.end(data);
    });
}

// support function: returns the list of errors (empty list = data valid)
function validaDati(params) {
    const name      = (params.get('name') || '').trim();
    const surname   = (params.get('surname') || '').trim();
    const matricola = (params.get('matricola') || '').trim();
    const regione   = (params.get('regione') || '').trim();
    const email     = (params.get('email') || '').trim();
    const telephone = (params.get('telephone') || '').trim();
    const requests  = (params.get('requests') || '').trim();

    const errors = {};

    if (!/^[\p{L}' -]{2,50}$/u.test(name))    errors.name = 'Nome non valido';
    if (!/^[\p{L}' -]{2,50}$/u.test(surname)) errors.surname = 'Cognome non valido';
    if (!/^\d{6,8}$/.test(matricola))         errors.matricola = 'Matricola non valida (6-8 cifre)';
    if (!regioni.includes(regione))           errors.regione = 'Regione non valida';
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) errors.email = 'Email non valida';
    if (!/^\+?[0-9 ]{8,15}$/.test(telephone)) errors.telephone = 'Numero di telefono non valido';
    if (requests.length > 500)                errors.requests = 'Richieste troppo lunghe';

    return errors;
}
function sendIndexWithErrors(res, errors) {
    fs.readFile(path.join(__dirname, 'index.html'), 'utf8', (err, htmlData) => {
        if (err) {
            res.writeHead(500, { 'Content-Type': 'text/plain' });
            res.end('Server Error loading index.html');
            return;
        }

        let personalizedHTML = htmlData;
        for (const campo in errors) {
            personalizedHTML = personalizedHTML.replace(
                `id="error-${campo}"></span>`,
                `id="error-${campo}">${errors[campo]}</span>`
            );
        }

        res.writeHead(400, { 'Content-Type': 'text/html; charset=utf-8' });
        res.end(personalizedHTML);
    });
}

function requestHandler(req, res) {
    const baseURL = `http://${req.headers.host}`;
    const parsedUrl = new URL(req.url, baseURL);

    // static files requested by the HTML pages
    if (parsedUrl.pathname === '/style.css') {
        return sendFile(res, 'style.css', 'text/css; charset=utf-8');
    }
    if (parsedUrl.pathname === '/script.js') {
        return sendFile(res, 'script.js', 'application/javascript; charset=utf-8');
    }

    // -------------- GET request ----------------
    if (parsedUrl.pathname === '/' && parsedUrl.search !== '') {
        const errors = validaDati(parsedUrl.searchParams);

        if (Object.keys(errors).length === 0) {
            // correct data
            sendFile(res, 'successful.html', 'text/html; charset=utf-8');
        } else {
            // incorrect data: stay on the form and show the errors under each field
            sendIndexWithErrors(res, errors);
        }
    }
    // return to the index page
    else if (parsedUrl.pathname === '/') {
        sendFile(res, 'index.html', 'text/html; charset=utf-8');
    }
    else {
        res.writeHead(404, { 'Content-Type': 'text/html; charset=utf-8' });
        res.end('<h1>404 - Page not found</h1>');
    }
}

const server = http.createServer(requestHandler);

server.listen(PORT, () => {
    console.log(`GET Server active on http://127.0.0.1:${PORT}`);
});