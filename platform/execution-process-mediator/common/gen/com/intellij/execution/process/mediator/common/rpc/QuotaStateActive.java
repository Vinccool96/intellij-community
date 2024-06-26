// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: processMediator.proto

package com.intellij.execution.process.mediator.common.rpc;

/**
 * Protobuf type {@code intellij.process.mediator.common.rpc.QuotaStateActive}
 */
public final class QuotaStateActive extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:intellij.process.mediator.common.rpc.QuotaStateActive)
    QuotaStateActiveOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QuotaStateActive.newBuilder() to construct.
  private QuotaStateActive(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QuotaStateActive() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new QuotaStateActive();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.intellij.execution.process.mediator.common.rpc.ProcessMediatorProto.internal_static_intellij_process_mediator_common_rpc_QuotaStateActive_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.intellij.execution.process.mediator.common.rpc.ProcessMediatorProto.internal_static_intellij_process_mediator_common_rpc_QuotaStateActive_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.intellij.execution.process.mediator.common.rpc.QuotaStateActive.class, com.intellij.execution.process.mediator.common.rpc.QuotaStateActive.Builder.class);
  }

  public static final int ELAPSED_MS_FIELD_NUMBER = 1;
  private long elapsedMs_ = 0L;
  /**
   * <code>int64 elapsed_ms = 1;</code>
   * @return The elapsedMs.
   */
  @java.lang.Override
  public long getElapsedMs() {
    return elapsedMs_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (elapsedMs_ != 0L) {
      output.writeInt64(1, elapsedMs_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (elapsedMs_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, elapsedMs_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.intellij.execution.process.mediator.common.rpc.QuotaStateActive)) {
      return super.equals(obj);
    }
    com.intellij.execution.process.mediator.common.rpc.QuotaStateActive other = (com.intellij.execution.process.mediator.common.rpc.QuotaStateActive) obj;

    if (getElapsedMs()
        != other.getElapsedMs()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ELAPSED_MS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getElapsedMs());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.intellij.execution.process.mediator.common.rpc.QuotaStateActive prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code intellij.process.mediator.common.rpc.QuotaStateActive}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:intellij.process.mediator.common.rpc.QuotaStateActive)
      com.intellij.execution.process.mediator.common.rpc.QuotaStateActiveOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.intellij.execution.process.mediator.common.rpc.ProcessMediatorProto.internal_static_intellij_process_mediator_common_rpc_QuotaStateActive_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.intellij.execution.process.mediator.common.rpc.ProcessMediatorProto.internal_static_intellij_process_mediator_common_rpc_QuotaStateActive_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.intellij.execution.process.mediator.common.rpc.QuotaStateActive.class, com.intellij.execution.process.mediator.common.rpc.QuotaStateActive.Builder.class);
    }

    // Construct using com.intellij.execution.process.mediator.common.rpc.QuotaStateActive.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      elapsedMs_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.intellij.execution.process.mediator.common.rpc.ProcessMediatorProto.internal_static_intellij_process_mediator_common_rpc_QuotaStateActive_descriptor;
    }

    @java.lang.Override
    public com.intellij.execution.process.mediator.common.rpc.QuotaStateActive getDefaultInstanceForType() {
      return com.intellij.execution.process.mediator.common.rpc.QuotaStateActive.getDefaultInstance();
    }

    @java.lang.Override
    public com.intellij.execution.process.mediator.common.rpc.QuotaStateActive build() {
      com.intellij.execution.process.mediator.common.rpc.QuotaStateActive result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.intellij.execution.process.mediator.common.rpc.QuotaStateActive buildPartial() {
      com.intellij.execution.process.mediator.common.rpc.QuotaStateActive result = new com.intellij.execution.process.mediator.common.rpc.QuotaStateActive(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.intellij.execution.process.mediator.common.rpc.QuotaStateActive result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.elapsedMs_ = elapsedMs_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.intellij.execution.process.mediator.common.rpc.QuotaStateActive) {
        return mergeFrom((com.intellij.execution.process.mediator.common.rpc.QuotaStateActive)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.intellij.execution.process.mediator.common.rpc.QuotaStateActive other) {
      if (other == com.intellij.execution.process.mediator.common.rpc.QuotaStateActive.getDefaultInstance()) return this;
      if (other.getElapsedMs() != 0L) {
        setElapsedMs(other.getElapsedMs());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              elapsedMs_ = input.readInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private long elapsedMs_ ;
    /**
     * <code>int64 elapsed_ms = 1;</code>
     * @return The elapsedMs.
     */
    @java.lang.Override
    public long getElapsedMs() {
      return elapsedMs_;
    }
    /**
     * <code>int64 elapsed_ms = 1;</code>
     * @param value The elapsedMs to set.
     * @return This builder for chaining.
     */
    public Builder setElapsedMs(long value) {

      elapsedMs_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int64 elapsed_ms = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearElapsedMs() {
      bitField0_ = (bitField0_ & ~0x00000001);
      elapsedMs_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:intellij.process.mediator.common.rpc.QuotaStateActive)
  }

  // @@protoc_insertion_point(class_scope:intellij.process.mediator.common.rpc.QuotaStateActive)
  private static final com.intellij.execution.process.mediator.common.rpc.QuotaStateActive DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.intellij.execution.process.mediator.common.rpc.QuotaStateActive();
  }

  public static com.intellij.execution.process.mediator.common.rpc.QuotaStateActive getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QuotaStateActive>
      PARSER = new com.google.protobuf.AbstractParser<QuotaStateActive>() {
    @java.lang.Override
    public QuotaStateActive parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<QuotaStateActive> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QuotaStateActive> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.intellij.execution.process.mediator.common.rpc.QuotaStateActive getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

