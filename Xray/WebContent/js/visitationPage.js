$(document).ready(function()
{    
    /* Get all rows from your 'table' but not the first one 
     * that includes headers. */
    var rows = $(".visitationList tr").not(":first");
    
    /* Create 'click' event handler for rows */
    rows.on("click", function(e)
    {  
        /* Get current row */
        var row = $(this);
            
        /* Highlight one row and clean others */
        rows.removeClass("highlight");
        row.addClass("highlight");
        getRekvisitation(e.target.id);
    });
    
    /* This 'event' is used just to avoid that the table text 
     * gets selected (just for styling). 
     * For example, when pressing 'Shift' keyboard key and clicking 
     * (without this 'event') the text of the 'table' will be selected.
     * You can remove it if you want, I just tested this in 
     * Chrome v30.0.1599.69 */
    $(document).on("selectstart dragstart", function(e) { 
        e.preventDefault(); return false; 
    });
});
function showOverlay()
{
	$("#overlay").css("display", "block");
	$("#overlayPanel").css("display", "block");
}
function getvisitationString(rekvisition_Id){
	return "visiter.jsp?rekvisition_Id="+rekvisition_Id;
}

function getRekvisitation(rekvisition_Id){
	$.get(getvisitationString(rekvisition_Id),function(data,status){
	    if(status = "success"){
	    	//indsæt de hentede data i div element
	    	$("#embededSite").html(data);
	    }
	});
	$('input[name="rekIDSubmit"]').val(rekvisition_Id);
	$('input[name="visiterAction"]').val("");
}

function doGodkend(){
	var rek_id;
	rek_id = $('input[name="rekIDSubmit"]').val();
	if(rek_id!=""){
		$('input[name="visiterAction"]').val("Godkend");
		$.post( 'VisitationServlet', $('form#visiterform').serialize(), function(data) {
			location.reload(true);
	});
	}
	else{
		alert("Klik på række");
	}
	
}

function doAfvis(){
	var rek_id;
	rek_id = $('input[name="rekIDSubmit"]').val();
	if(rek_id!=""){
		$('input[name="visiterAction"]').val("Afvis");
		$.post( 'VisitationServlet', $('form#visiterform').serialize(), function(data){
			location.reload(true);
	});

	}
	else{
		alert("Klik på række");
	}
	
}

function testpost(){
//	  context = '${pageContext.request.contextPath}';
//	  var http_request = getXMLHttpRequest();
//	            if (!http_request) {
//	        alert('Giving up :( Cannot create an XMLHTTP instance');
//	        return false;
//	    }
	    alert("hej");
//	    http_request.open("POST", context, true);
//	    http_request.onreadystatechange = function() {
//	        if (http_request.readyState == 4) {
//	            if (http_request.status == 200) {
//	                var response = http_request.responseText;
//	                //do something if you have a response from the servlet
//	            }
//	        }
//	    };
//	    http_request.send(null);
	}

function customJSFunction(){
	proto=window.location.protocol;
	host=window.location.host;
	context=window.location.pathname;
	context=window.location.pathname.substring(0,context.lastIndexOf("/"));
	context=context.replace("/pages", ""); //applicationcontext
	var xmlHttp;
	try
	{
		// Firefox, Opera 8.0+, Safari
		xmlHttp=new XMLHttpRequest();
	}
	catch (exception)
	{ // Internet Explorer
		try
		{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (exception)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (exception)
			{
				alert("Your browser does not support AJAX!");
				return false;
			}
		}
	}
	var host = window.location.host;
	url = proto+"//"+host+context+"/VisitationServlet";
	//alert("url ==== "+url);
	xmlHttp.open("POST",url,true); //the value true is for using asynchronous mode
	xmlHttp.send(null);
	xmlHttp.onreadystatechange=function(){
		if(xmlHttp.readyState==4)
		{
			alert("POST executed successfully");
		}
	};
}