function udfIndlagt()
{
	if($("#ambulant").is(":checked"))
	{
		$("#indlagtTransport").css("display","none");
		$("#ambulantTransport").css("display","block");
	}
	else
	{
		$("#indlagtTransport").css("display","block");
		$("#ambulantTransport").css("display","none");
		//todo minus hospitalsønske
	}
}

//add function to select us-types

//add function kontrolskema

//textbox for særlige forhold kun synlig når nødvendig