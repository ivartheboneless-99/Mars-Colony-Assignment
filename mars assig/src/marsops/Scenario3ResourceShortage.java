package marsops;

import EnumsP.ResourceType;
import java.util.EnumMap;
import java.util.Map;

public class Scenario3ResourceShortage {

        // Helper method to verify expected results
        private static void check(boolean condition, String message) {

                if (!condition) {
                        throw new AssertionError("FAIL: " + message);
                }

                System.out.println("PASS: " + message);
        }

        // Main demonstration method
        public static void run() {

                System.out.println("=== Scenario 3: Resource Shortage ===");

                // Create facilities
                Habitat habitat = new Habitat("H01", "Mars Base Alpha", 10, null);

                StorageFacility storage = new StorageFacility("S01", "Mars Base Alpha", 20);

                // Add initial resources
                habitat.addResource(ResourceType.OXYGEN, 100);
                habitat.addResource(ResourceType.WATER, 10);

                storage.addResource(ResourceType.OXYGEN, 50);
                storage.addResource(ResourceType.WATER, 10);

                // Create ResourceManager and register facilities
                ResourceManager manager = new ResourceManager();

                manager.addFacility(habitat);
                manager.addFacility(storage);

                // Create a resource request
                Map<ResourceType, Integer> request = new EnumMap<>(ResourceType.class);

                request.put(ResourceType.OXYGEN, 120);
                request.put(ResourceType.WATER, 30);

                // TEST 1: All-or-nothing resource shortage
                System.out.println("\n--- Test 1: Resource Shortage ---");

                boolean firstAttempt = manager.consumeResources(request);

                check(!firstAttempt, "Insufficient request is rejected");

                check(manager.getTotalResource(ResourceType.OXYGEN) == 150,
                                "Oxygen remains unchanged after rejection");

                check(manager.getTotalResource(ResourceType.WATER) == 20,
                                "Water remains unchanged after rejection");

                // TEST 2: Multi-facility resource consumption
                System.out.println("\n--- Test 2: Multi-Facility Consumption ---");

                // Add enough water to fulfill the request
                storage.addResource(ResourceType.WATER, 20);

                boolean secondAttempt = manager.consumeResources(request);

                check(secondAttempt, "Multi-facility consumption succeeds");

                check(habitat.getResourceQuantity(ResourceType.OXYGEN) == 0,
                                "Habitat supplies 100 oxygen units");

                check(storage.getResourceQuantity(ResourceType.OXYGEN) == 30,
                                "Storage supplies 20 oxygen units");

                check(habitat.getResourceQuantity(ResourceType.WATER) == 0,
                                "Habitat supplies 10 water units");

                check(storage.getResourceQuantity(ResourceType.WATER) == 10,
                                "Storage supplies remaining 20 water units");

                check(manager.getTotalResource(ResourceType.OXYGEN) == 30,
                                "Correct colony oxygen total after consumption");

                check(manager.getTotalResource(ResourceType.WATER) == 10,
                                "Correct colony water total after consumption");

                // TEST 3: Unavailable facilities are excluded
                System.out.println("\n--- Test 3: Unavailable Facility ---");

                StorageFacility unavailable = new StorageFacility("S02", "Mars Base Beta", 20);

                unavailable.addResource(ResourceType.OXYGEN, 500);
                unavailable.setOperational(false);

                manager.addFacility(unavailable);

                check(manager.getTotalResource(ResourceType.OXYGEN) == 30,
                                "Unavailable facility resources are excluded");

                Map<ResourceType, Integer> largeRequest = new EnumMap<>(ResourceType.class);

                largeRequest.put(ResourceType.OXYGEN, 100);

                boolean thirdAttempt = manager.consumeResources(largeRequest);

                check(!thirdAttempt,
                                "Cannot consume oxygen from unavailable facility");

                check(unavailable.getResourceQuantity(ResourceType.OXYGEN) == 500,
                                "Unavailable facility resources remain unchanged");

                check(manager.getTotalResource(ResourceType.OXYGEN) == 30,
                                "Available oxygen remains unchanged after rejection");

                // Final resource report
                System.out.println("\n--- Final Colony Resources ---");

                for (Map.Entry<ResourceType, Integer> entry : manager.getAllResourceTotals().entrySet()) {

                        System.out.println(entry.getKey() + ": " + entry.getValue());
                }

                System.out.println("\n=== Scenario 3 Completed Successfully ===");
        }
}
