package classesTest;

import EnumsP.ResourceType;
import marsops.Habitat;

public class HabitatTest {

    public static void main(String[] args) {

        Habitat habitat = new Habitat("H01", "Mars Base Alpha", 10, null);

        // Add oxygen
        habitat.addResource(ResourceType.OXYGEN, 100);

        System.out.println("Initial oxygen: "
                + habitat.getResourceQuantity(ResourceType.OXYGEN));

        // Initial oxygen leak
        habitat.applyOxygenLeak(30);

        System.out.println("After initial leak: "
                + habitat.getResourceQuantity(ResourceType.OXYGEN));

        // Additional loss if unresolved
        habitat.applyOxygenLeak(30);

        System.out.println("After unresolved leak: "
                + habitat.getResourceQuantity(ResourceType.OXYGEN));

        // Test life support
        System.out.println("Life support working: "
                + habitat.isLifeSupportOperational());

        habitat.stopAirFiltration();

        System.out.println("After filtration failure: "
                + habitat.isLifeSupportOperational());

        habitat.repairLifeSupport();

        System.out.println("After repair: "
                + habitat.isLifeSupportOperational());
    }
}
