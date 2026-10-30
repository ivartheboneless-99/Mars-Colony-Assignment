package FacilitiesPackage;

import EnumsP.ResourceType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import marsops.Equipment;

public abstract class Facility {
    /*
     * abstract class which contains all common characteristics of a facilities in
     * general thats why its abstract
     * we cant instancation it meaning we cant do this
     * Facility f =new Facility(); xx no
     * 
     * 
     * it contains:
     * 6 attributes and 8 methods plus one setter
     * 
     * setter: setOperational()
     * 
     * attributes: facilityId, location, capacity, operational, equipment, resources
     * 
     * methods: addEquipment(), getEquipment(), hasWorkingEquipment(),
     * getResourceQuantity(), getResources(), addResource(), consumeResource(),
     * loseLocalResource()
     * 
     */
    private String facilityId;
    private String location;
    private int capacity;
    private boolean operational;

    private ArrayList<Equipment> equipment;
    private Map<ResourceType, Integer> resources;
    // In Map list :
    // Key = resource type.
    // Value = quantity available.
    // resourceObj.put(resourceType.key,quantity)

    // constructor validate capacity should be a positive value
    public Facility(String facilityId, String location, int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity cannot be negative");

        }

        this.facilityId = facilityId;
        this.location = location;
        this.capacity = capacity;// 33
        this.operational = true; // intializes the it as working operational

        this.equipment = new ArrayList<>(); // creates empty equipment list
        this.resources = new EnumMap<>(ResourceType.class); // empty resources list

    }

    // getters
    public String getFacilityId() {
        return facilityId;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isOperational() {
        return operational;
    }

    // setter
    public void setOperational(boolean operational) {
        this.operational = operational;
    }

    // methods
    public void addEquipment(Equipment item) {
        if (item == null) {
            throw new IllegalArgumentException("Error 404 equipment cannot be NULL");
        }

        equipment.add(item);
    }

    public List<Equipment> getEquipment() {
        return Collections.unmodifiableList(equipment);
    }

    public boolean hasWorkingEquipment(String capability) { // has atleast 1 working equipment for a targeted capability
        if (!operational || capability == null) {
            return false;
        }

        for (Equipment item : equipment) { // means (for each object in the list of equipment do this)
            if (item.canPerform(capability)) // here it checks if the facility has the targeted capability working or
                                             // not for example: (research,true)
            {
                return true;
            }

        }

        return false;
    }

    public int getResourceQuantity(ResourceType type) {

        if (type == null) {
            throw new IllegalArgumentException("Resource type cannot be null");
        }

        return resources.getOrDefault(type, 0);
    }

    public Map<ResourceType, Integer> getResources() {
        return Collections.unmodifiableMap(resources);
    }

    public void addResource(ResourceType type, int quantity) {

        if (type == null || quantity < 0) {
            throw new IllegalArgumentException("Invalid resource or quantity");
        }

        int current = getResourceQuantity(type); // abstraction

        if (quantity > Integer.MAX_VALUE - current) {
            throw new IllegalArgumentException("Resource quantity is too large");
        }

        resources.put(type, current + quantity);
    }

    public boolean consumeResource(ResourceType type, int quantity) {

        if (type == null || quantity < 0) {
            throw new IllegalArgumentException("Invalid resource or quantity");
        }

        if (!operational) { // if the facility isnt functional the astornaut cant consume the resource
            return false;
        }

        int available = getResourceQuantity(type); // quantity of a resource inside a the specific facility

        if (available < quantity) {
            return false;
        }

        resources.put(type, available - quantity);
        return true;
    }

    protected int loseLocalResource(ResourceType type, int quantity) {

        if (type == null || quantity < 0) {
            throw new IllegalArgumentException("Invalid resource or quantity");
        }

        int current = getResourceQuantity(type);
        int lost = Math.min(current, quantity);

        resources.put(type, current - lost);

        return lost;
    }
}
