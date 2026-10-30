package FacilitiesPackage;

public class Laboratory extends Facility {
    // labortory facility
    // concrete class
    // This class has:
    // 6 inherited attributes, 0 new attributes, 1 new method
    // new method: boolean canConductResearch()

    // Constructor
    public Laboratory(String facilityId, String location, int capacity) {

        super(facilityId, location, capacity);
    }

    // Check if the laboratory supports research
    public boolean canConductResearch() {

        return isOperational() && hasWorkingEquipment("RESEARCH");
    }
}