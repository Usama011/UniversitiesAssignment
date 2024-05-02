package com.xische.module_b

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xische.module_b.databinding.ActivityDetailScreenBinding


class DetailScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailScreenBinding

    companion object {
        private const val universityDetails = "key_university_details"
        fun createOrderAgainActivity(context: Context, university: UniversityDetailsDataClass) =
            Intent(context, DetailScreenActivity::class.java).apply {
                putExtra(universityDetails, university)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.extras?.getParcelable<UniversityDetailsDataClass>(universityDetails)?.apply {
            binding.universityName.text = this.name
            binding.universityCountry.text = this.country
            binding.universityCountryCode.text = this.alpha_two_code
            binding.universityState.text = this.stateProvince
            binding.universityWebPage.text = this.webPages.joinToString()
        }
        binding.btnRefresh.setOnClickListener{
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

}