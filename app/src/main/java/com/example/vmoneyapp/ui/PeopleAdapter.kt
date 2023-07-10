package com.example.vmoneyapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vmoneyapp.R
import com.example.vmoneyapp.data.models.people.SinglePersonModel
import com.example.vmoneyapp.databinding.ItemPersonBinding

class PeopleAdapter(val peopleList: List<SinglePersonModel>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPersonBinding.bind(view)
        fun updateUI(person: SinglePersonModel) {
            binding.apply {
                txItemFullName.text = person.firstName +" "+person.lastName
                txItemEmail.text = person.email
                txItemJobTitle.text = person.jobtitle
                Glide
                    .with(view)
                    .load(person.avatar)
                    .placeholder(R.drawable.image_not_found_icon)
                    .into(ivItemAvatar)

                layoutItemPerson.setOnClickListener {
                    Navigation.findNavController(view).navigate(R.id.action_navigation_people_to_personDetailFragment,
                    bundleOf(
                        "person_id" to person.id
                    )
                    )

                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        )
    }

    override fun getItemCount() = peopleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.updateUI(peopleList[position])
        
    }

}