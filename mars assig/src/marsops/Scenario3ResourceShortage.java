package marsops;

import EnumsP.ResourceType;
import FacilitiesPackage.Facility;
import FacilitiesPackage.Habitat;
import FacilitiesPackage.StorageFacility;
import java.util.EnumMap;
import java.util.Map;

public class Scenario3ResourceShortage {

        // helper Check whether a condition is true
        private static void check(boolean condition, String message) {

                if (!condition) {
                        throw new AssertionError("FAIL: " + message);
                }

                System.out.println("PASS: " + message);
        }

        // helper Check whether an operation rejects invalid input
        private static void checkInvalid(Runnable action, String message) {

                try {
                        action.run();
                } catch (IllegalArgumentException e) {
                        System.out.println("PASS: " + message);
                        return;
                }

                throw new AssertionError("FAIL: " + message);
        }

        // run method
        public static void run() {

                System.out.println("=== Scenario 3: Resource Shortage ===");

                // Create facilities
                Habitat habitat = new Habitat("H01", "Mars Base Alpha", 10);

                StorageFacility storage = new StorageFacility("S01", "Mars Base Alpha", 20);

                // Add initial resources
                habitat.addResource(ResourceType.OXYGEN, 100);
                habitat.addResource(ResourceType.WATER, 10);

                storage.addResource(ResourceType.OXYGEN, 50);
                storage.addResource(ResourceType.WATER, 10);

                // Create manager and register facilities
                ResourceManager manager = new ResourceManager();

                manager.addFacility(habitat);
                manager.addFacility(storage);

                // Create a request
                Map<ResourceType, Integer> request = new EnumMap<>(ResourceType.class);

                request.put(ResourceType.OXYGEN, 120);
                request.put(ResourceType.WATER, 30);

                // TEST 1: Shortage reason
                System.out.println("\n--- Test 1: Shortage Reason ---");

                String reason = manager.getShortageReason(request);

                check(reason != null && reason.contains("WATER"),
                                "Shortage reason identifies WATER");

                check(reason.contains("Required: 30")
                                && reason.contains("Available: 20"),
                                "Shortage reason contains correct quantities");

                System.out.println("Failure reason: " + reason);

                // TEST 2: All-or-nothing rejection
                System.out.println("\n--- Test 2: Atomic Rejection ---");

                boolean firstAttempt = manager.consumeResources(request);

                check(!firstAttempt, "Insufficient request is rejected");

                check(habitat.getResourceQuantity(ResourceType.OXYGEN) == 100,
                                "Habitat oxygen remains unchanged");

                check(storage.getResourceQuantity(ResourceType.OXYGEN) == 50,
                                "Storage oxygen remains unchanged");

                check(habitat.getResourceQuantity(ResourceType.WATER) == 10,
                                "Habitat water remains unchanged");

                check(storage.getResourceQuantity(ResourceType.WATER) == 10,
                                "Storage water remains unchanged");

                // TEST 3: Negative quantities
                System.out.println("\n--- Test 3: Invalid Quantities ---");

                checkInvalid(
                                () -> storage.addResource(ResourceType.OXYGEN, -10),
                                "Negative resource addition rejected");

                checkInvalid(
                                () -> storage.consumeResource(ResourceType.WATER, -5),
                                "Negative local consumption rejected");

                Map<ResourceType, Integer> invalidRequest = new EnumMap<>(ResourceType.class);

                invalidRequest.put(ResourceType.OXYGEN, 20);
                invalidRequest.put(ResourceType.WATER, -10);

                checkInvalid(
                                () -> manager.consumeResources(invalidRequest),
                                "Negative colony consumption rejected");

                checkInvalid(
                                () -> manager.hasEnoughResources(invalidRequest),
                                "Invalid availability request rejected");

                check(manager.getTotalResource(ResourceType.OXYGEN) == 150,
                                "Oxygen unchanged after invalid requests");

                check(manager.getTotalResource(ResourceType.WATER) == 20,
                                "Water unchanged after invalid requests");

                // TEST 4: Multi-facility allocation
                System.out.println("\n--- Test 4: Multi-Facility Consumption ---");

                // Correct the water shortage
                storage.addResource(ResourceType.WATER, 20);

                check(manager.hasEnoughResources(request),
                                "All requested resources are available");

                check(manager.getShortageReason(request) == null,
                                "No shortage reason when resources are sufficient");

                boolean secondAttempt = manager.consumeResources(request);

                check(secondAttempt, "Multi-facility consumption succeeds");

                // Allocation follows facility registration order
                check(habitat.getResourceQuantity(ResourceType.OXYGEN) == 0,
                                "Habitat supplies 100 oxygen units");

                check(storage.getResourceQuantity(ResourceType.OXYGEN) == 30,
                                "Storage supplies remaining 20 oxygen units");

                check(habitat.getResourceQuantity(ResourceType.WATER) == 0,
                                "Habitat supplies 10 water units");

                check(storage.getResourceQuantity(ResourceType.WATER) == 10,
                                "Storage supplies remaining 20 water units");

                check(manager.getTotalResource(ResourceType.OXYGEN) == 30,
                                "Correct remaining oxygen total");

                check(manager.getTotalResource(ResourceType.WATER) == 10,
                                "Correct remaining water total");

                // TEST 5: Unavailable facilities
                System.out.println("\n--- Test 5: Unavailable Facility ---");

                StorageFacility unavailable = new StorageFacility("S02", "Mars Base Beta", 20);

                unavailable.addResource(ResourceType.OXYGEN, 500);
                unavailable.addResource(ResourceType.WATER, 100);

                unavailable.setOperational(false);

                manager.addFacility(unavailable);

                check(manager.getTotalResource(ResourceType.OXYGEN) == 30,
                                "Unavailable oxygen excluded from colony total");

                check(manager.getTotalResource(ResourceType.WATER) == 10,
                                "Unavailable water excluded from colony total");

                Map<ResourceType, Integer> largeRequest = new EnumMap<>(ResourceType.class);

                largeRequest.put(ResourceType.OXYGEN, 100);

                check(!manager.hasEnoughResources(largeRequest),
                                "Unavailable resources cannot satisfy request");

                check(!manager.consumeResources(largeRequest),
                                "Request requiring unavailable oxygen rejected");

                check(unavailable.getResourceQuantity(ResourceType.OXYGEN) == 500,
                                "Unavailable facility oxygen remains unchanged");

                check(storage.getResourceQuantity(ResourceType.OXYGEN) == 30,
                                "Operational storage unchanged after rejection");

                // TEST 6: No negative quantities
                System.out.println("\n--- Test 6: Nonnegative Resources ---");

                for (Facility facility : manager.getFacilities()) {

                        for (ResourceType type : ResourceType.values()) {

                                check(facility.getResourceQuantity(type) >= 0,
                                                facility.getFacilityId() + " " + type
                                                                + " is nonnegative");
                        }
                }

                // Final colony report
                System.out.println("\n--- Final Colony Resources ---");

                for (Map.Entry<ResourceType, Integer> entry : manager.getAllResourceTotals().entrySet()) {

                        System.out.println(entry.getKey() + ": " + entry.getValue());
                }

                System.out.println("\n=== Scenario 3 Completed Successfully ===");
        }
}
