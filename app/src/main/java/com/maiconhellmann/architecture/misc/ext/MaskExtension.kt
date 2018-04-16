package com.maiconhellmann.architecture.misc.ext

import android.support.annotation.StringDef
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Source: https://andremrezende.wordpress.com/tag/android-mask-mascara-edittext-java-layout-cpf-cnpj/
 */
@StringDef(PHONE_9_MASK, PHONE_8_MASK, CPF_MASK, ZIP_CODE_PT_BR, MONTH_YEAR, CREDIT_CARD)
@Retention(AnnotationRetention.SOURCE)
annotation class MaskType

const val PHONE_9_MASK = "(##) #####-####"
const val PHONE_8_MASK = "(##) ####-####"
const val CPF_MASK = "###.###.###-##"
const val ZIP_CODE_PT_BR = "#####-###"

const val MONTH_YEAR = "##/##"

const val CREDIT_CARD = "#### #### #### ####"

fun String.unmask(): String {
    return replace("[\\./\\(\\) \\-\\+]".toRegex(), "")
}

@Suppress("UNUSED")
fun EditText.insert(@MaskType mask: String): TextWatcher {
    val textWatcher = MaskTextWatcher(mask)

    addTextChangedListener(textWatcher)

    return textWatcher
}

fun EditText.insertPhoneMask(): TextWatcher {
    val textWatcher = object : MaskTextWatcher() {
        override fun getMask(unmaskedValue: String): String {
            if (unmaskedValue.length < 11) {
                return PHONE_8_MASK
            }

            return PHONE_9_MASK
        }
    }

    addTextChangedListener(textWatcher)

    return textWatcher
}

fun String.formatPhone(): String {
    var _phone = this

    if (length == 8)
        _phone = String.format("%s-%s", substring(0, 4), substring(4, length))
    else if (length == 9)
        _phone = String.format("%s-%s", substring(0, 5), substring(5, length))
    else if (length == 10)
        _phone = String.format("%s %s-%s", substring(0, 2), substring(2, 6), substring(6, length))
    else if (length == 11)
        _phone = String.format("%s %s-%s", substring(0, 2), substring(2, 7), substring(7, length))

    return _phone
}

open class MaskTextWatcher(val mask: String = ""): SimpleTextWatcher() {

    internal var oldValue = ""

    internal var isUpdating: Boolean = false

    open fun getMask(unmaskedValue: String): String {
        return mask
    }

    override fun afterTextChanged(edit: Editable) {
        val unmaskedString = edit.toString().unmask()
        val maskedString = StringBuilder("")
        val mask = getMask(unmaskedString)

        // EditText was GC'ed

        if (isUpdating) {
            oldValue = unmaskedString
            isUpdating = false

            return
        }

        var i = 0

        for (m in mask.toCharArray()) {
            if (m != '#' && i < unmaskedString.length) {
                maskedString.append(m)
                continue
            }

            try {
                maskedString.append(unmaskedString[i])
            } catch (e: Exception) {
                break
            }

            i++
        }

        isUpdating = true

        edit.replace(0, edit.length, maskedString)
    }
}

open class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }

    override fun afterTextChanged(edit: Editable) { }
}