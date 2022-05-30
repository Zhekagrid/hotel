package com.hrydziushka.finalproject.model.entity;

public class ApartmentImage extends AbstractEntity{
    private String encodedImage;

    public ApartmentImage(Long id, String encodedImage) {
        super(id);
        this.encodedImage = encodedImage;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApartmentImage)) return false;
        if (!super.equals(o)) return false;

        ApartmentImage that = (ApartmentImage) o;

        return getEncodedImage() != null ? getEncodedImage().equals(that.getEncodedImage()) : that.getEncodedImage() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getEncodedImage() != null ? getEncodedImage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ApartmentImage{");
        sb.append("encodedImage='").append(encodedImage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
