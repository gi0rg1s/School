google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(update);

function update(){
    var params = getParams()
    createChart(params)
}

function getParams() {
    return {
        a: parseFloat(document.getElementById('param-a').value),
        b: parseFloat(document.getElementById('param-b').value),
        c: parseFloat(document.getElementById('param-c').value),
        xMax: parseFloat(document.getElementById('max-n').value),
        xMin: parseFloat(document.getElementById('min-n').value),
        step: parseFloat(document.getElementById('n').value)
    }
}

function createChart(data){
    var dataTable = new google.visualization.DataTable()
    dataTable.addColumn('number', 'x')
    dataTable.addColumn('number', 'f(x)')

    var rows = (data['xMax'] - data['xMin']) / data['step']
    for (var i = 0; i <= rows; i++) {
        var x = data['xMin'] + i * data['step']
        var y = data['a'] * x * x + data['b'] * x + data['c']
        dataTable.addRow([x, y])
    }

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'))
    var options = { title: 'f(x) = ax² + bx + c' }
    chart.draw(dataTable, options)
}
document.getElementById('param-a').addEventListener('input', update)
document.getElementById('param-b').addEventListener('input', update)
document.getElementById('param-c').addEventListener('input', update)    
document.getElementById('max-n').addEventListener('input', update)
document.getElementById('min-n').addEventListener('input', update)
document.getElementById('n').addEventListener('input', update)