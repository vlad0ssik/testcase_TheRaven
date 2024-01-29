package com.example.test_case_springboot.entity;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class CustomerAdapter {
    private static final String pattern= "ddMMyyyyHHmm";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "updated")
    private String updated;
    @Column(name = "created")
    private String created;
    @Column(name = "is_active")
    private boolean isActive;

    public CustomerAdapter() {
    }
    public CustomerAdapter(Customer customer) {
        id = customer.getId();
        fullName = customer.getFullName();
        email = customer.getEmail();
        phone = customer.getPhone();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
    public void setUpdatedManually() {
        this.updated =simpleDateFormat.format(new Date());
    }
    public void setCreatedManually() {
        this.created = simpleDateFormat.format(new Date());
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAdapter that = (CustomerAdapter) o;
        return id == that.id && isActive == that.isActive && Objects.equals(fullName, that.fullName) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(updated, that.updated) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, phone, updated, created, isActive);
    }

    @Override
    public String toString() {
        return "CustomerAdapter{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", updated='" + updated + '\'' +
                ", created='" + created + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
