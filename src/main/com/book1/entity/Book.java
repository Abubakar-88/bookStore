package com.book1.entity;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.Base64;
import java.util.Date;


@Entity
@Table(name = "book", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@NamedQueries({
        @NamedQuery(name = "Book.countAll", query = "SELECT COUNT(*) FROM Book b"),
        @NamedQuery(name = "Book.countByCategory", query = "SELECT COUNT(b) FROM Book b "
                + "WHERE b.category.categoryId = :catId"),
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", unique = true, nullable = false)
    private Integer bookId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "title", unique = true, nullable = false, length = 128)
    private String title;

    @Column(name = "author", nullable = false, length = 64)
    private String author;

    @Column(name = "description", nullable = false, length = 16777215)
    private String description;
    @Column(name = "isbn", nullable = false, length = 15)
    private String isbn;
    @Column(name = "image", nullable = false)
    private byte[] image;

    private String base64Image;
    @Column(name = "price", nullable = false, precision = 12, scale = 0)
    private  float price;
    @Column(name = "publish_date", nullable = false, length = 10)
    private Date publishDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false, length = 19)
    private Date lastUpdateTime;

    public Book() {
    }
    public Book(Category category, String title, String author, String description, String isbn, byte[] image,
                float price, Date publishDate, Date lastUpdateTime) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.image = image;
        this.price = price;
        this.publishDate = publishDate;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Transient
    public String getBase64Image(){
        this.base64Image = Base64.getEncoder().encodeToString(this.image);
        return this.base64Image;
    }

    @Transient
    public void setBase64Image(String base64Image){
        this.base64Image = base64Image;
    }

}
