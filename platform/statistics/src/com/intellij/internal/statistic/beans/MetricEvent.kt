// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.internal.statistic.beans

import com.intellij.internal.statistic.eventLog.FeatureUsageData
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.TestOnly
import java.util.*

/**
 * Used to write measurements to event log, override one of these methods to use it: <br/>
 * [com.intellij.internal.statistic.service.fus.collectors.ProjectUsagesCollector.getMetrics]<br/>
 * [com.intellij.internal.statistic.service.fus.collectors.ApplicationUsagesCollector.getMetrics]
 *
 * To create MetricEvent use one of these methods:
 * [com.intellij.internal.statistic.eventLog.events.EventId.metric]
 * [com.intellij.internal.statistic.eventLog.events.EventId1.metric]
 * [com.intellij.internal.statistic.eventLog.events.EventId2.metric]
 * [com.intellij.internal.statistic.eventLog.events.VarargEventId.metric]
 */
@ApiStatus.Internal
class MetricEvent(@NonNls val eventId: String, data: FeatureUsageData? = null, recorderId: String) {
  @TestOnly
  @ApiStatus.ScheduledForRemoval
  @Deprecated("Recorder ID should be explicitly provided", replaceWith = ReplaceWith("MetricEvent(eventId, @Nullable data, recorderId)"),
              DeprecationLevel.WARNING)
  @JvmOverloads
  constructor(@NonNls eventId: String, data: FeatureUsageData? = null) : this(eventId, data, "FUS")

  val data: FeatureUsageData = data ?: FeatureUsageData(recorderId)

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val event = other as MetricEvent?
    return eventId == event!!.eventId && data == event.data
  }

  override fun hashCode(): Int {
    return Objects.hash(eventId, data)
  }

  override fun toString(): String {
    return "$eventId: {$data}"
  }
}
