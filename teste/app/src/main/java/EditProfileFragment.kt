// java/com/example/teste/EditProfileFragment.kt
package com.example.teste

import android.app.AlertDialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class EditProfileFragment : Fragment() {

    private lateinit var imageViewAvatar: ImageView
    private lateinit var buttonChangeAvatar: Button
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextGender: EditText
    private lateinit var editTextDob: EditText
    private lateinit var buttonSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        imageViewAvatar = view.findViewById(R.id.imageViewAvatar)
        buttonChangeAvatar = view.findViewById(R.id.buttonChangeAvatar)
        editTextName = view.findViewById(R.id.editTextName)
        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        editTextGender = view.findViewById(R.id.editTextGender)
        editTextDob = view.findViewById(R.id.editTextDob)
        buttonSave = view.findViewById(R.id.buttonSave)

        buttonChangeAvatar.setOnClickListener {
            showAvatarSelectionDialog()
        }

        buttonSave.setOnClickListener {
            saveProfile()
        }

        return view
    }

    private fun showAvatarSelectionDialog() {
        val avatarOptions = arrayOf("Avatar 1", "Avatar 2", "Avatar 3") // Lista de avatares
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Escolha um avatar")
        builder.setItems(avatarOptions) { _, which ->
            val selectedAvatar = when (which) {
                0 -> R.drawable.avatar1
                1 -> R.drawable.avatar2
                2 -> R.drawable.avatar3
                else -> R.drawable.default_avatar // Caso padrão
            }
            imageViewAvatar.setImageResource(selectedAvatar)
        }
        builder.create().show()
    }

    private fun saveProfile() {
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val gender = editTextGender.text.toString().trim()
        val dob = editTextDob.text.toString().trim()

        // Adicione a lógica para salvar as alterações, como enviar os dados para o Firebase

        Toast.makeText(context, "Perfil atualizado com sucesso", Toast.LENGTH_SHORT).show()
    }
}
