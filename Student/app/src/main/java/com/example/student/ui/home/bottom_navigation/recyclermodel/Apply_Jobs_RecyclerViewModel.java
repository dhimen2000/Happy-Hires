package com.example.student.ui.home.bottom_navigation.recyclermodel;

public class Apply_Jobs_RecyclerViewModel {

    String CompanyName;
    String CompanyEmail;

    public Apply_Jobs_RecyclerViewModel(String companyName, String companyEmail) {
        CompanyName = companyName;
        CompanyEmail = companyEmail;
    }


    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyEmail() {
        return CompanyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        CompanyEmail = companyEmail;
    }


}
