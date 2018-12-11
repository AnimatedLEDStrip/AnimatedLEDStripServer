import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import java.lang.Math.random
import kotlin.coroutines.CoroutineContext

object AnimationHandler {

    fun addAnimation(params: Map<*, *>) {
        println(params)
        GlobalScope.launch(newSingleThreadContext("Thread ${random()}")) {
            singleRunAnimation(params)
            println(Thread.currentThread().name)
        }
//        println("End addAnimation()")
    }
}

class singleRunAnimation(params: Map<*, *>) {

    init {
        val (animation,
                color1,
                color2,
                color3,
                color4,
                direction,
                spacing,
                delay) = params

//        println("Animation Start: $animation")
        when (animation) {
            Animations.COLOR1 ->
                color1(color1)
            Animations.COLOR2 ->
                color2(color1, color2)
            Animations.COLOR3 ->
                color3(color1, color2, color3)
            Animations.COLOR4 ->
                color4(color1, color2, color3, color4)
            Animations.MULTIPIXELRUNTOCOLOR -> {
//                println("MPR")
                multiPixelRunToColor(color1, spacing, direction, delay)
            }
            Animations.SPARKLETOCOLOR ->
                sparkleToColor(color1, delay)
            Animations.STACK -> {
//                println("Stacking")
                stack(color1, color2, direction)
            }
            Animations.STACKOVERFLOW ->
                stackOverflow(color1, color2)
            Animations.WIPE ->
                wipe(color1, direction)
        }


    }

    private fun color1(color: Long) = try {
        leds.setStripColor(color)
        Thread.sleep(1000)
    } catch (e: Exception) {
        println("Handler Error - Static Color 1: $e")
        color2(0x0, null)
    }

    private fun color2(color1: Long, color2: Long?) = try {
        val c2 = color2 ?: 0x0

        leds.setStripWithGradient(ColorContainer(color1), ColorContainer(c2))
        Thread.sleep(1000)
    } catch (e: Exception) {
        println("Handler Error - Static Color 2: $e")
    }

    private fun color3(color1: Long, color2: Long?, color3: Long?) = try {
        val c2 = color2 ?: 0x0
        val c3 = color3 ?: 0x0
        leds.setStripWithGradient(ColorContainer(color1), ColorContainer(c2), ColorContainer(c3))
        Thread.sleep(1000)
    } catch (e: Exception) {
        println("Handler Error - Static Color 3: $e")
    }

    private fun color4(color1: Long, color2: Long?, color3: Long?, color4: Long?) = try {
        val c2 = color2 ?: 0x0
        val c3 = color3 ?: 0x0
        val c4 = color4 ?: 0x0
        leds.setStripWithGradient(ColorContainer(color1), ColorContainer(c2), ColorContainer(c3), ColorContainer(c4))
        Thread.sleep(1000)
    } catch (e: Exception) {
        println("Handler Error - Static Color 3: $e")
    }

    private fun multiPixelRunToColor(color: Long, spacing: Int?, direction: Char?, delay: Int?) = try {
        val s = spacing ?: 4
        val d = delay ?: 10
        leds.multiPixelRunToColor(
            s,
            when (direction?.toUpperCase()) {
                'F' -> Direction.FORWARD
                'B' -> Direction.BACKWARD
                else -> Direction.FORWARD
            },
            ColorContainer(color)
        )
    } catch (e: Exception) {
        println("Handler Error - Multi-Pixel Run To Color Animation: $e")
    }

    private fun sparkleToColor(color: Long, delay: Int?) = try {
        val d = delay ?: 10
        leds.sparkleToColor(ColorContainer(color), d)
    } catch (e: Exception) {
        println("Handler Error - Sparkle to Color Animation: $e")
    }

    private fun stack(color1: Long, color2: Long?, direction: Char?) = try {
        val c2 = color2 ?: 0x0
        leds.stack(
            when (direction?.toUpperCase()) {
                'F' -> Direction.FORWARD
                'B' -> Direction.BACKWARD
                else -> Direction.FORWARD
            },
            ColorContainer(color1),
            ColorContainer(c2)
        )
    } catch (e: Exception) {
        println("Handler Error - Stack Animation: $e")
    }

    private fun stackOverflow(color1: Long, color2: Long?) = try {
        val c2 = color2 ?: 0xFF
        leds.stackOverflow(ColorContainer(color1), ColorContainer(c2))
    } catch (e: Exception) {
        println("Handler Error - Stack Overflow Animation: $e")
    }


    private fun wipe(color: Long, direction: Char?) = try {
        leds.wipe(
            ColorContainer(color),
            when (direction?.toUpperCase()) {
                'F' -> Direction.FORWARD
                'B' -> Direction.BACKWARD
                else -> Direction.FORWARD
            }
        )
    } catch (e: Exception) {
        println("Handler Error - Wipe Animation: $e")
    }

}