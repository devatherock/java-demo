package io.github.devatherock.util;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.jbcsrc.api.SoySauce;
import com.google.template.soy.parseinfo.TemplateName;
import com.google.template.soy.tofu.SoyTofu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ClosureTemplateUtil {

    public static String composeWithSoyTofu() {
        SoyFileSet.Builder builder = SoyFileSet.builder();
        builder.add(ClosureTemplateUtil.class.getClassLoader().getResource("templates/mail.soy"));
        SoyTofu soyTemplates = builder.build().compileToTofu();

        Map<String, Object> ijData = new HashMap<>();
        ijData.put("brand", "Accounts");

        return soyTemplates.newRenderer("templates.mail.accountRequestSubmittedSubject")
                .setData(Collections.singletonMap("username", "test"))
                .setIjData(ijData)
                .render();
    }

    public static String composeWithSoySauce() {
        SoyFileSet.Builder builder = SoyFileSet.builder();
        builder.add(ClosureTemplateUtil.class.getClassLoader().getResource("templates/mail.soy"));
        SoySauce soyTemplates = builder.build().compileTemplates();

        Map<String, Object> ijData = new HashMap<>();
        ijData.put("brand", "Accounts");

        return soyTemplates.renderTemplate(TemplateName.of("templates.mail.accountRequestSubmittedSubject"))
                .setData(Collections.singletonMap("username", "test"))
                .setIj(ijData)
                .renderText()
                .get();
    }
}
