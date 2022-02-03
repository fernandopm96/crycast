package com.example.crycast.repository

import com.example.crycast.dao.GroupsUsersDao
import com.example.crycast.model.GroupWithUsers
import com.example.crycast.model.GroupsUsers

class GroupUsersRepository(val groupsUsersDao: GroupsUsersDao) {

    fun anyGroup(): Boolean{
        var groups = groupsUsersDao.anyGroup()
        if(groups.isEmpty()){
            return false
        }
        return true
    }
}