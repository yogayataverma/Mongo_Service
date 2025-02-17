// WebConfig.java
@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println("Configuring CORS mappings...");
                registry.addMapping("/**")
                        .allowedOrigins(
                            "http://127.0.0.1:5500",
                            "http://localhost:5500",
                            "https://your-frontend-domain.com" // Add your production frontend URL
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600); // Cache CORS config for 1 hour
            }
        };
    }
}