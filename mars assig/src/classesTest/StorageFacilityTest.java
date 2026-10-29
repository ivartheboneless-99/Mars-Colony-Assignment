package classesTest;

import EnumsP.ResourceType;
import marsops.StorageFacility;

public class StorageFacilityTest {

        public static void main(String[] args) {

                // Create storage facility
                StorageFacility storage = new StorageFacility("ST01", "Mars Base Alpha", 100);

                // Store resources
                storage.addResource(ResourceType.OXYGEN, 500);
                storage.addResource(ResourceType.WATER, 300);
                storage.addResource(ResourceType.FOOD, 200);

                // Display stored resources
                System.out.println("Initial oxygen: "
                                + storage.getResourceQuantity(ResourceType.OXYGEN));

                // Successful consumption
                boolean success = storage.consumeResource(ResourceType.OXYGEN, 200);

                System.out.println("Consumption successful: " + success);

                System.out.println("Remaining oxygen: "
                                + storage.getResourceQuantity(ResourceType.OXYGEN));

                // Insufficient resources
                boolean failed = storage.consumeResource(ResourceType.OXYGEN, 500);

                System.out.println("Second consumption: " + failed);

                System.out.println("Final oxygen: "
                                + storage.getResourceQuantity(ResourceType.OXYGEN));

                // Facility unavailable
                storage.setOperational(false);

                System.out.println("Operational: "
                                + storage.isOperational());

                System.out.println("Consumption while unavailable: "
                                + storage.consumeResource(ResourceType.WATER, 50));
        }
}
