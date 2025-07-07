package com.somnionocte.somnio_design.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.somnionocte.compose_extensions.HRoundedCornerShape
import com.somnionocte.compose_extensions.VRoundedCornerShape

enum class PositionInList { First, Last, Middle, SingleItem }

fun definePositionInList(
    index: Int,
    lastIndex: Int,
    inParentGroup: PositionInList = PositionInList.SingleItem
) = when(inParentGroup) {
    PositionInList.First -> when(index) {
        lastIndex -> PositionInList.Last
        else -> PositionInList.Middle
    }
    PositionInList.Last -> when(index) {
        0 -> if(lastIndex == 0) PositionInList.SingleItem else PositionInList.First
        else -> PositionInList.Middle
    }
    PositionInList.Middle -> PositionInList.Middle
    PositionInList.SingleItem -> when(index) {
        0 -> if(lastIndex == 0) PositionInList.SingleItem else PositionInList.First
        lastIndex -> PositionInList.Last
        else -> PositionInList.Middle
    }
}

fun defineVerticalShapeByPositionInList(
    positionInList: PositionInList,
    innerCorner: Dp = 10.dp,
    outsideCorner: Dp = 20.dp
) = when(positionInList) {
    PositionInList.First -> VRoundedCornerShape(outsideCorner, innerCorner)
    PositionInList.Last -> VRoundedCornerShape(innerCorner, outsideCorner)
    PositionInList.Middle -> VRoundedCornerShape(innerCorner, innerCorner)
    PositionInList.SingleItem -> VRoundedCornerShape(outsideCorner, outsideCorner)
}

fun defineHorizontalShapeByPositionInList(
    positionInList: PositionInList,
    innerCorner: Dp = 10.dp,
    outsideCorner: Dp = 20.dp
) = when(positionInList) {
    PositionInList.First -> HRoundedCornerShape(outsideCorner, innerCorner)
    PositionInList.Last -> HRoundedCornerShape(innerCorner, outsideCorner)
    PositionInList.Middle -> HRoundedCornerShape(innerCorner, innerCorner)
    PositionInList.SingleItem -> HRoundedCornerShape(outsideCorner, outsideCorner)
}