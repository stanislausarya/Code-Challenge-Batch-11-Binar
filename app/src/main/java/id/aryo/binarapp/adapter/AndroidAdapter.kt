package id.aryo.binarapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.aryo.binarapp.model.AndroidData
import id.aryo.binarapp.R
import kotlinx.android.synthetic.main.item_android.view.*

class AndroidAdapter (private val androidList: List<AndroidData>, private val onClick: (android: AndroidData) -> Unit) :
    RecyclerView.Adapter<AndroidAdapter.Holder>() {
    override fun onCreateViewHolder(group: ViewGroup, type: Int): Holder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(group.context)
        val view: View = layoutInflater.inflate(R.layout.item_android, group, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return androidList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val android: AndroidData = androidList[position]
        holder.bind(android)
        holder.itemView.run {
            setOnClickListener { onClick(android) }
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(android: AndroidData) {
            itemView.run {
                tv_androidCallName.text = android.callName
                tv_androidVersion.text = android.version
                tv_androidAPILvl.text = android.APILevel
            }
        }
    }


}