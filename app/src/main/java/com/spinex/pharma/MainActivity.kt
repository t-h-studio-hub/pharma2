package com.spinex.pharma
import android.content.res.AssetManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Load data
        val districts = loadDistrictsFromAssets()
        val pharmacies = loadPharmaciesFromAssets()

        // Manually assign pharmacies to districts
        val districtPharmacyPairs = assignPharmaciesToDistricts(districts, pharmacies)

        // Set up RecyclerView with the selected district-pharmacy pairs
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DistrictPharmacyAdapter(districtPharmacyPairs)
        recyclerView.adapter = adapter

    }

    private fun loadPharmaciesFromAssets(): List<Pharmacy> {
        val assetManager: AssetManager = assets
        val inputStream = assetManager.open("pharmacies.json")
        val reader = InputStreamReader(inputStream)
        val pharmacyType = object : TypeToken<List<Pharmacy>>() {}.type
        return Gson().fromJson(reader, pharmacyType)
    }

    private fun loadDistrictsFromAssets(): List<District> {
        val assetManager: AssetManager = assets
        val inputStream = assetManager.open("districts.json")
        val reader = InputStreamReader(inputStream)
        val districtType = object : TypeToken<List<District>>() {}.type
        return Gson().fromJson(reader, districtType)
    }

    // Manually assign pharmacies to districts
    private fun assignPharmaciesToDistricts(
        districts: List<District>,
        pharmacies: List<Pharmacy>
    ): List<DistrictPharmacyPair> {
        val pairs = mutableListOf<DistrictPharmacyPair>()

        // Manually map districts to pharmacies
        pairs.add(DistrictPharmacyPair(districts[0], pharmacies[0]))  // District A -> Pharmacy A
        pairs.add(DistrictPharmacyPair(districts[1], pharmacies[2]))  // District B -> Pharmacy C
        pairs.add(DistrictPharmacyPair(districts[2], pharmacies[1]))  // District C -> Pharmacy B
        pairs.add(DistrictPharmacyPair(districts[0], pharmacies[3]))  // District A -> Pharmacy A
        pairs.add(DistrictPharmacyPair(districts[3], pharmacies[4]))  // District B -> Pharmacy C
        pairs.add(DistrictPharmacyPair(districts[4], pharmacies[0]))  // District C -> Pharmacy B

        return pairs
    }
}
