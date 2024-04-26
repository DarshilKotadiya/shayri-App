package com.example.postapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


class ViewAds : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null

    lateinit var Bannerads: LinearLayout
    lateinit var RewardAdssss: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ads)

        Bannerads = findViewById(R.id.Bannerads)
        RewardAdssss = findViewById(R.id.RewardAds)

        RewardAdssss.setOnClickListener {

            var intent = Intent(this@ViewAds,RewardAds::class.java)
            startActivity(intent)

        }

        // Create a new ad view.
        val adView = AdView(this)

        adView.setAdSize(AdSize.SMART_BANNER)
        adView.adUnitId = "ca-app-pub-3940256099942544/9214589741"

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        Bannerads.addView(adView)

        loadinterads()

    }

    fun interads(view: View) {

        if (mInterstitialAd != null) {
            startActivity(Intent(this@ViewAds, MainActivity::class.java))
            mInterstitialAd!!.show(this@ViewAds)
        }

        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Toast.makeText(this@ViewAds,"onAdClicked",Toast.LENGTH_LONG).show()

                Log.d("to", "Ad was clicked${mInterstitialAd}")
            }

            override fun onAdDismissedFullScreenContent() {
                Toast.makeText(this@ViewAds,"onAdDismissedFullScreenContent",Toast.LENGTH_LONG).show()

                // Called when ad is dismissed.
                Log.d(".......", "Ad dismissed fullscreen content.")
                mInterstitialAd = null

                loadinterads()

            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {

                Toast.makeText(this@ViewAds,"onAdFailedToShowFullScreenContent",Toast.LENGTH_LONG).show()

                // Called when ad fails to show.
                Log.e("=/=/=/=", "Ad failed to show fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                Toast.makeText(this@ViewAds,"onAdImpression",Toast.LENGTH_LONG).show()
                // Called when an impression is recorded for an ad.
                Log.d("===", "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                Toast.makeText(this@ViewAds,"onAdShowedFullScreenContent",Toast.LENGTH_LONG).show()

                // Called when ad is shown.
//                loadinterads()

                Log.d("******", "Ad showed fullscreen content.")
            }
        }
    }

    fun loadinterads() {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("++++", adError?.toString().toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("=-=-=-=  ", "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }

            })
    }
}