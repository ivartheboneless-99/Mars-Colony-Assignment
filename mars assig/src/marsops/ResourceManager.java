package marsops;

import EnumsP.ResourceType;
import FacilitiesPackage.Facility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResourceManager {

//the purpose of this class:
// is to manage, track, distribute, check resources for all FACILITIES in the mars colony

// we have :
// 1 attribute and 7 methods in this class

//attributes: private final List<Facility> facilities;
//methods: addFacility()  getFacilities()  getTotalResource()  getAllResourceTotals()   validateRequest() hasEnoughResources()  consumeResources()




    // Attributes
    private final List<Facility> facilities;

    // Constructor
    public ResourceManager() {
        this.facilities = new ArrayList<>();
    }

    // Register a facility
    public void addFacility(Facility facility) {

        if (facility == null) {
            throw new IllegalArgumentException("Facility cannot be null");
        }

        for (Facility existing : facilities) {
            if (existing.getFacilityId().equals(facility.getFacilityId())) {
                throw new IllegalArgumentException("Duplicate facility ID");
            }
        }

        facilities.add(facility);
    }

    // Get registered facilities
    public List<Facility> getFacilities() {
        return Collections.unmodifiableList(facilities);
    }

    // Calculate total available quantity of one resource
    public int getTotalResource(ResourceType type) {

        if (type == null) {
            throw new IllegalArgumentException("Resource type cannot be null");
        }

        long total = 0;

        for (Facility facility : facilities) {

            if (facility.isOperational()) {
                total += facility.getResourceQuantity(type);
            }
        }

        if (total > Integer.MAX_VALUE) {
            throw new ArithmeticException("Resource total is too large");
        }

        return (int) total;
    }

    // Calculate colony-wide totals for every resource
    public Map<ResourceType, Integer> getAllResourceTotals() {

        Map<ResourceType, Integer> totals = new EnumMap<>(ResourceType.class);

        for (ResourceType type : ResourceType.values()) {
            totals.put(type, getTotalResource(type));
        }

        return totals;
    }

    // Validate a resource request is private to apply abstraction helper method
    private void validateRequest(Map<ResourceType, Integer> request) {

        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        for (Map.Entry<ResourceType, Integer> entry : request.entrySet()) {

            if (entry.getKey() == null
                    || entry.getValue() == null
                    || entry.getValue() < 0) {

                throw new IllegalArgumentException("Invalid resource request");
            }
        }
    }

    // Check whether all resources are available
    public boolean hasEnoughResources(Map<ResourceType, Integer> request) {

        validateRequest(request);

        for (Map.Entry<ResourceType, Integer> entry : request.entrySet()) {

            ResourceType type = entry.getKey();
            int required = entry.getValue();

            if (getTotalResource(type) < required) {
                return false;
            }
        }

        return true;
    }

    // Consume resources using an all-or-nothing policy
    public boolean consumeResources(Map<ResourceType, Integer> request) {
// goal is to deduct the requested resource if available if not available then consume nothing and return false
        validateRequest(request);

        // Phase 1: Check all requested resources
        if (!hasEnoughResources(request)) {
            return false;
        }

        // Phase 2: Consume from operational facilities
        // in the order they were registered
        for (Map.Entry<ResourceType, Integer> entry : request.entrySet()) { // the entry set get both (key and value ) as a pair at once in the map list

            ResourceType type = entry.getKey();  //in the enums we get only the name of the resource (Key) in the map list
            int remaining = entry.getValue(); //we get the quantity (value) in the map list

            for (Facility facility : facilities) {

                if (!facility.isOperational()) {
                    continue;
                }

                int available = facility.getResourceQuantity(type);
                int taken = Math.min(available, remaining); // returns the min of the two numbers

                if (taken > 0) {
                    facility.consumeResource(type, taken);
                    remaining -= taken;
                }

                if (remaining == 0) {
                    break;
                }
            }
        }

        return true;
    }
}
