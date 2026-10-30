package FacilitiesPackage;

import EnumsP.ResourceType;
import marsops.LifeSupportSystem;

public class Habitat extends Facility{
    //this class has:
    // 6 inherited attributes from faculity superclass and 1 new attribute and has 5 new methods

    //New Attribute: (Habitat): lifeSupport (LifeSupportSystem, private final).
    //new Methods: isLifeSupportOperational(), stopOxygenCirculation(), stopAirFiltration(), repairLifeSupport(), applyOxygenLeak(int quantity).


 // Composition: Habitat owns its LifeSupportSystem
    private final LifeSupportSystem lifeSupport;

    public Habitat(String facilityId, String location, int capacity, LifeSupportSystem lifeSupport) {
        super(facilityId, location, capacity);
        this.lifeSupport = new LifeSupportSystem(); //composition owns a
    }


    //delgation a public method to excess the life support system
     public boolean isLifeSupportOperational() {
        return lifeSupport.isOperational();
    }
 public void stopOxygenCirculation() {
        lifeSupport.stopOxygenCirculation();
    }
 public void stopAirFiltration() {
        lifeSupport.stopAirFiltration();
    }
 public void repairLifeSupport() {
        lifeSupport.repair();
    }
     // Apply one local oxygen leak deduction
 public int applyOxygenLeak(int quantity) {

        return loseLocalResource(ResourceType.OXYGEN, quantity);
    }
}
