package com.book1.entity;


import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u ORDER BY u.fullName"),
        @NamedQuery(name= "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
        @NamedQuery(name = "Users.countAll", query = "SELECT Count(*) FROM Users u"),
        @NamedQuery(name = "Users.checkLogin", query = "SELECT u FROM Users u WHERE u.email = :email AND password = :password")

})
public class Users {
@Column(name = "user_id")
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer userId;

private String email;

@Column(name = "full_name")
private String fullName;

@Column(name = "password", length = 125, nullable = false)
private String password;

private String phone;

    public Users() {
    }

    public Users(Integer userId, String email, String fullName, String password, String phone) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
    }

    public Users(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public Users(String email, String fullName, String password, String phone) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
