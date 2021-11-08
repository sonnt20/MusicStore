package com.btec.store.musicstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.btec.store")
//public class TilesConfig implements WebMvcConfigurer {
//    @Bean
//    public TilesConfigurer tilesConfigurer() {
//        TilesConfigurer tilesConfigurer = new TilesConfigurer();
//        tilesConfigurer.setDefinitions(
//                new String[]{"/WEB-INF/tiles.xml"});
//        tilesConfigurer.setCheckRefresh(true);
//
//        return tilesConfigurer;
//    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        TilesViewResolver viewResolver = new TilesViewResolver();
//        registry.viewResolver(viewResolver);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("/static/");
//    }
@Configuration
public class TilesConfig {
    @Bean
    public UrlBasedViewResolver tilesViewResolver() {
        UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();

        tilesViewResolver.setViewClass(TilesView.class);

        return tilesViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();

        String[] defs = {"WEB-INF/tiles.xml"};

        tilesConfigurer.setDefinitions(defs);

        return tilesConfigurer;
    }

}