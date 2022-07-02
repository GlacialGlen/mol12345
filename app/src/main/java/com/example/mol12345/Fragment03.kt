package com.example.mol12345

import android.graphics.Color.parseColor
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
    private val orange = "#f1a33b"
    private val white = "#ffffff"
    private val grey = "#444444"
    private val darkGrey = "#999999"

    private val lightGreyColorSet = ColorSet(darkGrey, black)
    private val greyColorSet = ColorSet(grey, white)
    private val orangeColorSet = ColorSet(orange, white)

    private val buttonData = listOf(
        ButtonInfo("calculator_button_ac", lightGreyColorSet, R.id.calculator_button_ac),
        ButtonInfo("calculator_button_unary", lightGreyColorSet, R.id.calculator_button_unary),
        ButtonInfo("calculator_button_percent", lightGreyColorSet, R.id.calculator_button_percent),
        ButtonInfo("calculator_button_div", orangeColorSet, R.id.calculator_button_div),
        ButtonInfo("calculator_button_7", greyColorSet, R.id.calculator_button_7),
        ButtonInfo("calculator_button_8", greyColorSet, R.id.calculator_button_8),
        ButtonInfo("calculator_button_9", greyColorSet, R.id.calculator_button_9),
        ButtonInfo("calculator_button_mul", orangeColorSet, R.id.calculator_button_mul),
        ButtonInfo("calculator_button_4", greyColorSet, R.id.calculator_button_4),
        ButtonInfo("calculator_button_5", greyColorSet, R.id.calculator_button_5),
        ButtonInfo("calculator_button_6", greyColorSet, R.id.calculator_button_6),
        ButtonInfo("calculator_button_sub", orangeColorSet, R.id.calculator_button_sub),
        ButtonInfo("calculator_button_1", greyColorSet, R.id.calculator_button_1),
        ButtonInfo("calculator_button_2", greyColorSet, R.id.calculator_button_2),
        ButtonInfo("calculator_button_3", greyColorSet, R.id.calculator_button_3),
        ButtonInfo("calculator_button_add", orangeColorSet, R.id.calculator_button_add),
        ButtonInfo("calculator_button_0", greyColorSet, R.id.calculator_button_0),
        ButtonInfo("calculator_button_dot", greyColorSet, R.id.calculator_button_dot),
        ButtonInfo("calculator_button_cal", orangeColorSet, R.id.calculator_button_cal),
        )

    private val canBeHighlighted = listOf(
        R.id.calculator_button_div,
        R.id.calculator_button_mul,
        R.id.calculator_button_sub,
        R.id.calculator_button_add,
    )

    private val convertsToC = listOf(
        R.id.calculator_button_1,
        R.id.calculator_button_2,
        R.id.calculator_button_3,
        R.id.calculator_button_4,
        R.id.calculator_button_5,
        R.id.calculator_button_6,
        R.id.calculator_button_7,
        R.id.calculator_button_8,
        R.id.calculator_button_9,
        R.id.calculator_button_dot,
    )

    private var highlighted : Int? = null
    private var is_c = false
    private lateinit var acButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_03, container, false)
        val cal = Calculator()
        val text : TextView = fragmentView.findViewById(R.id.calculator_display)
        acButton = fragmentView.findViewById(R.id.calculator_button_ac)


        for (data in buttonData) {
            val buttonView: Button = fragmentView.findViewById(data.id)
            val context = buttonView.context
            val buttonResource = context.resources.getIdentifier(data.name, "string", context.packageName)

            buttonView.text = getString(buttonResource)
            buttonView.textSize = 30f
            buttonView.setTextColor(parseColor(data.colors.textColor))

            handleButtonColor(buttonView, data.colors)
            buttonView.setOnClickListener {
                text.text = cal.Call(buttonView.text as String)

                handleClear(acButton, data.id)
                handleButtonHighlight(fragmentView, buttonView, data.id)

            }
            buttonView.background = AppCompatResources.getDrawable(context, R.drawable.roundcorner)
        }

        return fragmentView
    }

    private fun handleButtonColor(buttonView: Button, colors: ColorSet) {
        buttonView.setBackgroundColor(parseColor(colors.buttonColor))
    }

    // Highlight the most recently pressed operator button.
    private fun handleButtonHighlight(fragmentView: View, targetButton: Button, id: Int) {
        // First, suppress the formerly highlighted one.
        if (highlighted != null) {
            val previousButton: Button = fragmentView.findViewById(highlighted!!)
            previousButton.setBackgroundColor(parseColor(orange))
            previousButton.setTextColor(parseColor(white))

            highlighted = null
        }

        // Highlight button only if it is a valid operator.
        if (id in canBeHighlighted) {
            targetButton.setBackgroundColor(parseColor(white))
            targetButton.setTextColor(parseColor(orange))

            highlighted = id
        }
    }

    private fun handleClear(targetButton: Button, id: Int) {
        if (id == R.id.calculator_button_ac) {
            is_c = false
            targetButton.text = getString(R.string.calculator_button_ac)
        }
        else if (id in convertsToC) {
            is_c = true
            targetButton.text = getString(R.string.calculator_button_c)
        }
    }
}


data class ColorSet(
    val buttonColor: String,
    val textColor: String,
)

data class ButtonInfo(
    val name: String,
    val colors: ColorSet,
    val id: Int,
)