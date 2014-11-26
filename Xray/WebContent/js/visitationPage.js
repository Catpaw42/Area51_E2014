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

$('form#visiterform').on('submit', function(event){
	event.preventDefault(); 
    $.ajax({
        url: $(this).attr('action'),
        type: $(this).attr('method'),
        data: $(this).serialize(),
        success: function(html) {
        alert('ok');
        }
    });
    //return false; 
});

function showOverlay()
{
	$("#overlay").css("display", "block");
	$("#overlayPanel").css("display", "block");
}
function getvisitationString(rekvisition_Id){
	return "VisRekvisitionServlet?rekvisition_Id="+rekvisition_Id;
}

function getRekvisitation(rekvisition_Id){
	$.get(getvisitationString(rekvisition_Id),function(data,status){
	    if(status = "success"){
	    	//indsæt de hentede data i div element
	    	$("#embededSite").html(data);
	    	var visiterDiv = document.getElementById('embededSite');
	    	visiterDiv.scrollTop = 0; //scroll til top af siden
	    }
	});
	$('input[name="rekIDSubmit"]').val(rekvisition_Id);
	$('input[name="action"]').val("");
}

function doGodkend(){
	var rek_id;
	rek_id = $('input[name="rekIDSubmit"]').val(); //sæt rekvisition id på hidden element
	if(rek_id!=""){
		$('input[name="action"]').val("Godkend"); //sæt action til godkend
		$.post('WEB-INF/VisitationServlet', $('form#visiterform').serialize(), function(data) { //post form
			window.location.href = window.location.pathname; //redirect til samme side
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
		$('input[name="action"]').val("Afvis");
		$.post( 'WEB-INF/VisitationServlet', $('form#visiterform').serialize(), function(data){
			window.location.href = window.location.pathname; //redirect til samme side
		});
	}
	else{
		alert("Klik på række");
	}
}

