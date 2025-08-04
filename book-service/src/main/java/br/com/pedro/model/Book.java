package br.com.pedro.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "author", length = 100)
    private String author;
    @Column(nullable = false, name = "title", length = 100)
    private String title;
    @Column(name = "launch_date", nullable = false)
    private Date lauchDate;
    @Column(name = "price", nullable = false)
    private Double price;
    @Transient
    private String currency;
    @Transient
    private String enviroment;

    public Book() {
    }

    public Book(Long id, String author, String title, Date lauchDate, Double price, String currency, String enviroment) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.lauchDate = lauchDate;
        this.price = price;
        this.currency = currency;
        this.enviroment = enviroment;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLauchDate() {
        return lauchDate;
    }

    public void setLauchDate(Date lauchDate) {
        this.lauchDate = lauchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }
}
