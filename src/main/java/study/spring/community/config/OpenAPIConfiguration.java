package study.spring.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {

  private static final String API_TITLE = "Spring Study Backend";
  private static final String API_VERSION = "0.1.0";
  private static final String API_DESCRIPTION = "Spring Study Backend API";

  @Bean
  public OpenAPI OpenAPIConfig() {
    return new OpenAPI()
        .info(new Info()
            .title(API_TITLE)
            .description(API_DESCRIPTION)
            .version(API_VERSION));
  }
}
