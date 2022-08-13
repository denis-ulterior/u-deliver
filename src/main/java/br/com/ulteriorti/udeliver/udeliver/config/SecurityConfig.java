package br.com.ulteriorti.udeliver.udeliver.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig implements WebMvcConfigurer {
    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.GET,"/products").permitAll()
                .antMatchers(HttpMethod.GET,"/orders").authenticated()
                .antMatchers(HttpMethod.POST,"/*").authenticated()
                .antMatchers(HttpMethod.PUT,"/*").authenticated()
                .antMatchers(HttpMethod.DELETE,"/*").authenticated()
                .and().httpBasic();
        http.headers().frameOptions().sameOrigin();
        //Habilita ou desabilita cors e csrf
        http.cors().and().csrf().disable();
        return http.build();
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");// <- assim permite de qualquer origem,// trocar "/**" pelo seu dominio por exemplo "http://meudominio.com"
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("dev").password("{noop}dev").roles("USER").and()
                .withUser("devadmin").password("{noop}devadmin").roles("USER","ADMIN");
    }
}