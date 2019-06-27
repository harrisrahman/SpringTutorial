package tacos.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final String id;

    private final String name;

    @Enumerated(EnumType.ORDINAL)
    private final Type type;

    @Column(name = "createdAt")
    private Date createdAt;

    public static enum Type {
        WRAP,PROTEIN,CHEESE,VEGGIES,SAUCE
    }
}
