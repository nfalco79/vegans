package it.nfalco79.example.core.internal.configuration;

import org.springframework.context.annotation.Import;

@Import({
          CoreServiceConfiguration.class, //
          CoreResourceConfiguration.class, //
})
public class CoreConfiguration {

}
