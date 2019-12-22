//package animatedledstrip.test
//
///*
// *  Copyright (c) 2019 AnimatedLEDStrip
// *
// *  Permission is hereby granted, free of charge, to any person obtaining a copy
// *  of this software and associated documentation files (the "Software"), to deal
// *  in the Software without restriction, including without limitation the rights
// *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// *  copies of the Software, and to permit persons to whom the Software is
// *  furnished to do so, subject to the following conditions:
// *
// *  The above copyright notice and this permission notice shall be included in
// *  all copies or substantial portions of the Software.
// *
// *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// *  THE SOFTWARE.
// */
//
//
//import animatedledstrip.animationutils.*
//import animatedledstrip.colors.ccpresets.CCBlue
//import animatedledstrip.leds.emulated.EmulatedAnimatedLEDStrip
//import animatedledstrip.server.AnimationHandler
//import animatedledstrip.server.SocketConnections
//import animatedledstrip.utils.delayBlocking
//import org.junit.Test
//import org.pmw.tinylog.Configurator
//import org.pmw.tinylog.Level
//import java.io.ByteArrayOutputStream
//import java.io.PrintStream
//import kotlin.test.assertTrue
//
//class AnimationHandlerTest {
//
//
//    init {
//        SocketConnections.connections.clear()
//    }
//
//    private val leds = EmulatedAnimatedLEDStrip(50)
//
//    @Test
//    fun testHandlerInit() {
//        val handler = AnimationHandler(leds)
//        assertTrue { handler.continuousAnimations.isEmpty() }
//    }
//
//    @Test
//    fun testNonRepetitiveAnimation() {
//        val handler = AnimationHandler(leds)
//
//        handler.addAnimation(AnimationData().animation(Animation.COLOR).color(CCBlue))
//
//        delayBlocking(1000)
//
//        checkAllPixels(leds, 0xFF)
//    }
//
//    @Test
//    fun testNonContinuousAnimation() {
//        val handler = AnimationHandler(leds)
//        handler.addAnimation(AnimationData().animation(Animation.MULTIPIXELRUN).continuous(false))
//        delayBlocking(1000)
//    }
//
//    @Test
//    fun testContinuousAnimation() {
//        val handler = AnimationHandler(leds)
//        handler.addAnimation(
//            AnimationData()
//                .animation(Animation.PIXELRUN)
//                .color(0xFF)
//                .direction(Direction.FORWARD)
//        )
//
//        delayBlocking(2000)
//
//        for (key in handler.continuousAnimations.keys) {
//            handler.addAnimation(AnimationData().animation(Animation.ENDANIMATION).id(key))
//        }
//
//        delayBlocking(1000)
//        checkAllPixels(leds, 0x0)
//    }
//
//    @Test
//    fun testRemoveNonExistentAnimation() {
//        val stderr: PrintStream = System.err
//        val tempOut = ByteArrayOutputStream()
//        System.setErr(PrintStream(tempOut))
//
//        tempOut.reset()
//
//        Configurator.defaultConfig()
//            .formatPattern("{{level}:|min-size=8} {message}")
//            .level(Level.INFO)
//            .activate()
//
//        val handler = AnimationHandler(leds)
//        handler.addAnimation(AnimationData().animation(Animation.ENDANIMATION).id("TEST"))
//
//        assertTrue {
//            tempOut
//                .toString("utf-8")
//                .replace("\r\n", "\n")
//                .replace("LOGGER ERROR: Cannot find a writer for the name \"socket\"\n", "") ==
//                    "WARNING: Animation TEST not running\n"
//        }
//
//        System.setErr(stderr)
//    }
//
//}