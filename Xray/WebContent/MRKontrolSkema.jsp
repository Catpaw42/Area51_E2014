<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
		<title></title>
	</head>
	<body class="MRKontrolSkema">
		<h2>MR Kontrolskema</h2>
		<p>Kontrolskemaet gennemgås af hendvisende læge sammen med patienten. Patienten skal forberedes 
		på eventuel kontrastinjektion. Samtlige punkter SKAL besvares (også højde og vægt), ellers 
		returneres skemaet og henvisningen.</p>
		<ul>
			<li>
				<p>Pacemaker, efterladte paceelektroder
				Ja<input class="required" type = "radio" id="pacemakerJa" name="pacemaker">
				Nej<input class="required" type = "radio" id="pacemakerNej" name="pacemaker"></p>
			</li>
			<li>
				<p>Metalliske implantater eller andet metallisk materiale fra hjerte-, <br> neuro- eller anden kirugi.
				Ja<input type = "radio" id="metal_implantaterJa" name="metal_implantater">
				Nej<input type = "radio" id="metal_implantaterNej" name="metal_implantater"></p>
				<p>Type og operationsår<p>
				<input type="text" id="metal_implantater_beskrivelse" name="metal_implantater_beskrivelse">
			</li>
			<li>
				<p>Andet metallisk materiale
				Ja<input type = "radio" id="andet_metaliskJa" name="andet_metalisk">
				Nej<input type = "radio" id="andet_metaliskNej" name="andet_metalisk"></p>
				<p>Type og operationsår<p>
				<input type="text" id="andet_metalisk_beskrivelse" name="andet_metalisk_beskrivelse">
			</li>
			<li>
				<p>Har patienten kendt nyresygdom
				Ja<input type = "radio" id="nyresygdomJa" name="nyresygdom">
				Nej<input type = "radio" id="nyresygdomNej" name="nyresygdom"></p>
				<p>P-Creatinin (max. 7 dage gammel)</p>
				<input type="text" id="nyresygdom_kreatinin" name="nyresygdom_kreatinin" placeholder="&#x3bcmol/l">
			</li>
			<li>
				<p>Graviditet
				Ja<input type = "radio" id="graviditetJa" name="graviditet">
				Nej<input type = "radio" id="graviditetNej" name="graviditet"></p>
				<p>Graviditetsuge</p>
				<input type="text" id="graviditet_uge" name="graviditet_uge">
			</li>
			<li>
				<p>Klaustrofobi
				Ja<input type = "radio" id="klaustrofobiJa" name="klaustrofobi">
				Nej<input type = "radio" id="klaustrofobiNej" name="klaustrofobi"></p>
			</li>
			<li>
				<p>Højde</p>
				<input type="text" id="hoejde" name="hoejde">
			</li>
			<li>
				<p>Vægt</p>
				<input type="text" id="vaegt" name="vaegt">
			</li>
			<li>
				<p>Ved MR skanning af børn <br>
				Uden sedering<input type = "radio" id="uden_sedering" name="sederingBoern">
				I generel anaestesi<input type = "radio" id="i_generel_anaestesi" name="sederingBoern"></p>
			</li>
			<li>
				<p>Ved MR skanning af voksne <br>
				Uden sedering<input type = "radio" id="uden_sedering" name="sederingVoksne">
				Med sedering<input type = "radio" id="med_sedering" name="sederingVoksne">
				I generel anaestesi<input type = "radio" id="i_generel_anaestesi" name="sederingVoksne"></p>
			</li>
			<li>
				<p>Præparat</p>
				<input type="text" id="praep_forsyn" name="praep_forsyn">
			</li>
		</ul>
	</body>
</html>