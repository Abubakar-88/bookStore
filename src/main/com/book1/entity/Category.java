package com.book1.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category", catalog = "bookstoredb")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c ORDER BY c.name"),
        @NamedQuery(name = "Category.countAll", query = "SELECT COUNT(*) FROM Category"),
        @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique= true, nullable = false)
    private Integer categoryId;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Book> books = new HashSet<>(0);

    public Category() {
    }
    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
