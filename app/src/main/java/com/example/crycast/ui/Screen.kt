package com.example.crycast.ui

sealed class Screen(val route: String) {
    object CurrentUser : Screen("current_user")
    object Splash : Screen(route = "splash_screen")
    object Scaffold : Screen(route = "scaffold_screen")
    object ViewConversation : Screen(route = "viewconversation_screen")
    object ViewConversationGroup : Screen(route = "viewconversationgroup_screen")
    object ViewProfile : Screen("viewprofile_screen")
    object GroupProfile : Screen("groupprofile_screen")
    object CreateUser : Screen("create_user")
    object LoginScreen : Screen("login_screen")
    object CreateGroup : Screen("create_group")
    object SelectUsers : Screen("select_users")
    object AddMembersToGroup : Screen("addmembers_group")
    object RemoveDialog: Screen("remove_members_group")
}