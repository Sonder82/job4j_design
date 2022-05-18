package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Aleksey Novoselov";
        int age = 40;
        char sex = 'M';
        double height = 1.75;
        boolean permit = true;
        LOG.debug("User info name : {}, age : {}, sex : {}, height : {}, job permit : {}", name, age, sex, height, permit);
        float pi = 3.14f;
        LOG.debug("Number PI is :  {}", pi);
        byte file = 25;
        LOG.debug("Size file is : {}", file);
        long distance = 149597870700L;
        LOG.debug("Distance to Sun at meters : {}", distance);
        short month = 3;
        LOG.debug("Count summer month : {}", month);

    }
}
