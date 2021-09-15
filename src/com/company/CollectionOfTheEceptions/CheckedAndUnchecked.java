package com.company.CollectionOfTheEceptions;

public class CheckedAndUnchecked {
    /*Мы не можем бросать, но предупредить о «меньшем»*/
    public static void main3(String[] args) throws Exception { // предупреждаем о Exception
        throw new Exception(); // и кидаем Exception
    }
    public static void main2(String[] args) throws Exception { // предупреждаем о Exception
        throw new Exception(); // и кидаем Exception
    }
    /*
    public static void f11() throws Exception {
        System.out.println("1 ");
        throw new Exception();
    }
    public static void main(String[] args) {
        f11(); // тут ошибка компиляции
    }*/


}
