package com.spinex.pharma

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DistrictPharmacyAdapter(
    private val districtPharmacyPairs: List<DistrictPharmacyPair>
) : RecyclerView.Adapter<DistrictPharmacyAdapter.DistrictPharmacyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictPharmacyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_district, parent, false)
        return DistrictPharmacyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DistrictPharmacyViewHolder, position: Int) {
        val pair = districtPharmacyPairs[position]
        val district = pair.district
        val pharmacy = pair.pharmacy

        // Bind data to the views
        holder.districtName.text = district.name
        holder.pharmacyName.text = pharmacy.name
        holder.pharmacyLocation.text = pharmacy.location
        holder.pharmacyPhone.text = pharmacy.phone

        holder.pharmacyLocation.setOnClickListener {
            val mapIntent = Intent(holder.itemView.context, MapActivity::class.java).apply {
                putExtra(MapActivity.EXTRA_LOCATION, pharmacy.location)
            }
            holder.itemView.context.startActivity(mapIntent)
        }

        holder.pharmacyPhone.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${pharmacy.phone}"))
            holder.itemView.context.startActivity(dialIntent)
        }
    }

    override fun getItemCount(): Int {
        return districtPharmacyPairs.size
    }

    // ViewHolder for the RecyclerView
    class DistrictPharmacyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val districtName: TextView = itemView.findViewById(R.id.districtName)
        val pharmacyName: TextView = itemView.findViewById(R.id.pharmacyName)
        val pharmacyLocation: TextView = itemView.findViewById(R.id.pharmacyLocation)
        val pharmacyPhone: TextView = itemView.findViewById(R.id.pharmacyPhone)
    }
}

