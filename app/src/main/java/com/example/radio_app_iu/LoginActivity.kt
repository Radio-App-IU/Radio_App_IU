package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.radio_app_iu.databinding.ActivityLoginBinding
import java.io.InputStreamReader
import java.lang.Exception
import android.widget.LinearLayout

private lateinit var bindingLogin: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)

        val faktorDpToPx = resources.displayMetrics.density

        //Funktion zum Lesen einer Datei "Datei.txt".
        fun inhalt():String {
            var t:String
            try {
                val datei = openFileInput("daten.txt")
                val leser = InputStreamReader(datei)
                t = leser.readText()
                leser.close()
                datei.close()
            }
            catch (ex:Exception) {
                t = "(Keine Daten)"
            }
            return t
        }

        //Der AktualisierungsButton soll eine neue Textview erstellen und die Zeilen der Datei einzeln Lesen und Einfügen.
        bindingLogin.buBewertungenAktualisieren.setOnClickListener {

            val t = inhalt()
            if (t.isNotEmpty()) {
                val zeilen = t.split("\n")

                for (x in 0 .. zeilen.size - 2) {
                    val tvNeu = TextView(this)
                    bindingLogin.lyBewertungen.addView(tvNeu)

                    val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ) //Parameter der neuen Textview
                    val mg = (5 * faktorDpToPx).toInt()
                    lp.setMargins(mg, mg, mg, mg)
                    tvNeu.layoutParams = lp
                    tvNeu.text = "%s".format(zeilen[x])
                }
            }
        }
    }
}