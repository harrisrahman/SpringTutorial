delete from Ingredient;
delete from Taco;
delete from Taco_Ingredients;
delete from Taco_Ingredients;
delete from Taco_Ingredients;
delete from Taco_order;
delete from Taco_Order_Tacos;


insert into Ingredient(id,name,type,createdAt)
        values('FLTO', 'Flour Tortilla', 'WRAP',CURRENT_TIMESTAMP());
insert into Ingredient(id,name,type,createdAt)
        values('COTO', 'Corn Tortilla', 'WRAP',CURRENT_TIMESTAMP());
insert into Ingredient(id,name,type,createdAt)
        values('GRBF', 'Ground Beef', 'PROTEIN',CURRENT_TIMESTAMP());
insert into Ingredient(id,name,type,createdAt)
        values('CARN', 'Carnitas', 'PROTEIN',CURRENT_TIMESTAMP());
insert into Ingredient(id,name,type,createdAt)
        values('TMTO', 'Diced Tomatoes', 'VEGGIES',CURRENT_TIMESTAMP());
insert into Ingredient(id,name,type,createdAt)
        values('LETC', 'Lettuce', 'VEGGIES',CURRENT_TIMESTAMP());
insert into Ingredient(id,name,type,createdAt)
        values('CHED', 'Cheddar', 'CHEESE',CURRENT_TIMESTAMP());
insert into Ingredient(id,name,type,createdAt)
        values('JACK', 'Monterrey Jack', 'CHEESE',CURRENT_TIMESTAMP());
insert into Ingredient(id,name,type,createdAt)
        values('SLSA', 'Salsa', 'SAUCE',CURRENT_TIMESTAMP());
insert into Ingredient(id,name,type,createdAt)
        values('SRCR', 'Sour Cream', 'SAUCE',CURRENT_TIMESTAMP());
