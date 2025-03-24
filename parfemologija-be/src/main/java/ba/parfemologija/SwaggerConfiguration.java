package ba.parfemologija;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi sampleApi() {
        return GroupedOpenApi.builder()
                .group("all-apis")
                .packagesToScan("ba.parfemologija.rest")
                .build();
    }
}
