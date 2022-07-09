package com.suromo.material.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = dark_primary,                                // 应用程序屏幕和组件中显示频率最高的颜色
    onPrimary = dark_onPrimary,                            // 用于显示在主色上面的文本和图标的颜色
    primaryContainer = dark_primaryContainer,              // 容器的首选色调
    onPrimaryContainer = dark_onPrimaryContainer,          // 主容器上面内容的颜色（和状态变体）
    inversePrimary = dark_inversePrimary,                  // 在需要反向配色方案的地方，例如顶部栏上面的按钮，用作“主”色的颜色
    secondary = dark_secondary,                            // 次要颜色提供了更多强调和区分产品的方法。浮动动作按钮：选择控件，如复选框和单选按钮，突出显示选定文本，链接和标题
    onSecondary = dark_onSecondary,                        // 用于显示在次要颜色上面的文本和图标的颜色
    secondaryContainer = dark_secondaryContainer,          // 次要容器的首选色调（和状态变体）
    onSecondaryContainer = dark_onSecondaryContainer,      // 次要容器上面内容的颜色
    tertiary = dark_tertiary,                              // 三级颜色，可用于平衡原色和二级颜色，或提高对元素（如输入字段）的注意力
    onTertiary = dark_onTertiary,                          // 用于显示在第三种颜色上面的文本和图标的颜色
    tertiaryContainer = dark_tertiaryContainer,            // 三级容器中使用的色调
    onTertiaryContainer = dark_onTertiaryContainer,        // 三级容器上面内容所使用的色调
    error = dark_error,                                    // 错误颜色用于指示组件中的错误，例如文本字段中的无效文本
    onError = dark_onError,                                // 用于显示在错误颜色顶部的文本和图标的颜色
    errorContainer = dark_errorContainer,                  // 错误容器的首选色调
    onErrorContainer = dark_onErrorContainer,              // 应用于错误容器上面内容的颜色（和状态变体）
    background = dark_background,                          // 显示在可滚动内容后面的背景色
    onBackground = dark_onBackground,                      // 用于显示在背景色顶部的文本和图标的颜色
    surface = dark_surface,                                // 影响组件表面（如卡片、图纸和菜单）的表面颜色
    onSurface = dark_onSurface,                            // 用于显示在表面颜色上面的文本和图标的颜色
    surfaceVariant = dark_surfaceVariant,                  // 另一种颜色选项，具有与表面类似的用途
    onSurfaceVariant = dark_onSurfaceVariant,              // 可用于表面顶部内容的颜色（和状态变体）
    inverseSurface = dark_inverseSurface,                  // 与表面形成鲜明对比的颜色。适用于位于具有表面颜色的其他表面顶部的表面
    inverseOnSurface = dark_inverseOnSurface,              // 与表面颜色上面的文本和图标的颜色形成良好对比的颜色。对于位于倒曲面容器顶部的内容非常有用
    surfaceTint = dark_surfaceTint,                        // 此颜色将由应用色调提升的组件使用，并应用于曲面顶部。数值越高，使用的颜色越多
    outline = dark_outline,
)

private val LightColorScheme = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    primaryContainer = light_primaryContainer,
    onPrimaryContainer = light_onPrimaryContainer,
    secondary = light_secondary,
    onSecondary = light_onSecondary,
    secondaryContainer = light_secondaryContainer,
    onSecondaryContainer = light_onSecondaryContainer,
    tertiary = light_tertiary,
    onTertiary = light_onTertiary,
    tertiaryContainer = light_tertiaryContainer,
    onTertiaryContainer = light_onTertiaryContainer,
    error = light_error,
    onError = light_onError,
    errorContainer = light_errorContainer,
    onErrorContainer = light_onErrorContainer,
    background = light_background,
    onBackground = light_onBackground,
    surface = light_surface,
    onSurface = light_onSurface,
    surfaceVariant = light_surfaceVariant,
    onSurfaceVariant = light_onSurfaceVariant,
    outline = light_outline,
    inverseSurface = light_inverseSurface,
    inverseOnSurface = light_inverseOnSurface,
    inversePrimary = light_inversePrimary,
    surfaceTint = light_surfaceTint,
)

@Composable
fun MagicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}