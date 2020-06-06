package com.SlenS.Task1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Student sam = new Student("Sam", 18);
        Student emma = new Student("Emma", 19);
        Task tsk1 = new Task("1");
        Task tsk2 = new Task("2");
        Task tsk3 = new Task("3");

        Dictionary list = new Dictionary(2);
        list.insertPair(sam, tsk1); //добавляем первую пару
        System.out.println(list.toString());
        list.insertValueForKey(sam, tsk2); //добавляем значение ключу
        System.out.println(list.toString());
        list.insertPair(sam, tsk3); //демонстрация того, что при добавлении пары, с уже существующим ключем, не создается новой пары
        System.out.println(list.toString());

        list.insertPair(emma, tsk1); //добавляем вторую пару
        System.out.println(list.toString());

        System.out.println(list.getKeysOfValue(tsk1).toString()); //получаем список ключей объекта

        list.removeValueForAllKeys(tsk1); //удаляем первый таск для всех студентов (заданный объект для всех значений ключа)
        System.out.println(list.toString());

        list.removeValueForKey(sam, tsk3); //удаляем третий таск для первого студента (заданный объект для задданого ключа)
        System.out.println(list.toString());

        list.removeKeyWithValues(sam); //удаляем из списка студента с тасками (ключ со всеми значениями)
        System.out.println(list.toString());

        list.insertValueForKey(emma, tsk2);
        list.insertValueForKey(emma, tsk3);
        System.out.println(list.toString());
        list.removeAllValuesForKey(emma); //удаляем все значения ключа
        System.out.println(list.toString());
    }
}
