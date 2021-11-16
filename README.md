## Testing proyecto 

* patterns: JUnit y Mockito
* service: JUnit y Mockito
* repository: @DataJpaTest y @Autowired y JUnit
* controller: @SpringBootTest y TestRestTemplate
* entities: Se prueba indirectamente al testear todos los demás

2 enfoques: 

1. Cada paquete es testeado por una persona del equipo
2. Cada persona testea las clases de una entidad completa desde la entidad hasta el controlador: 
   1. Category
   2. CategoryRepository
   3. CategoryService y CategoryServiceImpl
   4. CategoryMvcController y CategoryRestController

Recomendación: enfoque 2.

## Testing Controladores 

1. RestTemplateBuilder y TestRestTemplate
2. Crear petición con el objeto TestRestTemplate
   1. GET `getForEntity()`
      1. findAll 
      2. findOneOk
      3. findOneNotFound
   2. POST `postForEntity()` Requiere crear el json a enviar
      1. createOK
      2. createBadRequest
   3. PUT `exchange()`
      1. update()
   4. DELETE `delete()` y `exchange()`
      1. deleteById()
      2. deleteAll()
3. Procesar ResponseEntity (respuesta)
   1. getStatusCodeValue
   2. getStatusCode
   3. Aserciones sobre los datos que vienen en el cuerpo de la respuesta `getBody()`


Proceso para ahora: 
1. Crear Issue en GitHub
2. Implementar cambio solicitado
3. Realizar commit y push poniendo el número de la issue en 

Capa servicio 
* ProductServiceImpl --> Alan
* ManufacturerServiceImpl --> Jorge
* DirectionServiceImpl --> Karim
* CategoryServiceImpl --> Ioan
* ManufacturerServiceImpl método custom para filtrar fabricantes por pais --> Ismael
    1. Declarar método en interfaz ManufacturerRepository 
    2. Declarar método en interfaz ManufacturerService 
    3. Implementar método en interfaz ManufacturerServiceImpl
* DirectionServiceImpl método custom para filtrar por ciudad y país a la vez --> Miguel
    1. Declarar método en interfaz DirectionRepository 
    2. Declarar método en interfaz DirectionService
    3. Implementar método en interfaz DirectionServiceImpl
* CategoryServiceImpl método custom para filtrar por color --> Álvaro
    1. Declarar método en interfaz CategoryRepository
    2. Declarar método en interfaz CategoryService
    3. Implementar método en interfaz CategoryServiceImpl

Capa controlador
* ProductRestController --> alan
  * findAll()
  * findOne()
  * findByPriceBetween()
  * findByManufacturer()
  * create()
  * update()
  * deleteById()
  * deleteAll()
* ManufacturerRestController --> Alicia
* DirectionRestController --> María Ángeles
* CategoryRestController --> David

## Selenium 

Productos
1. Añadir producto: product-edit.jsp 
2. Borrar productos
3. Ver
4. Editar: product-edit.jsp 
5. Borrar
6. Manufacturer Link: comprobar el enlace a la pantalla fabricante desde el listado de productos
7. Categories column: comprobar que aparecen varias categorías en una misma columna

Manufacturer:
1. Añadir fabricante
2. Borrar fabricantes 
3. Ver
4. Editar
5. Borrar 
6. Comprobar enlaces a la pantalla Productos desde listado fabricantes

