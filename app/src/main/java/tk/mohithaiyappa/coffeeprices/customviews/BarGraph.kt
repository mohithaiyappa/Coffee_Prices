package tk.mohithaiyappa.coffeeprices.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class BarGraph @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val rect1: Rect = Rect()
    private val rect2: Rect = Rect()
    private val rect3: Rect = Rect()
    private val rect4: Rect = Rect()
    private val rect5: Rect = Rect()
    private val rect6: Rect = Rect()
    private val rect7: Rect = Rect()

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var fullBarWidth: Int = 0
    private var fullBarGap: Int = 0
    private var barWidth: Int = 0
    private var barGap: Int = 0

    private var barHeight: Int = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        fullBarWidth = width / 2
        fullBarGap = width / 2
        barWidth = fullBarWidth / 7
        barGap = fullBarGap / 6

        barHeight = height

        rect1.left = 0
        rect1.top = 0
        rect1.right = barWidth
        rect1.bottom = height

        rect2.left = barWidth * 1 + barGap * 1
        rect2.top = 0
        rect2.right = barWidth + barWidth * 1 + barGap * 1
        rect2.bottom = height

        rect3.left = barWidth * 2 + barGap * 2
        rect3.top = 0
        rect3.right = barWidth + barWidth * 2 + barGap * 2
        rect3.bottom = height

        rect4.left = barWidth * 3 + barGap * 3
        rect4.top = 0
        rect4.right = barWidth + barWidth * 3 + barGap * 3
        rect4.bottom = height

        rect5.left = barWidth * 4 + barGap * 4
        rect5.top = 0
        rect5.right = barWidth + barWidth * 4 + barGap * 4
        rect5.bottom = height

        rect6.left = barWidth * 5 + barGap * 5
        rect6.top = 0
        rect6.right = barWidth + barWidth * 5 + barGap * 5
        rect6.bottom = height

        rect7.left = barWidth * 6 + barGap * 6
        rect7.top = 0
        rect7.right = barWidth + barWidth * 6 + barGap * 6
        rect7.bottom = height

        paint.color = Color.BLUE

        canvas?.drawRect(rect1, paint)
        canvas?.drawRect(rect2, paint)
        canvas?.drawRect(rect3, paint)
        canvas?.drawRect(rect4, paint)
        canvas?.drawRect(rect5, paint)
        canvas?.drawRect(rect6, paint)
        canvas?.drawRect(rect7, paint)
    }
}