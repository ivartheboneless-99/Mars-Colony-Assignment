package marsops;

import EnumsP.ResourceType;

public class Habitat extends Facility{
    
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
