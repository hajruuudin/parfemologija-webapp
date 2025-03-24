package ba.parfemologija.rest;

import ba.parfemologija.service.core.SampleService;
import ba.parfemologija.service.core.models.sample.SampleModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "sample", description = "Sample API")
@RequestMapping(value = "sample")
@AllArgsConstructor
public class SampleRESTService {
    @Autowired private SampleService sampleService;

    @Operation(description = "Find a sample by ID")
    @GetMapping
    public SampleModel findById(){
        System.out.println(this.sampleService);
        return sampleService.findById(1L);
    }
}
