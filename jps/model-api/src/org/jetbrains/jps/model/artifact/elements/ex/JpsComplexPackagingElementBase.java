/*
 * Copyright 2000-2012 JetBrains s.r.o.
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
package org.jetbrains.jps.model.artifact.elements.ex;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.artifact.elements.JpsComplexPackagingElement;
import org.jetbrains.jps.model.ex.JpsCompositeElementBase;

public abstract class JpsComplexPackagingElementBase<Self extends JpsComplexPackagingElementBase<Self>> extends JpsCompositeElementBase<Self> implements
                                                                                                                                              JpsComplexPackagingElement {
  protected JpsComplexPackagingElementBase() {
  }

  /**
   * @deprecated creating copies isn't supported in for all elements in JPS anymore; if you need to create a copy for your element,
   * write the corresponding code in your class directly.
   */
  @Deprecated
  protected JpsComplexPackagingElementBase(JpsComplexPackagingElementBase<Self> original) {
    super(original);
  }

  @Override
  public @NotNull Self createCopy() {
    //noinspection unchecked
    return (Self)createElementCopy();
  }
}
