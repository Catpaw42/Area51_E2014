function udfIndlagt()
{
	if($("#ambulant").is(":checked"))
	{
		$("#indlagt_transport").css("display","none");
		$("#ambulant_transport").css("display","block");
	}
	else
	{
		$("#indlagt_transport").css("display","block");
		$("#ambulant_transport").css("display","none");
		//todo minus hospitalsønske
	}
}

//add function to select us-types

//add function kontrolskema

//textbox for særlige forhold kun synlig når nødvendig