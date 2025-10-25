fun main() {
    while (true) {
        println("\n=== МЕНЮ ===")
        println("1 - Задача 1")
        println("2 - Задача 2")
        println("3 - Задача 3")
        println("4 - Задача 4")
        println("4 - Задача 5")
        println("0 - Выход")
        print("Выберите цифру: ")

        val choice = readLine()!!.toInt()

        if (choice == 1) {
            task1()
        } else if (choice == 2) {
            task2()
        } else if (choice == 3) {
            task3()
        } else if (choice == 4) {
            task4()
        } else if (choice == 5) {
            task5()
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

fun task4() {

    println("Введите массив (числа через пробел):")
    val masOne = readLine()!!
    println("Введите второй массив (числа через пробел):")
    val masTwo = readLine()!!

    val parts1 = masOne.split(" ")
    val parts2 = masTwo.split(" ")

    val arr1 = IntArray(parts1.size)
    val arr2 = IntArray(parts2.size)
    
    var index1 = 0
    while (index1 < parts1.size) {
        arr1[index1] = parts1[index1].toInt()
        index1++
    }

    var index2 = 0
    while (index2 < parts2.size) {
        arr2[index2] = parts2[index2].toInt()
        index2++
    }

    val result = natiPeresechenie(arr1, arr2)

    println("Пересечение массивов:")
    print("[")
    for (i in result.indices) {
        print(result[i])
        if (i < result.size - 1) {
            print(", ")
        }
    }
    println("]")
}

fun natiPeresechenie(a: IntArray, b: IntArray): IntArray {

    val copyA = IntArray(a.size)
    val copyB = IntArray(b.size)

    for (i in a.indices) {
        copyA[i] = a[i]
    }
    for (i in b.indices) {
        copyB[i] = b[i]
    }

    val vremennoeHranilishe = IntArray(a.size + b.size)
    var resultCount = 0

    val usedMarker = -123456789

    for (i in copyA.indices) {
        for (j in copyB.indices) {
            if (copyA[i] == copyB[j] && copyB[j] != usedMarker) {
                vremennoeHranilishe[resultCount] = copyA[i]
                resultCount++
                copyB[j] = usedMarker
                break
            }
        }
    }

    val final = IntArray(resultCount)
    for (i in 0 until resultCount) {
        final[i] = vremennoeHranilishe[i]
    }

    var swapped = true
    var n = final.size
    while (swapped) {
        swapped = false
        for (i in 1 until n) {
            if (final[i - 1] > final[i]) {
                val tmp = final[i - 1]
                final[i - 1] = final[i]
                final[i] = tmp
                swapped = true
            }
        }
        n--
    }

    return final
}

fun task5() {
    println("Введите слова через пробел:")

    val input = readLine()!!
    val words = input.split(" ")

    val result = groupWords(words.toTypedArray())

    println("Группы слов:")
    for (group in result) {
        print("\"")
        for (i in group.indices) {
            print(group[i])
            if (i < group.size - 1) {
                print("\", \"")
            }
        }
        println("\"")
    }
}

fun groupWords(words: Array<String>): List<List<String>> {
    val groups = mutableListOf<List<String>>()
    val used = BooleanArray(words.size)

    for (i in words.indices) {
        if (used[i]) continue

        val currentGroup = mutableListOf<String>()
        currentGroup.add(words[i])
        used[i] = true

        for (j in i + 1 until words.size) {
            if (used[j]) continue

            if (haveSameLetters(words[i], words[j])) {
                currentGroup.add(words[j])
                used[j] = true
            }
        }

        groups.add(currentGroup)
    }

    return groups
}

fun haveSameLetters(word1: String, word2: String): Boolean {
    if (word1.length != word2.length) return false

    val chars1 = CharArray(word1.length)
    val chars2 = CharArray(word2.length)

    for (i in word1.indices) {
        chars1[i] = word1[i]
    }
    for (i in word2.indices) {
        chars2[i] = word2[i]
    }

    sortChars(chars1)
    sortChars(chars2)

    for (i in chars1.indices) {
        if (chars1[i] != chars2[i]) {
            return false
        }
    }

    return true
}

fun sortChars(chars: CharArray) {
    var swapped: Boolean
    var n = chars.size
    do {
        swapped = false
        for (i in 1 until n) {
            if (chars[i - 1] > chars[i]) {
                val temp = chars[i - 1]
                chars[i - 1] = chars[i]
                chars[i] = temp
                swapped = true
            }
        }
        n--
    } while (swapped)
}