package com.dk.mentoring.javapoet.gen.impl;

import com.dk.mentoring.javapoet.gen.Car;
import java.lang.Override;
import java.lang.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Audi implements Car {
  private String engine;

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() {
    LOGGER.info("The engine starts");
    setEngine("start");
  }

  @Override
  public void stop() {
    LOGGER.info("The engine stops");
    setEngine("stop");
  }

  public String getEngine() {
    return this.engine;
  }

  public void setEngine(final String engine) {
    this.engine = engine;
  }
}
