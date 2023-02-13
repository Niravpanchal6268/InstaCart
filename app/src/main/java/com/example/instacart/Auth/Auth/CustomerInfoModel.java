package com.example.instacart.Auth.Auth;

public class CustomerInfoModel {
    private String CustomerName,CustomerMobile,CustomerEmail,CustomerPassword;

    public CustomerInfoModel(String customerName, String customerMobile, String customerEmail, String customerPassword) {
        CustomerName = customerName;
        CustomerMobile = customerMobile;
        CustomerEmail = customerEmail;
        CustomerPassword = customerPassword;
    }

    public CustomerInfoModel() {
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        CustomerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return CustomerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        CustomerPassword = customerPassword;
    }
}
