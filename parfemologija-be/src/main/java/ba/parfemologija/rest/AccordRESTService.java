package ba.parfemologija.rest;

import ba.parfemologija.service.core.AccordService;
import ba.parfemologija.service.core.models.accord.AccordCreate;
import ba.parfemologija.service.core.models.accord.AccordModel;
import ba.parfemologija.service.core.models.accord.AccordUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Accord", description = "Accord API")
@AllArgsConstructor
@RequestMapping(value = "/accord")
public class AccordRESTService {
    AccordService accordService;

    @Operation(description = "Get accords from database with pagination")
    @GetMapping
    public ResponseEntity<Page<AccordModel>> findAll(){
        return accordService.find(PageRequest.of(0, 10));
    }

    @Operation(description = "Add an accord to the system")
    @PostMapping
    public ResponseEntity<AccordModel> create(@RequestBody AccordCreate request) throws Exception {
        return accordService.create(request);
    }

    @Operation(description = "Edit an accord in the system")
    @PutMapping
    public ResponseEntity<AccordModel> update(@RequestBody AccordUpdate request){
        return accordService.update(request);
    }

    @Operation(description = "Delete an accord from the system")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return accordService.deleteById(id);
    }
}
