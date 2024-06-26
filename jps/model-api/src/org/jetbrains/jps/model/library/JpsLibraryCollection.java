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
package org.jetbrains.jps.model.library;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.JpsElementTypeWithDefaultProperties;

import java.util.List;

public interface JpsLibraryCollection {
  @ApiStatus.Internal
  @NotNull
  <P extends JpsElement, LibraryType extends JpsLibraryType<P> & JpsElementTypeWithDefaultProperties<P>>
  JpsLibrary addLibrary(@NotNull String name, @NotNull LibraryType type);

  @ApiStatus.Internal
  @NotNull
  <P extends JpsElement>
  JpsTypedLibrary<P> addLibrary(@NotNull String name, @NotNull JpsLibraryType<P> type, @NotNull P properties);

  @NotNull
  List<JpsLibrary> getLibraries();

  @NotNull
  <P extends JpsElement>
  Iterable<JpsTypedLibrary<P>> getLibraries(@NotNull JpsLibraryType<P> type);

  @ApiStatus.Internal
  void addLibrary(@NotNull JpsLibrary library);

  @ApiStatus.Internal
  void removeLibrary(@NotNull JpsLibrary library);

  @Nullable
  JpsLibrary findLibrary(@NotNull String name);

  @Nullable
  <E extends JpsElement> JpsTypedLibrary<E> findLibrary(@NotNull String name, @NotNull JpsLibraryType<E> type);
}
