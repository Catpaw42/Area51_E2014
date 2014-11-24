/**
 * 
 */
/**
 * shows popup box and calls servlet to change status of rekvisition then reloads table
 */
function bookRekvisition(id){
	if (confirm("Er du sikker du vil booke rekvisitionen?") == true) {
        $.get("BookingServlet?action=book&bookingId=" + id, function(data,status){
        	if(status == "success"){
        		location.reload(true);
        	}
        });
        
    }
}

function revisitRekvisition(id){
	if(confirm("Er du sikker du vil få rekvisitionen visiteret igen?") == true){
		$.get("BookingServlet?action=revisit&bookingId=" + id, function(data, status){
			if(status == "success"){
				console.log("id="+id);
			}
		});
	}
}

$(document).ready(function()
		{    
		    /* Get all rows from your 'table' but not the first one 
		     * that includes headers. */
		    var rows = $(".rekvisitionList tr").not(":first");
		    
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
			$('input[name="visiterAction"]').val("");
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
	$('input[name="visiterAction"]').val("");
}

$(document).ready(function()
		{    
		    /* Get all rows from your 'table' but not the first one 
		     * that includes headers. */
		    var rows = $("#rekvisitionList tr").not(":first");
		    
		    /* Create 'click' event handler for rows */
		    rows.on("click", function(e)
		    {  
		        /* Get current row */
		        var row = $(this);
		            
		        /* Highlight one row and clean others */
		        rows.removeClass("highlight");
		        row.addClass("highlight");
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