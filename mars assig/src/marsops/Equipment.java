package marsops;

public class Equipment {
//the purpose of this class is represent the machines in the mars labortory as java objects
/*
 *identity: id and the name of the machine
 *capability: the function of the machine
 *condition: working or damaged
*/

//represents 1 machine in general

/*
 *we have :
 4 attributes and 3 methods
 
Attributes: equipmentId (String), name (String), capability (String), damaged (boolean) and their constructor and getters
methods: damage(), repair(), canPerform(String requiredCapability).

*/
/*  functions: we have 3 public functions in this class
 *1 damage();, 2 repair();, 3 canPerform(String requiredCapability);
 */ 

private String equipmentId; 
private String name;
private String capability;
private boolean damaged;


//constructor 
public Equipment(String equipmentId, String name, String capability) {

    if (equipmentId == null || equipmentId.isBlank()
            || name == null || name.isBlank()
            || capability == null || capability.isBlank()) {

        throw new IllegalArgumentException("Invalid equipment information");
    }

    this.equipmentId = equipmentId;
    this.name = name;
    this.capability = capability;
    this.damaged = false;
}



//getters
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