package br.edu.ifsp.arq.ads.dmos5.brotinho.repository

import android.app.Application
import br.edu.ifsp.arq.ads.dmos5.brotinho.database.AppDatabase
import br.edu.ifsp.arq.ads.dmos5.brotinho.model.User

class UsersRepository (application: Application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()

    fun login(email: String, password: String) = userDao.login(email, password)

    fun loadUserById(userID: String?) = userDao.loadUserById(userID)

    fun insert(user: User) = userDao.insert(user)

    fun update(user: User) = userDao.update(user)

}