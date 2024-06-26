// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.jetbrains.python;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.SettingsCategory;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "PythonFoldingSettings", storages = @Storage("editor.xml"), category = SettingsCategory.CODE)
public class PythonFoldingSettings implements PersistentStateComponent<PythonFoldingSettings> {
  public boolean COLLAPSE_LONG_STRINGS;
  public boolean COLLAPSE_LONG_COLLECTIONS;
  public boolean COLLAPSE_SEQUENTIAL_COMMENTS;
  public boolean COLLAPSE_TYPE_ANNOTATIONS;

  @Override
  public @Nullable PythonFoldingSettings getState() {
    return this;
  }

  public static @NotNull PythonFoldingSettings getInstance() {
    return ApplicationManager.getApplication().getService(PythonFoldingSettings.class);
  }

  @Override
  public void loadState(@NotNull PythonFoldingSettings state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  public boolean isCollapseLongStrings() {
    return COLLAPSE_LONG_STRINGS;
  }

  public boolean isCollapseLongCollections() {
    return COLLAPSE_LONG_COLLECTIONS;
  }

  public boolean isCollapseSequentialComments() {
    return COLLAPSE_SEQUENTIAL_COMMENTS;
  }

  public boolean isCollapseTypeAnnotations() {
    return COLLAPSE_TYPE_ANNOTATIONS;
  }
}
