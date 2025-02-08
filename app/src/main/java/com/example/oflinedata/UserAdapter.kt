package com.example.oflinedata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oflinedata.databinding.ItemDesignBinding

class UserAdapter(val listner: HandelUserClick, var userList: List<User>) : RecyclerView.Adapter<UserAdapter.viweHolder>() {

    interface HandelUserClick{
        fun onEditClick(user: User)
        fun deleteUser(user: User)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viweHolder {
       return viweHolder(ItemDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viweHolder, position: Int) {

        userList[position].let {user->

            holder.binding.apply {
                usernameTv.text = "Name  :${user.name}"
                userageTv.text = "Age:${user.age}"
                usermobileTv.text = "Mobile :${user.mobile}"
                editbtn.setOnClickListener{


                    listner.onEditClick(user)
                }

                root.setOnLongClickListener{
                    listner.deleteUser(user)
                    true
                }

            }




        }

    }

    override fun getItemCount(): Int = userList.size


    class viweHolder(val binding: ItemDesignBinding) : RecyclerView.ViewHolder(binding.root)
}