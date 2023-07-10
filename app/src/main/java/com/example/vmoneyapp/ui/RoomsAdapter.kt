package com.example.vmoneyapp.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vmoneyapp.R
import com.example.vmoneyapp.data.models.rooms.SingleRoomModel
import com.example.vmoneyapp.databinding.ItemRoomBinding

class RoomsAdapter(val roomList: List<SingleRoomModel>) : RecyclerView.Adapter<RoomsAdapter.ViewHolder> (){
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRoomBinding.bind(view)
        fun updateUI(room: SingleRoomModel) {
            binding.apply {
                var prefixNumber = ""
                val convertedNumber = room.id?.toInt()

                // Room Number format ...

                when(convertedNumber) {
                    in 1..9 -> {prefixNumber = "# 00"}
                    in 10..99 -> prefixNumber = "# 0"
                    else -> prefixNumber = "# "
                }



                txItemRoomNumber.text = prefixNumber+room.id
                txItemRoomOccupancy.text = room.maxOccupancy.toString()
                if (room.isOccupied == true)  {
                    txItemRoomOccupied.text = "Occupied"
                    itemRoomLayout.setBackgroundColor(Color.parseColor("#F88383"))
                } else txItemRoomOccupied.text = "Available"

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)


        )
    }

    override fun getItemCount() = roomList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.updateUI(roomList[position])
    }
}