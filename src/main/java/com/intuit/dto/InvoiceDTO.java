package com.intuit.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDTO {

    /*
    var invoice = {
    id: invoiceID,
    custName:  name,
    custEmail: email,
    dueDate: dDate,
    invLines: invoiceLines,
    invTotal: total
       }
     */

    private String invoiceID;
    private String customerName;
    private String customerEmail;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dueDate;

    private List<InvoiceLine> invoiceLineList;

    private Double invoiceTotal;

    public InvoiceDTO(String invID,String name, String email, Date dDate, List<InvoiceLine> invList, Double total) {
        this.invoiceID = invID;
        this.customerName = name;
        this.customerEmail = email;
        this.dueDate = dDate;
        this.invoiceLineList = invList;
        this.invoiceTotal = total;
    }

    public InvoiceDTO() {

    }

    public String getInvoiceID() {
        return this.invoiceID;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getEmail() {
        return this.customerEmail;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return this.invoiceLineList;
    }

    public Double getInvoiceTotal() {
        return this.invoiceTotal;
    }

    public void setCustomerName(String name){
        this.customerName = name;
    }

    public void setCustomerEmail(String email) {
        this.customerEmail = email;
    }

    public void setDueDate(Date dDate) {
        this.dueDate = dDate;
    }

    public void setInvoiceLines(List<InvoiceLine> invLines) {
        this.invoiceLineList = invLines;
    }

    public void setTotal(Double total) {
        this.invoiceTotal = total;
    }

    public List<InvoiceLine> getInvoiceLinesByDescription(String desc) {
         List<InvoiceLine> result = new ArrayList<>();
         for(InvoiceLine invL: this.invoiceLineList) {
             if(invL.getDescription().contentEquals(desc)) {
                 result.add(invL);
             }
         }
         return result;
    }


    class InvoiceLine {
        private String description;
        private Double amount;

        public InvoiceLine(String desc, Double amt) {
            this.description = desc;
            this.amount = amt;
        }

        public InvoiceLine() {

        }

        public String getDescription() {
            return this.description;
        }

        public Double getAmount() {
            return this.amount;
        }

        public void setDescription(String desc) {
            this.description = desc;
        }

        public void setAmount(Double amt) {
            this.amount = amt;
        }
    }
}
