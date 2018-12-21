enum class Animations {
    COLOR1,
    COLOR2,
    COLOR3,
    COLOR4,
    ALTERNATE,
    MULTIPIXELRUN,
    MULTIPIXELRUNTOCOLOR,
    PIXELRUN,
    PIXELMARATHON,
    SMOOTHCHASE,
    SPARKLE,
    SPARKLECC,
    SPARKLETOCOLOR,
    STACK,
    STACKOVERFLOW,
    WIPE
}


operator fun Map<*, *>.component1() = this["Animation"] as Animations
operator fun Map<*, *>.component2() = this["Color1"] as Long
operator fun Map<*, *>.component3() = this["Color2"] as Long?
operator fun Map<*, *>.component4() = this["Color3"] as Long?
operator fun Map<*, *>.component5() = this["Color4"] as Long?
operator fun Map<*, *>.component6() = this["Color5"] as Long?
operator fun Map<*, *>.component7() = this["Direction"] as Char?
operator fun Map<*, *>.component8() = this["Spacing"] as Int?
operator fun Map<*, *>.component9() = this["Delay"] as Int?