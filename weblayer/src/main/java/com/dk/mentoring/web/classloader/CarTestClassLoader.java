package com.dk.mentoring.web.classloader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class CarTestClassLoader extends ClassLoader {

	private static Logger LOGGER = LoggerFactory.getLogger(ClassGeneratorController.class);

	private static final String PATH = "D:\\Program Files\\mentor\\mentoringTask3\\mentoringTask3\\weblayer\\src\\main\\resources\\";

	public CarTestClassLoader(final ClassLoader parent) {
		super(parent);
	}

	@Override
	public Class<?> loadClass(final String name) throws ClassNotFoundException {

		LOGGER.info("CarTestClassLoader.loadClass method activated");
		if (name.startsWith("com.dk.mentoring.javapoet.gen.impl") || name.startsWith("com.dk.mentoring.javapoet.gen")) {
			return getClass(name);
		}
		return super.loadClass(name);
	}

	private Class getClass(final String name) throws ClassNotFoundException {
		LOGGER.info("getClass()");
		final String file = PATH + name.replaceAll("\\.", "/") + ".test";
		byte[] b = null;
		try {
			b = this.loadClassFileData(file);
			final Class c = defineClass(name, b, 0, b.length);
			resolveClass(c);
			return c;
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private byte[] loadClassFileData(final String name) throws IOException {
		final InputStream stream = new FileInputStream(name);
		final int size = stream.available();
		final byte buff[] = new byte[size];
		final DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}
}
