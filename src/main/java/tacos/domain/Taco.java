package tacos.domain;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min = 5,message = "Atleast 5 characters")
    private String name;
    @Size(min = 1,message = "Select atleast one ingredient")
    private List<String> ingredients;

    private Long id;

    private Date createdAt;
}
//