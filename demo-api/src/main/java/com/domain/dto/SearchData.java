package com.domain.dto;

public class SearchData {

    private String searchKey; // DTO untuk menerima searchKey dari pengguna

    private String otherSearchKey; // DTO untuk menerima searchKey kedua dari pengguna (jika ada kasus pencarian lebih dari 1 variabel)

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getOtherSearchKey() {
        return otherSearchKey;
    }

    public void setOtherSearchKey(String otherSearchKey) {
        this.otherSearchKey = otherSearchKey;
    }

}
