package com.example.student.ui.home.bottom_navigation.recyclermodel;

public class Company_List_RecyclerViewModel {

    String CompanyList;
    String CompanyNumber;

    public Company_List_RecyclerViewModel(String companyList, String companyNumber) {
        CompanyList = companyList;
        CompanyNumber = companyNumber;
    }

    public String getCompanyList() {
        return CompanyList;
    }

    public void setCompanyList(String companyList) {
        CompanyList = companyList;
    }

    public String getCompanyNumber() {
        return CompanyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        CompanyNumber = companyNumber;
    }
}
