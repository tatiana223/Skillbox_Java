public class InfoCargo {

    private final Dimensions dimensions;
    private final double weight;
    private final String deliveryAddress;
    private final boolean canBeFlipped;
    private final String registrationNumber;
    private final boolean isFragile;


    public InfoCargo(Dimensions dimensions, double weight, String deliveryAddress, boolean canBeFlipped, String registrationNumber, boolean isFragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.canBeFlipped = canBeFlipped;
        this.registrationNumber = registrationNumber;
        this.isFragile = isFragile;
    }


    public boolean getFragile() {
        return isFragile;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean getCanBeFlipped() {
        return canBeFlipped;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public double getWeight() {
        return weight;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public InfoCargo withDeliveryAddress(String newDeliveryAddress) {
        return new InfoCargo(dimensions, weight, newDeliveryAddress, canBeFlipped, registrationNumber, isFragile);
    }

    public InfoCargo modifiedDimensions(Dimensions newDimensions) {
        return new InfoCargo(newDimensions, weight, deliveryAddress, canBeFlipped, registrationNumber, isFragile);
    }
}
