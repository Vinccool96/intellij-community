// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.debugger.engine.events;

import com.intellij.debugger.engine.SuspendContextImpl;
import com.intellij.debugger.engine.SuspendManager;
import com.intellij.debugger.engine.SuspendManagerUtil;
import com.intellij.debugger.impl.DebuggerContextImpl;
import com.intellij.debugger.jdi.ThreadReferenceProxyImpl;
import com.intellij.openapi.diagnostic.Logger;
import com.sun.jdi.ObjectCollectedException;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.request.EventRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class DebuggerContextCommandImpl extends SuspendContextCommandImpl {
  private static final Logger LOG = Logger.getInstance(DebuggerContextCommandImpl.class);

  private final @NotNull DebuggerContextImpl myDebuggerContext;
  private final ThreadReferenceProxyImpl myCustomThread; // thread to perform command in
  private SuspendContextImpl myCustomSuspendContext;

  protected DebuggerContextCommandImpl(@NotNull DebuggerContextImpl debuggerContext) {
    this(debuggerContext, null);
  }

  protected DebuggerContextCommandImpl(@NotNull DebuggerContextImpl debuggerContext, @Nullable ThreadReferenceProxyImpl customThread) {
    super(debuggerContext.getSuspendContext());
    myDebuggerContext = debuggerContext;
    myCustomThread = customThread;
  }

  @Nullable
  @Override
  public SuspendContextImpl getSuspendContext() {
    if (myCustomSuspendContext == null) {
      myCustomSuspendContext = super.getSuspendContext();
      ThreadReferenceProxyImpl thread = getThread();
      if (myCustomThread != null &&
          (myCustomSuspendContext == null || myCustomSuspendContext.isResumed() || !myCustomSuspendContext.suspends(thread))) {
        myCustomSuspendContext = SuspendManagerUtil.findContextByThread(myDebuggerContext.getDebugProcess().getSuspendManager(), thread);
      }
    }
    return myCustomSuspendContext;
  }

  @Nullable
  private ThreadReferenceProxyImpl getThread() {
    return myCustomThread != null ? myCustomThread : myDebuggerContext.getThreadProxy();
  }

  @NotNull
  public final DebuggerContextImpl getDebuggerContext() {
    return myDebuggerContext;
  }

  @Override
  public final void contextAction(@NotNull SuspendContextImpl suspendContext) {
    SuspendManager suspendManager = suspendContext.getDebugProcess().getSuspendManager();
    ThreadReferenceProxyImpl thread = getThread();
    boolean isSuspended;
    if (thread == null) {
      isSuspended = suspendContext.getSuspendPolicy() == EventRequest.SUSPEND_ALL;
    }
    else {
      try {
        isSuspended = suspendManager.isSuspended(thread);
      }
      catch (ObjectCollectedException ignored) {
        notifyCancelled();
        return;
      }
    }
    if (isSuspended) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("Context thread " + suspendContext.getThread());
        LOG.debug("Debug thread " + thread);
      }
      threadAction(suspendContext);
    }
    else {
      // no suspend context currently available
      SuspendContextImpl suspendContextForThread = myCustomThread != null ? suspendContext :
                                                   SuspendManagerUtil.findContextByThread(suspendManager, thread);
      if (suspendContextForThread != null && (thread == null || thread.status() != ThreadReference.THREAD_STATUS_ZOMBIE)) {
        suspendContextForThread.postponeCommand(this);
      }
      else {
        notifyCancelled();
      }
    }
  }

  public abstract void threadAction(@NotNull SuspendContextImpl suspendContext);
}
