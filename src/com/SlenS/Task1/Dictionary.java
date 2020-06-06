package com.SlenS.Task1;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Dictionary {
    private KeyAndValue[] keysAndValues;
    public static final int DEFAULT_START_SIZE = 16; //первоначальный размер для хранения элементов
    private int count; //количество ключей в словаре, оно же - количество объектов класса keyAndValue в словаре
    private int countValues; //колчиество значений в словаре
    private static int objectCreatedCount = 0; //количество словарей

    public Dictionary(int maxSize) { //конструктор с параментром, где maxSize - первоначальный размер массива
        keysAndValues = new KeyAndValue[maxSize];
        count = 0;

        objectCreatedCount++;
    }

    public Dictionary() { //конструктор без параметра со значением по умолчанию
        this(DEFAULT_START_SIZE);
    }

    /*
     * Метод, который ищет индекс объекта класса ключ-значение,
     * содержащего определенный объект
     */

    private int getIndexWithKey(Object key) {
        for (int i = 0; i < count; i++) {
            KeyAndValue pair = keysAndValues[i];
            if (pair.getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isThereKey(Object key) { //Проверяет, есть ли определенный ключ в словаре
        return (getIndexWithKey(key) >= 0);
    }

    /*
     * Метод, который ищет индекс объекта класса ключ-значение,
     * содержащего определенное значение
     */

    private ArrayList<Integer> getListOfIndexesOfValue(Object value) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (keysAndValues[i].getValues().contains(value)) {
                list.add(i);
            }
        }
        return list;
    }

    public boolean isThereValue(Object value) {
        return (!getListOfIndexesOfValue(value).isEmpty()); //проверка, не пустой ли список, добавляющий индексы объектов, содержащих значение
    }


    public boolean isTherePair(Object key, Object value) //проверяет, есть ли пара в словаре
            throws Exception {
        if (key == null) {
            throw new Exception("Key is null");
        }
        if (value == null) {
            throw new Exception("Value is null");
        }
        return getListOfIndexesOfValue(value).contains(getIndexWithKey(key)); //проверка, есть ли в листе индексов, содержащих значение, индекс ключа
    }

    /*
     * добавляет пару ключ-значение в словарь
     * @return true - если пара добавлена, false - если такая пара уже есть в словаре
     */

    public boolean insertPair(Object key, Object value)
            throws Exception {
        if (key == null) {
            throw new Exception("Key is null");
        }
        if (value == null) {
            throw new Exception("Value is null");
        }
        if (count >= keysAndValues.length) { // проверка, есть ли в массиве свободные ячейки
            keysAndValues = Arrays.copyOf(keysAndValues, count * 2);
        }
        KeyAndValue pair = new KeyAndValue(key, value); // создаем новый экземпляр класса пар ключ-значение
        if (isTherePair(key, value)) { // проверка, есть ли уже такая пара в массиве
            return false;
        } else {
            if (isThereKey(key)) { //проверяет, есть ли уже объект с таким ключем в массиве
                insertValueForKey(key, value); //вместо создания новой пары с одинаковым ключем, просто добавляет значение к уже существующему
            } else {
                keysAndValues[count] = pair;
                count++;
            }
            return true;
        }
    }

    /*
     * удаляет ключ со всеми значениями (экземпляр класса, хранящий ключ и значения)
     * @return true - если операция выполнена, false - если нет такого ключа
     */

    public boolean removeKeyWithValues(Object key)
            throws Exception {
        if (key == null) {
            throw new Exception("Key is null");
        }
        int index = getIndexWithKey(key);
        if (index < 0) { //проверка, есть ли данный ключ в словаре
            return false;
        } else {
            keysAndValues[index] = null;
            if (index < count - 1) {
                keysAndValues[index] = keysAndValues[count - 1];
                keysAndValues[count - 1] = null;
            }
            count--;
            return true;
        }
    }

    public boolean removeAllValuesForKey(Object key)
            throws Exception {
        if (key == null) {
            throw new Exception("Key is null");
        }
        int index = getIndexWithKey(key);
        if (index < 0) { //проверка, есть ли данный ключ в словаре
            return false;
        } else {
            keysAndValues[index].getValues().clear();
            return true;
        }
    }

    /*
     * добавляет заданный объект для заданного ключа
     * @return true - если объект добавлен, false - если такой объект уже привязан к данному ключу
     */

    public boolean insertValueForKey(Object key, Object value)
            throws Exception {
        if (key == null) {
            throw new Exception("Key is null");
        }
        if (value == null) {
            throw new Exception("Value is null");
        }
        int index = getIndexWithKey(key);
        if (index < 0) { //проверка, есть ли данный ключ в словаре
            throw new Exception("There's no such a key");
        } else {
            if (keysAndValues[index].getValues().contains(value)) { //проверка, есть ли уже такое значение у ключа
                return false;
            } else {
                keysAndValues[index].addValue(value);
                return true;
            }
        }
    }

    /*
     * удаляет заданный объект для заданного ключа
     * @return true - если объект удален, false - если такого объекта с соответствующим ему значением нету в словаре
     */

    public boolean removeValueForKey(Object key, Object value)
            throws Exception {
        if (key == null) {
            throw new Exception("Key is null");
        }
        if (value == null) {
            throw new Exception("Value is null");
        }

        if (isTherePair(key, value)) {
            int index = getIndexWithKey(key);
            keysAndValues[index].removeValue(value);
            return true;
        } else {
            return false;
        }
    }

    /*
     * удаляет данный объект для всех значений ключа
     * @return true - если объект удален, false - если такого объекта нету в словаре
     */

    public boolean removeValueForAllKeys(Object value) {
        if (isThereValue(value)) { //проверка, есть ли такое значение в словаре
            for (int i = 0; i < count; i++) {
                keysAndValues[i].removeValue(value); //удаляет объект value из данного экземпляра
            }
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Object> getKeysOfValue(Object value) // Получает список ключей объекта
            throws Exception {
        if (value == null) {
            throw new Exception("Value is null");
        }
        ArrayList<Object> keys = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            KeyAndValue pair = keysAndValues[i];
            if (pair.getValues().contains(value)) { // Проверка, есть ли искомое значение у экземпляра
                keys.add(pair.getKey()); // Добавляет ключ, указывающий на искомое значение в список
            }
        }
        return keys;
    }


    @Override
    public String toString() {
        return "Dictionary{" +
                "Keys and Values=" + Arrays.toString(keysAndValues) +
                '}';
    }
}
