package tacos.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    private Date createdAt;

    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Type getType() {
//        return type;
//    }

    public static enum Type {
        WRAP,PROTEIN,CHEESE,VEGGIES,SAUCE
    }
}
