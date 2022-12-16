package br.com.raqfc.movieapp.common.presentation.composables.listing

//@Composable
//fun AvatarListItem(
//    isAssetImage: Boolean,
//    modifier: Modifier = Modifier,
//    srcUrl: String? = null,
//    @DrawableRes imgRes: Int? = null,
//    checkable: Boolean = false,
//    checked: Boolean = false,
//    onClick: () -> Unit = {},
//    content: @Composable RowScope.() -> Unit = {}
//) {
//    BaseListItem(
//        checked = checkable && checked,
//        modifier = modifier,
//        onClick = onClick
//    ) {
//
//        if(isAssetImage)
//            CircleImageView(
//                checked = checkable && checked,
//                imageRes = imgRes
//            )
//        else
//            CircleImageView(
//                checked = checkable && checked,
//                srcUrl = srcUrl
//            )
//        content()
//    }
//}