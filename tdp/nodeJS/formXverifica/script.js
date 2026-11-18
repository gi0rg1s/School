const http = require('http');
const fs = require('fs');
const path = require('path');

const PORT = 3000;

const prices = [
    { product: "notti-bianche", price: 10 },
    { product: "ricordi-sottosuolo", price: 11 },
    { product: "idiota", price: 15 },
    { product: "adolescente", price: 16 },
    { product: "fratelli-karamazov", price: 19 },
    { product: "mite", price: 8 }
];

function validateForm(params) {
    const prodotto = (params.get('product') || '').trim();
    const quantita = (params.get('quantita') || '').trim();

    const errors = {};
    if (!prices.some(p => p.product === prodotto)) {
        errors.product = 'Non hai selezionato un prodotto valido';
    }
    if (!/^[1-9][0-9]*$/.test(quantita)) {
        errors.quantita = 'Devi selezionare una quantità pari o superiore a 1';
    }
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
    if (req.method === 'GET') {
        fs.readFile(path.join(__dirname, 'index.html'), (err, data) => {
            if (err) {
                res.writeHead(500, { 'Content-Type': 'text/plain' });
                res.end('Server Error loading index.html');
                return;
            }
            res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
            res.end(data);
        });
    }
    else if (req.method === 'POST') {
        let body = '';

        req.on('data', chunk => {
            body += chunk.toString();
        });

        req.on('end', () => {
            const params = new URLSearchParams(body);
            const errors = validateForm(params);

            if (Object.keys(errors).length === 0) {
                const prodotto = params.get('product')
                const q = parseInt(params.get('quantita'))
                fs.readFile(path.join(__dirname, 'riepilogo.html'), 'utf8', (err, data) => {
                    if (err) {
                        res.writeHead(500, { 'Content-Type': 'text/plain' });
                        res.end('Server Error loading riepilogo.html');
                        return;
                    }
                    
                    const personalizedHTML = data
                    .replace('id="prezzo"></p>', `id="prezzo">Prezzo: ${prices.find(p=> p.product == prodotto).price} €</p>`)
                    .replace('id="totale"></p>', `id="totale">Totale: ${prices.find(p=> p.product == prodotto).price * q} €</p>`);


                    res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
                    res.end(personalizedHTML);
                });
            } else {
                sendIndexWithErrors(res, errors);
            }
        });
    }
    else {
        res.writeHead(405, { 'Content-Type': 'text/plain' });
        res.end('Method not allowed');
    }
}

const server = http.createServer(requestHandler);

server.listen(PORT, () => {
    console.log(`Server active on http://127.0.0.1:${PORT}`);
});