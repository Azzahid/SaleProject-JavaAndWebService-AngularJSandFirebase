function validateform() {
	var pname = document.forms["addForm"]["productName"].value;
	var pprice = document.forms["addForm"]["productPrice"].value;
	var pdesc = document.forms["addForm"]["productDescription"].value;
	if(pname === null || pname === ""){
		alert("Please fill the product name. ");
		return false;
	}
	if(pprice === null || pprice === ""){
		alert("Please fill the price. ");
		return false;
	}
	if(pprice % 1 !== 0){
		alert("Please enter the valid number for price. ");
		return false;
	}
	if(pdesc === null || pdesc === ""){
		alert("Please fill the product description. ");
		return false;
	}
	if(pdesc.toString().length > 200 ){
		alert("The description must not exceed 200 chars. ");
		return false;
	}
	if(document.getElementById('fileToUpload').value === "" || document.getElementById('fileToUpload').value === null ){
		alert("Please upload the image.");
		return false;
	}
}