# Задача Лягушка

## Описание
В этом задании попрактикуемся с шаблоном *Command* (*Команда*).

Есть поле - одномерный массив из 11 клеток, посередине сидит лягушка. Мы можем давать ей команды:
* +N - прыгни на N шагов направо
* -N - прыгни на N шагов налево
* << - Undo (отмени последнюю команду)
* \>\> - Redo (повтори отменённую команду)
* !! - повтори последнюю команду
* 0 - выход

Вы должны написать программу, которая будет спрашивать команды у пользователя, а после выполнения каждой команды должно выводиться поле с лягушкой.

## Реализация
1. Создайте класс `Frog`, в котором будет храниться состояние лягушки и её умения:
```java
public class Frog {
  public static final int MIN_POSITION = 0;
  public static final int MAX_POSITION = 10;

  protected int position;

  public Frog() { position = 5; }

  public boolean jump(int steps) {
    // сделаем прыжок, если прыжок слишком большой
    // для поля, то не прыгнем и вернём false
  }

  //...
}
```

2. Создайте интерфейс `FrogCommand`, в котором будут храниться команды лягушке:
```java
public interface FrogCommand {
  boolean doit();
  boolean undo();
}
```

3. Создайте класс со статичными методами конструирования команд для лягушки, например, так:
```java
public class FrogCommands {
  public static FrogCommand jumpRightCommand(Frog frog, int steps) {
    // возвращаете объект команды, у которого
    // если вызвать .doit(), то лягушка её выполнит,
    // если вызвать .undo(), то лягушка её отменит
  }
}
```

4. Создайте класс `Main`, в котором будете спрашивать пользователя команды, выполнять и складывать в список команд, например, примерно так:
```java
public static void main(String[] args) {
  //...
  List<FrogCommand> commands = new ArrayList<>();
  int curCommand = -1;
  while (true) {
    //считываем ввод и конструируем комманду, если
    //пользователь не хотел выйти

    if (/*пользователь хочет отменить действие*/) {
      if (curCommand < 0) {
        System.out.println("Нечего отменять!");
      } else {
        commands.get(curCommand).undo();
        curCommand--;
      }
    } else if (/*пользователь хочет повторить действие*/) {
      if (curCommand == commands.size() - 1) {
        System.out.println("Нечего отменять!");
      } else {
        curCommand++;
        commands.get(curCommand).doit();
      }
    } else { //пользователь ввёл новое движение для лягушки
      if (curCommand != commands.size() - 1) {
        //удаляем все команды которые были отменены
      }
      FrogCommand cmd = ...
      curCommand++;
      commands.add(cmd);
      cmd.doit();
    }

    //рисуем поле после команды
  }
}
```

5. Протестируйте работу программы. Не забывайте про правила форматирования кода (для автоформата можете выделить код в идее и нажать **Ctrl+Alt+L**).
