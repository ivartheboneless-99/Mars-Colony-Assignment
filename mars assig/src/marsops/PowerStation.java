package marsops;

import EnumsP.ResourceType;

public class PowerStation extends Facility{

    public PowerStation(String facilityId, String location, int capacity) {
        super(facilityId, location, capacity);
    }

 public int getAvailablePower() {

        if (!isOperational()) {
            return 0;
        }
          return getResourceQuantity(ResourceType.BATTERY_POWER);
    }

 public boolean hasSufficientPower(int requiredPower) {

        if (requiredPower < 0) {
            throw new IllegalArgumentException("Power cannot be negative");
        }

        return isOperational() && getAvailablePower() >= requiredPower;
    }

}
