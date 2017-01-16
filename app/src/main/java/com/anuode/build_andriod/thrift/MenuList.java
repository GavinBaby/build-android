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
 * 角色信息列表
 */
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-01-12")
public class MenuList implements org.apache.thrift.TBase<MenuList, MenuList._Fields>, java.io.Serializable, Cloneable, Comparable<MenuList> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("MenuList");

  private static final org.apache.thrift.protocol.TField BACK_FIELD_DESC = new org.apache.thrift.protocol.TField("back", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField MENU_FIELD_DESC = new org.apache.thrift.protocol.TField("menu", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new MenuListStandardSchemeFactory());
    schemes.put(TupleScheme.class, new MenuListTupleSchemeFactory());
  }

  /**
   * 结果
   */
  public Back back; // required
  /**
   * 权限信息
   */
  public List<Menu> menu; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 结果
     */
    BACK((short)1, "back"),
    /**
     * 权限信息
     */
    MENU((short)2, "menu");

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
        case 1: // BACK
          return BACK;
        case 2: // MENU
          return MENU;
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
    tmpMap.put(_Fields.BACK, new org.apache.thrift.meta_data.FieldMetaData("back", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Back.class)));
    tmpMap.put(_Fields.MENU, new org.apache.thrift.meta_data.FieldMetaData("menu", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Menu.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(MenuList.class, metaDataMap);
  }

  public MenuList() {
  }

  public MenuList(
    Back back,
    List<Menu> menu)
  {
    this();
    this.back = back;
    this.menu = menu;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public MenuList(MenuList other) {
    if (other.isSetBack()) {
      this.back = new Back(other.back);
    }
    if (other.isSetMenu()) {
      List<Menu> __this__menu = new ArrayList<Menu>(other.menu.size());
      for (Menu other_element : other.menu) {
        __this__menu.add(new Menu(other_element));
      }
      this.menu = __this__menu;
    }
  }

  public MenuList deepCopy() {
    return new MenuList(this);
  }

  @Override
  public void clear() {
    this.back = null;
    this.menu = null;
  }

  /**
   * 结果
   */
  public Back getBack() {
    return this.back;
  }

  /**
   * 结果
   */
  public MenuList setBack(Back back) {
    this.back = back;
    return this;
  }

  public void unsetBack() {
    this.back = null;
  }

  /** Returns true if field back is set (has been assigned a value) and false otherwise */
  public boolean isSetBack() {
    return this.back != null;
  }

  public void setBackIsSet(boolean value) {
    if (!value) {
      this.back = null;
    }
  }

  public int getMenuSize() {
    return (this.menu == null) ? 0 : this.menu.size();
  }

  public java.util.Iterator<Menu> getMenuIterator() {
    return (this.menu == null) ? null : this.menu.iterator();
  }

  public void addToMenu(Menu elem) {
    if (this.menu == null) {
      this.menu = new ArrayList<Menu>();
    }
    this.menu.add(elem);
  }

  /**
   * 权限信息
   */
  public List<Menu> getMenu() {
    return this.menu;
  }

  /**
   * 权限信息
   */
  public MenuList setMenu(List<Menu> menu) {
    this.menu = menu;
    return this;
  }

  public void unsetMenu() {
    this.menu = null;
  }

  /** Returns true if field menu is set (has been assigned a value) and false otherwise */
  public boolean isSetMenu() {
    return this.menu != null;
  }

  public void setMenuIsSet(boolean value) {
    if (!value) {
      this.menu = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case BACK:
      if (value == null) {
        unsetBack();
      } else {
        setBack((Back)value);
      }
      break;

    case MENU:
      if (value == null) {
        unsetMenu();
      } else {
        setMenu((List<Menu>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case BACK:
      return getBack();

    case MENU:
      return getMenu();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case BACK:
      return isSetBack();
    case MENU:
      return isSetMenu();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof MenuList)
      return this.equals((MenuList)that);
    return false;
  }

  public boolean equals(MenuList that) {
    if (that == null)
      return false;

    boolean this_present_back = true && this.isSetBack();
    boolean that_present_back = true && that.isSetBack();
    if (this_present_back || that_present_back) {
      if (!(this_present_back && that_present_back))
        return false;
      if (!this.back.equals(that.back))
        return false;
    }

    boolean this_present_menu = true && this.isSetMenu();
    boolean that_present_menu = true && that.isSetMenu();
    if (this_present_menu || that_present_menu) {
      if (!(this_present_menu && that_present_menu))
        return false;
      if (!this.menu.equals(that.menu))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_back = true && (isSetBack());
    list.add(present_back);
    if (present_back)
      list.add(back);

    boolean present_menu = true && (isSetMenu());
    list.add(present_menu);
    if (present_menu)
      list.add(menu);

    return list.hashCode();
  }

  @Override
  public int compareTo(MenuList other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetBack()).compareTo(other.isSetBack());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBack()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.back, other.back);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMenu()).compareTo(other.isSetMenu());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMenu()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.menu, other.menu);
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
    StringBuilder sb = new StringBuilder("MenuList(");
    boolean first = true;

    sb.append("back:");
    if (this.back == null) {
      sb.append("null");
    } else {
      sb.append(this.back);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("menu:");
    if (this.menu == null) {
      sb.append("null");
    } else {
      sb.append(this.menu);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (back != null) {
      back.validate();
    }
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

  private static class MenuListStandardSchemeFactory implements SchemeFactory {
    public MenuListStandardScheme getScheme() {
      return new MenuListStandardScheme();
    }
  }

  private static class MenuListStandardScheme extends StandardScheme<MenuList> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, MenuList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // BACK
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.back = new Back();
              struct.back.read(iprot);
              struct.setBackIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MENU
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.menu = new ArrayList<Menu>(_list8.size);
                Menu _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new Menu();
                  _elem9.read(iprot);
                  struct.menu.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setMenuIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, MenuList struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.back != null) {
        oprot.writeFieldBegin(BACK_FIELD_DESC);
        struct.back.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.menu != null) {
        oprot.writeFieldBegin(MENU_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.menu.size()));
          for (Menu _iter11 : struct.menu)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MenuListTupleSchemeFactory implements SchemeFactory {
    public MenuListTupleScheme getScheme() {
      return new MenuListTupleScheme();
    }
  }

  private static class MenuListTupleScheme extends TupleScheme<MenuList> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, MenuList struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetBack()) {
        optionals.set(0);
      }
      if (struct.isSetMenu()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetBack()) {
        struct.back.write(oprot);
      }
      if (struct.isSetMenu()) {
        {
          oprot.writeI32(struct.menu.size());
          for (Menu _iter12 : struct.menu)
          {
            _iter12.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, MenuList struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.back = new Back();
        struct.back.read(iprot);
        struct.setBackIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.menu = new ArrayList<Menu>(_list13.size);
          Menu _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new Menu();
            _elem14.read(iprot);
            struct.menu.add(_elem14);
          }
        }
        struct.setMenuIsSet(true);
      }
    }
  }

}
