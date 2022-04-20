package com.hrydziushka.finalproject.model.entity;

public class AbstractEntity {
    private int id;

    public AbstractEntity(int id) {
        this.id = id;
    }

    public AbstractEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity that = (AbstractEntity) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AbstractEntity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    public void setId(int id) {
        this.id = id;
    }
}
