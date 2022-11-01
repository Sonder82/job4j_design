package ru.job4j.ood.dip.third.incorrect;

/**
 * Модуль верхнего уровня ElectricPowerSwitch напрямую зависит от
 * модуля нижнего уровня LightBulb. это нарушает принцип DIP.
 * ElectricPowerSwitch должен иметь возможность управлять(вкл/выкл)
 * другими электрическими приборами.
 *
 */
public class ElectricPowerSwitch {
    private LightBulb lightBulb;
    private boolean on;

    public ElectricPowerSwitch(LightBulb lightBulb) {
        this.lightBulb = lightBulb;
        this.on = false;
    }

    public boolean isOn() {
        return this.on;
    }

    public void press() {
        boolean checkOn = isOn();
        if (checkOn) {
            lightBulb.turnOff();
            this.on = false;
        } else {
            lightBulb.turnOn();
            this.on = true;
        }
    }
}
