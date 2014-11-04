function transportCheck()
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
	}
}