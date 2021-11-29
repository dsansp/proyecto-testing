##Proyecto Testing Grupo 1

Del codigo de ManufacturerRestController

Codigo con Bug:

`@PutMapping("/api/manufacturers")
public ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer){
    if(manufacturer.getId() != null)
        return ResponseEntity.badRequest().build();
        Manufacturer result = manufacturerService.save(manufacturer);
        return ResponseEntity.ok(result);
    }`


Codigo arreglado:

`@PutMapping("/api/manufacturers")
public ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer){
    if(manufacturer.getId() == null)
       return ResponseEntity.badRequest().build();
    else{
       Manufacturer result = manufacturerService.save(manufacturer);
       return ResponseEntity.ok(result);
   }
}`


  