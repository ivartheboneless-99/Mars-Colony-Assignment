package classesTest;

import marsops.LifeSupportSystem;

public class LifeSupportTest {

    public static void main(String[] args) {

        LifeSupportSystem system =   new LifeSupportSystem();

        // Initially operational
        System.out.println(system.isOperational());

        // Simulate filtration failure
        system.stopAirFiltration();

        System.out.println(system.isOperational());

        // Repair the system
        system.repair();

        System.out.println(system.isOperational());
    }
}


