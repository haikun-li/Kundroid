package com.haikun.kundroid.ui.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.haikun.kundroid.R
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.ui.commonCompose.LesState
import com.haikun.kundroid.ui.commonCompose.MainButton


@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            val registerState by viewModel.register.collectAsState(initial = Resource.EmptyResource())
            LesState(registerState, scaffoldState) {
                navController.popBackStack()
            }

            //顶部图片
            LoginTop()
            //输入框和按钮
            RegisterCenter { account, password, rePassword ->
                viewModel.register(account, password, rePassword, navController)
            }
        }
    }
}




///输入框和按钮
@Composable
fun RegisterCenter(register: (account: String, password: String, rePassword: String) -> Unit) {
    var account by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var rePassword by rememberSaveable {
        mutableStateOf("")
    }

    ConstraintLayout {
        val (accountTextField, passwordTextField, rePasswordTextField) = createRefs()

        OutlinedTextField(
            modifier = Modifier
                .constrainAs(accountTextField) {
                    top.linkTo(parent.top, margin = 30.dp)
                },
            value = account,
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text(text = stringResource(R.string.account))
            },
            onValueChange = {
                account = it
            })

        OutlinedTextField(
            value = password,
            modifier = Modifier
                .padding(top = 16.dp)
                .constrainAs(passwordTextField) {
                    top.linkTo(accountTextField.bottom, margin = 0.dp)
                },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.password))
            },
            onValueChange = {
                password = it
            })

        OutlinedTextField(
            value = rePassword,
            modifier = Modifier
                .padding(top = 16.dp)
                .constrainAs(rePasswordTextField) {
                    top.linkTo(passwordTextField.bottom, margin = 0.dp)
                },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.confirm_password))
            },
            onValueChange = {
                rePassword = it
            })
    }

    MainButton(onClick = {
        register(account, password, rePassword)
    }, text = stringResource(R.string.register), modifier = Modifier.padding(top = 32.dp))

}

//顶部图片
@Composable
fun LoginTop() {
    Box {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.bg_login),
            contentDescription = null
        )
    }
}
