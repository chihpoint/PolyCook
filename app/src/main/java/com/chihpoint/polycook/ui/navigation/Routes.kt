package com.chihpoint.polycook.ui.navigation

sealed class Routes(val route: String) {
    object Welcome : Routes("welcome")
    object Login : Routes("login")
    object Register : Routes("register")
    object Verification : Routes("verification")
    object NewPassword : Routes("new_password")
    object Home : Routes("home")
    object Instruction : Routes("instruction")
    object SavedRecipes : Routes("saved_recipes")
    object RecipeDetails : Routes("recipe_details")
    object RandomRecipe : Routes("random_recipe")
    object SearchIngredients : Routes("search_ingredients")
    object SearchResult : Routes("search_result")
    object NoRecipesFound : Routes("no_recipes_found")
}
