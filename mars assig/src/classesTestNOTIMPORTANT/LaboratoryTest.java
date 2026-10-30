package classesTestNOTIMPORTANT;

import FacilitiesPackage.Laboratory;
import marsops.Equipment;

public class LaboratoryTest {

    public static void main(String[] args) {

        // Create laboratory
        Laboratory lab =
                new Laboratory("LAB01", "Mars Base Alpha", 5);

        // Create research equipment
        Equipment microscope =
                new Equipment("EQ01", "Microscope", "RESEARCH");

        // Add equipment to laboratory
        lab.addEquipment(microscope);

        // Test operational laboratory
        System.out.println("Research available: "
                + lab.canConductResearch());

        // Damage microscope
        microscope.damage();

        System.out.println("After equipment damage: "
                + lab.canConductResearch());

        // Repair microscope
        microscope.repair();

        System.out.println("After equipment repair: "
                + lab.canConductResearch());

        // Disable laboratory
        lab.setOperational(false);

        System.out.println("After laboratory shutdown: "
                + lab.canConductResearch());
    }
}

