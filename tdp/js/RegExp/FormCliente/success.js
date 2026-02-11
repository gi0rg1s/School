const params = new URLSearchParams(window.location.search);

const nome = params.get("nome") || "";
const email = params.get("email") || "";
const telefono = params.get("telefono") || "";
const budget = params.get("budget") || "";
const serviziParam = params.get("servizi") || "";
const servizi = serviziParam ? serviziParam.split(",") : [];

const rates = {
    html: 20,
    php: 25,
    asp: 30,
    java: 35,
    cpp: 40
};

const total = servizi.reduce((sum, servizio) => sum + (rates[servizio] || 0), 0);

document.getElementById("riepilogo-nome").textContent = nome;
document.getElementById("riepilogo-email").textContent = email;
document.getElementById("riepilogo-telefono").textContent = telefono;
document.getElementById("riepilogo-budget").textContent = budget;
document.getElementById("riepilogo-totale").textContent = total.toFixed(2) + " €";

const serviziList = document.getElementById("riepilogo-servizi");
servizi.forEach(servizio => {
    const li = document.createElement("li");
    li.textContent = `${servizio.toUpperCase()} - ${rates[servizio]} €/h`;
    serviziList.appendChild(li);
});

let totalCost = 0;
servizi.forEach(servizio => {
    totalCost += rates[servizio] * 10; // Assuming 10 hours of work for each service
});

document.getElementById("riepilogo-totale").textContent = totalCost.toFixed(2) ;

if(totalCost > budget)  document.getElementById("over-budget").textContent = "Attenzione: il totale supera il budget indicato!";
