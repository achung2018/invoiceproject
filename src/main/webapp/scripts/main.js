document.getElementById('submit').addEventListener('click', saveInvoice);
document.getElementById('addButton').addEventListener('click', addInvoiceLine);
document.getElementById('amountInput').addEventListener('change', lineAmountChanged);
var lineAmountChanged = 0;
var invoiceLines = [];

function saveInvoice(e) {
  var name = document.getElementById('nameInput').value;
  if (name === "") {
    alert("Please enter customer name!");
    e.preventDefault();
    return false;
  }
  var email = document.getElementById('emailInput').value;
  var dDate = document.getElementById('dueDateInput').value;
  var invoiceID = chance.guid();
  var total = parseFloat(document.getElementById('totalInvoiceAmount').value);

  if (lineAmountChanged == 1) {
    var desc = document.getElementById('descriptionInput').value;
    var amt = document.getElementById('amountInput').value;
    if ((desc != "") && (amt != "") && (!isNaN(amt))) {
      var invLine = {
        description: desc,
        amount: parseFloat(amt)
      }
      invoiceLines.push(invLine);
      total += parseFloat(amt);
    }
  }

  var invoice = {
    id: invoiceID,
    custName:  name,
    custEmail: email,
    dueDate: dDate,
    invLines: invoiceLines,
    invTotal: total
  }
  if (getConfirmation(invoice)) {
     console.log(JSON.stringify(invoice));
     postInvoice(JSON.stringify(invoice));
  } else {
    console.log("invoice save has been cancelled");
    e.preventDefault();
  }
}

function lineAmountChanged(e) {
  lineAmountChanged = 1;
}

function addInvoiceLine(e) {
  var invDescription = document.getElementById('descriptionInput').value;
  var lineAmount = document.getElementById('amountInput').value;
  if ((invDescription != "") && (lineAmount != "") && (!isNaN(lineAmount))) {
     document.getElementById('totalInvoiceAmount').value =
        parseFloat(document.getElementById('totalInvoiceAmount').value) + parseFloat(lineAmount);

     var invLines = {
        description: invDescription,
        amount: parseFloat(lineAmount)
     }
     invoiceLines.push(invLines);
     lineAmountChanged = 0;
     window.alert("Invoice line has been added!");
     return true;
  }
  window.alert("One of the following fields are invalid or empty: \n Description \n Amount");
}

function getConfirmation(invoice){
      var content = "Name: " + invoice.custName + "\n" +
                    "Email: " + invoice.custEmail + "\n" +
                    "Due Date: " + invoice.dueDate + "\n" +
                     "Invoice Lines: \n";

      for (i = 0; i < invoice.invLines.length; i++) {
          content += "          description: " + invoice.invLines[i].description +
                     ", amount: " + invoice.invLines[i].amount + "\n";
      }
      content += "Total: " + invoice.invTotal;
      return confirm("Do you want to submit the following invoice? \n\n" + content);
}

function postInvoice(payload) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/createInvoice", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(payload);
}
