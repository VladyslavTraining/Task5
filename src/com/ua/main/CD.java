package com.ua.main;


import java.time.Year;
import java.time.format.DateTimeFormatter;

public class CD extends AbstractColumnData {

    @Column(columnName = "Title", columnOrder = 1, columnType = "String")
    private String title;
    @Column(columnName = "Artist", columnOrder = 2, columnType = "String")
    private String artist;
    @Column(columnName = "Country", columnOrder = 3, columnType = "String")
    private String country;
    @Column(columnName = "Company", columnOrder = 4, columnType = "String")
    private String company;
    @Column(columnName = "Price", columnOrder = 5, columnType = "String")
    private String price;
    @Column(columnName = "Year", columnOrder = 6, columnType = "String")
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


    public void setYear(Integer year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Year of = Year.of(year);
        this.year = of.atDay(1).format(formatter);
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
