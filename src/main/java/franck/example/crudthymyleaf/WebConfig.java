//package franck.example.crudthymyleaf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Bean
//    public ITemplateResolver templateResolve(){
//        ClassLoaderTemplateResolver templateResolver= new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("templates");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML");
//        return  templateResolver;
//    }
//
//    @Bean
//    public TemplateEngine templateEngine(){
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolve());
//        return  templateEngine;
//    }
//
//
//    public ViewResolver viewResolver(){
//        ThymeleafViewResolver resolver= new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        resolver.setCharacterEncoding("UTF-8");
//    }
//}
