package com.hrydziushka.finalproject.model.entity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Apartment extends AbstractEntity {
    private int peopleCount;
    private BigDecimal dayPrice;
    private int apartmentNumber;
    private String description;
    private ApartmentType apartmentType;
    private Optional<Double> averageRating;
    private Set<ApartmentImage> images;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Apartment{");
        sb.append("peopleCount=").append(peopleCount);
        sb.append(", dayPrice=").append(dayPrice);
        sb.append(", apartmentNumber=").append(apartmentNumber);
        sb.append(", description='").append(description).append('\'');
        sb.append(", apartmentType=").append(apartmentType);
        sb.append(", averageRating=").append(averageRating);
        sb.append(", images=").append(images);
        sb.append('}');
        return sb.toString();
    }

    public static ApartmentBuilder newBuilder() {
        return new Apartment().new ApartmentBuilder();
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public BigDecimal getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(BigDecimal dayPrice) {
        this.dayPrice = dayPrice;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Optional<Double> getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating != null ? Optional.of(averageRating) : Optional.empty();
    }

    public Set<ApartmentImage> getImages() {
        return images;
    }

    public void setImages(Set<ApartmentImage> images) {
        this.images = images;
    }

    public class ApartmentBuilder {

        private ApartmentBuilder() {

        }

        public ApartmentBuilder setId(Long id) {
            Apartment.this.setId(id);
            return this;
        }


        public ApartmentBuilder setPeopleCount(int peopleCount) {
            Apartment.this.peopleCount = peopleCount;
            return this;
        }

        public ApartmentBuilder setDayPrice(BigDecimal dayPrice) {
            Apartment.this.dayPrice = dayPrice;
            return this;
        }

        public ApartmentBuilder setApartmentNumber(int apartmentNumber) {
            Apartment.this.apartmentNumber = apartmentNumber;
            return this;
        }

        public ApartmentBuilder setDescription(String description) {
            Apartment.this.description = description;
            return this;
        }

        public ApartmentBuilder setApartmentType(ApartmentType apartmentType) {
            Apartment.this.apartmentType = apartmentType;
            return this;
        }

        public ApartmentBuilder setAverageRating(Double averageRating) {
            Apartment.this.averageRating = averageRating != null ? Optional.of(averageRating) : Optional.empty();
            return this;
        }

        public Apartment build() {
            return Apartment.this;
        }

    }
}
