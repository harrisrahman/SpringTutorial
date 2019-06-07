package tacos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tacos.domain.Ingredient;
import tacos.domain.Taco;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

public class JdbcTacoRepository implements TacoRepository {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco design) {
        long id = saveTacoDesign(design);
        design.setId(id);
        design.getIngredients().forEach(ing -> saveTacoIngredient(ing,design.getId()));
        return design;
    }

    private long saveTacoDesign(Taco design){
        design.setCreatedAt(new Date());
        KeyHolder holder = new GeneratedKeyHolder();
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                "insert into Taco(name,createdAt) values(?,?)",
                Types.VARCHAR,Types.DATE);
        PreparedStatementCreator psc = factory.newPreparedStatementCreator(Arrays.asList(design.getName(),design.getCreatedAt()));
        jdbcTemplate.update(psc,holder);
        return holder.getKey().longValue();
    }

    private void saveTacoIngredient(String ingredient,long tacoId){
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                "insert into Taco_Ingredients(taco,ingredient) values(?,?)",
                Types.BIGINT,Types.VARCHAR);
        PreparedStatementCreator psc = factory.newPreparedStatementCreator(Arrays.asList(tacoId,ingredient));
        jdbcTemplate.update(psc);
    }
}
