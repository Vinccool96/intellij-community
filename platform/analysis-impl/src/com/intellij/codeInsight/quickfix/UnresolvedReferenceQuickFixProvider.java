// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.codeInsight.quickfix;

import com.intellij.codeInsight.daemon.QuickFixActionRegistrar;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.project.DumbService;
import com.intellij.psi.PsiReference;
import com.intellij.util.ReflectionUtil;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Register implementation of this class as {@code com.intellij.codeInsight.unresolvedReferenceQuickFixProvider} extension to provide additional
 * quick fixes for 'Unresolved reference' problems.<p>
 * For example, this line in the {@code plugin.xml} file:
 * <p>
 *   {@code <codeInsight.unresolvedReferenceQuickFixProvider implementation="com.intellij.jarFinder.FindJarQuickFixProvider"/>}
 * </p>
 * registers class {@code com.intellij.jarFinder.FindJarQuickFixProvider"} as an unresolved reference quick fix.
 *
 * @param <T> type of element you want register quick fixes for; for example, in Java language it may be {@link com.intellij.psi.PsiJavaCodeReferenceElement}
 */
public abstract class UnresolvedReferenceQuickFixProvider<T extends PsiReference> {
  /**
   * Call each registered {@link UnresolvedReferenceQuickFixProvider} for its quick fixes.
   * Please don't use because it might be very expensive.
   */
  @ApiStatus.Internal
  public static <T extends PsiReference> void registerReferenceFixes(@NotNull T ref, @NotNull QuickFixActionRegistrar registrar) {
    DumbService dumbService = DumbService.getInstance(ref.getElement().getProject());
    Class<? extends PsiReference> referenceClass = ref.getClass();
    for (UnresolvedReferenceQuickFixProvider<?> each : EP_NAME.getExtensionList()) {
      if (!dumbService.isUsableInCurrentContext(each)) {
        continue;
      }
      if (ReflectionUtil.isAssignable(each.getReferenceClass(), referenceClass)) {
        //noinspection unchecked
        ((UnresolvedReferenceQuickFixProvider<T>)each).registerFixes(ref, registrar);
      }
    }
  }

  private static final ExtensionPointName<UnresolvedReferenceQuickFixProvider<?>> EP_NAME = ExtensionPointName.create("com.intellij.codeInsight.unresolvedReferenceQuickFixProvider");

  public abstract void registerFixes(@NotNull T ref, @NotNull QuickFixActionRegistrar registrar);

  public abstract @NotNull Class<T> getReferenceClass();
}
