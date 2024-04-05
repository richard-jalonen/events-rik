package ee.richja.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    public static final String DEFAULT_USER = "DEFAULT_USER";

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(DEFAULT_USER);
    }
}
