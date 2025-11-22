import kotlin.system.exitProcess
import kotlin.math.abs

fun readNormalno(prompt: String): Int {
    while (true) {
        print(prompt)
        val line = readLine()
        if (line == null) {
            println("Ввод отсутствует. Попробуйте ещё раз.")
            continue
        }
        val num = line.trim().toIntOrNull()
        if (num == null) {
            println("Нужно ввести целое число. Попробуйте снова.")
            continue
        }
        return num
    }
}

fun readNonEmptyLine(nazvanie: String): String {
    while (true) {
        print(nazvanie)
        val line = readLine()
        if (line == null) {
            println("Ввод отсутствует. Попробуйте ещё раз.")
            continue
        }
        val trimmed = line.trim()
        if (trimmed.isEmpty()) {
            println("Пустая строка. Попробуйте снова.")
            continue
        }
        return trimmed
    }
}

fun main() {
    while (true) {
        println()
        println("=== МЕНЮ ===")
        println("1 - Задача 1 (матрица: разные цифры)")
        println("2 - Задача 2 (проверка симметрии 5x5)")
        println("3 - Задача 3 (в двоичную систему)")
        println("4 - Задача 4 (пересечение массивов)")
        println("5 - Задача 5 (группировка анаграмм)")
        println("0 - Выход")
        val choice = readNormalno("Выберите цифру: ")

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
            exitProcess(0)
        } else {
            println("Неправильный выбор!")
        }
    }
}

fun task1() {
    val rows = readNormalno("Сколько строк? ")
    val cols = readNormalno("Сколько столбцов? ")

    val numbers = Array(rows) { IntArray(cols) }
    val allDigits = mutableSetOf<Char>()

    println("Вводите числа (целые):")
    var i = 0
    while (i < rows) {
        var j = 0
        while (j < cols) {
            val prompt = "Число [$i][$j]: "
            val value = readNormalno(prompt)
            numbers[i][j] = value

            val s = value.toString()
            var k = 0
            while (k < s.length) {
                allDigits.add(s[k])
                k = k + 1
            }

            j = j + 1
        }
        i = i + 1
    }

    println("\nВаш массив:")
    i = 0
    while (i < rows) {
        var j = 0
        while (j < cols) {

            print(numbers[i][j].toString() + "\t")
            j = j + 1
        }
        println()
        i = i + 1
    }

    val sortedDigits = allDigits.toList().sorted()
    println("\nРазных цифр: " + sortedDigits.size)
    println("Цифры: " + sortedDigits.joinToString(", "))
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
    var i = 0
    while (i < 5) {
        var j = 0
        while (j < 5) {
            print(numbers[i][j].toString() + "\t")
            j = j + 1
        }
        println()
        i = i + 1
    }

    var symmetric = true
    i = 0
    while (i < 5) {
        var j = 0
        while (j < 5) {

            if (numbers[i][j] == numbers[j][i]) {

            } else {
                symmetric = false
            }
            j = j + 1
        }
        i = i + 1
    }

    if (symmetric == true) {
        println("Массив симметричный")
    } else {
        println("Массив НЕ симметричный")
    }
}

fun task3() {
    val number = readNormalno("Введите число: ")

    val binary = Integer.toBinaryString(number)
    println("В двоичной системе: " + binary)
}

fun task4() {
    println("Введите массив (числа через пробел):")
    val masOneLine = readNonEmptyLine("Первый массив: ")
    println("Введите второй массив (числа через пробел):")
    val masTwoLine = readNonEmptyLine("Второй массив: ")

    val parts1 = masOneLine.trim().split(Regex("\\s+"))
    val parts2 = masTwoLine.trim().split(Regex("\\s+"))

    val arr1 = IntArray(parts1.size)
    val arr2 = IntArray(parts2.size)

    var idx1 = 0
    while (idx1 < parts1.size) {
        arr1[idx1] = parts1[idx1].toIntOrNull() ?: 0
        idx1 = idx1 + 1
    }
    var idx2 = 0
    while (idx2 < parts2.size) {
        arr2[idx2] = parts2[idx2].toIntOrNull() ?: 0
        idx2 = idx2 + 1
    }

    val result = intersectionArrays(arr1, arr2)

    println("Пересечение массивов:")
    print("[")
    var p = 0
    while (p < result.size) {
        print(result[p])
        if (p < result.size - 1) {
            print(", ")
        }
        p = p + 1
    }
    println("]")
}

fun intersectionArrays(a: IntArray, b: IntArray): IntArray {

    val copyA = IntArray(a.size)
    var i = 0
    while (i < a.size) {
        copyA[i] = a[i]
        i = i + 1
    }
    val copyB = IntArray(b.size)
    i = 0
    while (i < b.size) {
        copyB[i] = b[i]
        i = i + 1
    }

    val usedB = BooleanArray(copyB.size)
    val temp = IntArray(minOf(copyA.size, copyB.size))
    var foundCount = 0

    i = 0
    while (i < copyA.size) {
        var j = 0
        var matched = false
        while (j < copyB.size) {
            if (copyA[i] == copyB[j] && usedB[j] == false) {

                var already = false
                var k = 0
                while (k < foundCount) {
                    if (temp[k] == copyA[i]) {
                        already = true
                        break
                    }
                    k = k + 1
                }
                if (already == false) {
                    temp[foundCount] = copyA[i]
                    foundCount = foundCount + 1
                }
                usedB[j] = true
                matched = true
                break
            }
            j = j + 1
        }
        if (matched == true) {

        }
        i = i + 1
    }

    val final = IntArray(foundCount)
    var t = 0
    while (t < foundCount) {
        final[t] = temp[t]
        t = t + 1
    }

    var swapped = true
    var n = final.size
    while (swapped == true) {
        swapped = false
        var pIndex = 1
        while (pIndex < n) {
            if (final[pIndex - 1] > final[pIndex]) {
                val tmp = final[pIndex - 1]
                final[pIndex - 1] = final[pIndex]
                final[pIndex] = tmp
                swapped = true
            }
            pIndex = pIndex + 1
        }
        n = n - 1
    }

    return final
}

fun task5() {
    println("Введите слова через пробел, например eat tea tan ate nat bat:")
    val input = readNonEmptyLine("Слова: ")
    val words = input.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }

    val result = groupWords(words.toTypedArray())

    println("Группы слов:")
    var i = 0
    while (i < result.size) {
        val group = result[i]
        print("\"")
        var j = 0
        while (j < group.size) {
            print(group[j])
            if (j < group.size - 1) {
                print("\", \"")
            }
            j = j + 1
        }
        println("\"")
        i = i + 1
    }
}

fun groupWords(words: Array<String>): List<List<String>> {
    val groups = mutableListOf<List<String>>()
    val used = BooleanArray(words.size)

    var i = 0
    while (i < words.size) {
        if (used[i] == true) {
            i = i + 1
            continue
        }
        val currentGroup = mutableListOf<String>()
        currentGroup.add(words[i])
        used[i] = true

        var j = i + 1
        while (j < words.size) {
            if (used[j] == true) {
                j = j + 1
                continue
            }
            if (haveSameLetters(words[i], words[j]) == true) {
                currentGroup.add(words[j])
                used[j] = true
            }
            j = j + 1
        }

        groups.add(currentGroup)
        i = i + 1
    }

    return groups
}

fun haveSameLetters(word1: String, word2: String): Boolean {
    if (word1.length != word2.length) {
        return false
    }
    val chars1 = word1.toCharArray()
    val chars2 = word2.toCharArray()

    sortChars(chars1)
    sortChars(chars2)

    var i = 0
    while (i < chars1.size) {
        if (chars1[i] == chars2[i]) {

        } else {
            return false
        }
        i = i + 1
    }
    return true
}

fun sortChars(chars: CharArray) {
    var swapped = true
    var n = chars.size
    while (swapped == true) {
        swapped = false
        var i = 1
        while (i < n) {
            if (chars[i - 1] > chars[i]) {
                val tmp = chars[i - 1]
                chars[i - 1] = chars[i]
                chars[i] = tmp
                swapped = true
            }
            i = i + 1
        }
        n = n - 1
    }
}