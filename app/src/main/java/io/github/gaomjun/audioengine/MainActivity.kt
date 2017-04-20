package io.github.gaomjun.audioengine

import android.app.Activity
import android.media.MediaCodec
import android.os.Bundle
import android.os.Handler
import io.github.gaomjun.extensions.postDelayedR

import java.nio.ByteBuffer

import io.github.gaomjun.recorder.AudioEngine

class MainActivity : Activity(), AudioEngine.AudioDataListener {

    private val audioEngine = AudioEngine(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        audioEngine.saveToFile = true
        audioEngine.audioDataListener = this

        Handler().postDelayedR(2000) {
            audioEngine.start()
        }

        Handler().postDelayedR(10000) {
            audioEngine.stop()
        }

    }

    override fun onPCMData(data: ByteArray, size: Int, timestamp: Long) {
        println("onPCMData $size $timestamp")
    }

    override fun onAACData(byteBuffer: ByteBuffer, info: MediaCodec.BufferInfo) {
        println("onAACData " + info.size + " " + info.presentationTimeUs)
    }
}
