package com.example.mol12345

import java.lang.Error

// 계산기의 '기능적'인 부분들
class Calculator {
    private val num_list = mutableListOf<String>()
    private val op_list = mutableListOf<String>()
    private var lastOp : String = ""
    private var lastNumber : String = "0"
    private var latest : String = ""
    private fun RemoveZeros(str : String) : String{
        val doub : Double = str.toDouble()
        val result : String =
            if(doub.toInt().toDouble() == doub){
                doub.toInt().toString()
            }else{
                doub.toString()
            }
        return result
    }
    private fun Calculating(in1 : String,in2 : String, op : String) : String{
        val old: Double = in1.toDouble()
        val new: Double = in2.toDouble()
        val d_result: Double =
            when (op) {
                "÷" -> {
                    if (new == 0.0) {
                        Clear()
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
        val retval : String = if(d_result.toInt().toDouble() == d_result){
            d_result.toInt().toString()
        }
        else{
            d_result.toString()
        }
        return retval
    }
    fun Call(new_data: String) : String{
        if (num_list.isEmpty() and (new_data == "0")){
            return "0"
        }
        when(new_data) {
            "0","1","2","3","4","5","6","7","8","9" ->
                return Call_Num(new_data)
            "C","AC" ->
                return Clear()
            "." ->
                return Call_Dot(new_data)
            "+/-" ->
                return Call_Neg(new_data)
            "=" ->
                return Call_Cal(new_data)
            "÷","×","-","+" ->
                return Call_Op(new_data)
            "%" ->
                return Call_Per(new_data)

            else -> {
                println("why you come here?")
                return ""
            }
        }
    }
    private fun Call_Num(new_data : String) : String {
        when(latest){
            "" -> {
                num_list.add(new_data)
                latest = num_list.last()
            }
            "÷","×","-","+" -> {
                num_list.add(new_data)
                latest = num_list.last()
            }
            "=" -> {
                num_list.clear()
                num_list[0] = new_data
                latest = new_data
            }
            else -> {
                if(num_list[num_list.lastIndex] == "-0"){
                    num_list[num_list.lastIndex] = "-$new_data"
                }
                else {
                    num_list[num_list.lastIndex] = num_list.last().plus(new_data)
                }
                latest = num_list.last()
            }
        }
        num_list[num_list.lastIndex] = RemoveZeros(num_list.last())
        return num_list.last()
    }
    private fun Call_Op(new_data : String) : String {
        when(latest) {
            "" -> {
                num_list.add("0")
            }
            "=" -> {
                if(num_list.isEmpty()){
                    num_list.add("0")
                }

            }
            "÷", "×", "-", "+" -> {
                op_list.removeAt(op_list.lastIndex)
            }
            else -> {
                while(op_list.isNotEmpty()) {
                    when (op_list.last()) {
                        "÷", "×" -> {
                            val result: String = Calculating(
                                num_list[num_list.lastIndex - 1],
                                num_list[num_list.lastIndex],
                                op_list.last()
                            )
                            if(result == "오류"){
                                return result
                            }
                            num_list.removeAt(num_list.lastIndex)
                            num_list[num_list.lastIndex] = result
                            op_list.removeAt(op_list.lastIndex)
                        }
                        "-", "+" -> {
                            if ((new_data == "+") or (new_data == "-")) {
                                val result: String = Calculating(
                                    num_list[num_list.lastIndex - 1],
                                    num_list[num_list.lastIndex],
                                    op_list.last()
                                )
                                if(result == "오류"){
                                    return result
                                }
                                num_list.removeAt(num_list.lastIndex)
                                num_list[num_list.lastIndex] = result
                                op_list.removeAt(op_list.lastIndex)
                            }
                            else{
                                break
                            }
                        }
                    }
                }
            }
        }
        op_list.add(new_data)
        latest = new_data
        num_list[num_list.lastIndex] = RemoveZeros(num_list.last())
        return num_list.last()
    }
    private fun Call_Cal(new_data : String) : String{
        //num1 op num2 = -> old : num2, new : result, op : op, latest : =
        if(latest.toDoubleOrNull() != null){
            lastNumber = num_list.last()

            if(op_list.isNotEmpty()) {
                lastOp = op_list.last()
                for (i: Int in 0..op_list.lastIndex) {
                    if ((op_list[i] == "÷") or (op_list[i] == "×")) {
                        val result: String = Calculating(num_list[i], num_list[i + 1], op_list[i])
                        if (result == "오류") {
                            return result
                        }
                        num_list.removeAt(i + 1)
                        num_list[i] = result
                        op_list.removeAt(i)
                    }
                }
                for (i: Int in 0..op_list.lastIndex) {
                    val result: String = Calculating(num_list[i], num_list[i + 1], op_list[i])
                    if (result == "오류") {
                        return result
                    }
                    num_list.removeAt(i + 1)
                    num_list[i] = result
                    op_list.removeAt(i)
                }
                latest = new_data
            }
        }
        else {
            //num op = -> num op num -> old : num, new : result, op : op, latest : =
            when(latest){
                "÷","×","-","+" -> {
                    lastOp = op_list.last()
                    lastNumber = num_list.last()
                    val result : String = Calculating(num_list[num_list.lastIndex],lastNumber,lastOp)
                    if(result == "오류"){
                        return  result
                    }
                    num_list[num_list.lastIndex] = result
                    op_list.removeAt(op_list.lastIndex)
                }
                "=" -> {
                    val result : String = Calculating(num_list[num_list.lastIndex],lastNumber,lastOp)
                    if(result == "오류"){
                        return result
                    }
                    num_list[num_list.lastIndex] = result

                }
                else -> {
                    return "0"
                }
            }
            //num1 op num2 == -> num1 op num2 op num2
            latest = new_data
        }

        num_list[num_list.lastIndex] = RemoveZeros(num_list.last())
        return num_list.last()
    }
    private fun Call_Dot(new_data : String) : String{
        when(latest.toIntOrNull()){
            null -> {//double,"",operator,"="
                when(latest.toDoubleOrNull()){
                    null-> {//operator or "" or "="
                        when(latest){
                            "=" -> {
//                                num_list.last() =
                                num_list[num_list.lastIndex] = num_list.last().plus(".")
                                latest = num_list.last()
                            }
                            else -> {//새로운 num만들기 0.
                                num_list.add("0.")
                                latest = num_list.last()
                            }
                        }
                    }
                    else -> {//double
                        return num_list.last()
                    }
                }
            }
            else -> {//int
//                n_new =
                num_list[num_list.lastIndex] = num_list.last().plus(".")
                latest = num_list.last()
            }
        }
        return num_list.last()
    }
    private fun Call_Neg(new_data : String) : String{
        if(num_list.isNotEmpty()){
            if(num_list.last() == "0"){
                num_list[num_list.lastIndex] = "-0"
            }
            else if (num_list.last() == "-0"){
                num_list[num_list.lastIndex] = "0"
            }
            else {
                val new: Double = num_list.last().toDouble()
                num_list[num_list.lastIndex] = if (new.toInt().toDouble() == new) {
                    (-(new.toInt())).toString()
                } else {
                    (-new).toString()
                }
            }
        }
        else{
            num_list.add("-0")
        }
        latest = num_list.last()
        return num_list.last()
    }
    private fun Call_Per(new_data : String) : String {
        if (num_list.isEmpty()) {
            return "0"
        }
        else if(latest.toDoubleOrNull() != null){
            var new : Double = num_list.last().toDouble()
            new /= 100
            num_list[num_list.lastIndex] = new.toString()
            latest = num_list[num_list.lastIndex]
        }
//        num_list[num_list.lastIndex] = RemoveZeros(num_list.last())
        return num_list.last()
    }
    private fun Clear() : String{
        num_list.clear()
        op_list.clear()
        latest = ""
        return "0"
    }
}