fun main() {
    while (true) {
        println("\n=== МЕНЮ ===")
        println("1 - Задача 1")
        println("2 - Задача 2")
        println("3 - Задача 3")
        println("0 - Выход")
        print("Выберите цифру: ")

        val choice = readLine()!!.toInt()

        if (choice == 1) {
            task1()
        } else if (choice == 2) {
            task2()
        } else if (choice == 3) {
            task3()
        } else if (choice == 0) {
            println("ВЫХОД")
            break
        } else {
            println("Неправильный выбор!")
        }
    }
}

fun task1() {
    print("Сколько строк? ")
    val rows = readLine()!!.toInt()
    print("Сколько столбцов? ")
    val cols = readLine()!!.toInt()

    val numbers = Array(rows) { IntArray(cols) }
    val allDigits = mutableSetOf<Char>()

    println("Вводите числа:")

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            print("Число [$i][$j]: ")
            numbers[i][j] = readLine()!!.toInt()

            val numStr = numbers[i][j].toString()
            for (digit in numStr) {
                allDigits.add(digit)
            }
        }
    }

    println("\nВаш массив:")
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            print("${numbers[i][j]}\t")
        }
        println()
    }

    println("\nРазных цифр: ${allDigits.size}")
    println("Цифры: ${allDigits.sorted().joinToString(", ")}")
}

fun task2() {

    val numbers = arrayOf(
        intArrayOf(5, 9, 6, 7, 2),
        intArrayOf(9, 8, 4, 5, 3),
        intArrayOf(6, 4, 3, 8, 7),
        intArrayOf(7, 5, 8, 4, 8),
        intArrayOf(2, 3, 7, 8, 1)
    )

    println("Массив 5x5:")
    for (i in 0..4) {
        for (j in 0..4) {
            print("${numbers[i][j]}\t")
        }
        println()
    }

    var symmetric = true
    for (i in 0..4) {
        for (j in 0..4) {
            if (numbers[i][j] != numbers[j][i]) {
                symmetric = false
            }
        }
    }

    if (symmetric) {
        println("Массив симметричный")
    } else {
        println("Массив НЕ симметричный")
    }
}

fun task3() {
    print("Введите число: ")
    val number = readLine()!!.toInt()

    val binary = number.toString(2)
    println("В двоичной системе: $binary")
}

fun task 4(){

}