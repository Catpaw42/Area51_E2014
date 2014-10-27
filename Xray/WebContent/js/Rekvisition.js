function transportCheck() {
		if(document.getElementById("ambulant").checked)
		{
			document.getElementById("indlagtTransport").style.display = "none";
			document.getElementById("ambulantTransport").style.display = "block";
		}
		else
		{
			document.getElementById("indlagtTransport").style.display = "block";
			document.getElementById("ambulantTransport").style.display = "none";
		}
	}