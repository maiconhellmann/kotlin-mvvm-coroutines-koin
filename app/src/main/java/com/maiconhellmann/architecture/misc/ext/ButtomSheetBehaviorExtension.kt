package com.maiconhellmann.architecture.misc.ext

import android.support.design.widget.BottomSheetBehavior

fun BottomSheetBehavior<*>.isExpanded(): Boolean{
    return state == android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED
}
fun BottomSheetBehavior<*>.isHidden(): Boolean{
    return isExpanded().not()
}
fun BottomSheetBehavior<*>.expand(){
    state = android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED
}
fun BottomSheetBehavior<*>.hide(){
    state = android.support.design.widget.BottomSheetBehavior.STATE_HIDDEN
}
fun BottomSheetBehavior<*>.toggle(){
    if(isHidden()){
        expand()
    }else if(isExpanded()){
        hide()
    }
}
