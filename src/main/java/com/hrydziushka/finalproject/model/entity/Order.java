package com.hrydziushka.finalproject.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order extends AbstractEntity {
    private LocalDate from;
    private LocalDate to;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;
    private Long userId;
    private Long apartmentId;

    public static OrderBuilder newBuilder() {
        return new Order().new OrderBuilder();
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public class OrderBuilder {
        public OrderBuilder setOrderId(Long orderId) {
            Order.this.setId(orderId);
            return this;
        }

        public OrderBuilder setFrom(LocalDate from) {
            Order.this.from = from;
            return this;
        }


        public OrderBuilder setTo(LocalDate to) {
            Order.this.to = to;
            return this;
        }

        public OrderBuilder setTotalPrice(BigDecimal totalPrice) {
            Order.this.totalPrice = totalPrice;
            return this;
        }


        public OrderBuilder setOrderStatus(OrderStatus orderStatus) {
            Order.this.orderStatus = orderStatus;
            return this;
        }


        public OrderBuilder setUserId(Long userId) {
            Order.this.userId = userId;
            return this;
        }


        public OrderBuilder setApartmentId(Long apartmentId) {
            Order.this.apartmentId = apartmentId;
            return this;
        }


        public Order build() {
            return Order.this;
        }
    }
}
