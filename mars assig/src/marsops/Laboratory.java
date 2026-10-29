package marsops;

public class Laboratory extends Facility {
    //concrete class
    // Constructor
    public Laboratory(String facilityId, String location, int capacity) {

        super(facilityId, location, capacity);
    }

    // Check if the laboratory supports research
    public boolean canConductResearch() {

        return isOperational() && hasWorkingEquipment("RESEARCH");
    }
}