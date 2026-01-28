public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor,int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown(int currentFloor) {
        currentFloor -= 1;
    }

    public void moveUp(int currentFloor) {
        currentFloor += 1;
    }

    public void move(int floor) {
        if (floor >= minFloor && floor <= maxFloor) {
            if (floor > currentFloor) {
                for (int i = currentFloor; i <= floor; i++) {
                    moveUp(i);
                    System.out.println("Этаж " + i);
                }
            } else if (floor < currentFloor) {
                for (int i = currentFloor; i >= floor; i--) {
                    moveDown(i);
                    System.out.println("Этаж " + i);
                }
            } else {
                System.out.println("Этаж " + currentFloor);
            }
            currentFloor = floor;
        } else {
            System.out.println("Error!!!");
        }
    }

}
