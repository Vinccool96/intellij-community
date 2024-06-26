// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.application;

import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.Contract;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Random;

import static org.junit.Assert.*;

public class PathManagerTest {
  private static final String TEST_RPOP = "__ij_subst_test__";
  private static final String TEST_VALUE = "__" + new Random().nextInt(1000) + "__";

  @Before
  public void setUp() {
    System.setProperty(TEST_RPOP, TEST_VALUE);
  }

  @After
  public void tearDown() {
    System.clearProperty(TEST_RPOP);
  }

  @Test
  public void testResourceRoot() {
    String jarRoot = PathManager.getResourceRoot(getClass(), "/" + Test.class.getName().replace('.', '/') + ".class");
    assertNotNull(jarRoot);
    assertTrue(jarRoot, jarRoot.endsWith(".jar"));
    assertTrue(new File(jarRoot).isFile());

    String dirRoot = PathManager.getResourceRoot(getClass(), "/" + PathManager.class.getName().replace('.', '/') + ".class");
    assertNotNull(dirRoot);
    assertFalse(dirRoot, dirRoot.endsWith("/"));
    assertTrue(new File(dirRoot).isDirectory());
  }

  @Test
  public void testVarSubstitution() {
    assertEquals("", substituteVars(""));
    assertEquals("abc", substituteVars("abc"));
    assertEquals("a$b$c", substituteVars("a$b$c"));

    assertEquals("/" + TEST_VALUE + "/" + TEST_VALUE + "/", substituteVars("/${" + TEST_RPOP + "}/${" + TEST_RPOP + "}/"));

    String home = System.clearProperty(PathManager.PROPERTY_HOME_PATH);
    try {
      assertEquals(PathManager.getHomePath() + "/build.txt", substituteVars("${idea.home}/build.txt"));
      assertEquals(PathManager.getHomePath() + "\\build.txt", substituteVars("${idea.home.path}\\build.txt"));
      assertEquals("/opt/idea/build.txt", PathManager.substituteVars("${idea.home}/build.txt", "/opt/idea"));
      assertEquals("C:\\opt\\idea\\build.txt", PathManager.substituteVars("${idea.home.path}\\build.txt", "C:\\opt\\idea"));
    }
    finally {
      if (home != null) {
        System.setProperty(PathManager.PROPERTY_HOME_PATH, home);
      }
    }

    String config = System.clearProperty(PathManager.PROPERTY_CONFIG_PATH);
    try {
      assertEquals(PathManager.getConfigPath() + "/opts", substituteVars("${idea.config.path}/opts"));
    }
    finally {
      if (config != null) {
        System.setProperty(PathManager.PROPERTY_CONFIG_PATH, config);
      }
    }

    String system = System.clearProperty(PathManager.PROPERTY_SYSTEM_PATH);
    try {
      assertEquals(PathManager.getSystemPath() + "/logs2", substituteVars("${idea.system.path}/logs2"));
    }
    finally {
      if (system != null) {
        System.setProperty(PathManager.PROPERTY_CONFIG_PATH, system);
      }
    }

    assertTrue(FileUtil.pathsEqual(PathManager.getBinPath() + "/../license", substituteVars("../license")));

    assertEquals("//", substituteVars("/${unknown_property_ignore_the_error}/"));
  }

  @Contract("null -> null")
  public static String substituteVars(String s) {
    return PathManager.substituteVars(s, PathManager.getHomePath());
  }
}