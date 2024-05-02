package com.xische.assigment.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xische.assigment.R
import com.xische.assigment.databinding.ItemUniversityBinding
import com.xische.assigment.domain.university.entity.UniversityEntity

class UniversityAdapter(private val universityList: MutableList<UniversityEntity>) :
    RecyclerView.Adapter<UniversityAdapter.UniversityItemViewHolder>() {

    interface Listener {
        fun onTap(universityEntity: UniversityEntity)
    }

    private var listener: Listener? = null

    fun setOnTapListener(l: Listener) {
        listener = l
    }

    fun updateList(list: List<UniversityEntity>) {
        universityList.clear()
        universityList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearList() {
        universityList.clear()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UniversityItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UniversityItemViewHolder, position: Int) {
        val universityEntity = universityList[position]
        holder.binding?.universityName?.text = universityEntity.name
        holder.binding?.universityState?.text = universityEntity.stateProvince
        holder.binding?.root?.setOnClickListener {
            listener?.onTap(universityEntity)
        }
    }

    override fun getItemCount() = universityList.size

    class UniversityItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(
            inflater.inflate(
                R.layout.item_university,
                parent,
                false
            )
        ) {
        var binding: ItemUniversityBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }
    }
}