package EnumsP;

/**
 * Respresents the type of resources we mange acros the mars colony facilities
 * enums is a special java type that represents a fixed collection of constants
 *we dont need a constructor, getters or setters
 *other classes will call the values by using ResourceType.OXYGEn for example
 *we use enums because they are more safe than using regular strings because they give a compile error if invalid enum is written while string accepts any thing all valid values are defined easier to control
*/
public enum ResourceType {
    OXYGEN,
    WATER,
    FOOD,
    FUEL,
    BATTERY_POWER,
    MEDICAL_SUPPLIES,
    SPARE_PARTS
}
//A facility is a building or place designed to perform a specific function.
//building or station on Mars that helps the colony operate
