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
        alert("ID=" +e.target.id);
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


function cancelRekvisition(id) {
    if (confirm("Er du sikker du vil annullere rekvisitionen?") == true) {
        $.get("RekvisitionServlet?action=cancel&cancelId=" + id, function(data,status){
        	if(status == "success"){
        		location.reload(true);
        	}
        });
        
    }
}