package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.RentalLogRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private RentalLogRepository rentalLogRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private RentalLogService rentalLogService;

    @Test
    @DisplayName("Reservation create test")
    void createReservation() {

        User mockUser = User.builder().email("existing@example.com").role("user").build();
        User mockUser2 = User.builder().email("abc@aa.com").role("user").build();
        Item mockitem = new Item("testItem" ,"test",mockUser,mockUser2);

        LocalDateTime startAt  = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusDays(2);
        Reservation mockReservation = new Reservation(mockitem, mockUser, ReservationStatus.PENDING, startAt, endAt);
        Reservation savedReservation = reservationRepository.save(mockReservation);

        RentalLog rentalLog = new RentalLog(savedReservation, "로그 메세지", "CREATE");
        rentalLogService.save(rentalLog);
    }
}