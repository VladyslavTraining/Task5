package com.delphi.data;


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
    @Column(columnName = "Price", columnOrder = 5, columnType = "Money")
    private double price;
    @Column(columnName = "Year", columnOrder = 6, columnType = "Date")
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


    public void setPrice(double price) {
        this.price = price;
    }


    public void setYear(int year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Year year1 = Year.of(year);
        this.year = year1.atDay(1).format(formatter);
    }

    @Override
    public String toString() {
        return title + "," + artist + "," + country + "," + company + "," + price + "," + year;
    }

}
