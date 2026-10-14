import java.util.InputMismatchException;
import java.util.Scanner;;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Matrix A = null, B = null;
        while(true){
            print_menu();
            System.out.print("Выберите операцию: ");
            try {
                int oper = sc.nextInt();
                switch (oper) {
                    case 1:
                        A = create_matrix(sc);
                        break;
                    case 2:
                        if (A != null) {
                            System.out.println("Матрица:");
                            print_matrix(A);
                        }
                        else {
                            System.out.println("Матрица не создана");
                        }
                        break;
                    case 3:
                        if (A != null){
                            System.out.println("Создайте матрицу, с которой хотите сложить");
                            B = create_matrix(sc);
                            A = A.add(B);
                            System.out.println("Результат сложения:");
                            print_matrix(A);
                            System.out.println("Результат сохранён вместо исходной матрицы");
                        }
                        else System.out.println("Матрица не создана");

                        break;
                    case 4:
                        if (A != null) {
                            System.out.println("Создайте матрицу, которую хотите вычесть");
                            B = create_matrix(sc);
                            A = A.subtract(B);
                            System.out.println("Результат вычитания:");
                            print_matrix(A);
                            System.out.println("Результат сохранён вместо исходной матрицы");
                        }
                        else System.out.println("Матрица не создана");
                        break;
                    case 5:
                        if (A != null) {
                            System.out.println("Создайте матрицу, на которую хотите умножить");
                            B = create_matrix(sc);
                            A = A.multiply(B);
                            System.out.println("Результат умножения:");
                            print_matrix(A);
                            System.out.println("Результат сохранён вместо исходной матрицы");
                        }
                        else System.out.println("Матрица не создана");
                        break;
                    case 6:
                        if (A != null) {
                            System.out.println("Создайте матрицу, на которую хотите разделить");
                            B = create_matrix(sc);
                            A = A.divide(B);
                            System.out.println("Результат деления:");
                            print_matrix(A);
                            System.out.println("Результат сохранён вместо исходной матрицы");
                        }
                        else System.out.println("Матрица не создана");
                        break;
                    case 7:
                        if (A != null){
                            if(A.getRows() == A.getCols()) {
                                System.out.print("Детерминант равен ");
                                A.determinant().print_numb();
                                System.out.println();
                            }
                            else System.out.println("Нельзя найти детерминант, так как матрица не квадратная");
                        }
                        else System.out.println("Матрица не создана");
                        break;
                    case 8 :
                        if (A != null ){
                            System.out.println("Транспонированная матрица:");
                            A = A.transpose();
                            print_matrix(A);
                            System.out.println("Результат сохранён вместо исходной матрицы");
                        }
                        else System.out.println("Матрица не создана");
                        break;
                    case 9:
                        System.out.println("Завершение программы");
                        sc.close();
                        return;
                    default:
                        System.out.println("Неверная операция");
                }
            }
            catch (ArithmeticException | IllegalArgumentException e){
                System.out.println("Ошибка: " + e.getMessage());
            }
            catch(InputMismatchException e){
                System.out.println("Ошибка: неверный тип данных, введите число");
                sc.nextLine();
            }
        }
    }

    static  Matrix create_matrix(Scanner sc){
        System.out.print("Введите кол-во строк: ");
        int rows = sc.nextInt();
        System.out.print("Введите кол-во столбцов: ");
        int cols = sc.nextInt();
        Matrix matr = new Matrix(rows, cols);
        double real, imag;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print("Введите вещественную часть для [" + i + "][" + j + "]: ");
                real = sc.nextDouble();
                System.out.print("Введите мнимую часть для [" + i + "][" + j + "]: ");
                imag = sc.nextDouble();
                matr.setElem(i, j, new ComplexNumber(real, imag));
            }
        }
        return matr;
    }

    static void print_matrix(Matrix matr){
        int rows = matr.getRows();
        int cols = matr.getCols();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                matr.getElem(i, j).print_numb();
            }
            System.out.println();
        }
    }

    static void print_menu(){
        System.out.println("МЕНЮ ПРОГРАММЫ");
        System.out.println("1. Создать/изменить матрицу");
        System.out.println("2. Вывести записанную матрицу");
        System.out.println("3. Сложить матрицу с другой");
        System.out.println("4. Вычесть из матрицы другую");
        System.out.println("5. Умножить матрицу на другую");
        System.out.println("6. Разделить матрицу на другую");
        System.out.println("7. Вычислить детерминант матрицы");
        System.out.println("8. Транспонировать матрицу");
        System.out.println("9. Выход");
    }
}



