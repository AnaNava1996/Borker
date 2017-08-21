var $xml,objSet = new Set(),cuentasSet = new Set(),cuentaName = new Set();

$(document).ready(function(){
    $.get("Main",function(data){
        xmlDoc = $.parseXML( data );
        $xml = $( xmlDoc );
        autoC();
    });
});

function autoC(){
    $xml.children().children().each(function(){
                $("<option/>").html($(this).attr("name")).appendTo("#mcuentas");
                objSet.add($(this).text());
    });
}

function clearAll(){
    $("#inside").html('<br/>');
    objSet.clear();
    cuentasSet.clear();
    cuentaName.clear();
}

function sendData(){
    var data='';
    cuentasSet.forEach(function(element){
	data = JSON.stringify(element);
	$.post("Main",
	   {
	       index:"save",
	       cuenta_name:element.nombre,
	       cuenta_type:element.tipo,
	       cuenta_info:data
	   },
	   function(){});
    });
    clearAll();
    
}

function setCuenta(){
    if(!$("#tcuenta").val().length||
       !$("#tcantidad").val().length||
       !$("#tycuenta").val().length){

	$("#alerta").html('Debe llenar todos los campos.');
	window.setTimeout(function(){
	    $("#alerta").html('');
	},1000);
			  
	return;
    }
    
    var insideHTML='<table>';
    var cuenta = new Cuenta($("#tcuenta").val(),$("#tycuenta").val(),[]);
    if(!cuentaName.has(cuenta.nombre)){
        cuenta.movs.push($("#tcantidad").val());
        cuentasSet.add(cuenta);
        cuentaName.add(cuenta.nombre);
    }
    else {
        cuentasSet.forEach(function(element){
           if(element.nombre === cuenta.nombre){
               element.movs.push($("#tcantidad").val());
           } 
        });
    }
    cuentasSet.forEach(function(element){
	insideHTML=insideHTML.concat('<tr><th>'+element.nombre+'</th></tr><tr><td><ul>');
        element.movs.forEach(function(thing){
	    insideHTML=insideHTML.concat('<li>'+thing+'</li>');
        });
	insideHTML=insideHTML.concat('</ul></td></tr>');
    });
    insideHTML=insideHTML.concat('</table>');
    document.getElementById('inside').innerHTML = insideHTML;
    $("#tcuenta").val(''),$("#tcantidad").val(''),$("#tycuenta").val('');
}
function getToShow(){
    $.get("Main",function(data){
        xmlDoc = $.parseXML( data );
        $xml = $( xmlDoc );
        $xml.children().forEach(function(node){
            alert(node.get(1));
        });
    });
    $("div.containerC").html('<br/>');
}