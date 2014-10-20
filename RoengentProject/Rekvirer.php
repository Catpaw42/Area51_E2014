<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<div id="mainmenu">
  <table width="1000" border="1">
    <tr>
      <td>Area51</td>
      <td><a href="main.php">Rekvirer</a></td>
      <td><a href="main.php">Visiter</a></td>
      <td><a href="main.php">Book</a></td>
      <td><p>Brian Bjørn</p>
      <p>Visitator</p></td>
    </tr>
  </table>
</div>'

<div id="mainpage">
<table width="1000" border="1">
  <tr>
    <td width="252">
      <h3><a href="rekvisition.php">Ny rekvisition</a></h3>
      <hr />
      <h3>Søg    </h3>
      <form id="form1" name="form1" method="post" action="">
        <table width="1000" border="1">
          <tr>
            <th width="82" scope="col">Cpr</th>
            <th width="68" scope="col">Modalitet</th>
            <th width="45" scope="col">Afd</th>
            <th scope="col">Dato</th>
            <th scope="col">Status</th>
            </tr>
          <tr>
            <td width="82"><input name="cpr" type="text" disabled="disabled" id="cpr" size="10" maxlength="11" /></td>
            <td><select name="modality" id="modality">
              <option value="ul">UL - radiograf</option>
              <option value="ct">CT</option>
              <option value="rg">Pet/CT</option>
              <option value="rtg">Rtg.</option>
              </select></td>
            <td><select name="department" id="department">
              <option value="o">O</option>
              <option value="m" selected="selected">M</option>
              <option>Alle</option>
              </select></td>
            <td>&nbsp;</td>
            <td><select name="status" id="status">
              <option value="sendt">Sendt</option>
              <option value="visiteret">Visiteret</option>
              <option value="anulleret">Anulleret</option>
              <option value="afvist">Afvist</option>
              <option value="booket">Booket</option>
              </select></td>
            </tr>
  </table>
        </form>
      <table width="1000" border="1">
        <tr>
          <th width="89" bgcolor="#CCCCCC" scope="col">Cpr</th>
          <th width="87" bgcolor="#CCCCCC" scope="col">Modalitet</th>
          <th width="49" bgcolor="#CCCCCC" scope="col">Afd</th>
          <th width="90" bgcolor="#CCCCCC" scope="col">Dato</th>
          <th width="90" bgcolor="#CCCCCC" scope="col">Status</th>
          </tr>
        <tr>
          <td>255255-5555</td>
          <td>UL</td>
          <td>O</td>
          <td>251014</td>
          <td>Sendt</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
</table></td>
    </tr>
</table>

</div>
</body>
</html>