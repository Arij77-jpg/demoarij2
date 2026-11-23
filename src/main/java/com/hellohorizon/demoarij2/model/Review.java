package com.hellohorizon.demoarij2.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Review Entity - Chapter 4 Activity 1
 *
 * Design justification:
 * The Review entity owns the foreign key (column "product_id") via @ManyToOne,
 * as each review belongs to exactly one product, while a product can have multiple reviews.
 */
@Entity
@Table(name = "reviews")
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String comment;
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Review() {}

    public Review(int rating, String comment, String author) {
        this.rating = rating;
        this.comment = comment;
        this.author = author;
    }

    public Review(int rating, String comment, String author, Product product) {
        this.rating = rating;
        this.comment = comment;
        this.author = author;
        this.product = product;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
