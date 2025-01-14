package com.example.wangyiyun.core.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

/**
 * 扩展Modifier， clickable,
 * 创建一个没有点击反馈（如涟漪效果）的点击事件
 */
@SuppressLint("ModifierFactoryUnreferencedReceiver") //这个注解用于抑制 Lint 检查器对未引用接收者的警告。因为在 Compose 中，Modifier 的扩展函数可能会被视为工厂方法，而实际上它们是扩展函数。这个注解告诉编译器忽略这个特定的检查。
fun Modifier.clickableNoRipple(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed {
    Modifier.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null, // 禁用默认的点击指示（如涟漪效果）
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick,
    )
}