//package com.somnionocte.somnio_design.extensions
//
//import androidx.compose.foundation.shape.CornerSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.ui.unit.Dp
//
///**
// * Creates [RoundedCornerShape] with sizes defined in [Dp].
// */
//fun VRoundedCornerShape(
//    top: Dp,
//    bottom: Dp
//) = RoundedCornerShape(
//    topStart = CornerSize(top),
//    topEnd = CornerSize(top),
//    bottomEnd = CornerSize(bottom),
//    bottomStart = CornerSize(bottom)
//)
//
///**
// * Creates [RoundedCornerShape] with sizes defined in [Int].
// */
//fun VRoundedCornerShape(
//    top: Int,
//    bottom: Int
//) = RoundedCornerShape(
//    topStart = CornerSize(top),
//    topEnd = CornerSize(top),
//    bottomEnd = CornerSize(bottom),
//    bottomStart = CornerSize(bottom)
//)
//
///**
// * Creates [RoundedCornerShape] with sizes defined in [Dp].
// */
//fun HRoundedCornerShape(
//    start: Dp,
//    end: Dp
//) = RoundedCornerShape(
//    topStart = CornerSize(start),
//    topEnd = CornerSize(end),
//    bottomEnd = CornerSize(end),
//    bottomStart = CornerSize(start)
//)
//
///**
// * Creates [RoundedCornerShape] with sizes defined in [Int].
// */
//fun HRoundedCornerShape(
//    start: Int,
//    end: Int
//) = RoundedCornerShape(
//    topStart = CornerSize(start),
//    topEnd = CornerSize(end),
//    bottomEnd = CornerSize(end),
//    bottomStart = CornerSize(start)
//)