import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Product[] products = {new Product("Milk", 100)
                , new Product("Apple", 150)
                , new Product("Sugar", 80)};
        Store store = new Store(1, products);
        Basket basket = new Basket(store);

        Scanner scanner = new Scanner(System.in);
        int menu;

        printMainMenu();
        while ((menu = scanner.nextInt()) != 0) {
            switch (menu) {
                case 1:
                    while (true) {
                        //magics
                        for (int i = 0; i < products.length; i++) {
                            System.out.println((i + 1) + ". " + products[i].toString() + ", в наличии: "
                                    + store.getProductBalance(products[i]));
                        }
                        System.out.println("Введите номер продукта (0 возврат в главное меню): ");
                        int productNumber = scanner.nextInt();
                        if (productNumber == 0) break;
                        System.out.println("Введите количество продукта: ");
                        int productCount = scanner.nextInt();
                        if (store.hasProduct(products[productNumber - 1], productCount)) {
                            basket.addProduct(products[productNumber - 1], productCount);
                            System.out.println("Продукт добавлен в корзину");
                        } else
                            System.out.println("На складе недостаточно продукта");
                    }
                    printMainMenu();
                    break;
                case 2:
                    while (true) {
                        if (basket.isEmpty()) {
                            System.out.println("Ваша корзина пуста\n");
                            break;
                        }
                        System.out.println("Ваша корзина: ");
                        for (int i = 0; i < products.length; i++) {
                            if (basket.getProductCount(products[i]) != -1)
                                System.out.println((i + 1) + ". " + products[i] +
                                        ", кол-во: " + basket.getProductCount(products[i]));
                        }
                        System.out.println("\n1 - Купить");
                        System.out.println("2 - Удалить продукт из корзины");
                        System.out.println("3 - Очистить корзину");
                        System.out.println("0 - Главное меню");

                        if ((menu = scanner.nextInt())==0) {
                            break;
                        } else if (menu==1) {
                            basket.buy();
                        } else if (menu==2) {
                            System.out.println("\n Введите номер продукта из корзины (0 - для отмены: ");
                            int productNumber;
                            if ((productNumber = scanner.nextInt())!=0) {
                                basket.removeProduct(products[productNumber-1]);
                            }
                        } else if (menu==3) {
                            basket.clear();
                        }
                    }
                    printMainMenu();
                    break;
            }
        }
    }

    //DRY
    public static void printMainMenu() {
        System.out.println("Продуктовый магазин:");
        System.out.println("1 - Выбрать продукты");
        System.out.println("2 - Перейти в корзину");
        System.out.println("0 - Выход");
    }
}
