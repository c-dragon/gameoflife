package gameoflife.finite;

public class CellFactory {
    public static Cell fromString(String image) {
        if (LivingCell.getImage().equals(image)) {
            return new LivingCell();
        }
        return new DeadCell();
    }
}
