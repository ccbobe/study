/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ccbobe.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2020-04-01")
public class Response implements org.apache.thrift.TBase<Response, Response._Fields>, java.io.Serializable, Cloneable, Comparable<Response> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Response");

  private static final org.apache.thrift.protocol.TField MSG_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("msgId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("code", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("data", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ResponseStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ResponseTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable String msgId; // optional
  public @org.apache.thrift.annotation.Nullable String code; // optional
  public @org.apache.thrift.annotation.Nullable String data; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MSG_ID((short)1, "msgId"),
    CODE((short)2, "code"),
    DATA((short)3, "data");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // MSG_ID
          return MSG_ID;
        case 2: // CODE
          return CODE;
        case 3: // DATA
          return DATA;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.MSG_ID,_Fields.CODE,_Fields.DATA};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MSG_ID, new org.apache.thrift.meta_data.FieldMetaData("msgId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CODE, new org.apache.thrift.meta_data.FieldMetaData("code", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATA, new org.apache.thrift.meta_data.FieldMetaData("data", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Response.class, metaDataMap);
  }

  public Response() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Response(Response other) {
    if (other.isSetMsgId()) {
      this.msgId = other.msgId;
    }
    if (other.isSetCode()) {
      this.code = other.code;
    }
    if (other.isSetData()) {
      this.data = other.data;
    }
  }

  public Response deepCopy() {
    return new Response(this);
  }

  @Override
  public void clear() {
    this.msgId = null;
    this.code = null;
    this.data = null;
  }

  @org.apache.thrift.annotation.Nullable
  public String getMsgId() {
    return this.msgId;
  }

  public Response setMsgId(@org.apache.thrift.annotation.Nullable String msgId) {
    this.msgId = msgId;
    return this;
  }

  public void unsetMsgId() {
    this.msgId = null;
  }

  /** Returns true if field msgId is set (has been assigned a value) and false otherwise */
  public boolean isSetMsgId() {
    return this.msgId != null;
  }

  public void setMsgIdIsSet(boolean value) {
    if (!value) {
      this.msgId = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public String getCode() {
    return this.code;
  }

  public Response setCode(@org.apache.thrift.annotation.Nullable String code) {
    this.code = code;
    return this;
  }

  public void unsetCode() {
    this.code = null;
  }

  /** Returns true if field code is set (has been assigned a value) and false otherwise */
  public boolean isSetCode() {
    return this.code != null;
  }

  public void setCodeIsSet(boolean value) {
    if (!value) {
      this.code = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public String getData() {
    return this.data;
  }

  public Response setData(@org.apache.thrift.annotation.Nullable String data) {
    this.data = data;
    return this;
  }

  public void unsetData() {
    this.data = null;
  }

  /** Returns true if field data is set (has been assigned a value) and false otherwise */
  public boolean isSetData() {
    return this.data != null;
  }

  public void setDataIsSet(boolean value) {
    if (!value) {
      this.data = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
    switch (field) {
    case MSG_ID:
      if (value == null) {
        unsetMsgId();
      } else {
        setMsgId((String)value);
      }
      break;

    case CODE:
      if (value == null) {
        unsetCode();
      } else {
        setCode((String)value);
      }
      break;

    case DATA:
      if (value == null) {
        unsetData();
      } else {
        setData((String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public Object getFieldValue(_Fields field) {
    switch (field) {
    case MSG_ID:
      return getMsgId();

    case CODE:
      return getCode();

    case DATA:
      return getData();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case MSG_ID:
      return isSetMsgId();
    case CODE:
      return isSetCode();
    case DATA:
      return isSetData();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Response)
      return this.equals((Response)that);
    return false;
  }

  public boolean equals(Response that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_msgId = true && this.isSetMsgId();
    boolean that_present_msgId = true && that.isSetMsgId();
    if (this_present_msgId || that_present_msgId) {
      if (!(this_present_msgId && that_present_msgId))
        return false;
      if (!this.msgId.equals(that.msgId))
        return false;
    }

    boolean this_present_code = true && this.isSetCode();
    boolean that_present_code = true && that.isSetCode();
    if (this_present_code || that_present_code) {
      if (!(this_present_code && that_present_code))
        return false;
      if (!this.code.equals(that.code))
        return false;
    }

    boolean this_present_data = true && this.isSetData();
    boolean that_present_data = true && that.isSetData();
    if (this_present_data || that_present_data) {
      if (!(this_present_data && that_present_data))
        return false;
      if (!this.data.equals(that.data))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetMsgId()) ? 131071 : 524287);
    if (isSetMsgId())
      hashCode = hashCode * 8191 + msgId.hashCode();

    hashCode = hashCode * 8191 + ((isSetCode()) ? 131071 : 524287);
    if (isSetCode())
      hashCode = hashCode * 8191 + code.hashCode();

    hashCode = hashCode * 8191 + ((isSetData()) ? 131071 : 524287);
    if (isSetData())
      hashCode = hashCode * 8191 + data.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Response other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetMsgId()).compareTo(other.isSetMsgId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMsgId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.msgId, other.msgId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCode()).compareTo(other.isSetCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.code, other.code);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetData()).compareTo(other.isSetData());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetData()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.data, other.data);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Response(");
    boolean first = true;

    if (isSetMsgId()) {
      sb.append("msgId:");
      if (this.msgId == null) {
        sb.append("null");
      } else {
        sb.append(this.msgId);
      }
      first = false;
    }
    if (isSetCode()) {
      if (!first) sb.append(", ");
      sb.append("code:");
      if (this.code == null) {
        sb.append("null");
      } else {
        sb.append(this.code);
      }
      first = false;
    }
    if (isSetData()) {
      if (!first) sb.append(", ");
      sb.append("data:");
      if (this.data == null) {
        sb.append("null");
      } else {
        sb.append(this.data);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ResponseStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ResponseStandardScheme getScheme() {
      return new ResponseStandardScheme();
    }
  }

  private static class ResponseStandardScheme extends org.apache.thrift.scheme.StandardScheme<Response> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Response struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MSG_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.msgId = iprot.readString();
              struct.setMsgIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.code = iprot.readString();
              struct.setCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.data = iprot.readString();
              struct.setDataIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Response struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.msgId != null) {
        if (struct.isSetMsgId()) {
          oprot.writeFieldBegin(MSG_ID_FIELD_DESC);
          oprot.writeString(struct.msgId);
          oprot.writeFieldEnd();
        }
      }
      if (struct.code != null) {
        if (struct.isSetCode()) {
          oprot.writeFieldBegin(CODE_FIELD_DESC);
          oprot.writeString(struct.code);
          oprot.writeFieldEnd();
        }
      }
      if (struct.data != null) {
        if (struct.isSetData()) {
          oprot.writeFieldBegin(DATA_FIELD_DESC);
          oprot.writeString(struct.data);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ResponseTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ResponseTupleScheme getScheme() {
      return new ResponseTupleScheme();
    }
  }

  private static class ResponseTupleScheme extends org.apache.thrift.scheme.TupleScheme<Response> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Response struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetMsgId()) {
        optionals.set(0);
      }
      if (struct.isSetCode()) {
        optionals.set(1);
      }
      if (struct.isSetData()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetMsgId()) {
        oprot.writeString(struct.msgId);
      }
      if (struct.isSetCode()) {
        oprot.writeString(struct.code);
      }
      if (struct.isSetData()) {
        oprot.writeString(struct.data);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Response struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.msgId = iprot.readString();
        struct.setMsgIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.code = iprot.readString();
        struct.setCodeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.data = iprot.readString();
        struct.setDataIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

