package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CinemaTest {

    @Test
    @Ignore
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    @Test
    @Ignore
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, -1, 1, date);
        });
    }

    @Test
    @Ignore
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 2, 1, date);
        });
    }

    @Test
    @Ignore
    public void whenPlaceIsReserved() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, 1, date);
        });
    }


}