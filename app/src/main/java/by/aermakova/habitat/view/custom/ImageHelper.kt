package by.aermakova.habitat.view.custom

import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

object ImageHelper {
    fun blurBitmapWithRenderscript(rs: RenderScript?, bitmap2: Bitmap?) {
        //this will blur the bitmapOriginal with a radius of 25 and save it in bitmapOriginal
        val input = Allocation.createFromBitmap(rs, bitmap2) //use this constructor for best performance, because it uses USAGE_SHARED mode which reuses memory
        val output = Allocation.createTyped(rs, input.type)
        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        // must be >0 and <= 25
        script.setRadius(20f)
        script.setInput(input)
        script.forEach(output)
        output.copyTo(bitmap2)
    }
}