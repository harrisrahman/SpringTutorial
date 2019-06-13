package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.Ingredient;

//public interface IngredientRepository {
//    Iterable<Ingredient> findAll();
//
//    Ingredient findOne(String id);
//
//    Ingredient save(Ingredient ingredient);
//}


public interface IngredientRepository
        extends CrudRepository<Ingredient,String> {

}
