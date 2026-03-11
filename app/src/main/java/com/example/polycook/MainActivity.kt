package com.example.polycook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.polycook.ui.theme.PolyCookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PolyCookTheme {
                // Создаем контроллер
                val navController = rememberNavController()

                // Настраиваем навигацию
                NavHost(navController = navController, startDestination = "welcome") {

                    // ЭКРАН 1: WELCOME
                    composable("welcome") {
                        WelcomeScreen(
                            onLoginClick = {
                                navController.navigate("login")
                            },
                            onSkipClick = {
                                navController.navigate("home")
                            }
                        )
                    }

                    // ЭКРАН 2: LOGIN
                    composable("login") {
                        LoginScreen(
                            onLoginClick = {
                                navController.navigate("home") {
                                    popUpTo("welcome") { inclusive = true }
                                }
                            },
                            onRegisterClick = {
                                // ПЕРЕХОД НА РЕГИСТРАЦИЮ
                                navController.navigate("register")
                            },
                            onForgotPasswordClick = {
                                navController.navigate("new_password")
                            },
                            onBackClick = {
                                // Возвращаемся на экран приветствия
                                navController.navigate("welcome") {
                                    // Очищаем стек, чтобы "welcome" стал единственным экраном
                                    popUpTo("welcome") { inclusive = true }
                                }
                            }
                        )
                    }

                    // ЭКРАН 3: REGISTER
                    composable("register") {
                        RegisterScreen(
                            onRegisterClick = {
                                // КОГДА ЖМЕМ КНОПКУ РЕГИСТРАЦИЯ -> ИДЕМ НА ПОДТВЕРЖДЕНИЕ
                                navController.navigate("verification")
                            },
                            onLoginClick = {
                                navController.popBackStack()
                            },
                            onBackClick = {
                                navController.navigate("login") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("verification") {
                        VerificationScreen(
                            onVerifyClick = {
                                println("Код введен, идем дальше")
                            },
                            onResendClick = {
                                println("Отправляем код заново")
                            },
                            onBackClick = {
                                navController.navigate("register") {
                                    popUpTo("register") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("new_password") {
                        NewPasswordScreen(
                            onChangePasswordClick = {
                                // Логика смены (например, вернуть на логин)
                                println("Пароль изменен")
                                navController.navigate("login") // Возвращаем на вход
                            },
                            onBackClick = {
                                // Возврат назад
                                navController.popBackStack()
                            }
                        )
                    }
                    composable("home") {
                        HomeScreen(
                            onSearchClick = { navController.navigate("search_ingredients") },
                            onRandomClick = { navController.navigate("random_recipe") },
                            onSavedClick = { navController.navigate("saved_recipes")  },
                            onInstructionClick = { navController.navigate("instruction")  },
                            onProfileSettingsClick = {
                                navController.navigate("profile_settings") // Укажите ваш route для настроек профиля
                            },
                            onLogoutClick = {
                                // Логика выхода (очистка данных, если надо)
                                navController.navigate("welcome") {
                                    // Очищаем весь стек навигации, чтобы нельзя было вернуться назад
                                    popUpTo(0)
                                }
                            }
                        )
                    }
                    composable("instruction") {
                        InstructionScreen(
                            onBackClick = {
                                // Возвращаемся назад (на главный экран)
                                navController.popBackStack()
                            }
                        )
                    }
                    composable("saved_recipes") {
                        SavedRecipesScreen(
                            onBackClick = {
                                // Кнопка внизу справа возвращает назад
                                navController.popBackStack()
                            },
                            onRecipeClick = { recipeName ->
                                // Пока просто выводим в консоль, на какой рецепт нажали
                                println("Открываем рецепт: $recipeName")
                                navController.navigate("recipe_details")
                            }
                        )
                    }
                    composable("recipe_details") {
                        RecipeDetailsScreen(
                            onBackClick = {
                                // Возвращаемся на предыдущий экран
                                navController.popBackStack()
                            }
                        )
                    }
                    composable("random_recipe") {
                        RandomRecipeScreen(
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onNextRandomClick = {
                                println("Загружаем следующий случайный рецепт...")
                            }
                        )
                    }
                    composable("search_ingredients") {
                        SearchByIngredientsScreen(
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onSearchClick = {
                                navController.navigate("search_result")
                            }
                        )
                    }
                    composable("search_result") {
                        SearchResultScreen(
                            onBackClick = {
                                navController.popBackStack() // Вернет на ввод ингредиентов
                            },
                            onNextRecipeClick = {
                                println("Ищем другой рецепт с теми же продуктами...")
                                // Здесь будет логика загрузки следующего подходящего рецепта
                            }
                        )
                    }
                    composable("no_recipes_found") {
                        NoRecipeFoundScreen(
                            onTryAgainClick = {
                                // Возвращаемся назад на экран ввода продуктов
                                navController.popBackStack()
                            },
                            onBackToMenuClick = {
                                // Возвращаемся в главное меню (home) и чистим историю переходов
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                            },
                        )
                    }
                    composable("profile_settings") { // route должен совпадать с тем, что вы указали в HomeScreen
                        SettingsScreen(
                            onBackClick = {
                                navController.popBackStack() // Просто возвращаемся назад
                            }
                        )
                    }
                }
            }
        }
    }
}