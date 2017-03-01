package com.dk.mentoring.web.classloader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.apache.log4j.Logger;

public class Compiler {

	private static Logger LOGGER = Logger.getLogger(ClassGeneratorController.class);
	private static final String PATH = ";c:\\Users\\Denis_Kanapin\\.m2\\repository\\org\\";

	public static void compile(final File file, final JavaFileObject... objects) {

		System.setProperty("java.home", "c:\\Program Files\\Java\\jdk1.7.0_45\\");
		System.setProperty("java.class.path", System.getProperty("java.class.path") + PATH + "slf4j\\slf4j-api\\1.7.7\\slf4j-api-1.7.7.jar");
		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		final StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.getDefault(), Charset.defaultCharset());

		final Iterable<? extends JavaFileObject> compilationUnits = new ArrayList<JavaFileObject>(Arrays.asList(objects));

		String[] compileOptions = new String[] { "-d", file.getAbsolutePath() };
		final Iterable compilationOptions = Arrays.asList(compileOptions);
		final JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, compilationOptions, null,
				new ArrayList(Arrays.asList(objects)));
		final boolean result = task.call();
		if (result) {
			LOGGER.info("Compilation was successful");
		} else {
			System.out.println("Compilation failed");
			for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
				LOGGER.info(String.format("Error on line %d in %s", diagnostic.getLineNumber(), diagnostic));
			}
		}
		try {
			fileManager.close();
		} catch (final IOException e) {
			LOGGER.error(e);
		}

		for (final JavaFileObject object : objects) {
			final File oldFile = new File(file.getAbsolutePath() + File.separatorChar + object.getName().replace(".java", ".class"));
			final File newFile = new File(file.getAbsolutePath() + File.separatorChar + object.getName().replace(".java", ".test"));
			oldFile.renameTo(newFile);
		}
	}
}
