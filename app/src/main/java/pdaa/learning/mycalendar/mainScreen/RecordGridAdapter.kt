package pdaa.learning.mycalendar.mainScreen

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pdaa.learning.mycalendar.Record
import pdaa.learning.mycalendar.databinding.GridViewItemBinding

class RecordGridAdapter (val onClickListener: OnClickListener): ListAdapter<Record,RecordGridAdapter.RecordViewHolder>(DiffCallback){
    class RecordViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(record: Record) {
            binding.record = record
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(record)
        }
        holder.bind(record)
    }

    class OnClickListener(val clickListener: (record:Record) -> Unit) {
        fun onClick(record:Record) = clickListener(record)
    }
}