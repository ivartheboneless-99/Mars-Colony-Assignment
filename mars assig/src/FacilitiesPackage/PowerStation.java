package FacilitiesPackage;

import EnumsP.ResourceType;

public class PowerStation extends Facility {

    // power Station facility
    // This class has:
    // 6 inherited attributes,0 new attributes,2 new methods

    // new methods: int getAvailablePower(), boolean hasSufficientPower(int
    // requiredPower)

    // consturctor
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
