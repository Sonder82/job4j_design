package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


    class ParkingAreaTest {

        @Test
        void whenAddTwoCarsAndOneTruck() {
            ParkingArea parkingArea = new ParkingArea(2, 1);
            Vehicle carOne = new Car();
            Vehicle carTwo = new Car();
            Vehicle truckOne = new Truck(2);
            assertThat(parkingArea.add(carOne)).isTrue();
            assertThat(parkingArea.add(carTwo)).isTrue();
            assertThat(parkingArea.add(truckOne)).isTrue();
            assertThat(parkingArea.getAll()).hasSize(3);

        }

        @Test
        void whenAddTwoTrucksUseTwoPlacesForCar() {
            ParkingArea parkingArea = new ParkingArea(2, 1);
            Vehicle truckOne = new Truck(2);
            Vehicle truckTwo = new Truck(2);
            assertThat(parkingArea.add(truckOne)).isTrue();
            assertThat(parkingArea.add(truckTwo)).isTrue();
            assertThat(parkingArea.getAll()).hasSize(2);
        }

        @Test
        void whenAddTwoTrucksUseTwoPlacesForCarAndThenAddCar() {
            ParkingArea parkingArea = new ParkingArea(2, 1);
            Vehicle truckOne = new Truck(2);
            Vehicle truckTwo = new Truck(2);
            Vehicle carOne = new Car();
            assertThat(parkingArea.add(truckOne)).isTrue();
            assertThat(parkingArea.add(truckTwo)).isTrue();
            assertThat(parkingArea.add(carOne)).isFalse();
            assertThat(parkingArea.getAll()).hasSize(2);
        }

        @Test
        void checkSize() {
            assertThatThrownBy(() ->  new Truck(1))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void whenAddTruckUseOnePlaceForCar() {
            ParkingArea parkingArea = new ParkingArea(1, 1);
            Vehicle truckOne = new Truck(2);
            Vehicle truckTwo = new Truck(2);
            assertThat(parkingArea.add(truckOne)).isTrue();
            assertThat(parkingArea.add(truckTwo)).isFalse();
            assertThat(parkingArea.getAll()).hasSize(1);
        }

        @Test
        void whenAddTruckUsePlaceForCar() {
            ParkingArea parkingArea = new ParkingArea(1, 1);
            Vehicle truckOne = new Truck(2);
            Vehicle truckTwo = new Truck(2);
            assertThat(parkingArea.add(truckOne)).isTrue();
            assertThat(parkingArea.add(truckTwo)).isFalse();
            assertThat(parkingArea.getAll()).hasSize(1);
        }

        @Test
        void whenAddAnyVehicleButParkPlacesAreOccupied() {
            ParkingArea parkingArea = new ParkingArea(1, 1);
            Vehicle carOne = new Car();
            Vehicle truckOne = new Truck(2);
            Vehicle carTwo = new Car();
            Vehicle truckTwo = new Truck(2);
            assertThat(parkingArea.add(carOne)).isTrue();
            assertThat(parkingArea.add(truckOne)).isTrue();
            assertThat(parkingArea.add(carTwo)).isFalse();
            assertThat(parkingArea.add(truckTwo)).isFalse();
            assertThat(parkingArea.getAll()).hasSize(2);
        }
}