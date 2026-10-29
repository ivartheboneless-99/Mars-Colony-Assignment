package classesTest;

import marsops.Equipment;

public class testEquipment {
    public static void main(String[] args) {

        Equipment e1 = new Equipment("XQC1", "Microscope", "research", false);
        System.out.println(e1.getCapability());
        e1.damage();
        System.out.println(e1.isDamaged());
        e1.repair();
        System.out.println(e1.isDamaged());
    }

}
