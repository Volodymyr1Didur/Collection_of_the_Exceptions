package com.company.CollectionOfTheEceptions;

public class TryCatch {
    /*По первому пункту: catch — полиморфная конструкция, т.е. catch по типу Parent перехватывает летящие экземпляры любого типа, который является Parent-ом (т.е. экземпляры непосредственно Parent-а или любого потомка Parent-а)*/
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (Exception e) { // catch по Exception ПЕРЕХВАТЫВАЕТ RuntimeException
            System.err.print(" 2");
        }
        System.err.println(" 3");
    }


    /*Даже так: в блоке catch мы будем иметь ссылку типа Exception на объект типа RuntimeException*/
    public static void main2(String[] args) {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                RuntimeException re = (RuntimeException) e;
                System.err.print("Это RuntimeException на самом деле!!!");
            } else {
                System.err.print("В каком смысле не RuntimeException???");
            }
        }
    }


/*Не ловит предка*/
public static void main3(String[] args) throws Exception { // пока игнорируйте 'throws'
    try {
        System.err.print(" 0");
        if (true) {throw new Exception();}
        System.err.print(" 1");
    } catch (RuntimeException e) {
        System.err.print(" 2");
    }
    System.err.print(" 3");
}
/*catch по одному «брату» не может поймать другого «брата» (Error и Exception не находятся в отношении предок-потомок, они из параллельных веток наследования от Throwable)*/

    public static void main4(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new Error();}
            System.err.print(" 1");
        } catch (Exception e) {
            System.err.print(" 2");
        }
        System.err.print(" 3");
    }


/*В таком случае выполнение метода тоже прерывается (не печатаем «3»). Новое исключение не имеет никакого отношения к try-catch

Мы можем даже кинуть тот объект, что у нас есть «на руках»
И мы не попадем в другие секции catch, если они есть
*/
    public static void main5(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) {     // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {throw new Error();} // и бросили новый Error
        } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
            System.err.print(" 3");
        }
        System.err.println(" 4");
    }

/*Как покажем ниже — можно строить вложенные конструкции, но вот пример, «исправляющий» эту ситуацию*/
    public static void main6(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) { // перехватили RuntimeException
            System.err.print(" 2.1");
            try {
                System.err.print(" 2.2");
                if (true) {throw new Error();} // и бросили новый Error
                System.err.print(" 2.3");
            } catch (Throwable t) {            // перехватили Error
                System.err.print(" 2.4");
            }
            System.err.print(" 2.5");
        } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
            System.err.print(" 3");
        }
        System.err.println(" 4");
    }


    /*Выбор catch осуществляется в runtime (а не в compile-time), значит учитывается не тип ССЫЛКИ (Throwable), а тип ССЫЛАЕМОГО (Exception)*/
    public static void main7(String[] args) {
        try {
            Throwable t = new Exception(); // ссылка типа Throwable указывает на объект типа Exception
            throw t;
        } catch (RuntimeException e) {
            System.err.println("catch RuntimeException");
        } catch (Exception e) {
            System.err.println("catch Exception");
        } catch (Throwable e) {
            System.err.println("catch Throwable");
        }
        System.err.println("next statement");
    }
/*
Однако finally-секция не может «починить» try-блок завершившийся исключение (заметьте, «more» — не выводится в консоль)*/
    public static void main8(String[] args) {
        try {
            System.err.println("try");
            if (true) {throw new RuntimeException();}
        } finally {
            System.err.println("finally");
        }
        System.err.println("more");
        
    }
    /*Может перебить throw/return через throw/return */
    public static int f() {
        try {
            return 0;
        } finally {
            return 1;
        }
    }
}

