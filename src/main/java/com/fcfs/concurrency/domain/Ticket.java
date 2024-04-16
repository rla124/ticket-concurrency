package com.fcfs.concurrency.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReservationStatus reservationStatus;

    public Ticket() {
        this.reservationStatus = ReservationStatus.WAITING;
    }

    public void succeed() {
        this.reservationStatus = ReservationStatus.RESERVED;
    }

    public void failed() {
        this.reservationStatus = ReservationStatus.FAILED;
    }
}
