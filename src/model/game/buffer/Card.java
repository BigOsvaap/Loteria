package model.game.buffer;

public class Card {

    private final int id;
    private final String imagePath;

    public Card(int id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

}
