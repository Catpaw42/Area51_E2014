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