// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.kotlin.idea.debugger.coroutine.proxy

import org.jetbrains.kotlin.idea.debugger.coroutine.data.CoroutineInfoData
import org.jetbrains.kotlin.idea.debugger.coroutine.data.LazyCoroutineInfoData
import org.jetbrains.kotlin.idea.debugger.coroutine.proxy.mirror.DebugProbesImpl
import org.jetbrains.kotlin.idea.debugger.base.util.evaluate.DefaultExecutionContext
import org.jetbrains.kotlin.idea.debugger.coroutine.data.CoroutineJobHierarchyProvider
import org.jetbrains.kotlin.idea.debugger.coroutine.data.CoroutineStackFramesProvider

class CoroutineLibraryAgent2Proxy(
    private val executionContext: DefaultExecutionContext,
    private val debugProbesImpl: DebugProbesImpl
) : CoroutineInfoProvider {
    private val stackFramesProvider = CoroutineStackFramesProvider(executionContext)
    private val jobHierarchyProvider = CoroutineJobHierarchyProvider()

    override fun dumpCoroutinesInfo(): List<CoroutineInfoData> {
        val result = debugProbesImpl.dumpCoroutinesInfo(executionContext)
        return result.map {
            LazyCoroutineInfoData(
                it,
                stackFramesProvider,
                jobHierarchyProvider
            )
        }
    }
}
