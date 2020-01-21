package com.maiconhellmann.architecture.misc.ext

import com.google.android.material.bottomsheet.BottomSheetBehavior

fun BottomSheetBehavior<*>.isExpanded(): Boolean{
    return state == BottomSheetBehavior.STATE_EXPANDED
}
fun BottomSheetBehavior<*>.isHidden(): Boolean{
    return isExpanded().not()
}
fun BottomSheetBehavior<*>.expand(){
    state = BottomSheetBehavior.STATE_EXPANDED
}
fun BottomSheetBehavior<*>.hide(){
    state = BottomSheetBehavior.STATE_HIDDEN
}
fun BottomSheetBehavior<*>.toggle(){
    if(isHidden()){
        expand()
    }else if(isExpanded()){
        hide()
    }
}
