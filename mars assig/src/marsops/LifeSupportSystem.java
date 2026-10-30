package marsops;

public class LifeSupportSystem {
    //OWNED BY THE HABITAT  CLASS IMPORTANT ((COMPOSITION))
    // this class is has a composition relationship with the habitat meaning that 
    // the lifesupportsystem is a part of the habitat class
    // each habitat has it own lifesupportsystem
    //lifesupportSytem cant be an object for another facility

    // it has :
    // 2 attributes : boolean oxygenCirculatiom,boolean airFiltration
    // 4 functions: stopOxygenCirculation(), stopAirFiltration(), void repair(),
    // boolean isOperational()

    /*
     * Purpose: monitor O2 circuilation,filteration is enabled
     * determine wether life support is functioning or not
     * 
     */
    private boolean oxygenCirculation;
    private boolean airFiltration;

    public LifeSupportSystem() {
        oxygenCirculation = true;
        airFiltration = true;
    }

    public boolean isOxygenCirculation() {
        return oxygenCirculation;
    }

    public boolean isAirFiltration() {
        return airFiltration;
    }

    public void stopOxygenCirculation() {

        oxygenCirculation = false;
    }

    public void stopAirFiltration() {
        airFiltration = false;
    }

    public void repair() {
        oxygenCirculation = true;
        airFiltration = true;
    }

    public boolean isOperational() {
        return oxygenCirculation && airFiltration;
    }

}