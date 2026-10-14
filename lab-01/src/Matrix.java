public class Matrix {
    private int rows;
    private int cols;
    private ComplexNumber[][] matrix;

    Matrix(int rows, int cols){
        if (rows <= 0 || cols <= 0)
            throw new IllegalArgumentException("Нельзя создать матрицу с такой размерностью");
        this.rows = rows;
        this.cols = cols;
        matrix = new ComplexNumber[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                matrix[i][j] = new ComplexNumber(0,0);
            }
        }
    }

    int getRows(){
        return rows;
    }

    int getCols(){
        return cols;
    }

    void setElem(int i, int j, ComplexNumber val){
        matrix[i][j] = val;
    }

    ComplexNumber getElem(int i, int j){
        return matrix[i][j];
    }


    Matrix transpose(){
        Matrix a = new Matrix(cols, rows);
        for(int i = 0; i < cols; i++){
            for(int j = 0; j < rows; j++){
                a.matrix[i][j] = this.matrix[j][i];
            }
        }
        return a;
    }

    Matrix add(Matrix matr){
        Matrix a = new Matrix(rows, cols);
        if (rows == matr.rows && cols == matr.cols){
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    a.matrix[i][j] = this.matrix[i][j].add(matr.matrix[i][j]);
                }
            }
        }
        else{
            throw new ArithmeticException("Нельзя сложить из-за разных размерностей матриц");
        }
        return a;
    }

    Matrix subtract(Matrix matr){
        Matrix a = new Matrix(rows,cols);
        if (rows == matr.rows && cols == matr.cols){
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    a.matrix[i][j] = this.matrix[i][j].subtract(matr.matrix[i][j]);
                }
            }
        }
        else{
            throw new ArithmeticException("Нельзя вычесть из-за разных размерностей матриц");
        }
        return a;
    }

    Matrix multiply(Matrix matr){
        Matrix a = new Matrix(rows, matr.cols);
        if(cols == matr.rows){
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < matr.cols; j++){
                    for(int k = 0; k < cols; k++){
                        a.matrix[i][j] = a.matrix[i][j].add(this.matrix[i][k].multiply(matr.matrix[k][j]));
                    }
                }
            }
        }
        else{
            throw new ArithmeticException("Нельзя перемножить, так как кол-во столбцов у матрицы A не совпадает с кол-вом строк у B");
        }
        return a;
    }

    Matrix get_minor(int row, int col){
        Matrix minor = new Matrix(rows - 1, cols - 1);
        int row_min = 0, col_min = 0;
        for(int i = 0; i < rows; i++){
            if (i == row)
                continue;
            for(int j = 0; j < cols;j++){
                if (j == col)
                    continue;
                minor.matrix[row_min][col_min] = this.matrix[i][j];
                col_min++;
            }
            row_min++;
            col_min = 0;
        }
        return minor;
    }

    ComplexNumber determinant(){
        if (rows == cols){
            if (matrix.length == 1)
                return matrix[0][0];
            else{
                ComplexNumber det = new ComplexNumber(0,0);
                for(int i = 0; i < cols; i++){
                    Matrix minor = get_minor(0, i);
                    ComplexNumber sign;
                    if (i % 2 == 0){
                        sign = new ComplexNumber(1,0);
                    }
                    else{
                        sign = new ComplexNumber(-1,0);
                    }
                    det = det.add(matrix[0][i].multiply(minor.determinant()).multiply(sign));
                }
                return det;
            }
        }
        else{
            throw new ArithmeticException("Нельзя найти детерминант, так как матрица не квадратная");
        }
    }
    Matrix inverse(){
        ComplexNumber det = this.determinant();
        if (det.is_zero())
            throw new ArithmeticException("Нельзя найти обратную матрицу, потому что детерминант равен нулю");
        if (rows == 1 && cols == 1){
            Matrix inv_matr = new Matrix(1, 1);
            inv_matr.matrix[0][0] = new ComplexNumber(1,0).divide(this.matrix[0][0]);
            return inv_matr;
        }

        Matrix A = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                ComplexNumber sign;
                if((i + j) % 2 == 0){
                    sign = new ComplexNumber(1, 0);
                }
                else{
                    sign = new ComplexNumber(-1, 0);
                }
                A.matrix[i][j] = get_minor(i,j).determinant().multiply(sign);
            }
        }
        Matrix A_trans = A.transpose();
        Matrix inv_matr = new Matrix(rows,cols);
        for(int i = 0; i < inv_matr.rows; i++){
            for(int j = 0; j < inv_matr.cols; j++){
                inv_matr.matrix[i][j] = A_trans.matrix[i][j].divide(det);
            }
        }
        return inv_matr;
    }

    Matrix divide(Matrix matr){
        return this.multiply(matr.inverse());
    }

}
