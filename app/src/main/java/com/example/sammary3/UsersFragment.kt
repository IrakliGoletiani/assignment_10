package com.example.sammary3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sammary3.databinding.FragmentUsersBinding


class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding

    private val users = mutableListOf<User>()

    lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)

        setData()
        init()

        getEditedUser()

        return binding.root
    }

    private fun setData() {
        repeat(1) {
            users.add(User("Lionel", "Messi", "lionel.messi@gmail.com"))
            users.add(User("Cristiano", "Ronaldo", "cristiano.ronaldo@gmail.com"))
            users.add(User("Harry", "Kane", "harry.kane@gmail.com"))
        }
    }

    private fun init() {
        adapter = UserAdapter(users, object : UserItemListener {
            override fun updateUser(position: Int) {
                val user = users[position]
                findNavController().navigate(
                    UsersFragmentDirections.actionUsersFragmentToEditFragment(
                        user, position
                    )
                )
            }

            override fun deleteUser(position: Int) {
                users.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        })

        binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            users.add(User("Awesome", "Person", "awesome.person@gmail.com"))
            adapter.notifyItemInserted(users.size - 1)
            binding.usersRecyclerView.scrollToPosition(users.size - 1)
        }
    }

    private fun getEditedUser() {
        val getUser = requireArguments().getParcelable<User>("user")
        val position = requireArguments().getInt("position", -1)

        if (position != -1 && getUser != null) {
            users[position] = getUser
            adapter.notifyItemChanged(position)
        }
    }

}