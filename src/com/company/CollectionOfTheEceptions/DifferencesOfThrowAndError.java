package com.company.CollectionOfTheEceptions;

/*Либо передача управления происходит в «стопке» фреймов между СОСЕДНИМИ фреймами
вызов метода: создаем новый фрейм, помещаем его на верхушку стека и переходим в него
выход из метода: возвращаемся к предыдущему фрейму (через return или просто кончились инструкции в методе)


return — выходим из ОДНОГО фрейма (из фрейма #4(метод h()))*/
public class DifferencesOfThrowAndError {
    public static void main(String[] args) {
        System.err.println("#1.in");
        f(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // ПРОПУСТИЛИ!
    }

    public static void f() {
        System.err.println(".   #2.in");
        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }



/*throw — выходим из ВСЕХ фреймов*/

    public static void mainSecond(String[] args) {
        System.err.println("#1.in");
        f2(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // вернулись
    } // выходим из текущего фрейма, кончились инструкции

    public static void f2() {
        System.err.println(".   #2.in");
        g2(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out");  //вернулись
    } // выходим из текущего фрейма, кончились инструкции

    public static void g2() {
        System.err.println(".   .   #3.in");
        h2(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // вернулись
    } // выходим из текущего фрейма, кончились инструкции

    public static void h2() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.RETURN");
            return; // выходим из текущего фрейма по 'return'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСКАЕМ
    }

/*При помощи catch мы можем остановить летящее исключение (причина, по которой мы автоматически покидаем фреймы).
Останавливаем через 3 фрейма, пролетаем фрейм #4(метод h()) + пролетаем фрейм #3(метод g()) + фрейм #2(метод f())*/

        public static void main3(String[] args) {
            System.err.println("#1.in");
            try {
                f3(); // создаем фрейм, помещаем в стек, передаем в него управление
            } catch (Error e) { // "перехватили" "летящее" исключение
                System.err.println("#1.CATCH");  // и работаем
            }
            System.err.println("#1.out");  // работаем дальше
        }

        public static void f3() {
            System.err.println(".   #2.in");
            g3(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
        }

        public static void g3() {
            System.err.println(".   .   #3.in");
            h3(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
        }

        public static void h3() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.THROW");
                throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
        }

/*Останавливаем через 2 фрейма, пролетаем фрейм #4(метод h()) + пролетаем фрейм #3(метод g())*/
public static void main4(String[] args) {
    System.err.println("#1.in");
    f(); // создаем фрейм, помещаем в стек, передаем в него управление
    System.err.println("#1.out"); // вернулись и работаем
}

    public static void f4() {
        System.err.println(".   #2.in");
        try {
            g4(); // создаем фрейм, помещаем в стек, передаем в него управление
        } catch (Error e) { // "перехватили" "летящее" исключение
            System.err.println(".   #2.CATCH");  // и работаем
        }
        System.err.println(".   #2.out");  // работаем дальше
    }

    public static void g4() {
        System.err.println(".   .   #3.in");
        h4(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
    }

    public static void h4() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }
}
