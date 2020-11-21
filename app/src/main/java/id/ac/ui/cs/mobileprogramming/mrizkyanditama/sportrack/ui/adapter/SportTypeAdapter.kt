package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.selection.OnContextClickListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.databinding.ItemSporttypeBinding
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import androidx.recyclerview.selection.SelectionTracker


class SportTypeAdapter(clickListener: SportTypeListener) : RecyclerView.Adapter<SportTypeAdapter.SportTypeViewHolder>() {
    private val items: MutableList<SportType> = mutableListOf()
    private var onClickedAt = NO_POSITION
    private val clickListener = clickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SportTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemSporttypeBinding>(inflater, R.layout.item_sporttype, parent, false)
        return SportTypeViewHolder(binding).apply {
            binding.root.setOnClickListener{
                val position = adapterPosition
                if(position == onClickedAt){
                    onClickedAt = NO_POSITION
                }
                else{
                    onClickedAt = position
                }
                notifyItemChanged(position)
            }
        }
    }

    fun addSportTypeList(sportTypeList: List<SportType>){
        items.addAll(sportTypeList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SportTypeViewHolder, position: Int) {
        holder.binding.apply {
            sporttype = items[position]
            listener = clickListener
            executePendingBindings()
        }
        if (onClickedAt == position){
            holder.binding.root.setBackgroundColor(Color.GREEN)
        }
        else{
            holder.binding.root.setBackgroundColor(Color.TRANSPARENT)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class SportTypeViewHolder(val binding: ItemSporttypeBinding) :
            RecyclerView.ViewHolder(binding.root)

    class SportTypeListener(var clickListener: (sportType: SportType) -> Unit) {
        fun onClick(sportType: SportType) = clickListener(sportType)
    }
}