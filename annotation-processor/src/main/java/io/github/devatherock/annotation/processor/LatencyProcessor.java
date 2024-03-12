package io.github.devatherock.annotation.processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.github.devatherock.annotation.Latency;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.Set;

public class LatencyProcessor extends AbstractProcessor {

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		return Set.of(Latency.class.getName());
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		System.out.println("LatencyProcessor is running...");
		for (Element element : roundEnv.getElementsAnnotatedWith(Latency.class)) {
			if (element.getKind().equals(ElementKind.METHOD)) {
				generateLatencyCode((ExecutableElement) element);
			}
		}
		return true;
	}

	private void generateLatencyCode(ExecutableElement methodElement) {
		String methodName = methodElement.getSimpleName().toString();

		CodeBlock codeBlock = CodeBlock.builder().beginControlFlow("try")
				.addStatement("long startTime = System.currentTimeMillis()")
				.addStatement("// TODO Add method implementation")
				.addStatement("long endTime = System.currentTimeMillis()")
				.addStatement(
						"System.out.println(\"Method $N execution time: \" + (endTime - startTime) + \" milliseconds\")",
						methodName)
				.nextControlFlow("catch (Exception e)").addStatement("e.printStackTrace()").endControlFlow().build();

		MethodSpec latencyMethod = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class).returns(TypeName.VOID).addCode(codeBlock).build();

		TypeSpec latencyClass = TypeSpec.classBuilder("Latency_" + methodName).addModifiers(Modifier.PUBLIC)
				.addSuperinterface(ClassName.get(methodElement.getEnclosingElement().asType())).addMethod(latencyMethod)
				.build();

		JavaFile javaFile = JavaFile.builder("generated", latencyClass).build();

		try {
			javaFile.writeTo(processingEnv.getFiler());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}