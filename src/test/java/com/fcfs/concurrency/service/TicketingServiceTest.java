package com.fcfs.concurrency.service;

import com.fcfs.concurrency.domain.ReservationOrder;
import com.fcfs.concurrency.domain.ReservationStatus;
import com.fcfs.concurrency.repository.ReservationRepository;
import com.fcfs.concurrency.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TicketingServiceTest {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void test() throws InterruptedException {
        //given
        int memberCount = 30;
        int ticketAmount = 10;

        ReservationOrder ticketReservationAmount = new ReservationOrder(ticketAmount);
        TicketingService ticketingService = new TicketingService(ticketReservationAmount, ticketRepository, reservationRepository);

        ExecutorService executorService = Executors.newFixedThreadPool(memberCount);
        CountDownLatch latch = new CountDownLatch(memberCount);

        //when
        for (int i = 0; i < memberCount; i++) {
            executorService.submit(() -> {
                try {
                    ticketingService.ticketing();
                } catch (IllegalStateException e) {
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        //then
        assertThat(ticketRepository.findByReservationStatus(ReservationStatus.RESERVED).size()).isEqualTo(ticketAmount);
        assertThat(ticketRepository.findByReservationStatus(ReservationStatus.FAILED).size()).isEqualTo(memberCount - ticketAmount);
        assertThat(reservationRepository.findAll().size()).isEqualTo(ticketAmount);

    }
}
