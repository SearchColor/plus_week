package com.example.demo.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    PENDING("pending"),
    APPROVED("approved"),
    CANCELED("canceled"),
    EXPIRED("expired");

    private final String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    public static ReservationStatus of(String status) {
        for (ReservationStatus reservationStatus : values()) {
            if (reservationStatus.getStatus().equals(status)) {
                return reservationStatus;
            }
        }

        throw new IllegalArgumentException("해당하는 이름의 상태를 찾을 수 없습니다: " + status);
    }
}
