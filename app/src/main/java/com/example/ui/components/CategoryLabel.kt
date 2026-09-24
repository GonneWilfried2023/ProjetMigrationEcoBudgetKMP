package com.example.ui.components

import androidx.compose.runtime.Composable
import com.example.model.Category
import com.example.shared.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun getCategoryLabel(category: Category): String = stringResource(
    when (category) {
        Category.TRANSPORT -> Res.string.category_transport
        Category.ALIMENTATION -> Res.string.category_alimentation
        Category.LOISIRS -> Res.string.category_loisirs
        Category.LOGEMENT -> Res.string.category_logement
    }
)