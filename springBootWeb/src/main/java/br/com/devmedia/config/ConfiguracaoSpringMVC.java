package br.com.devmedia.config;

import com.sun.tools.doclint.Entity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ConfiguracaoSpringMVC extends WebMvcConfigurerAdapter {

    // CONFIGURACAO PARA O THYMELEAF
    //  indicamos ao Spring que esse método deve ser chamado por ele e que ele deve gerenciar o objeto retornado;
    //  nesse caso um objeto do tipo SpringTemplateEngine.
    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver resolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
    }
}
