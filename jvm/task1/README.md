# Задача "Понимание JVM"

## Описание
Просмотрите код ниже и опишите (текстово или с картинками) каждую строку с точки зрения происходящего в JVM  

Не забудьте упомянуть про: 
- ClassLoader'ы, 
- области памяти (стэк (и его фреймы), хип, метаспейс)  
- сборщик мусора

## Код для исследования
```java

public class JvmComprehension {

    public static void main(String[] args) {
        int i = 1;                      // 1
        Object o = new Object();        // 2
        Integer ii = 2;                 // 3
        printAll(o, i, ii);             // 4
        System.out.println("finished"); // 7
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5
        System.out.println(o.toString() + i + ii);  // 6
    }
}

```
Описание кода:
1. Выполняется загрузка класса JvmComprehension в Metaspace, в Stack Memory (стек) создается фрейм в момент вызова метода main(), в данный фрейм записываются данные о переменной i.
2. Выполняется поиск и загрузка данных о классе Object ClassLoader'ом в Metaspace. Создается экземпляр класса Object, который загружается в heap (куча). Локальная переменная "o" (ссылка на объект) загружается в фрейм main() в стеке.
3. Выполняется поиск и загрузка данных о классе Integer ClassLoader'ом в Metaspace. Создается экземпляр класса Integer (=2), который загружается в heap (куча). Локальная переменная "ii" (ссылка на объект) загружается в фрейм main() в стеке.
4. Создается фрейм printAll() в стеке, в который загружаются данные о параметрах метода "o" (ссылка на объект типа Object), "i" (переменная примитивного типа int), "ii" (ссылка на объект Integer(=2)).
5. Создается экземпляр класса Integer (=700), который загружается в heap (куча). Переменная "uselessVar" (ссылка на объект) загружается в фрейм printAll() в стеке.
6. Создается фрейм println() в стеке. Создается фрейм toString() в стеке. Метод toString() создает объект типа String, который помещается в кучу. Во фрейм println() помещаются ссылки на объекты типа String и Integer (ii), а также переменная i = 1. Сборщик мусора (GC) удаляет из кучи объекты типа Integer (uselessVar = 700) и String (o.toString()).
7. Создается фрейм println() в стеке, создается объект типа String со значением "finished" в куче, ссылка на которую помещается во фрейм println(). GC выполняет удаление данных из кучи.
