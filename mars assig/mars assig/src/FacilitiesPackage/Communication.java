package FacilitiesPackage;

public class Communication extends Facility{

    //communication facility
    
    private boolean communicationEnabled;

    public Communication(boolean communicationEnabled, String facilityId, String location, int capacity) {
        super(facilityId, location, capacity);
        this.communicationEnabled = true;
    }

 public boolean canCommunicate() {
        return isOperational() && communicationEnabled;
    }
 public void disableCommunication() {
        communicationEnabled = false;
    }
     public void enableCommunication() {
        communicationEnabled = true;
    }
}
