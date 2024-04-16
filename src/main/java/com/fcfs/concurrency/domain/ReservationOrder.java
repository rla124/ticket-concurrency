package com.fcfs.concurrency.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class ReservationOrder {

    private int totalAmount;

    public ReservationOrder(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPossibleToReserve(Ticket ticket) {
        return ticket.getId() <= totalAmount;
    }
}
