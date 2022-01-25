
$(document).ready( function() {
    var anio = 2018;
    //var inicio = new Date(Date.UTC(anio));
    //var inicioTime = inicio.getTime();
    var finTime = new Date(Date.now());
    var finAnio = finTime.getUTCFullYear();
    
    var anios = [];
    while(anio <= finAnio){
        var fechas =  new Date(Date.UTC(anio));
        console.log(fechas.toUTCString());
        anios.push(anio.toString() + "-1");
        anios.push(anio.toString() + "-2");
        anio += 1;
    }

    const op = document.createElement('option');
    op.text = "Selecciona un período";
    $("#selectPeriodo").empty();
    $("#selectPeriodo").append(op);
    anios.forEach(element => {
        const option = document.createElement('option');
        option.value = element;
        option.text = element;
        $("#selectPeriodo").append(option);
    });
    console.log(anios);
});