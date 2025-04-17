package ba.parfemologija.rest;

import ba.parfemologija.service.core.SampleService;
import ba.parfemologija.service.core.models.sample.SampleCreateRequest;
import ba.parfemologija.service.core.models.sample.SampleModel;
import ba.parfemologija.service.core.models.sample.SampleUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Lastly there is the rest service. THis is basically all the endpoints of the app.
* Every entity has a rest service (similar to how it has a service, a dao and its models)
* Here there shouldn't be any logic, just the routes which call the service
*
* */
@RestController // U need this on every rest controller
@Tag(name = "sample", description = "Sample API") // This is for swagger
@RequestMapping(value = "sample") // This is the default route, so all routes in this contorller will start with /sample
@AllArgsConstructor //U also need this
public class SampleRESTService {
    SampleService sampleService;

    @Operation(description = "Find a sample by ID")
    @GetMapping(value = "{id}")
    public SampleModel findById(@PathVariable Long id){
        return sampleService.findById(id);
    }

    @Operation(description = "Find all samples from the database")
    @GetMapping
    public List<SampleModel> findAll(){
        return sampleService.getAll();
    }

    @Operation(description = "Create a new sample")
    @PostMapping
    public SampleModel create(SampleCreateRequest request){
        return sampleService.save(request);
    }

    @Operation(description = "Update an existing sample")
    @PutMapping
    public SampleModel update(SampleUpdateRequest request){
        return sampleService.update(request);
    }

    @Operation(description = "Delete an existing sample")
    @DeleteMapping(value = "{id}")
    public void delete(Long id){
        sampleService.delete(id);
    }
}
