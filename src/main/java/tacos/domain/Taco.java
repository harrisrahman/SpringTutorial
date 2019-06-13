package tacos.domain;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @NotNull
    @Size(min = 5, message = "Atleast 5 characters")
    private String name;

    @Size(min = 1, message = "Select atleast one ingredient")
    @ManyToMany(targetEntity = Ingredient.class)
    private List<String> ingredients;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}