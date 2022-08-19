package io.github.devatherock.util;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

public class TestUtil {
    private static final VelocityEngine VELOCITY_ENGINE = createEngine();

    private static VelocityEngine createEngine() {
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(Velocity.RESOURCE_LOADERS, "string");
        engine.setProperty("resource.loader.string.class", StringResourceLoader.class.getName());
        engine.setProperty("resource.loader.string.cache", true);
        engine.setProperty("resource.loader.string.modification_check_interval", 60);
        engine.init();

        // Add template to repository
        StringResourceRepository repository = StringResourceLoader.getRepository();
        repository.putStringResource("hello_world", "Hello $w");
        repository.putStringResource("hello_world2", "Hello, World!");

        return engine;
    }

    public static String sayHello() {
        return "Hello";
    }

    public static String getVelocityTemplate(String templateName) {
        return StringResourceLoader.getRepository().getStringResource(templateName).getBody();
    }

    public static String expandVelocityTemplate(String templateName, Map<String, Object> context) {
        VelocityContext velocityContext = new VelocityContext();
        context.forEach((key, value) -> {
            velocityContext.put(key, value);
        });

        StringWriter writer = new StringWriter();
        VELOCITY_ENGINE.getTemplate(templateName).merge(velocityContext, writer);
        
        return writer.toString();
    }
}
