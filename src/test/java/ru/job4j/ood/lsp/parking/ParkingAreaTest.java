package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

    @Disabled
    class ParkingAreaTest {

        @Test
        void addTwoCarsAndOneTruck() {
            ParkingArea parkingArea = new ParkingArea(2, 1);
            Vehicle carOne = new Car();
            Vehicle carTwo = new Car();
            Vehicle truckOne = new Truck(2);
            assertThat(parkingArea.add(carOne)).isTrue();
            assertThat(parkingArea.add(carTwo)).isTrue();
            assertThat(parkingArea.add(truckOne)).isTrue();

        }

        @Test
        void addTwoTrucksUseTwoPlacesForCar() {
            ParkingArea parkingArea = new ParkingArea(2, 1);
            Vehicle truckOne = new Truck(2);
            Vehicle truckTwo = new Truck(2);
            assertThat(parkingArea.add(truckOne)).isTrue();
            assertThat(parkingArea.add(truckTwo)).isTrue();
        }

        @Test
        void checkSize() {
            Truck truckOne = new Truck(1);
            assertThatThrownBy(() -> truckOne.checkSize(1))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void addTruckUseOnePlaceForCar() {
            ParkingArea parkingArea = new ParkingArea(1, 1);
            Vehicle truckOne = new Truck(2);
            Vehicle truckTwo = new Truck(2);
            assertThat(parkingArea.add(truckOne)).isTrue();
            assertThat(parkingArea.add(truckTwo)).isFalse();
        }

        @Test
        void addCarUsePlaceForTruck() {
            ParkingArea parkingArea = new ParkingArea(0, 1);
            Vehicle carOne = new Car();
            assertThat(parkingArea.add(carOne)).isFalse();
        }
}