package tacos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.domain.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {

        List<Ingredient> ingredientList =  jdbcTemplate.query("select id,name,type from Ingredient",this::mapRowToIngredient);
        return ingredientList;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(resultSet.getString("id"),
                        resultSet.getString("name"),
                        Ingredient.Type.valueOf(resultSet.getString("type")));
    }

    @Override
    public Ingredient findOne(String id) {
        jdbcTemplate.query("select id,name,type from Ingredient where id=?",this::mapRowToIngredient,id);
        return null;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("insert into Ingredient(id,name,type) values(?,?,?)",
                            ingredient.getId(),
                            ingredient.getName(),
                            ingredient.getType().toString());
        return ingredient;
    }
}
