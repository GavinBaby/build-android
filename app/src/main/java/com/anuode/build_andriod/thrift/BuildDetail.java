package com.anuode.build_andriod.thrift; /**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
/**
 * 建筑明细
 */
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-01-12")
public class BuildDetail implements org.apache.thrift.TBase<BuildDetail, BuildDetail._Fields>, java.io.Serializable, Cloneable, Comparable<BuildDetail> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BuildDetail");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField MAIN_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("main_id", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField DESC_FIELD_DESC = new org.apache.thrift.protocol.TField("desc", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField URL1_FIELD_DESC = new org.apache.thrift.protocol.TField("url1", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField URL2_FIELD_DESC = new org.apache.thrift.protocol.TField("url2", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField URL3_FIELD_DESC = new org.apache.thrift.protocol.TField("url3", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField URL4_FIELD_DESC = new org.apache.thrift.protocol.TField("url4", org.apache.thrift.protocol.TType.STRING, (short)8);
  private static final org.apache.thrift.protocol.TField URL5_FIELD_DESC = new org.apache.thrift.protocol.TField("url5", org.apache.thrift.protocol.TType.STRING, (short)9);
  private static final org.apache.thrift.protocol.TField URL6_FIELD_DESC = new org.apache.thrift.protocol.TField("url6", org.apache.thrift.protocol.TType.STRING, (short)10);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new BuildDetailStandardSchemeFactory());
    schemes.put(TupleScheme.class, new BuildDetailTupleSchemeFactory());
  }

  public String id; // required
  public String main_id; // required
  public String name; // required
  public String desc; // required
  public String url1; // required
  public String url2; // required
  public String url3; // required
  public String url4; // required
  public String url5; // required
  public String url6; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    MAIN_ID((short)2, "main_id"),
    NAME((short)3, "name"),
    DESC((short)4, "desc"),
    URL1((short)5, "url1"),
    URL2((short)6, "url2"),
    URL3((short)7, "url3"),
    URL4((short)8, "url4"),
    URL5((short)9, "url5"),
    URL6((short)10, "url6");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // MAIN_ID
          return MAIN_ID;
        case 3: // NAME
          return NAME;
        case 4: // DESC
          return DESC;
        case 5: // URL1
          return URL1;
        case 6: // URL2
          return URL2;
        case 7: // URL3
          return URL3;
        case 8: // URL4
          return URL4;
        case 9: // URL5
          return URL5;
        case 10: // URL6
          return URL6;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MAIN_ID, new org.apache.thrift.meta_data.FieldMetaData("main_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESC, new org.apache.thrift.meta_data.FieldMetaData("desc", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL1, new org.apache.thrift.meta_data.FieldMetaData("url1", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL2, new org.apache.thrift.meta_data.FieldMetaData("url2", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL3, new org.apache.thrift.meta_data.FieldMetaData("url3", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL4, new org.apache.thrift.meta_data.FieldMetaData("url4", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL5, new org.apache.thrift.meta_data.FieldMetaData("url5", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL6, new org.apache.thrift.meta_data.FieldMetaData("url6", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BuildDetail.class, metaDataMap);
  }

  public BuildDetail() {
  }

  public BuildDetail(
    String id,
    String main_id,
    String name,
    String desc,
    String url1,
    String url2,
    String url3,
    String url4,
    String url5,
    String url6)
  {
    this();
    this.id = id;
    this.main_id = main_id;
    this.name = name;
    this.desc = desc;
    this.url1 = url1;
    this.url2 = url2;
    this.url3 = url3;
    this.url4 = url4;
    this.url5 = url5;
    this.url6 = url6;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BuildDetail(BuildDetail other) {
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetMain_id()) {
      this.main_id = other.main_id;
    }
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetDesc()) {
      this.desc = other.desc;
    }
    if (other.isSetUrl1()) {
      this.url1 = other.url1;
    }
    if (other.isSetUrl2()) {
      this.url2 = other.url2;
    }
    if (other.isSetUrl3()) {
      this.url3 = other.url3;
    }
    if (other.isSetUrl4()) {
      this.url4 = other.url4;
    }
    if (other.isSetUrl5()) {
      this.url5 = other.url5;
    }
    if (other.isSetUrl6()) {
      this.url6 = other.url6;
    }
  }

  public BuildDetail deepCopy() {
    return new BuildDetail(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.main_id = null;
    this.name = null;
    this.desc = null;
    this.url1 = null;
    this.url2 = null;
    this.url3 = null;
    this.url4 = null;
    this.url5 = null;
    this.url6 = null;
  }

  public String getId() {
    return this.id;
  }

  public BuildDetail setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public String getMain_id() {
    return this.main_id;
  }

  public BuildDetail setMain_id(String main_id) {
    this.main_id = main_id;
    return this;
  }

  public void unsetMain_id() {
    this.main_id = null;
  }

  /** Returns true if field main_id is set (has been assigned a value) and false otherwise */
  public boolean isSetMain_id() {
    return this.main_id != null;
  }

  public void setMain_idIsSet(boolean value) {
    if (!value) {
      this.main_id = null;
    }
  }

  public String getName() {
    return this.name;
  }

  public BuildDetail setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public String getDesc() {
    return this.desc;
  }

  public BuildDetail setDesc(String desc) {
    this.desc = desc;
    return this;
  }

  public void unsetDesc() {
    this.desc = null;
  }

  /** Returns true if field desc is set (has been assigned a value) and false otherwise */
  public boolean isSetDesc() {
    return this.desc != null;
  }

  public void setDescIsSet(boolean value) {
    if (!value) {
      this.desc = null;
    }
  }

  public String getUrl1() {
    return this.url1;
  }

  public BuildDetail setUrl1(String url1) {
    this.url1 = url1;
    return this;
  }

  public void unsetUrl1() {
    this.url1 = null;
  }

  /** Returns true if field url1 is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl1() {
    return this.url1 != null;
  }

  public void setUrl1IsSet(boolean value) {
    if (!value) {
      this.url1 = null;
    }
  }

  public String getUrl2() {
    return this.url2;
  }

  public BuildDetail setUrl2(String url2) {
    this.url2 = url2;
    return this;
  }

  public void unsetUrl2() {
    this.url2 = null;
  }

  /** Returns true if field url2 is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl2() {
    return this.url2 != null;
  }

  public void setUrl2IsSet(boolean value) {
    if (!value) {
      this.url2 = null;
    }
  }

  public String getUrl3() {
    return this.url3;
  }

  public BuildDetail setUrl3(String url3) {
    this.url3 = url3;
    return this;
  }

  public void unsetUrl3() {
    this.url3 = null;
  }

  /** Returns true if field url3 is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl3() {
    return this.url3 != null;
  }

  public void setUrl3IsSet(boolean value) {
    if (!value) {
      this.url3 = null;
    }
  }

  public String getUrl4() {
    return this.url4;
  }

  public BuildDetail setUrl4(String url4) {
    this.url4 = url4;
    return this;
  }

  public void unsetUrl4() {
    this.url4 = null;
  }

  /** Returns true if field url4 is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl4() {
    return this.url4 != null;
  }

  public void setUrl4IsSet(boolean value) {
    if (!value) {
      this.url4 = null;
    }
  }

  public String getUrl5() {
    return this.url5;
  }

  public BuildDetail setUrl5(String url5) {
    this.url5 = url5;
    return this;
  }

  public void unsetUrl5() {
    this.url5 = null;
  }

  /** Returns true if field url5 is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl5() {
    return this.url5 != null;
  }

  public void setUrl5IsSet(boolean value) {
    if (!value) {
      this.url5 = null;
    }
  }

  public String getUrl6() {
    return this.url6;
  }

  public BuildDetail setUrl6(String url6) {
    this.url6 = url6;
    return this;
  }

  public void unsetUrl6() {
    this.url6 = null;
  }

  /** Returns true if field url6 is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl6() {
    return this.url6 != null;
  }

  public void setUrl6IsSet(boolean value) {
    if (!value) {
      this.url6 = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case MAIN_ID:
      if (value == null) {
        unsetMain_id();
      } else {
        setMain_id((String)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case DESC:
      if (value == null) {
        unsetDesc();
      } else {
        setDesc((String)value);
      }
      break;

    case URL1:
      if (value == null) {
        unsetUrl1();
      } else {
        setUrl1((String)value);
      }
      break;

    case URL2:
      if (value == null) {
        unsetUrl2();
      } else {
        setUrl2((String)value);
      }
      break;

    case URL3:
      if (value == null) {
        unsetUrl3();
      } else {
        setUrl3((String)value);
      }
      break;

    case URL4:
      if (value == null) {
        unsetUrl4();
      } else {
        setUrl4((String)value);
      }
      break;

    case URL5:
      if (value == null) {
        unsetUrl5();
      } else {
        setUrl5((String)value);
      }
      break;

    case URL6:
      if (value == null) {
        unsetUrl6();
      } else {
        setUrl6((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case MAIN_ID:
      return getMain_id();

    case NAME:
      return getName();

    case DESC:
      return getDesc();

    case URL1:
      return getUrl1();

    case URL2:
      return getUrl2();

    case URL3:
      return getUrl3();

    case URL4:
      return getUrl4();

    case URL5:
      return getUrl5();

    case URL6:
      return getUrl6();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case MAIN_ID:
      return isSetMain_id();
    case NAME:
      return isSetName();
    case DESC:
      return isSetDesc();
    case URL1:
      return isSetUrl1();
    case URL2:
      return isSetUrl2();
    case URL3:
      return isSetUrl3();
    case URL4:
      return isSetUrl4();
    case URL5:
      return isSetUrl5();
    case URL6:
      return isSetUrl6();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof BuildDetail)
      return this.equals((BuildDetail)that);
    return false;
  }

  public boolean equals(BuildDetail that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_main_id = true && this.isSetMain_id();
    boolean that_present_main_id = true && that.isSetMain_id();
    if (this_present_main_id || that_present_main_id) {
      if (!(this_present_main_id && that_present_main_id))
        return false;
      if (!this.main_id.equals(that.main_id))
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_desc = true && this.isSetDesc();
    boolean that_present_desc = true && that.isSetDesc();
    if (this_present_desc || that_present_desc) {
      if (!(this_present_desc && that_present_desc))
        return false;
      if (!this.desc.equals(that.desc))
        return false;
    }

    boolean this_present_url1 = true && this.isSetUrl1();
    boolean that_present_url1 = true && that.isSetUrl1();
    if (this_present_url1 || that_present_url1) {
      if (!(this_present_url1 && that_present_url1))
        return false;
      if (!this.url1.equals(that.url1))
        return false;
    }

    boolean this_present_url2 = true && this.isSetUrl2();
    boolean that_present_url2 = true && that.isSetUrl2();
    if (this_present_url2 || that_present_url2) {
      if (!(this_present_url2 && that_present_url2))
        return false;
      if (!this.url2.equals(that.url2))
        return false;
    }

    boolean this_present_url3 = true && this.isSetUrl3();
    boolean that_present_url3 = true && that.isSetUrl3();
    if (this_present_url3 || that_present_url3) {
      if (!(this_present_url3 && that_present_url3))
        return false;
      if (!this.url3.equals(that.url3))
        return false;
    }

    boolean this_present_url4 = true && this.isSetUrl4();
    boolean that_present_url4 = true && that.isSetUrl4();
    if (this_present_url4 || that_present_url4) {
      if (!(this_present_url4 && that_present_url4))
        return false;
      if (!this.url4.equals(that.url4))
        return false;
    }

    boolean this_present_url5 = true && this.isSetUrl5();
    boolean that_present_url5 = true && that.isSetUrl5();
    if (this_present_url5 || that_present_url5) {
      if (!(this_present_url5 && that_present_url5))
        return false;
      if (!this.url5.equals(that.url5))
        return false;
    }

    boolean this_present_url6 = true && this.isSetUrl6();
    boolean that_present_url6 = true && that.isSetUrl6();
    if (this_present_url6 || that_present_url6) {
      if (!(this_present_url6 && that_present_url6))
        return false;
      if (!this.url6.equals(that.url6))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true && (isSetId());
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_main_id = true && (isSetMain_id());
    list.add(present_main_id);
    if (present_main_id)
      list.add(main_id);

    boolean present_name = true && (isSetName());
    list.add(present_name);
    if (present_name)
      list.add(name);

    boolean present_desc = true && (isSetDesc());
    list.add(present_desc);
    if (present_desc)
      list.add(desc);

    boolean present_url1 = true && (isSetUrl1());
    list.add(present_url1);
    if (present_url1)
      list.add(url1);

    boolean present_url2 = true && (isSetUrl2());
    list.add(present_url2);
    if (present_url2)
      list.add(url2);

    boolean present_url3 = true && (isSetUrl3());
    list.add(present_url3);
    if (present_url3)
      list.add(url3);

    boolean present_url4 = true && (isSetUrl4());
    list.add(present_url4);
    if (present_url4)
      list.add(url4);

    boolean present_url5 = true && (isSetUrl5());
    list.add(present_url5);
    if (present_url5)
      list.add(url5);

    boolean present_url6 = true && (isSetUrl6());
    list.add(present_url6);
    if (present_url6)
      list.add(url6);

    return list.hashCode();
  }

  @Override
  public int compareTo(BuildDetail other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMain_id()).compareTo(other.isSetMain_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMain_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.main_id, other.main_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDesc()).compareTo(other.isSetDesc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDesc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.desc, other.desc);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl1()).compareTo(other.isSetUrl1());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl1()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url1, other.url1);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl2()).compareTo(other.isSetUrl2());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl2()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url2, other.url2);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl3()).compareTo(other.isSetUrl3());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl3()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url3, other.url3);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl4()).compareTo(other.isSetUrl4());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl4()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url4, other.url4);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl5()).compareTo(other.isSetUrl5());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl5()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url5, other.url5);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl6()).compareTo(other.isSetUrl6());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl6()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url6, other.url6);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BuildDetail(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("main_id:");
    if (this.main_id == null) {
      sb.append("null");
    } else {
      sb.append(this.main_id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("desc:");
    if (this.desc == null) {
      sb.append("null");
    } else {
      sb.append(this.desc);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("url1:");
    if (this.url1 == null) {
      sb.append("null");
    } else {
      sb.append(this.url1);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("url2:");
    if (this.url2 == null) {
      sb.append("null");
    } else {
      sb.append(this.url2);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("url3:");
    if (this.url3 == null) {
      sb.append("null");
    } else {
      sb.append(this.url3);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("url4:");
    if (this.url4 == null) {
      sb.append("null");
    } else {
      sb.append(this.url4);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("url5:");
    if (this.url5 == null) {
      sb.append("null");
    } else {
      sb.append(this.url5);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("url6:");
    if (this.url6 == null) {
      sb.append("null");
    } else {
      sb.append(this.url6);
    }
    first = false;
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

  private static class BuildDetailStandardSchemeFactory implements SchemeFactory {
    public BuildDetailStandardScheme getScheme() {
      return new BuildDetailStandardScheme();
    }
  }

  private static class BuildDetailStandardScheme extends StandardScheme<BuildDetail> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BuildDetail struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MAIN_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.main_id = iprot.readString();
              struct.setMain_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DESC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.desc = iprot.readString();
              struct.setDescIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // URL1
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url1 = iprot.readString();
              struct.setUrl1IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // URL2
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url2 = iprot.readString();
              struct.setUrl2IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // URL3
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url3 = iprot.readString();
              struct.setUrl3IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // URL4
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url4 = iprot.readString();
              struct.setUrl4IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 9: // URL5
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url5 = iprot.readString();
              struct.setUrl5IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 10: // URL6
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url6 = iprot.readString();
              struct.setUrl6IsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, BuildDetail struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.main_id != null) {
        oprot.writeFieldBegin(MAIN_ID_FIELD_DESC);
        oprot.writeString(struct.main_id);
        oprot.writeFieldEnd();
      }
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.desc != null) {
        oprot.writeFieldBegin(DESC_FIELD_DESC);
        oprot.writeString(struct.desc);
        oprot.writeFieldEnd();
      }
      if (struct.url1 != null) {
        oprot.writeFieldBegin(URL1_FIELD_DESC);
        oprot.writeString(struct.url1);
        oprot.writeFieldEnd();
      }
      if (struct.url2 != null) {
        oprot.writeFieldBegin(URL2_FIELD_DESC);
        oprot.writeString(struct.url2);
        oprot.writeFieldEnd();
      }
      if (struct.url3 != null) {
        oprot.writeFieldBegin(URL3_FIELD_DESC);
        oprot.writeString(struct.url3);
        oprot.writeFieldEnd();
      }
      if (struct.url4 != null) {
        oprot.writeFieldBegin(URL4_FIELD_DESC);
        oprot.writeString(struct.url4);
        oprot.writeFieldEnd();
      }
      if (struct.url5 != null) {
        oprot.writeFieldBegin(URL5_FIELD_DESC);
        oprot.writeString(struct.url5);
        oprot.writeFieldEnd();
      }
      if (struct.url6 != null) {
        oprot.writeFieldBegin(URL6_FIELD_DESC);
        oprot.writeString(struct.url6);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BuildDetailTupleSchemeFactory implements SchemeFactory {
    public BuildDetailTupleScheme getScheme() {
      return new BuildDetailTupleScheme();
    }
  }

  private static class BuildDetailTupleScheme extends TupleScheme<BuildDetail> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BuildDetail struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetMain_id()) {
        optionals.set(1);
      }
      if (struct.isSetName()) {
        optionals.set(2);
      }
      if (struct.isSetDesc()) {
        optionals.set(3);
      }
      if (struct.isSetUrl1()) {
        optionals.set(4);
      }
      if (struct.isSetUrl2()) {
        optionals.set(5);
      }
      if (struct.isSetUrl3()) {
        optionals.set(6);
      }
      if (struct.isSetUrl4()) {
        optionals.set(7);
      }
      if (struct.isSetUrl5()) {
        optionals.set(8);
      }
      if (struct.isSetUrl6()) {
        optionals.set(9);
      }
      oprot.writeBitSet(optionals, 10);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetMain_id()) {
        oprot.writeString(struct.main_id);
      }
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetDesc()) {
        oprot.writeString(struct.desc);
      }
      if (struct.isSetUrl1()) {
        oprot.writeString(struct.url1);
      }
      if (struct.isSetUrl2()) {
        oprot.writeString(struct.url2);
      }
      if (struct.isSetUrl3()) {
        oprot.writeString(struct.url3);
      }
      if (struct.isSetUrl4()) {
        oprot.writeString(struct.url4);
      }
      if (struct.isSetUrl5()) {
        oprot.writeString(struct.url5);
      }
      if (struct.isSetUrl6()) {
        oprot.writeString(struct.url6);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BuildDetail struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(10);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.main_id = iprot.readString();
        struct.setMain_idIsSet(true);
      }
      if (incoming.get(2)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.desc = iprot.readString();
        struct.setDescIsSet(true);
      }
      if (incoming.get(4)) {
        struct.url1 = iprot.readString();
        struct.setUrl1IsSet(true);
      }
      if (incoming.get(5)) {
        struct.url2 = iprot.readString();
        struct.setUrl2IsSet(true);
      }
      if (incoming.get(6)) {
        struct.url3 = iprot.readString();
        struct.setUrl3IsSet(true);
      }
      if (incoming.get(7)) {
        struct.url4 = iprot.readString();
        struct.setUrl4IsSet(true);
      }
      if (incoming.get(8)) {
        struct.url5 = iprot.readString();
        struct.setUrl5IsSet(true);
      }
      if (incoming.get(9)) {
        struct.url6 = iprot.readString();
        struct.setUrl6IsSet(true);
      }
    }
  }

}
