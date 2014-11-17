$(function(){
	$("#patientData .required").prop('required',true);
	$("#rekvirentData .required").prop('required',true);
	$("#undersoegelsesData .required").prop('required',true);
});

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

function showSkema()
{
	$("#CTSkema").css("display","none");
	$("#CTSkema .required").prop('required',false);
	$("#MRSkema").css("display","none");
	$("#MRSkema .required").prop('required',false);
	$("#PETCTSkema").css("display","none");
	$("#PETCTSkema .required").prop('required',false);
	$("#ULInvSkema").css("display","none");
	$("#ULInvSkema .required").prop('required',false);
	
	switch ($("#modalitet_navn").val())
	{
		case "3":
			$("#ULInvSkema").css("display","block");
			$("#ULInvSkema .required").prop('required',true);
			break;
		case "8":
			$("#MRSkema").css("display","block");
			$("#MRSkema .required").prop('required',true);
			break;
		case "5":
			$("#CTSkema").css("display","block");
			$("#CTSkema .required").prop('required',true);
			break;
		case "6":
			$("#PETCTSkema").css("display","block");
			$("#PETCTSkema .required").prop('required',true);
			break;
		default:
			break;
	}
}

//textbox for særlige forhold kun synlig når nødvendig