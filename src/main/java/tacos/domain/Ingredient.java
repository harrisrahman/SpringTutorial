package tacos.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class Ingredient {

    @Id
    private final String id;

    private final String name;
    private final Type type;

    @Column(name = "createdAt")
    private Date createdAt;

    public static enum Type {
        WRAP,PROTEIN,CHEESE,VEGGIES,SAUCE
    }
}
