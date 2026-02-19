package com.chihpoint.polycook.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chihpoint.polycook.ui.screens.HomeScreen
import com.chihpoint.polycook.ui.screens.LoginScreen
import com.chihpoint.polycook.ui.screens.WelcomeScreen
import com.chihpoint.polycook.ui.screens.RegisterScreen
import com.chihpoint.polycook.ui.screens.InstructionScreen
import com.chihpoint.polycook.ui.screens.VerificationScreen
import com.chihpoint.polycook.ui.screens.NewPasswordScreen
import com.chihpoint.polycook.ui.screens.SavedRecipesScreen
import com.chihpoint.polycook.ui.screens.RecipeDetailsScreen
import com.chihpoint.polycook.ui.screens.RandomRecipeScreen
import com.chihpoint.polycook.ui.screens.SearchByIngredientsScreen
import com.chihpoint.polycook.ui.screens.SearchResultScreen
import com.chihpoint.polycook.ui.screens.NoRecipeFoundScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Welcome.route
    ) {
        composable(Routes.Welcome.route) {
            WelcomeScreen(
                onLoginClick = { navController.navigate(Routes.Login.route) },
                onSkipClick = { /* Logic */ }
            )
        }

        composable(Routes.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Welcome.route) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate(Routes.Register.route) },
                onForgotPasswordClick = { navController.navigate(Routes.NewPassword.route) }
            )
        }

        composable(Routes.Register.route) {
            RegisterScreen(
                onRegisterClick = { navController.navigate(Routes.Verification.route) },
                onLoginClick = { navController.popBackStack() }
            )
        }

        composable(Routes.Verification.route) {
            VerificationScreen(
                onVerifyClick = { /* Logic */ },
                onResendClick = { /* Logic */ }
            )
        }

        composable(Routes.NewPassword.route) {
            NewPasswordScreen(
                onChangePasswordClick = { navController.navigate(Routes.Login.route) }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                onSearchClick = { navController.navigate(Routes.SearchIngredients.route) },
                onRandomClick = { navController.navigate(Routes.RandomRecipe.route) },
                onSavedClick = { navController.navigate(Routes.SavedRecipes.route) },
                onInstructionClick = { navController.navigate(Routes.Instruction.route) }
            )
        }

        composable(Routes.Instruction.route) {
            InstructionScreen(onBackClick = { navController.popBackStack() })
        }

        composable(Routes.SavedRecipes.route) {
            SavedRecipesScreen(
                onBackClick = { navController.popBackStack() },
                onRecipeClick = { navController.navigate(Routes.RecipeDetails.route) }
            )
        }

        composable(Routes.RecipeDetails.route) {
            RecipeDetailsScreen(onBackClick = { navController.popBackStack() })
        }

        composable(Routes.RandomRecipe.route) {
            RandomRecipeScreen(
                onBackClick = { navController.popBackStack() },
                onNextRandomClick = { /* Logic */ }
            )
        }

        composable(Routes.SearchIngredients.route) {
            SearchByIngredientsScreen(
                onBackClick = { navController.popBackStack() },
                onSearchClick = { navController.navigate(Routes.SearchResult.route) }
            )
        }

        composable(Routes.SearchResult.route) {
            SearchResultScreen(
                onBackClick = { navController.popBackStack() },
                onNextRecipeClick = { /* Logic */ }
            )
        }

        composable(Routes.NoRecipesFound.route) {
            NoRecipeFoundScreen(
                onTryAgainClick = { navController.popBackStack() },
                onBackToMenuClick = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
