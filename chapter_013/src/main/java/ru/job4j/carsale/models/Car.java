package ru.job4j.carsale.models;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.04.2020
 */

@Entity(name = "Car")
@Table(name = "car")
@EqualsAndHashCode
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "status")
    private String status;

    @Column(name = "image")
    private String image;

    @Column(name = "model")
    private String model;

    @Column(name = "year_of_release")
    private String yearOfRelease;

    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "power")
    private String power;

    @Column(name = "volume")
    private String volume;

    @Column(name = "price")
    private String price;

    @Column(name = "mileage")
    private String mileage;

    @ManyToOne
    @JoinColumn(name = "seller_id", foreignKey = @ForeignKey(name = "SELLER_ID_FK"))
    private Seller seller;

    public Car() {
    }

    public Car(Seller seller) {
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
