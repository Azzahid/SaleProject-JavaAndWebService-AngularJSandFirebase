function getConfirmation(){
   var quantity = document.getElementById("quantity").value.trim();
   var consignee = document.getElementById("consignee").value.trim();
   var full_address = document.getElementById("full_address").value.trim();
   var postal_code = document.getElementById("postal_code").value.trim();
   var phone_number = document.getElementById("phone_number").value.trim();
   var credit_card = document.getElementById("credit_card").value.trim();
   var card_verification = document.getElementById("card_verification").value.trim();
   if(quantity==null || quantity=="") {
      alert("Quantity field must be filled.");
      console.log('quantity not filled');
      return;
   }
   if(quantity % 1 !== 0) {
      alert("Quantity field must be an integer.");
      console.log('quantity not number');
      return;  
   }
   if(consignee==null || consignee=="") {
      alert("Consignee field must be filled.");
      console.log('consignee not filled');
      return;
   }
   if(full_address==null || full_address=="") {
      alert("Full address field must be filled.");
      console.log('full_address not filled');
      return;
   }
   if(postal_code==null || postal_code=="") {
      alert("Postal code field must be filled.");
      console.log('postal_code not filled');
      return;
   }
   if(postal_code % 1 !== 0) {
      alert("Postal code field must be a number.");
      console.log('postal_code not number');
      return;  
   }
   if(phone_number==null || phone_number=="") {
      alert("Phone number field must be filled.");
      console.log('phone_number not filled');
      return;
   }
   if(phone_number % 1 !== 0) {
      alert("Phone number field must be a number.");
      console.log('phone_number not number');
      return;  
   }
   if(credit_card==null || credit_card=="") {
      alert("Credit card number field must be filled.");
      console.log('credit_card not filled');
      return;
   }
   if(credit_card % 1 !== 0) {
      alert("Credit card number field must be a number.");
      console.log('credit_card not number');
      return;  
   }
   if(credit_card.toString().length != 12) {
      alert("Credit card number field must consists of 12 digits.");
      console.log('credit_card not 12 digits');
      return;  
   }
   if(card_verification==null || card_verification=="") {
      alert("Card verification value field must be filled.");
      console.log('card_verification not filled');
      return;
   }
   if(card_verification % 1 !== 0) {
      alert("Card verification value field must be a number.");
      console.log('card_verification not number');
      return;  
   }
   if(card_verification.toString().length != 3) {
      alert("Card verification value field must consists of 3 digits.");
      console.log('card_verification not 3 digits');
      return;  
   }

   
   var retVal = confirm("Are you sure with your input?");
   if( retVal == true ){
      document.getElementById("purchase_form").submit();
      // window.location.assign("http://localhost/purchases.php");
      // return true;
   }
   else{
      // document.write ("User does not want to continue!");
      // return false;
   }
}

function updateTotalPrice() {
   var quantity = document.getElementById("quantity").value;
   var price = document.getElementById("product_price").getAttribute("value");
   document.getElementById("total_price").innerHTML = numberWithCommas(quantity * price).replace(/,/g,".");
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}