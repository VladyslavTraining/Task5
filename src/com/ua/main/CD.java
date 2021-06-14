package com.ua.main;


public class CD extends AbstractColumnData {

    @Column(columnName = "TITLE", columnOrder = 1, columnType = "String")
    private String title;
    @Column(columnName = "ARTIST", columnOrder = 2, columnType = "String")
    private String artist;
    @Column(columnName = "COUNTRY", columnOrder = 3, columnType = "String")
    private String country;
    @Column(columnName = "COMPANY", columnOrder = 4, columnType = "String")
    private String company;
    @Column(columnName = "PRICE", columnOrder = 5, columnType = "String")
    private String price;
    @Column(columnName = "YEAR", columnOrder = 6, columnType = "String")
    private String year;


    public void setTitle(String title) {
        this.title = title;
    }


    public void setArtist(String artist) {
        this.artist = artist;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public void setCompany(String company) {
        this.company = company;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String[] getRow() {
        return super.getRow();
    }

    @Override
    public String toString() {
        return title + "," + artist + "," + country + "," + company + "," + price + "," + year;
    }

}
