// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.ide.impl;

import com.intellij.ide.DataManager;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.CustomizedDataContext;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.concurrency.AsyncPromise;
import org.jetbrains.concurrency.Promise;

import java.awt.*;
import java.util.Map;
import java.util.function.Supplier;

// not final - used in Google's bazel plugin (in tests)
@ApiStatus.NonExtendable
public class HeadlessDataManager extends DataManagerImpl {

  private volatile DataProvider myTestDataProvider;
  private volatile boolean myUseProductionDataManager = false;

  @TestOnly
  public void setTestDataProvider(@Nullable DataProvider provider) {
    myTestDataProvider = provider;
  }

  @TestOnly
  public void setTestDataProvider(@Nullable DataProvider provider, @NotNull Disposable parentDisposable) {
    DataProvider previous = myTestDataProvider;
    myTestDataProvider = provider;
    Disposer.register(parentDisposable, () -> myTestDataProvider = previous);
  }

  /**
   * By default {@link HeadlessDataManager} never traverses across Swing component hierarchy.
   * This method enables usage of production {@link DataManagerImpl} in the test mode.
   *
   * @param disposable Specifies when the forwarding should be unregistered.
   * @throws IllegalStateException If already called and still not disposed.
   */
  @TestOnly
  public static void fallbackToProductionDataManager(@NotNull Disposable disposable) {
    var manager = (HeadlessDataManager)DataManager.getInstance();
    if (manager.myUseProductionDataManager) {
      throw new IllegalStateException("Already called and still not disposed.");
    }

    Disposer.register(disposable, () -> {
      manager.myUseProductionDataManager = false;
    });
    manager.myUseProductionDataManager = true;
  }

  @Override
  public @NotNull DataContext getDataContext() {
    return new HeadlessContext(productionDataContext(super::getDataContext), myTestDataProvider);
  }

  @Override
  public @NotNull Promise<DataContext> getDataContextFromFocusAsync() {
    AsyncPromise<DataContext> promise = new AsyncPromise<>();
    promise.setResult(getDataContext());
    return promise;
  }

  @Override
  public @NotNull DataContext getDataContext(Component component) {
    return new HeadlessContext(productionDataContext(() -> super.getDataContext(component)), myTestDataProvider);
  }

  @Override
  public @NotNull DataContext getDataContext(@NotNull Component component, int x, int y) {
    return new HeadlessContext(productionDataContext(() -> super.getDataContext(component, x, y)), myTestDataProvider);
  }

  private @Nullable DataContext productionDataContext(@NotNull Supplier<? extends @NotNull DataContext> dataContextSupplier) {
    return myUseProductionDataManager ? dataContextSupplier.get() : null;
  }

  private static final class HeadlessContext extends CustomizedDataContext implements UserDataHolder {

    HeadlessContext(@Nullable DataContext parent, @Nullable DataProvider provider) {
      super(parent == null ? EMPTY_CONTEXT : parent,
            provider == null ? o -> null : provider,
            new MyUserDataHolder());
    }
  }

  private static class MyUserDataHolder implements UserDataHolder {
    private Map<Key<?>, Object> myUserData;

    @Override
    public <T> T getUserData(@NotNull Key<T> key) {
      //noinspection unchecked
      return (T)getOrCreateMap().get(key);
    }

    @Override
    public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {
      getOrCreateMap().put(key, value);
    }

    private @NotNull Map<Key<?>, Object> getOrCreateMap() {
      Map<Key<?>, Object> userData = myUserData;
      if (userData == null) {
        myUserData = userData = ContainerUtil.createWeakValueMap();
      }
      return userData;
    }
  }
}