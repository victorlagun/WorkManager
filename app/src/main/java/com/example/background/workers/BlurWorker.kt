package com.example.background.workers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.R

private const val TAG = "BlurWorker"

class BlurWorker(ctx: Context, params: WorkerParameters): Worker(ctx, params) {
    override fun doWork(): Result {
        val appContext: Context = applicationContext
        makeStatusNotification("Blur started!", appContext)
        return try {
            val picture: Bitmap = BitmapFactory.decodeResource(
                appContext.resources,
                R.drawable.android_cupcake
            )
            val output: Bitmap = blurBitmap(picture, appContext)

            val uri = writeBitmapToFile(appContext, output)
            Result.success()
        } catch (t: Throwable) {
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }

    }
}