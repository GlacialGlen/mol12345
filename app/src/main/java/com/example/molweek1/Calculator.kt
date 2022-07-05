package com.example.molweek1

class Calculator {
    private val numList = mutableListOf<String>()
    private val opList = mutableListOf<String>()
    private var lastOp : String = ""
    private var lastNumber : String = "0"
    private var latest : String = ""
    private fun removeZeros(str : String) : String{
        val doubleNum : Double = str.toDouble()
        val result : String =
            if(doubleNum.toInt().toDouble() == doubleNum){
                doubleNum.toInt().toString()
            }else{
                doubleNum.toString()
            }
        return result
    }
    private fun calculating(in1 : String, in2 : String, op : String) : String{
        val old: Double = in1.toDouble()
        val new: Double = in2.toDouble()
        val dResult: Double =
            when (op) {
                "÷" -> {
                    if (new == 0.0) {
                        clear()
                        return "오류"
                    } else {
                        old / new
                    }
                }
                "×" -> old * new
                "-" -> old - new
                "+" -> old + new
                else -> {
                    return "0.0"
                }
            }
        val retVal : String = if(dResult.toInt().toDouble() == dResult){
            dResult.toInt().toString()
        }
        else{
            dResult.toString()
        }
        return retVal
    }

    fun call(new_data: String) : String{
        if (numList.isEmpty() and (new_data == "0")){
            return "0"
        }
        when(new_data) {
            "0","1","2","3","4","5","6","7","8","9" ->
                return callNum(new_data)
            "C","AC" ->
                return clear()
            "." ->
                return callDot()
            "+/-" ->
                return callNeg()
            "=" ->
                return callCal(new_data)
            "÷","×","-","+" ->
                return callOp(new_data)
            "%" ->
                return callPer()

            else -> {
                println("why you come here?")
                return ""
            }
        }
    }

    private fun callNum(new_data : String) : String {
        when(latest){
            "" -> {
                numList.add(new_data)
                latest = numList.last()
            }
            "÷","×","-","+" -> {
                numList.add(new_data)
                latest = numList.last()
            }
            "=" -> {
                numList.clear()
                numList.add(new_data)
                latest = new_data
            }
            else -> {
                if(numList[numList.lastIndex] == "-0"){
                    numList[numList.lastIndex] = "-$new_data"
                }
                else if (numList[numList.lastIndex].length < 9) {
                    numList[numList.lastIndex] = numList.last().plus(new_data)
                }
                latest = numList.last()
            }
        }
        numList[numList.lastIndex] = removeZeros(numList.last())
        return numList.last()
    }
    private fun callOp(new_data : String) : String {
        when(latest) {
            "" -> {
                numList.add("0")
            }
            "=" -> {
                if(numList.isEmpty()){
                    numList.add("0")
                }

            }
            "÷", "×", "-", "+" -> {
                opList.removeAt(opList.lastIndex)
            }
            else -> {
                while(opList.isNotEmpty()) {
                    when (opList.last()) {
                        "÷", "×" -> {
                            val result: String = calculating(
                                numList[numList.lastIndex - 1],
                                numList[numList.lastIndex],
                                opList.last()
                            )
                            if(result == "오류"){
                                return result
                            }
                            numList.removeAt(numList.lastIndex)
                            numList[numList.lastIndex] = result
                            opList.removeAt(opList.lastIndex)
                        }
                        "-", "+" -> {
                            if ((new_data == "+") or (new_data == "-")) {
                                val result: String = calculating(
                                    numList[numList.lastIndex - 1],
                                    numList[numList.lastIndex],
                                    opList.last()
                                )
                                if(result == "오류"){
                                    return result
                                }
                                numList.removeAt(numList.lastIndex)
                                numList[numList.lastIndex] = result
                                opList.removeAt(opList.lastIndex)
                            }
                            else{
                                break
                            }
                        }
                    }
                }
            }
        }
        opList.add(new_data)
        latest = new_data
        numList[numList.lastIndex] = removeZeros(numList.last())
        return numList.last()
    }
    private fun callCal(new_data : String) : String{
        //num1 op num2 = -> old : num2, new : result, op : op, latest : =
        if(latest.toDoubleOrNull() != null){
            lastNumber = numList.last()

            if(opList.isNotEmpty()) {
                lastOp = opList.last()
                for (i: Int in 0..opList.lastIndex) {
                    if ((opList[i] == "÷") or (opList[i] == "×")) {
                        val result: String = calculating(numList[i], numList[i + 1], opList[i])
                        if (result == "오류") {
                            return result
                        }
                        numList.removeAt(i + 1)
                        numList[i] = result
                        opList.removeAt(i)
                    }
                }
                for (i: Int in 0..opList.lastIndex) {
                    val result: String = calculating(numList[i], numList[i + 1], opList[i])
                    if (result == "오류") {
                        return result
                    }
                    numList.removeAt(i + 1)
                    numList[i] = result
                    opList.removeAt(i)
                }
            }
        }
        else {
            //num op = -> num op num -> old : num, new : result, op : op, latest : =
            when(latest){
                "÷","×","-","+" -> {
                    lastOp = opList.last()
                    lastNumber = numList.last()
                    val result : String = calculating(numList[numList.lastIndex],lastNumber,lastOp)
                    if(result == "오류"){
                        return  result
                    }
                    numList[numList.lastIndex] = result
                    opList.removeAt(opList.lastIndex)
                }
                "=" -> {
                    val result : String = calculating(numList[numList.lastIndex],lastNumber,lastOp)
                    if(result == "오류"){
                        return result
                    }
                    numList[numList.lastIndex] = result

                }
                else -> {
                    return "0"
                }
            }
            //num1 op num2 == -> num1 op num2 op num2

        }
        latest = new_data
        return numList.last()
    }
    private fun callDot() : String{
        when(latest.toIntOrNull()){
            null -> {//double,"",operator,"="
                when(latest.toDoubleOrNull()){
                    null-> {//operator or "" or "="
                        when(latest){
                            "=" -> {
//                                num_list.last() =
                                numList[numList.lastIndex] = numList.last().plus(".")
                                latest = numList.last()
                            }
                            else -> {//새로운 num 만들기 0.
                                numList.add("0.")
                                latest = numList.last()
                            }
                        }
                    }
                    else -> {//double
                        return numList.last()
                    }
                }
            }
            else -> {//int
//                n_new =
                numList[numList.lastIndex] = numList.last().plus(".")
                latest = numList.last()
            }
        }
        return numList.last()
    }
    private fun callNeg() : String{
        if(numList.isNotEmpty()){
            if(numList.last() == "0"){
                numList[numList.lastIndex] = "-0"
            }
            else if (numList.last() == "-0"){
                numList[numList.lastIndex] = "0"
            }
            else {
                val new: Double = numList.last().toDouble()
                numList[numList.lastIndex] = if (new.toInt().toDouble() == new) {
                    (-(new.toInt())).toString()
                } else {
                    (-new).toString()
                }
            }
        }
        else{
            numList.add("-0")
        }
        latest = numList.last()
        return numList.last()
    }
    private fun callPer() : String {
        if (numList.isEmpty()) {
            return "0"
        }
        else if(numList.last().toDouble().toInt() == numList.last().toInt()){
            var new : Double = numList.last().toDouble()
            if (new != 0.0) {
                new /= 100
                numList[numList.lastIndex] = new.toString()
                if(latest.toDoubleOrNull() != null){
                    latest = numList.last()
                }
            }
        }
        return numList.last()
    }
    private fun clear() : String{
        numList.clear()
        opList.clear()
        latest = ""
        return "0"
    }
}