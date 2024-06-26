// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.psi.presentation.java;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProvider;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiVariable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public final class VariablePresentationProvider<T extends PsiVariable & NavigationItem> implements ItemPresentationProvider<T> {
  @Override
  public ItemPresentation getPresentation(final @NotNull T variable) {
    return new ItemPresentation() {
      @Override
      public String getPresentableText() {
        //return PsiFormatUtil.formatVariable(variable, PsiFormatUtilBase.SHOW_TYPE, PsiSubstitutor.EMPTY);
        return variable.getName();
      }

      @Override
      public String getLocationString() {
        return "";
      }

      @Override
      public Icon getIcon(boolean open) {
        return variable.getIcon(0);
      }
    };
  }
}
