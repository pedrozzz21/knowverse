package com.example.ui.components

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import java.io.File

@Composable
fun UserAvatarDisplay(
    photoKeyOrUrl: String?,
    sizeDp: Int = 46,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // 1. Tenta recuperar arquivo local em filesDir ou SharedPreferences se photoKeyOrUrl for nulo ou nome relativo
    val effectivePhotoSource: String? = remember(photoKeyOrUrl) {
        if (!photoKeyOrUrl.isNullOrBlank()) {
            photoKeyOrUrl
        } else {
            try {
                val files = context.filesDir.listFiles { f ->
                    (f.name.startsWith("avatar_") || f.name.endsWith(".jpg") || f.name.endsWith(".png")) && f.length() > 0
                }
                val latestFile = files?.maxByOrNull { it.lastModified() }
                if (latestFile != null && latestFile.exists()) {
                    latestFile.absolutePath
                } else {
                    val sp1 = context.getSharedPreferences("knowverse_user_custom_profiles", Context.MODE_PRIVATE)
                    val photo1 = sp1.all.entries.firstOrNull { it.key.endsWith("_photo") && (it.value as? String)?.isNotBlank() == true }?.value as? String
                    if (!photo1.isNullOrBlank()) {
                        photo1
                    } else {
                        val sp2 = context.getSharedPreferences("user_custom_profiles", Context.MODE_PRIVATE)
                        val photo2 = sp2.all.entries.firstOrNull { it.key.endsWith("_photo") && (it.value as? String)?.isNotBlank() == true }?.value as? String
                        if (!photo2.isNullOrBlank()) {
                            photo2
                        } else {
                            val spRepo = context.getSharedPreferences("knowverse_prefs", Context.MODE_PRIVATE)
                            val repoEntry = spRepo.all.entries.firstOrNull { it.key.startsWith("active_user_photo_") && (it.value as? String)?.isNotBlank() == true }
                            repoEntry?.value as? String
                        }
                    }
                }
            } catch (_: Exception) {
                null
            }
        }
    }

    // 2. Decode Base64 image data URL or raw Base64 string for universal cross-device photo sharing
    val base64Bitmap = remember(effectivePhotoSource) {
        if (!effectivePhotoSource.isNullOrBlank() && (
            effectivePhotoSource.startsWith("data:image") ||
            effectivePhotoSource.startsWith("/9j/") ||
            effectivePhotoSource.startsWith("iVBOR") ||
            (effectivePhotoSource.length > 200 && !effectivePhotoSource.startsWith("http") && !effectivePhotoSource.startsWith("/"))
        )) {
            try {
                val cleanBase64 = (if (effectivePhotoSource.contains(",")) {
                    effectivePhotoSource.substringAfter(",")
                } else {
                    effectivePhotoSource
                }).trim().replace("\n", "").replace("\r", "").replace(" ", "")
                val decodedBytes = android.util.Base64.decode(cleanBase64, android.util.Base64.DEFAULT)
                BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)?.asImageBitmap()
            } catch (_: Exception) {
                null
            }
        } else {
            null
        }
    }

    // 3. Verifica se aponta para arquivo local em filesDir pelo nome simples
    val localFile = remember(effectivePhotoSource) {
        if (!effectivePhotoSource.isNullOrBlank()) {
            val direct = File(effectivePhotoSource)
            if (direct.exists() && direct.length() > 0) return@remember direct
            val inFiles = File(context.filesDir, effectivePhotoSource)
            if (inFiles.exists() && inFiles.length() > 0) return@remember inFiles
        }
        null
    }

    when {
        base64Bitmap != null -> {
            Image(
                bitmap = base64Bitmap,
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(sizeDp.dp)
                    .clip(CircleShape)
            )
        }
        localFile != null -> {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(localFile)
                    .crossfade(true)
                    .build(),
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(sizeDp.dp)
                    .clip(CircleShape)
            )
        }
        !effectivePhotoSource.isNullOrBlank() && (
            effectivePhotoSource.startsWith("http:") ||
            effectivePhotoSource.startsWith("https:") ||
            effectivePhotoSource.startsWith("content:") ||
            effectivePhotoSource.startsWith("file:")
        ) -> {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(effectivePhotoSource)
                    .crossfade(true)
                    .build(),
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(sizeDp.dp)
                    .clip(CircleShape)
            )
        }
        effectivePhotoSource == "mascot_otter_girl" || effectivePhotoSource == "avatar_girl" || effectivePhotoSource == "otter_girl" -> {
            Image(
                painter = painterResource(id = R.drawable.img_mascot_otter_girl),
                contentDescription = "Avatar Mascote Menina",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(sizeDp.dp)
                    .clip(CircleShape)
            )
        }
        effectivePhotoSource == "avatar_male" || effectivePhotoSource == "coder_male" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_avatar_coder_male),
                contentDescription = "Avatar Masculino",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(sizeDp.dp)
                    .clip(CircleShape)
            )
        }
        effectivePhotoSource == "avatar_female" || effectivePhotoSource == "coder_female" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_avatar_coder_female),
                contentDescription = "Avatar Feminino",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(sizeDp.dp)
                    .clip(CircleShape)
            )
        }
        else -> {
            // Default avatar: Boy Otter Mascot with glasses, blue hoodie & backpack!
            Image(
                painter = painterResource(id = R.drawable.img_mascot_otter_boy),
                contentDescription = "Avatar Mascote Knowverse",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(sizeDp.dp)
                    .clip(CircleShape)
            )
        }
    }
}
