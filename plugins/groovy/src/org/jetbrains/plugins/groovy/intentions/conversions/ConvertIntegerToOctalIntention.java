/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.plugins.groovy.intentions.conversions;

import com.intellij.modcommand.ActionContext;
import com.intellij.modcommand.ModPsiUpdater;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.intentions.base.GrPsiUpdateIntention;
import org.jetbrains.plugins.groovy.intentions.base.PsiElementPredicate;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.expressions.literals.GrLiteral;
import org.jetbrains.plugins.groovy.lang.psi.impl.PsiImplUtil;

import java.math.BigInteger;

public final class ConvertIntegerToOctalIntention extends GrPsiUpdateIntention {


  @Override
  @NotNull
  public PsiElementPredicate getElementPredicate() {
    return new ConvertIntegerToOctalPredicate();
  }

  @Override
  protected void processIntention(@NotNull PsiElement element, @NotNull ActionContext context, @NotNull ModPsiUpdater updater) {
    final GrLiteral exp = (GrLiteral)element;
    @NonNls String textString = exp.getText().replaceAll("_", "");
    final int textLength = textString.length();
    final char lastChar = textString.charAt(textLength - 1);
    final boolean isLong = lastChar == 'l' || lastChar == 'L';
    if (isLong) {
      textString = textString.substring(0, textLength - 1);
    }
    final BigInteger val;
    if (textString.startsWith("0x") || textString.startsWith("0X")) {
      final String rawTextString = textString.substring(2);
      val = new BigInteger(rawTextString, 16);
    }
    else if (textString.startsWith("0b") || textString.startsWith("0B")) {
      final String rawTextString = textString.substring(2);
      val = new BigInteger(rawTextString, 2);
    }
    else {
      val = new BigInteger(textString, 10);
    }
    String octString = '0' + val.toString(8);
    if (isLong) {
      octString += 'L';
    }
    PsiImplUtil.replaceExpression(octString, exp);
  }
}
