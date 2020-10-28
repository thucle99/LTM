package mvcPure;

public class Room {

    private String id;
    private String name;
    private String type;
    private float displayPrice;
    private String description;

    public Room() {
        super();
    }

    public Room(String id, String name, String type,
            float displayPrice, String description) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.displayPrice = displayPrice;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(float displayPrice) {
        this.displayPrice = displayPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
