package com.example.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin

/**
 * Dynamic Audio Engine for Teste Final Arena:
 * - Pre-allocated static PCM AudioTracks for zero-latency (<1ms) answer feedback.
 * - Captivating, suspenseful synthesized game show arena soundtrack with rhythmic
 *   bassline, clock tension ticking, and accelerating urgency.
 */
class DynamicAudioEngine {

    private val sampleRate = 44100
    private var isPlayingMusic = false
    private var musicJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default)

    @Volatile
    private var currentRemainingSeconds = 30

    // Pre-allocated static AudioTracks for instant playback upon tap
    private var correctTrack: AudioTrack? = null
    private var wrongTrack: AudioTrack? = null

    init {
        initSoundEffects()
    }

    private fun initSoundEffects() {
        try {
            // 1. Pre-render instant Correct Sound: crisp upbeat major chord arpeggio (C5 -> E5 -> G5 -> C6)
            val correctNotes = listOf(523.25, 659.25, 783.99, 1046.50)
            val noteDurationMs = 70
            val samplesPerNote = (sampleRate * (noteDurationMs / 1000.0)).toInt()
            val totalCorrectSamples = samplesPerNote * correctNotes.size
            val correctBuffer = ShortArray(totalCorrectSamples)

            var offset = 0
            for (freq in correctNotes) {
                for (i in 0 until samplesPerNote) {
                    val time = i.toDouble() / sampleRate
                    val envelope = (1.0 - (i.toDouble() / samplesPerNote)).coerceIn(0.0, 1.0)
                    // Blend fundamental and harmonic for a bright, cheerful bell tone
                    val s1 = sin(2.0 * PI * freq * time)
                    val s2 = sin(4.0 * PI * freq * time) * 0.3
                    val sample = ((s1 + s2) * envelope * 0.7 * Short.MAX_VALUE).toInt()
                    correctBuffer[offset + i] = sample.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
                }
                offset += samplesPerNote
            }

            correctTrack = createStaticTrack(correctBuffer)

            // 2. Pre-render instant Wrong Sound: quick descending buzz with low dissonance
            val wrongDurationMs = 280
            val totalWrongSamples = (sampleRate * (wrongDurationMs / 1000.0)).toInt()
            val wrongBuffer = ShortArray(totalWrongSamples)

            for (i in 0 until totalWrongSamples) {
                val time = i.toDouble() / sampleRate
                val tRatio = i.toDouble() / totalWrongSamples
                val freq1 = 180.0 - (tRatio * 70.0)
                val freq2 = 254.0 - (tRatio * 90.0) // dissonant interval
                val envelope = (1.0 - tRatio).coerceIn(0.0, 1.0)
                val s1 = sin(2.0 * PI * freq1 * time)
                val s2 = sin(2.0 * PI * freq2 * time)
                // Add a bit of square wave crunch for game feel
                val crunch = if (sin(2.0 * PI * (freq1 * 0.5) * time) > 0) 0.15 else -0.15
                val sample = ((s1 * 0.5 + s2 * 0.4 + crunch) * envelope * 0.65 * Short.MAX_VALUE).toInt()
                wrongBuffer[i] = sample.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            wrongTrack = createStaticTrack(wrongBuffer)
        } catch (_: Exception) {}
    }

    private fun createStaticTrack(buffer: ShortArray): AudioTrack {
        val track = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .setSampleRate(sampleRate)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .build()
            )
            .setBufferSizeInBytes(buffer.size * 2)
            .setTransferMode(AudioTrack.MODE_STATIC)
            .build()

        track.write(buffer, 0, buffer.size)
        return track
    }

    /**
     * Starts the captivating and suspenseful quiz arena soundtrack.
     * Features a driving electronic bass groove + clock tension ticking
     * that increases in intensity and tempo as time elapses.
     */
    fun startTensionMusic() {
        if (isPlayingMusic) return
        isPlayingMusic = true
        currentRemainingSeconds = 30

        musicJob = scope.launch {
            var step = 0
            while (isActive && isPlayingMusic) {
                val isUrgent = currentRemainingSeconds <= 10
                val isCritical = currentRemainingSeconds <= 5

                val tempoDelay = when {
                    isCritical -> 190L // Rapid panic pace
                    isUrgent -> 280L   // High urgency
                    else -> 420L       // Catchy, confident groove
                }

                playArenaGrooveBeat(step = step, isUrgent = isUrgent, isCritical = isCritical)
                step = (step + 1) % 8
                delay(tempoDelay)
            }
        }
    }

    fun updateRemainingTime(seconds: Int) {
        currentRemainingSeconds = seconds.coerceAtLeast(0)
    }

    fun stopMusic() {
        isPlayingMusic = false
        musicJob?.cancel()
        musicJob = null
    }

    /**
     * Synthesizes a rhythmic, captivating game show beat:
     * - Driving bass notes following a suspenseful minor sequence (D minor)
     * - Sharp clock ticking sound on alternating subdivisions
     * - High tension synth pulse
     */
    private fun playArenaGrooveBeat(step: Int, isUrgent: Boolean, isCritical: Boolean) {
        try {
            val beatDurationMs = if (isCritical) 140 else if (isUrgent) 180 else 240
            val numSamples = (sampleRate * (beatDurationMs / 1000.0)).toInt()
            val buffer = ShortArray(numSamples)

            // Bass note frequencies for D minor tense groove (D2, F2, G2, A2, C#3)
            val bassFreq = when (step) {
                0 -> 73.42  // D2
                1 -> 73.42  // D2
                2 -> 87.31  // F2
                3 -> 73.42  // D2
                4 -> 110.00 // A2
                5 -> 98.00  // G2
                6 -> 87.31  // F2
                else -> if (isUrgent) 138.59 else 69.30 // C#3 tension
            } * (if (isUrgent) 1.25 else 1.0) // Transpose up when urgent for higher tension

            // High tension clock-tick frequency
            val tickFreq = if (step % 2 == 1) 3200.0 else 2400.0

            for (i in 0 until numSamples) {
                val time = i.toDouble() / sampleRate
                val tRatio = i.toDouble() / numSamples

                // Bass synth punch with quick pitch drop
                val instantBassFreq = bassFreq * (1.0 - tRatio * 0.35)
                val bassDecay = (1.0 - tRatio).coerceIn(0.0, 1.0)
                val bassWave = sin(2.0 * PI * instantBassFreq * time) +
                        sin(4.0 * PI * instantBassFreq * time) * 0.35

                // Sharp metallic clock tick
                val tickDecay = (1.0 - (tRatio * 4.0).coerceAtMost(1.0))
                val tickWave = sin(2.0 * PI * tickFreq * time) * 0.45 * tickDecay

                // Subtle high suspense chord drone on even steps
                val drone = if (step % 2 == 0) sin(2.0 * PI * (bassFreq * 4.0) * time) * 0.15 else 0.0

                val combined = (bassWave * 0.5 * bassDecay + tickWave + drone) * Short.MAX_VALUE * 0.6
                buffer[i] = combined.toInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            playEphemeralTrack(buffer)
        } catch (_: Exception) {}
    }

    /**
     * Plays sound effects INSTANTLY upon tap using pre-allocated static tracks.
     * Zero thread allocation, zero calculation latency.
     */
    fun playCorrectSound() {
        try {
            correctTrack?.let { track ->
                if (track.playState == AudioTrack.PLAYSTATE_PLAYING) {
                    track.stop()
                }
                track.reloadStaticData()
                track.play()
            }
        } catch (_: Exception) {}
    }

    fun playWrongSound() {
        try {
            wrongTrack?.let { track ->
                if (track.playState == AudioTrack.PLAYSTATE_PLAYING) {
                    track.stop()
                }
                track.reloadStaticData()
                track.play()
            }
        } catch (_: Exception) {}
    }

    private fun playEphemeralTrack(buffer: ShortArray) {
        val audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .setSampleRate(sampleRate)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .build()
            )
            .setBufferSizeInBytes(buffer.size * 2)
            .setTransferMode(AudioTrack.MODE_STATIC)
            .build()

        audioTrack.write(buffer, 0, buffer.size)
        audioTrack.play()
        audioTrack.setNotificationMarkerPosition(buffer.size)
        audioTrack.setPlaybackPositionUpdateListener(object : AudioTrack.OnPlaybackPositionUpdateListener {
            override fun onMarkerReached(track: AudioTrack?) {
                track?.release()
            }
            override fun onPeriodicNotification(track: AudioTrack?) {}
        })
    }

    fun release() {
        stopMusic()
        try {
            correctTrack?.release()
            correctTrack = null
            wrongTrack?.release()
            wrongTrack = null
        } catch (_: Exception) {}
    }
}
