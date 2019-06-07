package tacos.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import tacos.domain.Order;
import tacos.domain.Taco;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                            .withTableName("Taco_Order")
                            .usingGeneratedKeyColumns("id");
        orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                            .withTableName("Taco_Order_Tacos");

        objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {

        long orderId = saveTacoOrder(order);
        order.setId(orderId);
        order.getTacoList().forEach(tacos -> saveTacoOrderTacos(tacos,order.getId()));
        return order;
    }

    private void saveTacoOrderTacos(Taco tacos, Long id) {
        Map<String,Object> map = new HashMap<>();
        map.put("taco",tacos.getName());
        map.put("tacoOrder",id);
        orderTacoInserter.execute(map);
    }

    private long saveTacoOrder(Order order) {

        Map<String,Object> values = objectMapper.convertValue(order,Map.class);
        values.put("placedAt",order.getPlacedAt());
        long id = orderInserter.executeAndReturnKey(values).longValue();
        return  id;
    }



}
