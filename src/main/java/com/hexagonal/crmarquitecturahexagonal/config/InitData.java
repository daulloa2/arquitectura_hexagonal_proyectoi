package com.hexagonal.crmarquitecturahexagonal.config;

public class InitData {
     // CREATE INITIAL DATA FOR CLIENTS
    public static final String clientTable = "INSERT INTO client (uuid, first_name, last_name, email, phone_number, address, uuid_user_manager) VALUES ('c1fee7e1-a16a-42b5-8d09-6a3a536aa353', 'Daniel', 'Ulloa', 'daullo2@utpl.edu.ec', '0985834567', 'Las Peñas', 'e1ac6e16-6289-4373-90e4-cf415b41e1ec'); \n" 
    + "INSERT INTO client (uuid, first_name, last_name, email, phone_number, address, uuid_user_manager) VALUES ('1d43f1ee-74de-11ec-90d6-0242ac120003', 'Jhon', 'Dhoe', 'jhondoe30@gmail.com', '0985834568', 'Clodoveo', 'e1ac6e16-6289-4373-90e4-cf415b41e1ec') ; \n"
    + "INSERT INTO client (uuid, first_name, last_name, email, phone_number, address, uuid_user_manager) VALUES ('68cfdad5-bba9-48a1-be15-ed57421d5b71', 'Jhonson', 'Robertson', 'robertson24@gmail.com', '0985834566', 'Av Univertitaria y 10 de agosto', 'e1ac6e16-6289-4373-90e4-cf415b41e1ec') ; \n"
    + "INSERT INTO client (uuid, first_name, last_name, email, phone_number, address, uuid_user_manager) VALUES ('1aa079b3-cb49-4ddd-86f1-a74c8b22ec423', 'Maik', 'Andrew', 'andrew30@gmail.com', '0985834578', '18 de Noviembre y Lourdes', 'c5f8da14-f272-4ea6-8fb6-ed5df5ba7c31') ; \n"
    + "INSERT INTO client (uuid, first_name, last_name, email, phone_number, address, uuid_user_manager) VALUES ('448c8afc-923f-41c7-93ae-261057218dcc', 'Mario', 'Brock', 'brokn64@gmail.com', '0985834526', 'calle Manuel Aguirre y Alnzo de Mercadillo ', 'c5f8da14-f272-4ea6-8fb6-ed5df5ba7c31') ; \n";

    // CREATE INITIAL DATA FOR ROLES 
    public static final String roleTable ="INSERT INTO role (role_name, uuid) VALUES ('ROLE_VENTAS','21802fe8-49bf-4fb9-925b-0dd202e392f0'); \n"
    + "INSERT INTO role (role_name, uuid) VALUES ('ROLE_ADMINISTRADOR','71903b3a-6de4-4054-8786-f46512443f24') ; \n"
    + "INSERT INTO role (role_name, uuid) VALUES ('ROLE_ASESOR','694abf74-2c5c-4a16-ad75-f8320d0f2157') ; \n"
    + "INSERT INTO role (role_name, uuid) VALUES ('ROLE_MECANICO','61a24a14-7326-41e7-98ef-0752bfb62957') ; \n";


     // CREATE INITIAL DATA FOR USERS
    public static final String userTable = "INSERT INTO users (uuid, first_name, last_name, email, phone_number, address, username, password, id_role) VALUES ('c5f8da14-f272-4ea6-8fb6-ed5df5ba7c31', 'Bratt', 'Carrot', 'carrot13@enterprise.crm.com', '0981234560', 'Coliseo de Loja', 'carrot13', '$2a$10$9g484VjhSRSPE7i3A6HQ4uTbxHPbAfgATKRY.77kCk17jCr84DcqW', (SELECT id_role FROM role WHERE role_name='ROLE_VENTAS'));"
    + "INSERT INTO users (uuid, first_name, last_name, email, phone_number, address, username, password, id_role) VALUES ('e1ac6e16-6289-4373-90e4-cf415b41e1ec', 'Berenise', 'Charlies', 'charlies34@enterprise.crm.com', '0981234534', 'Calle Quito y 18 de Noviembre', 'charlies34', '$2a$10$Q54blZccdTxc4HgIr3JCO.9iAOGLLiqK8CiE..tedA5m8WasdIa5i', (SELECT id_role FROM role WHERE role_name='ROLE_VENTAS'));" ;


    // CREATE INITIAL DATA FOR VEHICLES
    public static final String carTable = "INSERT INTO cars (brand, color, model, price, stock, transmission, year) VALUES ('NISSAN', 'Rojo', 'ALTIMAN', 37.990, 5, 'Hibrido', '2020');"
    
    + "INSERT INTO cars (brand, color, model, price, stock, transmission, year) VALUES ('NISSAN', 'Rojo', 'X-TRAIL', 35.490, 2, 'Manual', '2019');"
    
    + "INSERT INTO cars (brand, color, model, price, stock, transmission, year) VALUES ('CHEVROLET', 'Blanco', 'SPARK GT', '30.590', 5, 'Hibrido', '2021');"
    
    +"INSERT INTO cars (brand, color, model, price, stock, transmission, year) VALUES ('CHEVROLET', 'Azul', 'BEAT', '20.990', 4, 'Automático', '2018');"

    + "INSERT INTO cars (brand, color, model, price, stock, transmission, year) VALUES ('HYUNDAI', 'Gris', 'BEAT-2', '21.990', 2, 'Manual', '2017');"
    
    +"INSERT INTO cars (brand, color, model, price, stock, transmission, year) VALUES ('HYUNDAI', 'Negro', 'ALL NEW ACCENT', '25.990', 4, 'Manual', '2020');";


    public static final String clientCarTable = "INSERT INTO client_car (registration_date, id_client, id_car) VALUES (TO_DATE('2022-01-14', 'YYYY-mm-dd'), (SELECT id_client FROM client WHERE uuid='c1fee7e1-a16a-42b5-8d09-6a3a536aa353'), (SELECT id_car FROM cars WHERE model='ALTIMAN'));"
    
    + "INSERT INTO client_car (registration_date, id_client, id_car) VALUES (TO_DATE('2022-01-14', 'YYYY-mm-dd'), (SELECT id_client FROM client WHERE uuid='c1fee7e1-a16a-42b5-8d09-6a3a536aa353'), (SELECT id_car FROM cars WHERE model='X-TRAIL'));"
    
    +"INSERT INTO client_car (registration_date, id_client, id_car) VALUES (TO_DATE('2022-01-12', 'YYYY-mm-dd'), (SELECT id_client FROM client WHERE uuid='1d43f1ee-74de-11ec-90d6-0242ac120003'), (SELECT id_car FROM cars WHERE model='SPARK GT'));" 

    +"INSERT INTO client_car (registration_date, id_client, id_car) VALUES (TO_DATE('2022-01-12', 'YYYY-mm-dd'), (SELECT id_client FROM client WHERE uuid='1d43f1ee-74de-11ec-90d6-0242ac120003'), (SELECT id_car FROM cars WHERE model='BEAT'));";

}
