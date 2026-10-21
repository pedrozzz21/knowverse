package com.example.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast

object ShareUtil {
    const val KNOWVERSE_SHARE_URL = "https://ais-pre-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app"
    const val SHARE_MESSAGE = "Vem estudar Python e Cálculo I comigo no Knowverse! Acesse diretamente pelo navegador ou instale o app no seu celular: $KNOWVERSE_SHARE_URL"

    fun shareApp(context: Context) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Estude Python e Cálculo I no Knowverse")
            putExtra(Intent.EXTRA_TEXT, SHARE_MESSAGE)
        }
        val chooser = Intent.createChooser(intent, "Compartilhar Knowverse via:")
        chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(chooser)
    }

    fun copyShareLink(context: Context) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Knowverse App Link", KNOWVERSE_SHARE_URL)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Link do Knowverse copiado para a área de transferência!", Toast.LENGTH_SHORT).show()
    }
}
