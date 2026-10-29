package marsops;

public class Equipment {
//the purpose of this class is represent the machines in the mars labortory as java objects
/*
 *identity: id and the name of the machine
 *capability: the function of the machine
 *condition: working or damaged
*/

//represents 1 machine in general


private String equipmentId; 
private String name;
private String capability;
private boolean damaged;


public Equipment(String equipmentId, String name, String capability, boolean damaged) {
    this.equipmentId = equipmentId;
    this.name = name;
    this.capability = capability;
    this.damaged = damaged;
}

    public String getEquipmentId() {
        return equipmentId;
    }

    public String getName() {
        return name;
    }

    public String getCapability() {
        return capability;
    }

    public boolean isDamaged() {
        return damaged;
    }

/*  functions: we have 3 public functions in this class
 *1 damage();, 2 repair();, 3 canPerform(String requiredCapability);
 */ 
    public void damage(){
        damaged =true;
    }

   public void repair(){
        damaged = false;
    }
     public boolean canPerform(String requiredCapability){
        return !damaged && capability.equalsIgnoreCase(requiredCapability);
    }

}