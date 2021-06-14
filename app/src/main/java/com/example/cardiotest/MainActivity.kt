package com.example.cardiotest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import io.card.payment.CardIOActivity
import io.card.payment.CreditCard

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onScanPress(view: View) {
        val scanIntent = Intent(this, CardIOActivity::class.java)

        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false)
        scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true)
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, false)

        startActivityForResult(scanIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1000){
            val resultDisplayStr: String = if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)){
                val scanResult: CreditCard = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT)!!
                "Card Number: ${scanResult.redactedCardNumber}"
            } else{
                "Scan cancelled."
            }
            findViewById<TextView>(R.id.textView).text = resultDisplayStr
        }
    }
}