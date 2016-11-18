function validateform() {
	var fullname = document.forms["register"]["full_name"].value;
	var username = document.forms["register"]["username"].value;
	var email = document.forms["register"]["email"].value;
	var pass = document.forms["register"]["pass"].value;
	var cpass = document.forms["register"]["confirm_pass"].value;
	var fulladr = document.forms["register"]["full_address"].value;
	var postalcode = document.forms["register"]["postal_code"].value;
	var phone = document.forms["register"]["phone_number"].value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(fullname === null || fullname === ""){
		alert("Please fill the fullname field. ");
		return false;
	}
	if(username === null || username === ""){
		alert("Please fill the username field. ");
		return false;
	}
	if(email === null || email === ""){
		alert("Please fill the email. ");
		return false;
	}
	/*
	if(mailformat.test(document.register.email) ==  false){
		alert("Please enter the valid email address");
		document.register.mail.focus();
		return false;
	}*/
	if(isvalidemail(email) ==  false){
		alert("Please enter the valid email address");
		// document.register.mail.focus();
		return false;
	}
	if(pass === null || pass === ""){
		alert("Please fill the password. ");
		return false;
	}
	if(cpass === null || cpass === "" || cpass !== pass){
		alert("Please enter the same password. ");
		return false;
	}
	if(fulladr === null || fulladr === ""){
		alert("Please fill your address. ");
		return false;
	}
	if(fulladr.toString().length === 0 ){
		alert("Address can't be empty. ");
		return false;
	}
	if(postalcode === null || postalcode === ""){
		alert("Please fill the postal code. ");
		return false;
	}
	if(postalcode % 1 !== 0){
		alert("Postal code must be a number");
		return false;
	}
	if(phone === null || phone === ""){
		alert("Please enter your phone number. ");
		return false;
	}
	if(phone % 1 !== 0){
		alert("Phone number must be a number");
		return false;
	}
	
	var retVal = confirm("Are you sure with your input?");
   if( retVal == true ){
      document.getElementById("register").submit();
      // window.location.assign("http://localhost/purchases.php");
      // return true;
   }
   else{
      // document.write ("User does not want to continue!");
      // return false;
   }
	
}

function isvalidemail(email) {

    // Get email parts
    var emailParts = email.split('@');

    // There must be exactly 2 parts
    if(emailParts.length !== 2) {
        return false;   
    }

    // Name the parts
    var emailName = emailParts[0];
    var emailDomain = emailParts[1];

    // === Validate the parts === \\

    // Must be at least one char before @ and 3 chars after
    if(emailName.length < 1 || emailDomain.length < 3) {
        return false;
    }

    // Define valid chars
    var validChars = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','.','0','1','2','3','4','5','6','7','8','9','_','-'];

    // emailName must only include valid chars
    for(var i = 0; i < emailName.length; i += 1) {
        if(validChars.indexOf(emailName.charAt(i)) < 0 ) {
            alert("Invalid character in name section");
            return false;   
        }
    }
    // emailDomain must only include valid chars
    for(var j = 0; j < emailDomain.length; j += 1) {
        if(validChars.indexOf(emailDomain.charAt(j)) < 0) {
            alert("Invalid character in domain section");
            return false;   
        }
    }

    // Domain must include but not start with .
    if(emailDomain.indexOf('.') <= 0) {
        alert("Domain must include but not start with .");
        return false;
    }

    // Domain's last . should be 2 chars or more from the end
    var emailDomainParts = emailDomain.split('.');
    if(emailDomainParts[emailDomainParts.length - 1].length < 2) {
        alert("Domain's last . should be 2 chars or more from the end");
        return false;   
    }
    return true;
}