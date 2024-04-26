package com.example.postapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardAds : AppCompatActivity() {

    private var rewardedAd: RewardedAd? = null

    lateinit var main : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward_ads)

        main = findViewById(R.id.main)

        loadreward()
    }

    fun Reads(view: View){

        rewardedAd?.let { ad ->
            ad.show(this, OnUserEarnedRewardListener { rewardItem ->
                // Handle the reward.
                val rewardAmount = rewardItem.amount
                val rewardType = rewardItem.type
                Log.d("----", "User earned the reward.")
            })
        } ?: run {
            Log.d("=-=--=-", "The rewarded ad wasn't ready yet.")
        }


        rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {

            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Toast.makeText(this@RewardAds,"onAdClicked", Toast.LENGTH_LONG).show()

                Log.d("to", "Ad was clicked${rewardedAd}")
            }

            override fun onAdDismissedFullScreenContent() {
                Toast.makeText(this@RewardAds,"onAdDismissedFullScreenContent", Toast.LENGTH_LONG).show()

                // Called when ad is dismissed.
                Log.d(".......", "Ad dismissed fullscreen content.")
                rewardedAd = null

                loadreward()

            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {

                Toast.makeText(this@RewardAds,"onAdFailedToShowFullScreenContent", Toast.LENGTH_LONG).show()

                // Called when ad fails to show.
                Log.e("=/=/=/=", "Ad failed to show fullscreen content.")
                rewardedAd = null
            }

            override fun onAdImpression() {
                Toast.makeText(this@RewardAds,"onAdImpression", Toast.LENGTH_LONG).show()
                // Called when an impression is recorded for an ad.
                Log.d("===", "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                Toast.makeText(this@RewardAds,"onAdShowedFullScreenContent", Toast.LENGTH_LONG).show()

                Log.d("******", "Ad showed fullscreen content.")
            }
        }
    }

     fun loadreward() {
         var adRequest = AdRequest.Builder().build()
         RewardedAd.load(this,"ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback() {
             override fun onAdFailedToLoad(adError: LoadAdError) {
                 Log.d("==========", adError?.toString().toString())
                 rewardedAd = null
             }

             override fun onAdLoaded(ad: RewardedAd) {
                 Log.d("++++++++", "Ad was loaded.")
                 rewardedAd = ad
             }
         })
    }
}