package tk.mohithaiyappa.coffeeprices.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class BarGraph @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val rect: RectF = RectF()

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var fullBarWidth: Float = 0f
    private var fullBarGap: Float = 0f
    private var barWidth: Float = 0f
    private var barGap: Float = 0f

    private var paintColor: Int = Color.CYAN

    private var barHeight: Float = 0f

    private var hiPoint: Float = 0f
    private var hiValue: Float = 0f
    private var lowPoint: Float = 0f
    private var lowValue: Float = 0f
    private var hiLowDiff: Float = 0f
    private var currentValue: Float = 0f

    private var radius: Float = 7f

    private var priceList: List<Float> = listOf()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (priceList.isNullOrEmpty()) return

        fullBarWidth = (width * 0.667).toFloat()
        fullBarGap = (width * 0.333).toFloat()
        barWidth = fullBarWidth / priceList.size
        barGap = fullBarGap / (priceList.size - 1)

        barHeight = height.toFloat()
        lowPoint = barHeight * 0.2f

        calculateHiAndLow()

        paint.color = paintColor

        for (i in priceList.indices) {
            rect.left = barWidth * i + barGap * i
            rect.top = barHeight - (barHeight * getCurrentHeight(priceList[i]) * 0.01f)
            rect.right = barWidth + rect.left
            rect.bottom = hiPoint + barHeight

            canvas?.drawRoundRect(rect, radius, radius, paint)
        }
    }

    private fun calculateHiAndLow() {
        findHiAndLowValues()
    }

    private fun findHiAndLowValues() {
        hiValue = priceList[0]
        lowValue = priceList[0]
        for (value in priceList) {
            if (value <= lowValue) lowValue = value
            if (value >= hiValue) hiValue = value
        }

        hiLowDiff = hiValue - lowValue
    }

    private fun getCurrentHeight(value: Float): Float {
        val valueDiff = value - lowValue
        var heightInPercentage = 20f

        if (valueDiff == 0f) return heightInPercentage

        heightInPercentage = (valueDiff * 100) / hiLowDiff

        return if (heightInPercentage < 20f) 20f
        else heightInPercentage
    }

    fun setBarColor(color: Int) {
        paintColor = color
        invalidate()
    }

    fun setRadius(rad: Float) {
        radius = rad
    }

    fun submitData(prices: List<Float>) {
        priceList = prices
        invalidate()
    }
}