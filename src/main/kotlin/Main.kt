fun main() {
    while (true) {
        println("\n=== МЕНЮ ДЛЯ ЗАДАЧ ===")
        println("1")
        println("2")
        println("3")
        println("4")
        println("5")
        println("0 - Выход")
        print("Выбери цифру: ")

        when (readLine()!!.toIntOrNull()) {
            1 -> task1()
            2 -> task2()
            3 -> task3()
            4 -> task4()
            5 -> task5()
            0 -> {
                println("ВЫХОД ИЗ ПРОГРАММЫ")
                return
            }
            else -> println("ВЫБЕРИТЕ ЦИФРУ ОТ 0 ДО 6")
        }
    }
}

fun task1(){
        print("Введите количество строк: ")
        val rows = readLine()!!.toInt()
        print("Введите количество столбцов: ")
        val cols = readLine()!!.toInt()

        val matrix = Array(rows) { IntArray(cols) }
        val uniqueNums = mutableSetOf<Char>()

        println("Введите $rows строк по $cols трехзначных чисел:")

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                print("Элемент [$i][$j]: ")
                matrix[i][j] = readLine()!!.toInt()

                val digits = matrix[i][j].toString().toCharArray()
                uniqueNums.addAll(digits)
            }
        }

        println("\nВведенный массив:")
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                print("${matrix[i][j]}\t")
            }
            println()
        }

        println("\nВ массиве использовано ${uniqueNums.size} различных цифр")
        println("Использованные цифры: ${uniqueNums.sorted().joinToString(", ")}")
    }

fun task2()
{
        val matrix = arrayOf(
            intArrayOf(5, 9, 6, 7, 2),
            intArrayOf(9, 8, 4, 5, 3),
            intArrayOf(6, 4, 3, 8, 7),
            intArrayOf(7, 5, 8, 4, 8),
            intArrayOf(2, 3, 7, 8, 1)
        )

        println("Исходный массив 5x5:")
        printMatrix(matrix)

        val isSymmetric = checkSymmetry(matrix)

        if (isSymmetric) {
            println("Массив симметричен относительно главной диагонали")
        } else {
            println("Массив НЕ симметричен относительно главной диагонали")
        }
    }

    fun printMatrix(matrix: Array<IntArray>) {
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                print("${matrix[i][j]}\t")
            }
            println()
        }
    }

    fun checkSymmetry(matrix: Array<IntArray>): Boolean {
        val n = matrix.size

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (matrix[i][j] != matrix[j][i]) {
                    println("Найдено несоответствие: matrix[$i][$j] = ${matrix[i][j]} != matrix[$j][$i] = ${matrix[j][$i]}")
                    return false
                }
            }
        }

        return true
    }

