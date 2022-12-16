package br.com.raqfc.movieapp.common.presentation.composables.listing

//@Composable
//fun CircleImageView(
//    srcUrl: String?,
//    modifier: Modifier = Modifier,
//    checked: Boolean = false,
//    @DrawableRes placeholderImageRes: Int = R.drawable.ic_person_placeholder,
//    contentDescription: String = stringResource(id = R.string.content_description_avatar_image)
//) {
//    CircleImageView(
//        modifier = modifier,
//        checked = checked
//    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(srcUrl)
//                .crossfade(true)
//                .build(),
//            placeholder = painterResource(placeholderImageRes),
//            contentDescription = contentDescription,
//            contentScale = ContentScale.Crop,
//        )
//    }
//}

//@Composable
//fun CircleImageView(
//    @DrawableRes imageRes: Int?,
//    modifier: Modifier = Modifier,
//    checked: Boolean = false,
//    @DrawableRes placeholderImageRes: Int = R.drawable.ic_person_placeholder,
//    contentDescription: String = stringResource(id = R.string.content_description_avatar_image)
//) {
//    CircleImageView(
//        modifier = modifier,
//        checked = checked
//    ) {
//        Image(
//            painter = painterResource(id = imageRes ?: placeholderImageRes),
//            contentDescription = contentDescription,
//            contentScale = ContentScale.Crop,
//        )
//    }
//}
//
//@Composable
//private fun CircleImageView(
//    modifier: Modifier = Modifier,
//    checked: Boolean = false,
//    content: @Composable () -> Unit = {}
//) {
//    Box(
//        modifier = Modifier
//            .clip(CircleShape)
//            .size(AppTheme.dimensions.defaultCircleAvatarSize)
//            .then(modifier)
//    ) {
//        content()

//        if (checked) {
//            Box(//todo selection color
//                modifier = Modifier
//                    .background(Color.Black.copy(alpha = 0.4f))
//                    .fillMaxSize()
//            )
//            AnimatedCheck(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .padding(AppTheme.dimensions.padding3)
//            )
//        }
//    }
//}

//@Composable
//private fun AnimatedCheck(
//    modifier: Modifier = Modifier
//) {
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animated_check_lottie))
//    LottieAnimation(
//        modifier = modifier,
//        composition = composition,
//        speed = 3.5f
//    )
//}