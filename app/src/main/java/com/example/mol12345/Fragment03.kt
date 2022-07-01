package com.example.mol12345

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment

class Fragment03: Fragment() {
    private val black = "#000000"
    private val orange = "#f7a307"
    private val white = "#ffffff"
    private val grey = "#444444"
    private val darkGrey = "#999999"

    private val greyColorSet = ColorSet(grey, white, black, black)
    private val darkGreyColorSet = ColorSet(darkGrey, white, white, black)
    private val orangeColorSet = ColorSet(orange, white, white, black)

    private val buttonData = listOf(
        ButtonInfo("calculator_button_ac", greyColorSet, R.id.calculator_button_ac),
        ButtonInfo("calculator_button_unary", greyColorSet, R.id.calculator_button_unary),
        ButtonInfo("calculator_button_percent", greyColorSet, R.id.calculator_button_percent),
        ButtonInfo("calculator_button_div", orangeColorSet, R.id.calculator_button_div),
        ButtonInfo("calculator_button_7", darkGreyColorSet, R.id.calculator_button_7),
        ButtonInfo("calculator_button_8", darkGreyColorSet, R.id.calculator_button_8),
        ButtonInfo("calculator_button_9", darkGreyColorSet, R.id.calculator_button_9),
        ButtonInfo("calculator_button_mul", orangeColorSet, R.id.calculator_button_mul),
        ButtonInfo("calculator_button_4", darkGreyColorSet, R.id.calculator_button_4),
        ButtonInfo("calculator_button_5", darkGreyColorSet, R.id.calculator_button_5),
        ButtonInfo("calculator_button_6", darkGreyColorSet, R.id.calculator_button_6),
        ButtonInfo("calculator_button_sub", orangeColorSet, R.id.calculator_button_sub),
        ButtonInfo("calculator_button_1", darkGreyColorSet, R.id.calculator_button_1),
        ButtonInfo("calculator_button_2", darkGreyColorSet, R.id.calculator_button_2),
        ButtonInfo("calculator_button_3", darkGreyColorSet, R.id.calculator_button_3),
        ButtonInfo("calculator_button_add", orangeColorSet, R.id.calculator_button_add),
        ButtonInfo("calculator_button_0", darkGreyColorSet, R.id.calculator_button_0),
        ButtonInfo("calculator_button_dot", darkGreyColorSet, R.id.calculator_button_dot),
        ButtonInfo("calculator_button_cal", orangeColorSet, R.id.calculator_button_cal),
        )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_03, container, false)
        val cal  = Calculator()
        val text : TextView = fragmentView.findViewById(R.id.calculator_display)

        val calculator = Calculator()

        for (data in buttonData) {
            val buttonView: Button = fragmentView.findViewById(data.id)
            val context = buttonView.context
            val buttonResource = context.resources.getIdentifier(data.name, "string", context.packageName)
            buttonView.text = getString(buttonResource)
            buttonView.setOnClickListener {
                text.text = cal.Call(buttonView.text as String)

            }
            buttonView.background = AppCompatResources.getDrawable(context, R.drawable.roundcorner)
        }

        return fragmentView
    }
}

data class ColorSet(
    val buttonColor: String,
    val onClickColor: String,
    val textColor: String,
    val onClickTextColor: String,
)

data class ButtonInfo(
    val name: String,
    val colors: ColorSet,
    val id: Int,
)