package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main (String[] args) {
/*    String[] langs = new String[4]; // объявлена переменная типа массив строк, р-р массива 4 элемента
    langs[0] = "Java";
    langs[1] = "Scala";
    langs[2] = "C#";
    langs[3] = "Python";*/

    String[] langs = {"Java", "Scala", "C#", "Python"};

    List<String> languages = Arrays.asList("Java", "Scala", "C#", "Python");
    //languages.add("Java"); добавление элемента

    for (String l : languages) {
      System.out.println("Я хочу выучить " + l);
    }
  }
}
