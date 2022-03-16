package com.example.radio_app_iu

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.radio_app_iu.databinding.ActivityLoginBinding
import android.widget.LinearLayout
import com.airbnb.paris.extensions.style

private lateinit var bindingLogin: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val datenbank = DatenbankKlasse(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)

        bindingLogin.buLogout.setOnClickListener {finish()}


            fun lesen() {
                val leser = datenbank.readableDatabase
                var daten = ""

                val ergebnis = leser.rawQuery(
                    "SELECT Bewertungstext, Nickname, Rating FROM Bewertungen ORDER BY id DESC", null
                )
                if (ergebnis.count == 0)
                    daten += "keine Bewertungen"
                else {
                    while (ergebnis.moveToNext()) {
                        daten = ""
                        val bewertung = ergebnis.getString(0) //"ganz gut"
                        val nickname = ergebnis.getString(1) //"Timo"
                        val rating = ergebnis.getInt(2) // 2
                        daten += "%s:  %s %d/5".format(nickname, bewertung, rating)

                        val tvNeu = TextView(this)
                        bindingLogin.lyBewertungen.addView(tvNeu)

                        val lp = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ) //Parameter der neuen Textview
                        tvNeu.style(R.style.Bewertung)
                        tvNeu.layoutParams = lp
                        tvNeu.text = daten
                    }
                }
                ergebnis.close()
                leser.close()
            }
        //Der AktualisierungsButton soll eine neue Textview erstellen und die Zeilen der Datei einzeln Lesen und Einfügen.
        bindingLogin.buBewertungenAktualisieren.setOnClickListener {
            lesen()
        }
    }
}