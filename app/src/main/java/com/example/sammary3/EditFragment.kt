package com.example.sammary3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sammary3.databinding.FragmentEditBinding
import kotlin.properties.Delegates


class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding

    private lateinit var getUser: User

    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)

        getUser()
        setData()
        updateUser()


        return binding.root
    }

    private fun getUser() {
        getUser = requireArguments().getParcelable("user")!!
        position = requireArguments().getInt("position")
    }

    private fun setData() {
        binding.etFirstName.setText(getUser.firstName)
        binding.etLastName.setText(getUser.lastName)
        binding.etEmail.setText(getUser.email)
    }

    private fun updateUser() {
        binding.btnSave.setOnClickListener {
            val user = User(
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etEmail.text.toString()
            )

            findNavController().navigate(
                EditFragmentDirections.actionEditFragmentToUsersFragment(
                    position,
                    user
                )
            )
        }
    }

}