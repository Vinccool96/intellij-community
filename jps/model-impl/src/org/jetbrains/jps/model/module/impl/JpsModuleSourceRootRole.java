// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.jps.model.module.impl;

import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.model.ex.JpsElementCollectionRole;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;

final class JpsModuleSourceRootRole extends JpsElementChildRoleBase<JpsModuleSourceRoot> {
  private static final JpsModuleSourceRootRole INSTANCE = new JpsModuleSourceRootRole();
  static final JpsElementCollectionRole<JpsModuleSourceRoot> ROOT_COLLECTION_ROLE = JpsElementCollectionRole.create(INSTANCE);

  private JpsModuleSourceRootRole() {
    super("module source root");
  }
}
