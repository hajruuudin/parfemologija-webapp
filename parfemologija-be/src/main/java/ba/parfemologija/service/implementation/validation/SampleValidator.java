package ba.parfemologija.service.implementation.validation;

import ba.parfemologija.dao.SampleDAO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SampleValidator {
    @Autowired SampleDAO sampleDAO;

    public void validateSampleExists(Long id){
        if(sampleDAO.findById(id) == null){
            throw new IllegalArgumentException("ELEMENT NOT FOUND");
        }
    }
}
