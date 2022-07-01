package com.example.mol12345

// 계산기의 '기능적'인 부분들
class Calculator {
    private lateinit var n_old : String
    private lateinit var n_new : String
    private lateinit var op : String
    private lateinit var lastest : String

    fun Call(new_data: String) : String{
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
    fun Call_Num(new_data : String) : String{

    }
    fun Call_Op(new_data : String) : String{

    }
    fun Call_Cal(new_data : String) : String{

    }
    fun Call_Dot(new_data : String) : String{

    }
    fun Call_Neg(new_data : String) : String{

    }
    fun Call_Per(new_data : String) : String {

    }
    fun Clear() : String{
        n_old = ""
        n_new = ""
        op = ""
        lastest = ""
        return "0"
    }
}