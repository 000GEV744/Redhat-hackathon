<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Rentickle-Message</title>
</head>

<body>
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" valign="top" bgcolor="black"
				style="background-color: "black";"><br> <br>
				<table width="600" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" bgcolor="#d3be6c"
							style="background-color: #d3be6c; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px 15px;">
							
							<div style="font-size: 48px; color:blue;">
								<h2><b>Health Tracker<b></h2>
								<br>Keeps you fit</br>
							</div>
							
							<div style="font-size: 24px; color: #555100;">
								<br> Please verify your email ID !!! <br>
							</div>
							<div>
								<br>Hello <b>${fName} ${lName}</b>, <br> <br>
								Please verify your email by entering the clicking the link:<a href="http://localhost:8005/validateEmail/token/${token}">Verify Email</a>
								<br> "Your verified email ID will help you login to your Health Tracker Account without 
								any hassle. Hence, it is crucial to get it verified."<br>
								<br>Thank you for choosing us. <br> <br>
								<br> <br> ${location}<br>
								
								<br>
							</div>
						</td>
					</tr>
				</table> <br> <br></td>
		</tr>
	</table>
</body>
</html>
