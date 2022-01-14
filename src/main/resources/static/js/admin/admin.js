$(document).ready( function() {
    var myModal = new bootstrap.Modal(document.getElementById('mensajeEliminar'));
    $(".btn-rechazar").on('click', function(event){
        let id = $(this).val();
        $("#idRechazar").val(id);
		myModal.toggle();
    });
    
    if ($('.alertaUpdate').length) {
        setTimeout(function() {
            $('.alertaUpdate').fadeOut(1000);
        },4000);
    }

    var lastId = -1;
    var lastNum = 1;
    var lastIndex = 0;
    $("td.m").each(function(index, item){
        console.log("I -> " + $(item).text());
        var classList = $(item).attr('class').split(/\s+/);
        $.each(classList, function(indexC, itemC) {
            const exp = /^m[0-9]+$/;
            if (exp.test(itemC) && itemC != lastId) {
                if(lastId != -1){
                    $("td." + lastId).eq(0).attr('rowspan', lastNum);
                    $("td." + lastId).eq(0).attr('class', 'm ' + lastId + 'l');
                    $("td." + lastId).remove();
                }
                lastNum = 1;
                lastId = itemC;
                lastIndex = index;
                console.log("cambio");
            }else if(exp.test(itemC)){
                lastNum++;
            }
        });
    });
});