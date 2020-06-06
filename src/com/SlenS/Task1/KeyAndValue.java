package com.SlenS.Task1;

import java.security.Key;
import java.util.ArrayList;

public class KeyAndValue {
    private Object key;
    private ArrayList<Object> values = new ArrayList<>();
    public static final int MAX_SIZE = 100;

    public KeyAndValue(Object key, Object value) { //конструктор с двумя параментрами
        this.key = key;
        values.add(value); //добавляет лишь одно значение паре, а не целый набор/лист
        this.values = values;
    }

    public KeyAndValue() { //конструктор без параметра
    }

    public void setValues(ArrayList<Object> values) {
        this.values = values;
    }

    public KeyAndValue(Object key) { //конструктор с одним параметром, добавляющий лишь ключ
        this.key = key;
    }

    public void addValue(Object value) {
        values.add(value);
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public ArrayList<Object> getValues() {
        return values;
    }

    public Object getKey() {
        return key;
    }

    public void removeValue(Object value) {
        values.remove(value);
    }

    public void removeKey() {
        key = null;
    }

    @Override
    public String toString() {
        return "KeyAndValue{" +
                "key=" + key +
                ", values=" + values +
                '}';
    }
}
