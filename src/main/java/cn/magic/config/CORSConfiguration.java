package cn.magic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration implements WebMvcConfigurer {
    @Value("${web.load-path}")
    private String loadPath; // 图片访问路径
    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                // 建议：明确指定允许的前端地址，不要使用通配符
                .allowedOriginPatterns(
                    "http://localhost:8080", 
                    "http://localhost:5173", // Vite 默认端口
                    "https://www.your-production-domain.com"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")        // 允许跨域访问的方法
                .allowedHeaders("*")        // 允许跨域访问的请求头
                .maxAge(3600)               // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
                .allowCredentials(true);//允许携带cookie
    }
    // 图片访问
    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations(loadPath);
    }
}
